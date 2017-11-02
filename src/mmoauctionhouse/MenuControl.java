
package mmoauctionhouse;

import java.util.*;

public class MenuControl {
    
    /**
     * Asks the user to select one of the available menu options
     * @author Vilius
     */
    public static String selectOption() {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"Adventure", "Buy", "Sell", "Payment Details", "Buy Coins", "Quit"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    public static String selectDifficulty()
    {
	Scanner in = new Scanner(System.in);
        String[] menuOptions = {"Bronze", "Silver", "Gold", "Exit Adventure"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    
    public static String selectCreditCardOptions() {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"Show Credit Cards", "Add Credit Card (Max. 5)", "Remove Credit Card", "Change Primary Card", "Exit"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    /* @author Chris & Neil
    */
    public static String selectCoinOptions()
    {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"Buy Bronze Coins", "Buy Silver Coins", "Buy Gold Coins", "Exit"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    /* @author Chris & Neil
    */
    public static String buyBronzeCoins()
    {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"10 Bronze Coins (99c)", "25 Bronze Coins (\u20ac1.98)", "50 Bronze Coins (\u20ac2.97)", "Exit"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    /* @author Chris & Neil
    */
    public static String buySilverCoins()
    {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"10 Silver Coins (\u20ac8.99)", "25 Silver Coins (\u20ac10.99)", "50 Silver Coins (\u20ac12.99)", "Exit"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    /* @author Chris & Neil
    */
    public static String buyGoldCoins()
    {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"10 Gold Coins (\u20ac15.99)", "25 Gold Coins(\u20ac18.99)", "50 Gold Coins(\u20ac20.99)", "Exit"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    private static void displayOptions(String[] menuOptions) {
        for(int i = 0; i < menuOptions.length; i++) {
            System.out.println(i + 1 + ". " + menuOptions[i]);
        }
    }
    
    public static String selectPrimaryCreditCard(String [] cards)
    {
        Scanner in = new Scanner(System.in);
        String selectedOption = "";
        int numInput;
        
        int numCards = 0;
        for (int i = 0; i < cards.length; i++)
        {
            if (cards[i] != null)
            {
                numCards++;
            }
        }
        String cardsNonNull [] = new String [numCards];
        numCards = 0;
        for (int i = 0; i < cards.length; i++)
        {
            if (cards[i] != null)
            {
                cardsNonNull[numCards] = cards[i];
                numCards++;
            }
        }
        
        while (selectedOption.isEmpty()) {
            displayOptions(cardsNonNull);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= cards.length) {
                selectedOption = cards[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
    
    public static String selectAuthenticationOption() {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"Login", "Register"};
        String selectedOption = "";
        int numInput;
        
        while (selectedOption.isEmpty()) {
            displayOptions(menuOptions);
            System.out.println("Please select one of the options by typing in a number:");
            
            String input = in.nextLine();
            numInput = Integer.parseInt(input);
            if (numInput > 0 && numInput <= menuOptions.length) {
                selectedOption = menuOptions[numInput - 1];
            } else {
                System.out.println("Invalid menu option selection");
            }
        }
        
        return selectedOption;
    }
}
