/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import mmoauctionhouse.creditcardpackage.CreditCard;
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
    private CreditCard primaryCard;
    private CreditCard[] creditCard = new CreditCard[5];

    public CreditCard[] getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard[] creditCard) {
        this.creditCard = creditCard;
    }

    public CreditCard getPrimaryCard() {
        return primaryCard;
    }

    public void setPrimaryCard(CreditCard primaryCard) {
        this.primaryCard = primaryCard;
    }
    
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
    
    public int getBronzeCoins() {
        return wallet.getBronzeCoins();
    }

    public int getSilverCoins() {
        return wallet.getSilverCoins();
    }

    public int getGoldCoins() {
        return wallet.getGoldCoins();
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
    
    public String[] getItemNames(String tier, String searchFilter) {
        return inventory.getItemNames(tier, searchFilter);
    }
    
    public String findItemInformation(String itemName) {
        return inventory.findItemInformation(itemName);
    }
    
    public String[] getAvailableItemTiers() {
        return inventory.getAvailableItemTiers();
    }
    
    public double getItemTax(String itemName) {
        return inventory.getItemTax(itemName);
    }
    
    public abstract double getRisk();
    public Inventory getInventory(){
        return this.inventory ;
    }
    
    public boolean addCreditCard(CreditCard creditCard)
    {
       if (!checkHasCard())
           this.primaryCard = creditCard;
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
                    if (this.creditCard[i].getCardNo().equals(primaryCard.getCardNo()));
                    {
                       // System.out.println("got in here");
                        this.primaryCard = null;
                        if (checkHasCard())
                            
                            for (int j = 0; j < this.creditCard.length; j++)
                            {
                                if (this.creditCard[j] != null && !this.creditCard[j].isCanUse())
                                    this.primaryCard = creditCard[j];
                            }
                    }
                    this.creditCard[i] = null;
                    System.out.println("Credit Card removed!");
                    return true;
                }
            }
        }
        //System.out.println("There were no Credit Cards matching that number");
        return false;
    }
    //for display
    public String [] getCreditCardString()
    {
        int count = 0;
        String [] returnString = new String[this.creditCard.length];
        for (int i = 0; i < this.creditCard.length; i++)
        {
            if (this.creditCard[i] != null)
            {
                returnString[i] = (this.creditCard[i].toString());
                count++;
            }
        }
        if (count != 0)
            return returnString;
        else
            return null;
    }
    
    //for removing
    public String [] getCreditCardsStringArray()
    {
        String [] temp = new String [5];
        boolean foundCard = false;
        int countNonNull = 0;
        for (int i = 0; i < temp.length; i++)
        {
            if (this.creditCard[i] != null)
            {
                if(this.creditCard[i].isCanUse())
                {
                    foundCard = true;
                    temp[i] = this.creditCard[i].getCardNo();
                    countNonNull++;
                }
            }
        }
        String [] returnString = new String[countNonNull];
        countNonNull = 0;
        for (int i = 0; i < temp.length; i++)
        {
            if (this.creditCard[i] != null)
            {
                if(this.creditCard[i].isCanUse())
                {
                    foundCard = true;
                    returnString[countNonNull] = this.creditCard[i].getCardNo();
                    countNonNull++;
                }
            }
        }
        if (foundCard)
            return returnString;
        else 
            return null;
    }
    
    public boolean checkHasCard()
    {
        boolean hasCard = false;
        for (int i = 0; i < 5; i++)
        {
            if (this.creditCard[i] != null)
                hasCard = true;
        }
        return hasCard;
    }
    
    public boolean checkNewCard(String cardNo)
    {
        boolean foundCard = false;
        for (int i = 0; i < creditCard.length; i++)
        {
            if (creditCard[i] != null)
            {
                if(creditCard[i].getCardNo().equals(cardNo))
                {
                    foundCard = true;
                }
            }
        }
        return !foundCard;
    }
    
    public void findAndSetPrimaryCard(String cardNo)
    {
        primaryCard = null;
        for (int i = 0; i < creditCard.length; i++)
        {
            if (creditCard[i] != null)
            {
                if(creditCard[i].getCardNo().equals(cardNo) && creditCard[i].isCanUse())
                {
                    primaryCard = creditCard[i];
                }
            }
        }
    }
    
    public void findAndSetPrimaryCardAfterBan()
    {
        primaryCard = null;
        for (int i = 0; i < creditCard.length; i++)
        {
            if (creditCard[i] != null)
            {
                if(creditCard[i].isCanUse())
                {
                    primaryCard = creditCard[i];
                    break;
                }
            }
        }
    }
    
    public CreditCard getCreditCardByCardNo(String cardNo)
    {
        for (int i = 0; i < this.creditCard.length; i++)
        {
            if(this.creditCard[i] != null)
                if (this.creditCard[i].getCardNo().equals(cardNo))
                    return this.creditCard[i];
        }
        return null;
    }
}
