
package mmoauctionhouse;

import java.util.*;

public class MenuControl {
    
    /**
     * Asks the user to select one of the available menu options
     * @author Vilius
     */
    public static String selectOption() {
        Scanner in = new Scanner(System.in);
        String[] menuOptions = {"Adventure", "Buy", "Sell", "Buy Coins", "Quit"};
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
        String[] menuOptions = {"Show Credit Cards", "Add Credit Card (Max. 5)", "Remove Credit Card", "Exit"};
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
    /**
     * outputs all available options onto the screen
     * @author Vilius
     */
    private static void displayOptions(String[] menuOptions) {
        for(int i = 0; i < menuOptions.length; i++) {
            System.out.println(i + 1 + ". " + menuOptions[i]);
        }
    }
}
