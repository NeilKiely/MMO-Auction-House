/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuctionHouse;
import Control.ReadWriteControl;
import Inventory.InventoryToBuy;
import Player.Player;
import Item.ItemFactory;
import Item.Item;
import Item.ItemToBuy;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jhonata
 */
public class BuyControl {
      private Player currentPlayer;
      private InventoryToBuy listOfItems;
    
    public BuyControl(Player aPlayer) {
       
        currentPlayer = aPlayer;
        listOfItems = new InventoryToBuy();
    
     try
        {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String filename = s + "\\ItemsOnSale.txt";
            Scanner input = new Scanner(new File(filename)); //the true will append the new data
             while(input.hasNextLine())
            {
                String temp = input.nextLine();
                
                ItemFactory aItemFactory = new ItemFactory();
                String[] tempArray = temp.split(";");
                temp = "";
                int i ;
                for(i = 0 ; i < tempArray.length -3;i++){
                    temp += tempArray[i] + ";";
                }
                temp += tempArray[i];
                Item aItem = aItemFactory.createItemFromString(temp);
                ItemToBuy tempItem = new ItemToBuy(aItem, (int)Double.parseDouble(tempArray[tempArray.length -2]), tempArray[tempArray.length -1]);
                listOfItems.addItem(tempItem);
                
                
            }
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
    public void listItems(){
        Scanner input = new Scanner(System.in);
        System.out.println("These are the items avalaible");
        System.out.println(listOfItems.toString(currentPlayer.getTier(),currentPlayer.getUsername()));
        
        System.out.println("Enter a number for a item you want to buy");
        String in = input.nextLine();
        int index = Integer.parseInt(in);
        
        ItemToBuy itemToBuy = listOfItems.getItem((index-1));
        if(currentPlayer.getWallet().hasEnough(itemToBuy.getPrice())){
            currentPlayer.getInventory().addItem(itemToBuy.getItem());
            currentPlayer.getWallet().reduceAmount(itemToBuy.getPrice());
            paySeller(itemToBuy.getSeller(),itemToBuy.getPrice());
            listOfItems.removeItem(index-1);
        }
        System.out.println(listOfItems.getNumOfItems());
        updateFile(listOfItems);
        
        
    }
    private void paySeller(String seller,int price){
        ReadWriteControl.paySellerInFile(seller,price);
    }
    private void updateFile(InventoryToBuy inventory){
        ReadWriteControl.writeBuyToFile(inventory);
    }
}
