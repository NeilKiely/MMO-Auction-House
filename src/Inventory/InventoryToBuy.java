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
    
    public ItemToBuy getItem(int index) {
        return items.get(index);
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
