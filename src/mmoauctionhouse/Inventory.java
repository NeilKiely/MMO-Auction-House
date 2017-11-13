
package mmoauctionhouse;

import itempackage.*;
import java.util.*;

/**
 * @author Vilius
 */
public class Inventory {
    private ArrayList<Item> items;
    
    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }
    
    public Inventory(String[] items) {
        ItemFactoryInterface itemFactory = new ItemFactory();
        this.items = new ArrayList<Item>();
        
        for (int i = 0; i < items.length; i++) {
            addItem(itemFactory.createItemFromString(items[i]));
        }
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public Item getItem(int index) {
        return items.get(index);
    }
    
    public int getNumOfItems() {
        return items.size();
    }
    
    public String findItemInformation(String itemName) {
        String result = getItem(findIndexOfItem(itemName)).getDescription();
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
    
    public String toString(){
        String result = "";
         for (int i = 0; i < items.size(); i++) {
            result += "\n"+ (i+1) +". " + this.getItem(i).getDescription();
        }
        return result ;
    }
    public String[] getAvailableItemTiers() {
        ArrayList<String> availableTiers = new ArrayList<String>();
        
        for (int i = 0; i < getNumOfItems(); i++) {
            String currentItemTier = getItem(i).getTier();
            
            if (!availableTiers.contains(currentItemTier)) {
                availableTiers.add(currentItemTier);
            }
        }
        
        String[] result = new String[availableTiers.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = availableTiers.get(i);
        }
        return result;
    }
    public String[] getItemNames(String tier, String searchFilter) {
        ArrayList<String> foundItems = new ArrayList<String>();
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
    public void removeItem(int index){
        items.remove(index);
    }
}
