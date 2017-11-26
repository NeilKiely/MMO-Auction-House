/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import Item.ItemToBuy;
import java.util.*;

/**
 *
 * @author jhonata
 */
public class InventoryToBuy {
     private ArrayList<ItemToBuy> items;
     
     public InventoryToBuy(){
        this.items = new ArrayList<ItemToBuy>();
    }
    public void addItem(ItemToBuy item) {
        items.add(item);
    }
    
    public String[] getAvailableBuyTiers() {
        ArrayList<String> tiers = new ArrayList<>();
        
        for (int i = 0; i < items.size(); i++) {
            String currentTier = items.get(i).getTier();
            if (!tiers.contains(currentTier)) {
                tiers.add(currentTier);
            }
        }
        
        return tiers.toArray(new String[0]);
    }
    public ItemToBuy getItem(int index) {
        return items.get(index);
    }
    public int getItemPrice(int index) {
        return items.get(index).getPrice();
    }
    public int getItemPrice(String itemName) {
        int itemIndex = findIndexOfItem(itemName);
        return getItemPrice(itemIndex);
    }
    public String[] getItemNames(String tier, String searchFilter) {
        ArrayList<String> foundItems = new ArrayList<>();
        searchFilter = searchFilter.toLowerCase();
        
        for (int i = 0; i < getNumOfItems(); i++) {
            if (getItem(i).getTier().equals(tier) && getItem(i).getName().toLowerCase().contains(searchFilter)) {
                foundItems.add(getItem(i).getName());
            }
        }
        
        String[] result = new String[foundItems.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = foundItems.get(i);
        }
        
        return result;
    }
    public String findItemInformation(String itemName) {
        int index = findIndexOfItem(itemName);
        String result = "";
        if (index != -1) {
            result = getItem(findIndexOfItem(itemName)).getDescription();
        }
        return result;
    }
    public int findIndexOfItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(itemName)) {
                return i;
            }
        }
        
        return -1;
    }
    public int getNumOfItems() {
        return items.size();
    }
    public String toString(String playerTier,String pName){
        String result = "";
         for (int i = 0; i < items.size(); i++) {
             if(playerTier.matches(this.getItem(i).getItem().getTier()) && !(pName.matches(this.getItem(i).getSeller())))
                result += "\n"+ (i+1) +". " + this.getItem(i).getDescription();
        }
        return result ;
    } 
    public void removeItem(int index){
        items.remove(index);
    }
}
