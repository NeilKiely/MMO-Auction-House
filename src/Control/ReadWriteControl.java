package Control;

import Inventory.Inventory;
import Inventory.InventoryToBuy;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReadWriteControl {
    /**
     * @author Vilius
     */
    
    private static IFile ifile = null;
    
    public static void setIFile(IFile ifile)
    {
        ReadWriteControl.ifile = ifile;
    }
    
    public static void addRegisteredUser(String username, String password) {
        //try
        //{
            // Add username details to user.txt
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String filename = s + "\\resources\\users.txt";
            /*
            FileWriter fw = new FileWriter(filename, true);
            
            fw.write(username + ";" + password);
            fw.write("\r\n");
            
            fw.close();
            
            // Create a player template and add it to players.txt
            s = currentRelativePath.toAbsolutePath().toString();
            filename = s + "\\resources\\players.txt";
            fw = new FileWriter(filename, true);
            
            fw.write("\r\n");
            fw.write(username + ";Bronze;20;0;0");
            fw.write("\r\n");
            
            fw.close();
            */
            ifile.put(filename);
            
            //writing to users.txt
            String [][] userDetails = new String[1][2];
            userDetails[0][0] = username;
            userDetails[0][1] = password;
            ifile.write(userDetails);
            
            s = currentRelativePath.toAbsolutePath().toString();
            filename = s + "\\resources\\players.txt";
            
            //writing to players.txt
            ifile.put(filename);
            String [][] playerDetails = new String[1][5];
            playerDetails[0][0] = username; playerDetails[0][1] = "Bronze"; playerDetails[0][2] = "20";
            playerDetails[0][3] = "0"; playerDetails[0][4] = "0";
            ifile.write(playerDetails);
        //}
        /*
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        */
    }
    
    /**
     * @author Vilius
     */
    public static ArrayList<String> readUserDetails() {
        // ArrayList holds username;password Strings of all the users
        ArrayList<String> users = new ArrayList<String>();
        
        File usersFile = new File("resources/users.txt");
        
        ifile.get(usersFile.getAbsolutePath());
        String [][] userDetails = ifile.read();
        if (userDetails != null)
        {
            for (int i = 0; i < userDetails.length; i++)
            {
                String detailsString = "";
                for (int j = 0 ; j < userDetails[i].length; j++)
                {
                    detailsString += userDetails[i][j] + ifile.getDelimiter();
                }
                detailsString = detailsString.substring(0, detailsString.length() - 1);
                users.add(detailsString);
            }
        }
        
        return users;
    }
    
    public static String[] readPlayerInventory(String username) {
        File playersFile = new File("resources/players.txt");
        String[] itemArr = new String[0];
        boolean foundPlayer = false;
        
        ifile.get(playersFile.getAbsolutePath());
        String [][] playerInventory = ifile.read();
        //String [] itemArr = null;
        
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
        /*
        try {
            Scanner in = new Scanner(playersFile);
            boolean foundPlayer = false;
            String[] tempArr = new String[0];
            
            while (!foundPlayer && in.hasNext()) {
                tempArr = in.nextLine().split(";");
                
                if (tempArr.length > 0 && tempArr[0].equals(username)) {
                    foundPlayer = true;
                    
                    int numOfItems = Integer.parseInt(tempArr[4]);
                    itemsArr = new String[numOfItems];
                    
                    for (int i = 0; i < numOfItems; i++) {
                        itemsArr[i] = in.nextLine();
                    }
                }
            }
            
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        */
        
        return itemArr;
    }
    
    public static String[] readPlayerInfo(String username) {
        File playersFile = new File("resources/players.txt");
        String[] playerInfoArr = new String[0];
        boolean foundPlayer = false;
        
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
        /*
        try {
            Scanner in = new Scanner(playersFile);
            boolean foundPlayer = false;
            String[] tempArr = new String[0];
            
            while (!foundPlayer && in.hasNext()) {
                tempArr = in.nextLine().split(";");
                
                if (tempArr.length > 0 && tempArr[0].equals(username)) {
                    foundPlayer = true;
                    
                    playerInfoArr = tempArr;
                }
            }
            
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        */
        
        return playerInfoArr;
    }
    
    public static void writSellToFile(String itemInfo, int price, String userName) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String filename = s + "\\ItemsOnSale.txt";
            
        ifile.put(filename);
        
        String writeOut[][] = null;
        writeOut = new String[1][3];
        writeOut[0][0] = itemInfo;
        writeOut[0][1] = "" + price;
        writeOut[0][2] = userName;
        
        ifile.write(writeOut);
    }
    
    public static void writeBuyToFile(InventoryToBuy inventory){
        
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        String filename = s + "\\ItemsOnSale.txt";
            
        ifile.put(filename);
        String stringForItem = "";
        String writeOut[][] = null;
        if (inventory.getNumOfItems() > 0)
        {
            writeOut = new String[inventory.getNumOfItems()][3];
            for (int i = 0; i < writeOut.length; i++)
            {
                writeOut[i][0] = inventory.getItem(i).toStringToFile();
                writeOut[i][1] = "" + inventory.getItem(i).getPrice();
                writeOut[i][2] = inventory.getItem(i).getSeller();
            }
        }
        if (writeOut != null)
            ifile.write(writeOut);
    }
    public static void paySellerInFile(String seller,int price){
        
        File playersFile = new File("resources/playerWallet.txt");
        String filePath = "resources/playerWallet.txt";
        
        String [][] tempListArray = null;
        ifile.get(filePath);
        tempListArray = ifile.read();
        
        for (int i = 0; i < tempListArray.length; i++)
        {
            for (int j = 0; j < tempListArray[i].length; j++)
            {
                if (tempListArray[i][0].matches(seller))
                {
                    String temp = (price + Double.parseDouble(tempListArray[i][1])) + "";
                    tempListArray[i][1] = temp;
                }
            }
        }
        
        String writeString [][] = new String[tempListArray.length][2];
        for (int i = 0; i < writeString.length; i++)
        {
            for (int j = 0; j < writeString[i].length; j++)
            {
                writeString[i][0] = tempListArray[i][0];
                writeString[i][1] = tempListArray[i][1];
            }
        }
        ifile.put("resources/playerWallet.txt");
        ifile.write(writeString);
    }
    
    public static void updateInventory(String playerName, Inventory playerInv) {
        String filePath = "resources/players.txt";
        
        String [][] tempListArray = null;
        ifile.get(filePath);
        ifile.put(filePath);
        
        tempListArray = ifile.read();
        
        int oldInvCount = 0;
        // Update inventory item count on the appropriate player
        for (int i = 0; i < tempListArray.length; i++) {
            if (tempListArray[i][0].equals(playerName)) {
                oldInvCount = Integer.parseInt(tempListArray[i][4]);
                tempListArray[i][4] = Integer.toString(playerInv.getNumOfItems());
            }
        }
        
        int oldItemCounter = 0;
        String [][] allPlayersNewItems = new String[tempListArray.length - oldInvCount + playerInv.getNumOfItems()][tempListArray[0].length];
        for (int i = 0; i < allPlayersNewItems.length; i++) {
            allPlayersNewItems[i] = tempListArray[oldItemCounter];
            if (tempListArray[oldItemCounter][0].equals(playerName)) {
                i++;
                for (int j = 0; j < playerInv.getNumOfItems(); j++) {
                    String[] temp = playerInv.findItemToStringInformation(playerInv.getItemName(j)).split(ifile.getDelimiter());
                    allPlayersNewItems[i + j] = temp;
                }
                oldItemCounter += oldInvCount;
                i += playerInv.getNumOfItems() - 1;
            }
            oldItemCounter++;
        }
        
        ifile.append(false);
        ifile.write(allPlayersNewItems);
    }
    
    public static void addCreditCard()
    {
    
    }
    
    public static void deleteCreditCard()
    {
    
    }
    
    public static void updateCreditCard()
    {
    
    }
}
