package mmoauctionhouse;

import java.io.*;
import java.util.*;

public class ReadWriteControl {
    /**
     *
     * @author Vilius
     */
    public static ArrayList<String> readUserDetails() {
        // ArrayList holds username;password Strings of all the users
        ArrayList<String> users = new ArrayList<String>();
        
        File usersFile = new File("resources/users.txt");
        
        try {
            Scanner in = new Scanner(usersFile);
            
            while (in.hasNext()) {
                users.add(in.nextLine());
            }
            
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return users;
    }
    
    public static String[] readPlayerInventory(String username) {
        File playersFile = new File("resources/players.txt");
        String[] itemsArr = new String[0];
        
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
        
        return itemsArr;
    }
    
    public static String[] readPlayerInfo(String username) {
        File playersFile = new File("resources/players.txt");
        String[] playerInfoArr = new String[0];
        
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
        
        return playerInfoArr;
    }
}
