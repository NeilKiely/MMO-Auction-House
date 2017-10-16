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
}
