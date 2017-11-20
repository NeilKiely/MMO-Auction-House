
package mmoauctionhouse;

import java.util.*;

public class LoginControl {
    /**
     * Method which keeps asking the user for his login details
     * until a match with the existing login details is found
     * @author Vilius
     */
    public static LoginDetails loginProcess(String username,String password) {
        int userIndex = -1;
        ArrayList<LoginDetails> users = readUsersFromFile();
      
        userIndex = findUser(username, password, users);
        if (userIndex == -1) {
            System.out.println("Invalid login details");
        } else {
            System.out.println("Successfully logged in"); 
            return users.get(userIndex);
        }
        
        return null;
    }
    
    public static boolean registerAUser(String username,String password,String fName,String lName) {
        boolean validName = false;
        boolean validPassword = false;
        String invalidNames = "Chest Head Gloves Shoes Club Dagger Hammer Sword";
        //String username, password;
        
        
       
            validName = false;
            validPassword = false;
            
            if (username.length() >= 4 && !invalidNames.contains(username) && !username.contains(";")) {
                validName = true;
            } else {
                System.out.println("Invalid username");
            }
            
            if (password.length() >= 4 && !password.contains(";")) {
                validPassword = true;
            } else {
                System.out.println("Invalid password");
            }
        
        
        System.out.println("Successfully registered");
        if(validName && validPassword){
            ReadWriteControl.addRegisteredUser(username, password);
            return true;
        }
        else
            return false;
        
    }
    
    /**
     * Checks if the login details match with the existing login details
     * @author Vilius
     */
    private static int findUser(String username, String password, ArrayList<LoginDetails> users) {
        boolean found = false;
        int userIndex = -1;
        
        for (int i = 0; i < users.size() && !found; i++) {
            if (users.get(i).getUsername().equals(username)) {
                found = true;
                if (users.get(i).getPassword().equals(password)) {
                    userIndex = i;
                }
            }
        }
        
        return userIndex;
    }
    
    /**
     * Using ReadWriteControl, reads login details from users.txt
     * and populates users AL
     * @author Vilius
     */
    private static ArrayList<LoginDetails> readUsersFromFile() {
        ArrayList<String> userStrings = ReadWriteControl.readUserDetails();
        String[] temp;
        ArrayList<LoginDetails> users = new ArrayList<LoginDetails>();
        
        for (int i = 0; i < userStrings.size(); i++) {
            temp = userStrings.get(i).split(";");
            users.add(new LoginDetails(temp[0], temp[1]));
        }
        
        return users;
    }
}
