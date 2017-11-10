/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import itempackage.Item;
import itempackage.ItemFactory;
import itempackage.ItemFactoryInterface;
import java.util.ArrayList;

/**
 *
 * @author Neil
 */
public class SilverAdventure implements IAdventure {
    ArrayList<String> tiers = new ArrayList<String>();
    Player player = null;
    
    public SilverAdventure(Player player)
    {
        this.player = player;
    }
    
    @Override
    public Item createItem(String tier) {
        ItemFactoryInterface itemFactory = new ItemFactory();
        return itemFactory.createRandomItem(tier);
    }

    @Override
    public boolean calculateRisk() {
        double risk = 0;
		if (player != null)
		{
			risk = player.getRisk() * 0.8;
		}
		double result = Math.random() * 1;

		if (risk > result)
		{
			//System.out.println("You win");
			Item testItem = createItem("Silver");
                        player.addItem(testItem);
                        //System.out.println(testItem);
                        //System.out.println(player.toString());
                        return true;
		}
		else
		{
			//System.out.println("You lose!");
			//cost to be decided -- for silver failure 
                        int cost = 0;
                        if (player.getWallet().getSilverCoins() - cost < 0)
                        {
                            //sets players amount of silver coins to zero
                            player.getWallet().reduceAmount(0 ,player.getWallet().getSilverCoins());
                        }
                        else
                            player.getWallet().reduceAmount(0, cost);
                        return false;
		}
    }

    @Override
    public void addTierCanPlay(String tier) {
        tiers.add(tier);
    }

    @Override
    public boolean checkCanPlay() {
        for (int i = 0; i < tiers.size(); i++)
        {
            if (tiers.get(i).equals(player.getTier()))
                return true;
        }
        return false;
    }
}
