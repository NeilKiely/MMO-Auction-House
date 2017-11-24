/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AuctionHouse;
import Control.MergeSorter;
import Control.ReadWriteControl;
import Inventory.Inventory;
import Player.Player;
import Item.Item;
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
        System.out.println("name[0] = " + names[0]);
        MergeSorter sorter = new MergeSorter();
        names = sorter.sort(names);
        return names;
    }
    
    public String[] getAvailableItemTiers() {
        return currentPlayer.getAvailableItemTiers();
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
        return currentPlayer.getGoldCoins();
    }
    
    public void writeToItemsOnSale(String itemName, int price) {
        String itemInfo = currentPlayer.findItemToStringInformation(itemName);
        ReadWriteControl.writSellToFile(itemInfo, price, currentPlayer.getUsername());
        currentPlayer.removeItem(itemName);
        ReadWriteControl.updateInventory(currentPlayer.getUsername(), currentPlayer.getInventory());
    }
}
