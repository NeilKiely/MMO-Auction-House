/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuctionHouse;
import Control.MergeSorter;
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
    
    public String[] getAvailableItemTiers() {
        return listOfItems.getAvailableBuyTiers();
    }
    
    public String[] getItemNames(String tier, String searchFilter) {
        String[] names = listOfItems.getItemNames(tier, searchFilter);
        
        MergeSorter sorter = new MergeSorter();
        names = sorter.sort(names);
        return names;
    }
    
    public String findItemInformation(String itemName) {
        return listOfItems.findItemInformation(itemName);
    }
    
    public int getBronzeCoins() {
        return currentPlayer.getBronzeCoins();
    }

    public int getSilverCoins() {
        return currentPlayer.getSilverCoins();
    }

    public int getGoldCoins() {
        return currentPlayer.getGoldCoins();
    }
    
    public int getGoldPrice(String itemName) {
        return listOfItems.getItemPrice(itemName) / 10000;
    }
    
    public int getSilverPrice(String itemName) {
        return listOfItems.getItemPrice(itemName) / 100 - getGoldPrice(itemName) * 100;
    }
    
    public int getBronzePrice(String itemName) {
        return listOfItems.getItemPrice(itemName) - getGoldPrice(itemName) * 10000 - getSilverPrice(itemName) * 100;
    }
    
    public int getGoldTax(String itemName) {
        int price = getGoldPrice(itemName);
        double tax = currentPlayer.getTax();
        int taxPrice = (int) ((double) price * tax / 100);
        return taxPrice;
    }
    
    public int getSilverTax(String itemName) {
        int price = getSilverPrice(itemName) + getGoldPrice(itemName) * 100;
        double tax = currentPlayer.getTax();
        int taxPrice = (int) ((double) price * tax / 100);
        taxPrice -= getGoldTax(itemName) * 100;
        return taxPrice;
    }
    
    public int getBronzeTax(String itemName) {
        int price = getBronzePrice(itemName) + getSilverPrice(itemName) * 100 + getGoldPrice(itemName) * 10000;
        double tax = currentPlayer.getTax();
        int taxPrice = (int) ((double) price * tax / 100);
        taxPrice -= (getSilverTax(itemName) * 100 + getGoldTax(itemName) * 10000);
        return taxPrice;
    }
    
    public boolean purchaseItem(String itemName) {
        int priceBeforeTaxes = 0;
        priceBeforeTaxes += getGoldPrice(itemName) * 10000;
        priceBeforeTaxes += getSilverPrice(itemName) * 100;
        priceBeforeTaxes += getBronzePrice(itemName);
        
        int totalPrice = 0;
        totalPrice += priceBeforeTaxes;
        totalPrice += getGoldTax(itemName) * 10000;
        totalPrice += 100 + getSilverTax(itemName) * 100;
        totalPrice += getBronzeTax(itemName);
        
        int totalFunds = 0;
        totalFunds += currentPlayer.getGoldCoins() * 10000;
        totalFunds += currentPlayer.getSilverCoins() * 100;
        totalFunds += currentPlayer.getBronzeCoins();
        
        if (totalFunds < totalPrice) {
            return false;
        } else {
            // Reduce the money in the buyer's walle
            currentPlayer.deductBronzeCoins(totalPrice);
            // Add the item to the buyer's inventory
            currentPlayer.addItem(listOfItems.getItem(listOfItems.findIndexOfItem(itemName)).getItem());
            ReadWriteControl.updateInventory(currentPlayer.getUsername(), currentPlayer.getInventory());
            // Pay the seller
            paySeller(listOfItems.getItem(listOfItems.findIndexOfItem(itemName)).getSeller(), priceBeforeTaxes);
            // Remove item from the items for sale list
            listOfItems.removeItem(listOfItems.findIndexOfItem(itemName));
            updateFile(listOfItems);
            
            return true;
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
