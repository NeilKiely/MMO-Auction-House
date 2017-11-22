/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;
import itempackage.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author jhonata
 */
public class SellControl {
    
    private Player currentPlayer;
    
    public SellControl(Player aPlayer) {
       
        currentPlayer = aPlayer;
    }
    
    public String[] getItemNames(String tier, String searchFilter) {
        String[] names = currentPlayer.getItemNames(tier, searchFilter);
        MergeSorter sorter = new MergeSorter();
        names = sorter.sort(names);
        return names;
    }
    
    public String[] getAvailableItemTiers() {
        return currentPlayer.getAvailableItemTiers();
    }    
            
    public void listItems(){
        Scanner input = new Scanner(System.in);
        Inventory inventory = currentPlayer.getInventory();
        System.out.println("These are the items avalaible");
        System.out.println(inventory.toString());
        
        System.out.println("Enter a number for a item you want to sell");
        String in = input.nextLine();
        int index = Integer.parseInt(in);
        Item itemToSell = inventory.getItem((index-1));
        double totaltax = itemToSell.getTax();
        System.out.println("The tax for this item is: " + totaltax);
        System.out.println("Enter a price for the item as in Gold:Silver:Bronze ");
        in = input.nextLine();
        String []temp = in.split(":");
        
        double itemPrice  = (Double.parseDouble(temp[0])*10000) + (Double.parseDouble(temp[1])*100) +(Double.parseDouble(temp[2])) ;
        int fee = (int)(itemPrice * (totaltax /100));
        if(currentPlayer.getWallet().hasEnough(fee,0,0)){
            currentPlayer.getWallet().increaseAmount(fee);
            inventory.removeItem((index-1));
            String stringForItem = itemToSell.toStringToFile() + ";" + itemPrice + ";" + currentPlayer.getUsername() ;
            //stringForItem += "\n"; 
            writeToFile(stringForItem);
        }
    }
    
    public String findItemInformation(String itemName) {
        return currentPlayer.findItemInformation(itemName);
    }
    
    public double getItemTax(String itemName) {
        return currentPlayer.getItemTax(itemName);
    }
    
    public int getBronzeCoins() {
        return currentPlayer.getBronzeCoins();
    }

    public int getSilverCoins() {
        return currentPlayer.getSilverCoins();
    }

    public int getGoldCoins() {
        System.out.println("all good");
        return currentPlayer.getGoldCoins();
    }
    
    private void writeToFile(String aItem){
        try
        {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String filename = s + "\\ItemsOnSale.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(aItem);
            fw.write("\r\n");                //appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }


    
}
