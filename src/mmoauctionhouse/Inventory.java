
package mmoauctionhouse;

import itempackage.*;
import java.util.*;

public class Inventory {
    private ArrayList<Item> items;
    
    public Inventory(ArrayList<Item> items) {
        this.items = items;
    }
    
    /**
     * Creates items from a string array and adds them to the items ArrayList
     * @author Vilius
     */
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
    public String toString(){
        String result = "";
         for (int i = 0; i < items.size(); i++) {
            result += "\n"+ (i+1) +". " + this.getItem(i).getDescription();
        }
        return result ;
    } 
    public void removeItem(int index){
        items.remove(index);
    }
}
