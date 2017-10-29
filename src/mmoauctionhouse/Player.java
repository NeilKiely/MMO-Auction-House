/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import itempackage.*;

/**
 *
 * @author Vilius
 */
public abstract class Player {
    private double tax;
    private Wallet wallet;
    private String tier;
    private Inventory inventory;
    private String username;
    private CreditCard[] creditCard = new CreditCard[5];
    
    public abstract int calculateTax(int cost);
    public Wallet getWallet() {
        return wallet;
    }
    
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
    
    public void setWallet(int startingMoney) {
        wallet = new Wallet(startingMoney);
    }
    
    public void setTier(String tier) {
        this.tier = tier;
    }
    
    public String getTier()
    {
        return tier;
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setTax(double tax) {
        this.tax = tax;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toString() {
        int numOfItems = inventory.getNumOfItems();
        String result = "Player = " + username;
        result += "\nTier = " + tier;
        result += "\nTax = " + tax;
        result += "\nCoins(Bronze/Silver/Gold) = (" + wallet.getBronzeCoins() + "/" + wallet.getSilverCoins() + "/" + wallet.getGoldCoins() + ")";
        result += "\n" + numOfItems + " items:";
        
        result += inventory.toString();
        
        return result;
    }
    
    public void addItem(Item item)
    {
        inventory.addItem(item);
    }
    
    public abstract double getRisk();
    public Inventory getInventory(){
        return this.inventory ;
    }
    
    public boolean addCreditCard(CreditCard creditCard)
    {
       for(int i = 0; i < this.creditCard.length; i++)
       {
           if (this.creditCard[i] == null) 
           {
               this.creditCard[i] = creditCard;
               System.out.println("Credit Card Added to Player!");
               return true;
           }
       }
       System.out.println("Error: Too many credit cards added for this Account");
       return false;
    }
    
    public boolean removeCreditCard(String cardNo)
    {
        for (int i = 0; i < this.creditCard.length; i++)
        {
            if (this.creditCard[i] != null)
            {
                if(this.creditCard[i].getCardNo().equalsIgnoreCase(cardNo))
                {
                this.creditCard[i] = null;
                System.out.println("Credit Card removed!");
                return true;
                }
            }
        }
        System.out.println("There were no Credit Cards matching that number");
        return false;
    }
    
    public void displayCreditCards()
    {
        int count = 0;
        for (int i = 0; i < this.creditCard.length; i++)
        {
            if (this.creditCard[i] != null)
            {
                System.out.println(this.creditCard[i].toString());
                count++;
            }
        }
        if (count == 0)
            System.out.println("There are no credit cards to display");
    }
}
