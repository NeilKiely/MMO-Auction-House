package mmoauctionhouse;

import UIpackage.UIWindow;
import java.util.ArrayList;
import mmoauctionhouse.creditcardpackage.CreditCard;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Vilius
 */
public class MMOAuctionHouseControl implements Subject {
    /**
    * Main control class
    * @author Vilius
    */
    private UIWindow ui;
    private  Player currentPlayer;
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    
    private static MMOAuctionHouseControl control = null;
    
    private MMOAuctionHouseControl() {
        ui = new UIWindow(this);
    }
    
    public static MMOAuctionHouseControl getInstance()
    {
        if (control == null)
        {
            control = new MMOAuctionHouseControl();
        }
        return control;
    }
    
    
    public boolean registerUser(String username,String password,String fName,String lName){
        
        boolean registerd = LoginControl.registerAUser(username,password,fName,lName);
        return registerd;
    
    }
    public boolean loginUser(String userName,String password){
        LoginDetails currentLogIn = null;
        currentLogIn  =  LoginControl.loginProcess(userName,password);
        if(currentLogIn != null){
            currentPlayer = PlayerMapper.getPlayer(currentLogIn.getUsername());
            BronzeAdventure bronze = new BronzeAdventure(currentPlayer);
            bronze.addTierCanPlay("Bronze");
            bronze.addTierCanPlay("Silver");
            bronze.addTierCanPlay("Gold");
            
            SilverAdventure silver = new SilverAdventure(currentPlayer);
            silver.addTierCanPlay("Silver");
            silver.addTierCanPlay("Gold");
            
            GoldAdventure gold = new GoldAdventure(currentPlayer);
            gold.addTierCanPlay("Gold");
            
            IAdventureFactory.addIAdventure("bronze", bronze);
            IAdventureFactory.addIAdventure("silver", silver);
            IAdventureFactory.addIAdventure("gold", gold);
            return true;
        }else
            return false;
    }
    public void getChoiceLR(){
        boolean keepLooping = true;
         LoginDetails currentLogIn = null;
        while (keepLooping) {
            String selectedMenuOption = MenuControl.selectAuthenticationOption();
            
            switch (selectedMenuOption) {
                case "Login":
                    //currentLogIn = LoginControl.loginProcess();
                    keepLooping = false;
                    break;
                case "Register":
                    //LoginControl.registerAUser();
                    break;
                default:
                    System.out.println("ERROR SELECTING AUTHENTICATION OPTION");
                    break;
            }
        }
       currentPlayer = retrieveMatchingPlayer(currentLogIn.getUsername());
    }
    public void getAdventure(String tier){
        IAdventure adventure = IAdventureFactory.getIAdventure(tier);
        if (adventure.checkCanPlay())
        {
            String adventureResult = "Unsuccessful";
            if (adventure.calculateRisk() == true)
                adventureResult = "Successful";
            JOptionPane.showMessageDialog(null, "You have completed your adventure. It was... ");
            JOptionPane.showMessageDialog(null, adventureResult + "!");
        }
        else
            JOptionPane.showMessageDialog(null, "You need to be of tier " + tier + " or higher to do this adventure!");
        
    }
    public void Menu(int x){
        
            String selectedMenuOption = MenuControl.selectOption(x);

            switch (selectedMenuOption) {
                case "Adventure":
                    selectAdventure(currentPlayer);
                    break;
                case "Buy":
                    selectBuy(currentPlayer);
                    break;
                case "Payment Details":
                    paymentDetails(currentPlayer);
                    break;    
                case "Buy Coins":
                    buyCoins(currentPlayer);
                    break;
                case "Quit":
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Uh oh! Something went wrong");
                    break;
            }
        
        
    }
    
    private Player retrieveMatchingPlayer(String username) {
        Inventory inventory = retrieveInventory(username);
        //String[] playerInfo = ReadWriteControl.readPlayerInfo(username);
        Player player = PlayerMapper.getPlayer(username);
        
        
        System.out.println(player.toString());
        
        return player;
    }
    
    private Inventory retrieveInventory(String username) {
        String[] itemsArr = ReadWriteControl.readPlayerInventory(username);
        for (int i = 0; i < itemsArr.length; i++)
        {
                System.out.println(itemsArr[i]);
        }
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
    
    public SellControl retrieveSell() {
        SellControl sell = new SellControl(currentPlayer);
        return sell;
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

    @Override
    public void addObservser(Observer o) {
        observers.add(o);
    }

    @Override
    public void detachObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObserver(String string) {
        for (int i = 0; i < observers.size(); i++)
        {
            observers.get(i).update(string);
        }
    }
}
 