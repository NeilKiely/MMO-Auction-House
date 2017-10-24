
package mmoauctionhouse;

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
        String[] temp;
        this.items = new ArrayList<Item>();
        
        for (int i = 0; i < items.length; i++) {
            temp = items[i].split(";");
            
            if (temp[0].equals("Armor")) {
                Item item = new Item(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
                                    temp[4], temp[5], Double.parseDouble(temp[6]), temp[7]);
                addItem(item);
            } else if (temp[0].equals("Weapon")) {
                Item item = new Item(Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
                                    temp[4], temp[5], temp[6], Double.parseDouble(temp[7]), temp[8]);
                addItem(item);
            }
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
            result += "\n"+ (i+1) +". " + this.getItem(i).toString();
        }
        return result ;
    } 
    public void removeItem(int index){
        items.remove(index);
    }
}
