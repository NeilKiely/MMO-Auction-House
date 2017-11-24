/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Control.IFile;
import Inventory.Inventory;
import Player.Silver;
import Player.Player;
import Player.Bronze;
import Player.Gold;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Chris Mulcahy
 */
public class PlayerMapper {
    
    private static IFile ifile = null;
    
    public static Player getPlayer(String username) {
        File playersFile = new File("resources/players.txt");
        String[] playerInfoArr = new String[0];
        boolean foundPlayer = false;
        Player player = null;
        
        String[] itemsArr = readPlayerInventory(username);
        for (int i = 0; i < itemsArr.length; i++)
        {
                System.out.println(itemsArr[i]);
        }
        Inventory inv = new Inventory(itemsArr);
        
        if (PlayerMapper.ifile != null)
        {
            ifile.get(playersFile.getAbsolutePath());
            String [][] playerDetails = ifile.read();

            for (int i =0;  i< playerDetails.length; i++)
            {
                if (playerDetails[i][0].equals(username))
                {
                    foundPlayer = true;
                    playerInfoArr = new String[playerDetails[i].length];
                    playerInfoArr = playerDetails[i];
                }
            }
            switch (playerInfoArr[1]) {
                case "Bronze":
                    player = (Player) new Bronze(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, username);
                    break;
                case "Silver":
                    player = (Player) new Silver(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, username);
                    break;
                case "Gold":
                    player = (Player) new Gold(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, username);
                    break;
                default:
                    player = (Player) new Bronze("ERROR_USERNAME", 90.0, 0, inv, username);
            }
            return player;
        }
        return null;
    }
    
    public static String[] readPlayerInventory(String username) {
        File playersFile = new File("resources/players.txt");
        String[] itemArr = new String[0];
        boolean foundPlayer = false;
        
        if (PlayerMapper.ifile != null)
        {
            ifile.get(playersFile.getAbsolutePath());
            String [][] playerInventory = ifile.read();

            for (int i = 0; i < playerInventory.length && !foundPlayer; i++)
            {
                if (playerInventory[i][0].equals(username))
                {
                    foundPlayer = true;
                    System.out.println("Found user");
                    int numItems = Integer.parseInt(playerInventory[i][4]);
                    itemArr = new String[numItems];

                    for (int j = 0; j < numItems; j++)
                    {
                        String itemDetails = "";
                        for (int k = 0; k < playerInventory[i+j+1].length; k++)
                        {
                            itemDetails += playerInventory[i+j+1][k] + ifile.getDelimiter(); 
                        }
                        itemDetails = itemDetails.substring(0, itemDetails.length() - 1);
                        itemArr[j] = itemDetails;
                    }
                }
            }
            return itemArr;
        }
        return null;
    }
    
    public static void setIFile(IFile ifile)
    {
        PlayerMapper.ifile = ifile;
    }
    
    public static  Player[] findAll()
    {
        Player [] returnPlayerArray = null;
        
        if (PlayerMapper.ifile != null)
        {
            File playersFile = new File("resources/players.txt");
            ifile.get(playersFile.getAbsolutePath());
            String [][] playerArray = ifile.read();
            File usersFile = new File("resources/users.txt");
            ifile.get(usersFile.getAbsolutePath());
            String [][] usersArray = ifile.read();
            returnPlayerArray = new Player[usersArray.length];
            if (playerArray != null)
            {
                Player player = null;
                String [] playerInfoArr = playerArray[0];
                String [] itemsArr = readPlayerInventory(playerInfoArr[0]);
                Inventory inv = new Inventory(itemsArr);
                switch (playerInfoArr[1]) {
                case "Bronze":
                    player = (Player) new Bronze(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, playerInfoArr[0]);
                    break;
                case "Silver":
                    player = (Player) new Silver(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, playerInfoArr[0]);
                    break;
                case "Gold":
                    player = (Player) new Gold(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, playerInfoArr[0]);
                    break;
                default:
                    player = (Player) new Bronze("ERROR_USERNAME", 90.0, 0, inv, playerInfoArr[0]);
                }
                returnPlayerArray[0] = player;
                int indexPlayer = Integer.parseInt(playerInfoArr[4] + 1);
                int numPlayers = 1;
                while(indexPlayer < playerArray.length)
                {
                    playerInfoArr = playerArray[indexPlayer];
                    itemsArr = readPlayerInventory(playerInfoArr[0]);
                    inv = new Inventory(itemsArr);
                    indexPlayer += Integer.parseInt(playerInfoArr[4] + 1);
                    switch (playerInfoArr[1]) {
                    case "Bronze":
                        player = (Player) new Bronze(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, playerInfoArr[0]);
                        break;
                    case "Silver":
                        player = (Player) new Silver(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, playerInfoArr[0]);
                        break;
                    case "Gold":
                        player = (Player) new Gold(playerInfoArr[1], Double.parseDouble(playerInfoArr[2]), Integer.parseInt(playerInfoArr[3]), inv, playerInfoArr[0]);
                        break;
                    default:
                        player = (Player) new Bronze("ERROR_USERNAME", 90.0, 0, inv, playerInfoArr[0]);
                    }
                    returnPlayerArray[numPlayers] = player;
                    numPlayers++;
                }
                return returnPlayerArray;
            }
        }
        return null;
    }
}
