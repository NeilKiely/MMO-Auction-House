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
        String [][] tempItems = ReadWriteControl.readItemToBuy();
        ItemFactory aItemFactory = new ItemFactory();
        String temp = "";
        for(int j= 0; j < tempItems.length;j++){
            int i;
            for(i = 0 ; i < tempItems[j].length -3;i++){
                temp += tempItems[j][i] + ";";
            }
            temp += tempItems[j][i];
            Item aItem = aItemFactory.createItemFromString(temp);
            ItemToBuy tempItem = new ItemToBuy(aItem, (int)Double.parseDouble(tempItems[j][tempItems[j].length -2]), tempItems[j][tempItems[j].length -1]);
            listOfItems.addItem(tempItem);
            temp= "";
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
        totalPrice += getSilverTax(itemName) * 100;
        totalPrice += getBronzeTax(itemName);
        
        int totalFunds = 0;
        totalFunds += currentPlayer.getGoldCoins() * 10000;
        totalFunds += currentPlayer.getSilverCoins() * 100;
        totalFunds += currentPlayer.getBronzeCoins();
        
        if (totalFunds < totalPrice) {
            return false;
        } else {
            // Reduce the money in the buyer's wallet
            currentPlayer.deductBronzeCoins(totalPrice);
            int currentWalletAmount = currentPlayer.getBronzeCoins() + currentPlayer.getSilverCoins() * 100 + currentPlayer.getGoldCoins() * 10000;
            ReadWriteControl.updateWallet(currentPlayer.getUsername(), currentWalletAmount);
            // Add the item to the buyer's inventory
            currentPlayer.addItem(listOfItems.getItem(listOfItems.findIndexOfItem(itemName)).getItem());
            ReadWriteControl.updateInventory(currentPlayer.getUsername(), currentPlayer.getInventory());
            // Pay the seller
            ReadWriteControl.addToWallet(listOfItems.getItem(listOfItems.findIndexOfItem(itemName)).getSeller(), priceBeforeTaxes);
            // Remove item from the items for sale list
            listOfItems.removeItem(listOfItems.findIndexOfItem(itemName));
            updateFile(listOfItems);
            
            return true;
        }
    }
    
    private void paySeller(String seller,int price){
        ReadWriteControl.paySellerInFile(seller,price);
    }
    
    private void updateFile(InventoryToBuy inventory){
        ReadWriteControl.writeBuyToFile(inventory);
    }
}
