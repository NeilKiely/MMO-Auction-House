/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neil & Chris
 */
package mmoauctionhouse;

import itempackage.*;

public class Adventure
{
	Player player = null;
	
	public Adventure(Player player)
	{
		this.player = player;
	}
        
        private Item createItem(String tier) {
            ItemFactoryInterface itemFactory = new ItemFactory();
            return itemFactory.createRandomItem(tier);
        }
	
	public void calculateBronzeRisk()
        {
                double risk = 0;
		if (player != null)
		{
			risk = player.getRisk() * 1;
		}
		double result = Math.random() * 1;

		if (risk > result)
		{
			System.out.println("You win");
			Item testItem = createItem("Bronze");
                        player.addItem(testItem);
                        //System.out.println(testItem);
                        System.out.println(player.toString());
		}
		else
		{
			System.out.println("You lose!");
			//cost to be decided -- for bronze failure 
                        int cost = 0;
                        if (player.getWallet().getBronzeCoins() - cost < 0)
                        {
                            //sets players amount of bronze coins to zero
                            player.getWallet().reduceAmount(player.getWallet().getBronzeCoins());
                        }
                        else
                            player.getWallet().reduceAmount(cost);
		}
	}
	
	
	public void calculateSilverRisk()
	{
		double risk = 0;
		if (player != null)
		{
			risk = player.getRisk() * 0.8;
		}
		double result = Math.random() * 1;

		if (risk > result)
		{
			System.out.println("You win");
			Item testItem = createItem("Silver");
                        player.addItem(testItem);
                        //System.out.println(testItem);
                        System.out.println(player.toString());
		}
		else
		{
			System.out.println("You lose!");
			//cost to be decided -- for silver failure 
                        int cost = 0;
                        if (player.getWallet().getSilverCoins() - cost < 0)
                        {
                            //sets players amount of silver coins to zero
                            player.getWallet().reduceAmount(0 ,player.getWallet().getSilverCoins());
                        }
                        else
                            player.getWallet().reduceAmount(0, cost);
		}
	}
	
	public void calculateGoldenRisk()
	{
		double risk = 0;
		if (player != null)
		{
			risk = player.getRisk() * 0.6;
		}
		double result = Math.random() * 1;

		if (risk > result)
		{
			System.out.println("You win");
			Item testItem = createItem("Gold");
                        player.addItem(testItem);
                        //System.out.println(testItem);
                        System.out.println(player.toString());
		}
		else
		{
			System.out.println("You lose!");
			//cost to be decided -- for gold failure 
                        int cost = 0;
                        if (player.getWallet().getGoldCoins() - cost < 0)
                        {
                            //sets players amount of gold coins to zero
                            player.getWallet().reduceAmount(0 ,0 ,player.getWallet().getGoldCoins());
                        }
                        else
                            player.getWallet().reduceAmount(0, 0, cost);
		}
	}
	
}
