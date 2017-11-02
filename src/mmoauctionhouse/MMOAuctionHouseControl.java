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
                    selectBuy(currentPlayer);
                    break;
                case "Sell":
                    selectSell(currentPlayer);
                    break;
                case "Payment Details":
                    paymentDetails(currentPlayer);
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
    
   private void selectBuy(Player currentPlayer) {
        System.out.println("Buy selected");
        
        BuyControl buy = new BuyControl(currentPlayer);
        buy.listItems();
    }
    
    private void selectSell(Player currentPlayer) {
        System.out.println("Sell selected");
        
        SellControl sell = new SellControl(currentPlayer);
        sell.listItems();
        
    }
    
    /* @author Chris & Neil
    */
    private void paymentDetails(Player currentPlayer){
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
                 case "Change Primary Card":
                     changePrimaryPaymentCard(currentPlayer);
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
    
    /* @author Chris & Neil
    */
    private void AddCreditCard(Player currentPlayer)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter your first name: ");
        String firstName = in.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = in.nextLine();
        
        boolean cardCheck = false;
        String cardNo = null;
        boolean csvCheck = false;
        String expDate = null;
        boolean dateCheck = false;
        String csvNo = null;
        
        while(!cardCheck)
        {
            System.out.println("Please enter your card number: ");
            cardNo = in.nextLine();
            cardCheck = CreditCard.validateCreditCardInformation(cardNo);
        }
        while(!dateCheck)
        {
            System.out.println("Please enter your cards date (FORMAT MM/YY) ");
            expDate = in.nextLine();
            dateCheck = CreditCard.vaildateDate(expDate);
        }
        while(!csvCheck)
        {
            System.out.println("Please enter your csv number: ");
            csvNo = in.nextLine();
            csvCheck = CreditCard.validateCsvNo(csvNo);
        }
        CreditCard card = new CreditCard(firstName, lastName, cardNo, expDate, csvNo);
        currentPlayer.addCreditCard(card);
    }
    
    /* @author Chris & Neil
    */
    private void RemoveCreditCard(Player currentPlayer)
    {
       Scanner in = new Scanner(System.in);
       System.out.println("Please enter the number of the card you want to remove: ");
       String cardNo = in.nextLine();
       currentPlayer.removeCreditCard(cardNo);
    }
    
    /* @author Chris & Neil
    */
    private void buyCoins(Player currentPlayer)
    {
        boolean keepLooping = true;
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectCoinOptions();

            switch (selectedMenuOption) {
                case "Buy Bronze Coins":
                    buyBronzeCoins(currentPlayer);
                    break;
                 case "Buy Silver Coins":
                    buySilverCoins(currentPlayer);
                    break;
                case "Buy Gold Coins":
                    buyGoldCoins(currentPlayer);
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
    
    /* @author Chris & Neil
    */
    private void buyBronzeCoins(Player currentPlayer)
    {
        boolean keepLooping = true;
        while (keepLooping) {
            String selectedMenuOption = MenuControl.buyBronzeCoins();

            switch (selectedMenuOption) {
                case "10 Bronze Coins (99c)":
                    currentPlayer.getWallet().increaseAmount(10);
                    break;
                 case "25 Bronze Coins (\u20ac1.98)":
                    currentPlayer.getWallet().increaseAmount(25);
                   // System.out.println(currentPlayer.getWallet().getBronzeCoins());
                    break;
                case "50 Bronze Coins (\u20ac2.97)":
                    currentPlayer.getWallet().increaseAmount(50);
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
    
    /* @author Chris & Neil
    */
    private void buySilverCoins(Player currentPlayer)
    {
        boolean keepLooping = true;
        while (keepLooping) {
            String selectedMenuOption = MenuControl.buySilverCoins();

            switch (selectedMenuOption) {
                case "10 Silver Coins (\u20ac8.99)":
                   currentPlayer.getWallet().increaseAmount(0, 10);
                    break;
                case "25 Silver Coins (\u20ac10.99)":
                   currentPlayer.getWallet().increaseAmount(0, 25);
                    break;
                case "50 Silver Coins (\u20ac12.99)":
                   currentPlayer.getWallet().increaseAmount(0, 50);
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
    
    /* @author Chris & Neil
    */
    private void buyGoldCoins(Player currentPlayer)
    {
        boolean keepLooping = true;
        while (keepLooping) {
            String selectedMenuOption = MenuControl.buyGoldCoins();

            switch (selectedMenuOption) {
                case "10 Gold Coins (\u20ac15.99)":
                   currentPlayer.getWallet().increaseAmount(0,0,10);
                    break;
                 case "25 Gold Coins(\u20ac18.99)":
                   currentPlayer.getWallet().increaseAmount(0,0,25);
                    break;
                case "50 Gold Coins(\u20ac20.99)":
                   currentPlayer.getWallet().increaseAmount(0,0,50);
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
    
    private void changePrimaryPaymentCard(Player currentPlayer)
    {
        boolean keepLooping = false;
        if (currentPlayer.checkHasCard()) keepLooping = true;
        if (keepLooping == false)
            System.out.println("No cards found!");
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectPrimaryCreditCard(currentPlayer.getCreditCardsStringArray());

            for (int i = 0; i < 5; i++)
            {
                if (currentPlayer.getCreditCard()[i] != null)
                {
                    if (selectedMenuOption.equals(currentPlayer.getCreditCard()[i].getCardNo().substring(currentPlayer.getCreditCard()[i].getCardNo().length() - 4)))
                    {
                        currentPlayer.setPrimaryCard(currentPlayer.getCreditCard()[i]);
                        keepLooping = false;
                        System.out.print("Primary card set!");
                    }
                }
            }
        }
    }
}
 