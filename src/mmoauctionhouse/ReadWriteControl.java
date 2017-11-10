package mmoauctionhouse;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReadWriteControl {
    /**
     * @author Vilius
     */
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
            IFile scFile = IFileFactory.getIFile("SCFile");
            scFile.put(filename);
            
            //writing to users.txt
            String [][] userDetails = new String[1][2];
            userDetails[0][0] = username;
            userDetails[0][1] = password;
            scFile.write(userDetails);
            
            s = currentRelativePath.toAbsolutePath().toString();
            filename = s + "\\resources\\players.txt";
            
            //writing to players.txt
            scFile.put(filename);
            String [][] playerDetails = new String[1][5];
            playerDetails[0][0] = username; playerDetails[0][1] = "Bronze"; playerDetails[0][2] = "20";
            playerDetails[0][3] = "0"; playerDetails[0][4] = "0";
            scFile.write(playerDetails);
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
        
        IFile scFile = IFileFactory.getIFile("SCFile");
        scFile.get(usersFile.getAbsolutePath());
        String [][] userDetails = scFile.read();
        for (int i = 0; i < userDetails.length; i++)
        {
            String detailsString = "";
            for (int j = 0 ; j < userDetails[i].length; j++)
            {
                detailsString += userDetails[i][j] + scFile.getDelimiter();
            }
            detailsString = detailsString.substring(0, detailsString.length() - 1);
            users.add(detailsString);
        }
        /*
        try {
            Scanner in = new Scanner(usersFile);
            
            while (in.hasNext()) {
                users.add(in.nextLine());
            }
            
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        */
        
        return users;
    }
    
    public static String[] readPlayerInventory(String username) {
        File playersFile = new File("resources/players.txt");
        String[] itemsArr = new String[0];
        boolean foundPlayer = false;
        
        IFile iFile = IFileFactory.getIFile("SCFile");
        iFile.get(playersFile.getAbsolutePath());
        String [][] playerInventory = iFile.read();
        String [] itemArr = null;
        
        for (int i = 0; i < playerInventory.length && !foundPlayer; i++)
        {
            if (playerInventory[i][0].equals(username))
            {
                foundPlayer = true;
                int numItems = Integer.parseInt(playerInventory[i][4]);
                itemArr = new String[numItems];
                
                for (int j = 0; j < numItems; j++)
                {
                    String itemDetails = null;
                    for (int k = 0; k < playerInventory[i+j+1].length; k++)
                    {
                        itemDetails += playerInventory[j][k] + iFile.getDelimiter(); 
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
        
        return itemsArr;
    }
    
    public static String[] readPlayerInfo(String username) {
        File playersFile = new File("resources/players.txt");
        String[] playerInfoArr = new String[0];
        boolean foundPlayer = false;
        
        IFile iFile = IFileFactory.getIFile("SCFile");
        iFile.get(playersFile.getAbsolutePath());
        String [][] playerInventory = iFile.read();
        
        for (int i =0;  i< playerInventory.length; i++)
        {
            if (playerInventory[i][0].equals(username))
            {
                foundPlayer = true;
                for (int j = 0; j < playerInventory[i].length; j++)
                {
                    if (j < playerInventory[i].length - 1)
                        playerInfoArr[j] = playerInventory[i][j] + iFile.getDelimiter();
                    else
                        playerInfoArr[j] = playerInventory[i][j];
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
    public static void writeBuyToFile(InventoryToBuy inventory){
        try
        {
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            String filename = s + "\\ItemsOnSale.txt";
            FileWriter fw = new FileWriter(filename);
            String stringForItem = "";
            if(inventory.getNumOfItems()> 0)
                for(int i = 0 ; i < inventory.getNumOfItems();i++){
                    stringForItem = inventory.getItem(i).toStringToFile() + ";" + inventory.getItem(i).getPrice() + ";" + inventory.getItem(i).getSeller() ;
                    fw.write(stringForItem);
                    fw.write("\r\n");    
                    stringForItem = "";
                }
            else
            {
                fw.write(stringForItem);
            }
            //fw.write(stringForItem);
            //fw.write("\r\n");                //appends the string to the file
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        
    }
    public static void paySellerInFile(String seller,int price){
        File playersFile = new File("resources/playerWallet.txt");
        ArrayList<ArrayList<String>> tempList = new ArrayList<ArrayList<String>>();
        
        int counter = 0;
         boolean foundFile = false;
        try {
            Scanner in = new Scanner(playersFile);
            String[] tempArr = new String[0];
            while (in.hasNext()) {
               tempArr = in.nextLine().split(";");
                tempList.add(new ArrayList<String>());
               for(int i =0 ; i < tempArr.length; i++){
                    tempList.get(counter).add(tempArr[i]);
                }
               counter++;
                
            }
            foundFile = true;
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if(foundFile){
            for(int i = 0 ; i < tempList.size();i++){
                if(tempList.get(i).get(0).matches(seller)){
                    
                    String temp =(price + Double.parseDouble(tempList.get(i).get(1))) + "";
                    tempList.get(i).set(1,temp);
                }
            }
             try
            {
               // Path currentRelativePath = Paths.get("");
                //String s = currentRelativePath.toAbsolutePath().toString();
                String filename =  "resources/playerWallet.txt";
                FileWriter fw = new FileWriter(filename); 
                String stringForItem = "";
                for(int i = 0 ; i < tempList.size();i++){
                    stringForItem = tempList.get(i).get(0) + ";" + tempList.get(i).get(1) ;
                    fw.write(stringForItem);
                    fw.write("\r\n");    
                    stringForItem = "";
                }

                //fw.write(stringForItem);
                //fw.write("\r\n");                //appends the string to the file
                fw.close();
            }
            catch(IOException ioe)
            {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }
}
