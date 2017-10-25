/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

/**
 *
 * @author Neil & Chris
 */
public class PlayerTierFactory {
    
    public Player getPlayer(String PlayerTier, int tax, int startingMoney, Inventory inventory, String username)
    {
        if(PlayerTier.equalsIgnoreCase("BRONZE"))
            return new Bronze(PlayerTier,tax,startingMoney,inventory,username);
        if(PlayerTier.equalsIgnoreCase("SILVER"))
            return new Silver(PlayerTier,tax,startingMoney,inventory,username);
        if(PlayerTier.equalsIgnoreCase("GOLD"))
            return new Gold(PlayerTier,tax,startingMoney,inventory,username);
        else
            return null;
    }
}
