/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

import java.util.*;

/**
 *
 * @author jhonata
 */
public class ItemToBuy {
    private Item item ;
    private int price;
    private String seller;
    
    public ItemToBuy(Item aItem,int aPrice,String aSeller){
          this.item = aItem;
          this.price = aPrice;
          this.seller = aSeller;
    }
    public String getName() {
        return item.getName();
    }
    public String getDescription(){
        return this.item.getDescription();
    }
    public int getPrice(){
        return this.price;
    }
    public String getSeller(){
        return this.seller;
    }
    public Item getItem(){
        return this.item;
    }
    public String getTier() {
        return item.getTier();
    }
    public String toStringToFile(){
        return this.item.toStringToFile();
    }
}
