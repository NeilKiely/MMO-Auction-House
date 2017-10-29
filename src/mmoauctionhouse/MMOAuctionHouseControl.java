package mmoauctionhouse;

import java.util.Scanner;

/**
 *
 * @author Vilius
 */
public class MMOAuctionHouseControl {
    /**
    * Main control class
    * @author Vilius
    */
    public MMOAuctionHouseControl() {
        boolean keepLooping = true;
        LoginDetails currentLogIn = LoginControl.loginProcess();
        Player currentPlayer = retrieveMatchingPlayer(currentLogIn.getUsername());
        
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectOption();

            switch (selectedMenuOption) {
                case "Adventure":
                    selectAdventure(currentPlayer);
                    break;
                case "Buy":
                    selectBuy();
                    break;
                case "Sell":
                    selectSell(currentPlayer);
                    break;
                case "Buy Coins":
                    buyCoins(currentPlayer);
                    break;    
                case "Quit":
                    System.out.println("Bye bye!");
                    keepLooping = false;
                    break;
                default:
                    System.out.println("Uh oh! Something went wrong");
                    break;
            }
        }
    }
    
    private Player retrieveMatchingPlayer(String username) {
        Inventory inventory = retrieveInventory(username);
        String[] playerInfo = ReadWriteControl.readPlayerInfo(username);
        Player player;
        
        // Depending on the player tier, create appropriate classes
        switch (playerInfo[1]) {
            case "Bronze":
                player = (Player) new Bronze(playerInfo[1], Double.parseDouble(playerInfo[2]), Integer.parseInt(playerInfo[3]), inventory, username);
                break;
            case "Silver":
                player = (Player) new Silver(playerInfo[1], Double.parseDouble(playerInfo[2]), Integer.parseInt(playerInfo[3]), inventory, username);
                break;
            case "Gold":
                player = (Player) new Gold(playerInfo[1], Double.parseDouble(playerInfo[2]), Integer.parseInt(playerInfo[3]), inventory, username);
                break;
            default:
                player = (Player) new Bronze("ERROR_USERNAME", 90.0, 0, inventory, username);
        }
        
        System.out.println(player.toString());
        
        return player;
    }
    
    private Inventory retrieveInventory(String username) {
        String[] itemsArr = ReadWriteControl.readPlayerInventory(username);
        Inventory inv = new Inventory(itemsArr);
        
        return inv;
    }
    
    private void selectAdventure(Player currentPlayer) {
        Adventure adventure = new Adventure(currentPlayer);
        boolean keepLooping = true;
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectDifficulty();


            switch (selectedMenuOption) {
                case "Bronze":
                    adventure.calculateBronzeRisk();
                    break;
                 case "Silver":
                     if (currentPlayer.getTier().equals("Silver") || currentPlayer.getTier().equals("Gold"))   
                        adventure.calculateBronzeRisk();
                     else
                         System.out.println("You are a " + currentPlayer.getTier() + " Tier. You need to be silver or higher to access this adventure.");
                    break;
                case "Gold":
                    if (currentPlayer.getTier().equals("Gold"))
                        adventure.calculateBronzeRisk();
                    else
                        System.out.println("You are a " + currentPlayer.getTier() + " Tier. You need to be gold.");
                    break;
                case "Exit Adventure":
                    System.out.println("Your adventure has ended!");
                    keepLooping = false;
                    break;
                default:
                    System.out.println("Uh oh! Something went wrong");
                    break;
            }
        }
    }
    
    private void selectBuy() {
        System.out.println("Buy selected");
    }
    
    private void selectSell(Player currentPlayer) {
        System.out.println("Sell selected");
        
        SellControl sell = new SellControl(currentPlayer);
        sell.listItems();
        
    }
    
    private void buyCoins(Player currentPlayer){
        //System.out.println("Buying Coins");
        System.out.println("Please select or add a new Credit Card");
		boolean keepLooping = true;
		while (keepLooping) {
            String selectedMenuOption = MenuControl.selectCreditCardOptions();
            
            switch (selectedMenuOption) {
                case "Show Credit Cards":
                    currentPlayer.displayCreditCards();
                    break;
                 case "Add Credit Card (Max. 5)":
                     AddCreditCard(currentPlayer);
                    break;
                 case "Remove Credit Card":
                     RemoveCreditCard(currentPlayer);
                    break;
                 case "Exit":
                     keepLooping = false;
                 break;
                default:
                    System.out.println("Uh oh! Something went wrong");
                    break;
            }
        }
	
    }
    
    private void AddCreditCard(Player currentPlayer)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your first name: ");
        String firstName = in.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = in.nextLine();
        System.out.println("Please enter your card number: ");
        String cardNo = in.nextLine();
        System.out.println("Please enter your cards date (FORMAT MM/YY) ");
        String expDate = in.nextLine();
        System.out.println("Please enter your csv number: ");
        String csvNo = in.nextLine();
        boolean makeCard = CreditCard.validateCreditCardInformation(cardNo, csvNo, expDate);
        if (makeCard)
        {
            CreditCard card = new CreditCard(firstName, lastName, cardNo, expDate, csvNo);
            currentPlayer.addCreditCard(card);
        }
        else
            System.out.println("Incorrect details");
    }
    
    private void RemoveCreditCard(Player currentPlayer)
    {
       Scanner in = new Scanner(System.in);
       System.out.println("Please enter the number of the card you want to remove: ");
       String cardNo = in.nextLine();
       currentPlayer.removeCreditCard(cardNo);
    }
}
 