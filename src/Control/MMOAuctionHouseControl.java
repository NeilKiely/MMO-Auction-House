package Control;

import Player.PlayerMapper;
import Adventure.GoldAdventure;
import Adventure.SilverAdventure;
import Adventure.Adventure;
import Adventure.IAdventureFactory;
import Adventure.BronzeAdventure;
import Adventure.IAdventure;
import AuctionHouse.BuyControl;
import AuctionHouse.SellControl;
import Inventory.Inventory;
import Player.Player;
import UI.UIWindow;
import java.util.ArrayList;
import CreditCard.CreditCard;
import java.util.Scanner;
import javax.swing.JList;
import javax.swing.JOptionPane;
import mmoauctionhouse.creditcardpackage.CreditCardMapper;

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
            CreditCard [] playersCards = CreditCardMapper.findAllPlayersCards(currentPlayer.getUsername());
            if (playersCards != null)
                for (int i = 0; i < playersCards.length; i++)
                {
                    currentPlayer.addCreditCard(playersCards[i]);
                }
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
    
    public void banCardNo()
    {
        String cardNo = "";
        boolean validCardNo = false;
        if (currentPlayer.getTier().equalsIgnoreCase("Silver"))
        {
            try{
                cardNo = (String)JOptionPane.showInputDialog(null, "Enter Card Number to ban: ");
                if (cardNo != null)
                {   
                    System.out.println("Getsinhere");
                    validCardNo = CreditCard.validateCreditCardInformation(cardNo);
                }
            }
            catch(Exception e){
                //System.out.println(e);
                validCardNo = false;
            } 
            if(validCardNo)
            {
                notifyObserver(cardNo);
                currentPlayer.findAndSetPrimaryCardAfterBan();
            }
            else if (!validCardNo){
                //System.out.println(currentPlayer.getPrimaryCard().toString());
                JOptionPane.showMessageDialog(null, "Invalid Card Number");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Don't have privilage to do this");
        }
        System.out.println(currentPlayer.getPrimaryCard().toString() + "In ban method");
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
    
    public BuyControl retrieveBuy() {
        BuyControl buy = new BuyControl(currentPlayer);
        return buy;
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
                    //currentPlayer.displayCreditCards();
                    break;
                 case "Add Credit Card (Max. 5)":
                     //AddCreditCard(currentPlayer);
                    break;
                 case "Remove Credit Card":
                    // RemoveCreditCard(currentPlayer);
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
    
    
    public void showCreditCards()
    {
        String [] getCreditCards = currentPlayer.getCreditCardString();
        if (getCreditCards != null)
        {
            JOptionPane.showMessageDialog(null, new JList(getCreditCards));
        }
        else
            JOptionPane.showMessageDialog(null, "There are no cards to display.");
    }
    
    public void addCreditCard()
    {
        JOptionPane.showMessageDialog(null, "Adding Credit Card");
    }
    
    public void buyBronzeCoins()
    {
        buyBronzeCoins(currentPlayer);
    }
    
    public void buySilverCoins()
    {
        buySilverCoins(currentPlayer);
    }
    
    public void buyGoldCoins()
    {
        buyGoldCoins(currentPlayer);
    }
    
    
    
    /* @author Chris & Neil
    */
    public boolean checkCreditCard(String firstName,String lastName,String cardNo,String date,String csvNo)
    {
        boolean cardCheck = false;
        boolean csvCheck = false;
        boolean dateCheck = false;
        boolean realCard = true;
        boolean checkNewCard = true;
        String result = "";
        cardCheck = CreditCard.validateCreditCardInformation(cardNo);
        if(!cardCheck)
        {
            realCard = false;
            result += "Credit Card Number is invalid\n";
        }
        dateCheck = CreditCard.vaildateDate(date);
        if(!dateCheck)
        {
            realCard = false;
            result += "Date is invalid\n";
        }
        csvCheck = CreditCard.validateCsvNo(csvNo);
        if(!csvCheck)
        {
            realCard = false;
            result += "CSV is invalid\n";
        }  
        checkNewCard = currentPlayer.checkNewCard(cardNo);
        System.out.println(checkNewCard);
        if (!checkNewCard)
        {
            realCard = false;
            result = "Card No already exists in this account";
        }
        
        
        if(realCard)
        {
            CreditCard card = new CreditCard(firstName, lastName, cardNo, date, csvNo);
            currentPlayer.addCreditCard(card);
            CreditCardMapper.addCreditCard(card, currentPlayer.getUsername());
            this.addObservser(card);
            return true;
        }
        else 
        {
            JOptionPane.showMessageDialog(null, result);
            
        }
        return false;
    }
    
    /* @author Chris & Neil
    */
    public void removeCreditCard()
    {
       String [] creditCardArray = currentPlayer.getCreditCardsStringArray();
       if (creditCardArray != null)
       {
            String cardNo = (String)JOptionPane.showInputDialog(null, "Select a card", "Remove Card", 1, null, creditCardArray, creditCardArray[0]);
            CreditCard card = currentPlayer.getCreditCardByCardNo(cardNo);
            CreditCardMapper.deleteCreditCard(card);
            currentPlayer.removeCreditCard(cardNo);
            currentPlayer.findAndSetPrimaryCardAfterBan();
       }
       else
           JOptionPane.showMessageDialog(null, "No Credit cards no remove");
    }
    
    public void changePrimaryCard()
    {
        String [] creditCardArray = currentPlayer.getCreditCardsStringArray();
        if (creditCardArray != null)
        {
            System.out.println(currentPlayer.getPrimaryCard().toString());
            String cardNo = (String)JOptionPane.showInputDialog(null,"Current Primary Card: " + currentPlayer.getPrimaryCard().toString() + "\nSelect a card" , "Select Primary Card", 1, null, creditCardArray, creditCardArray[0]);
            if(cardNo != null)
            {
                currentPlayer.findAndSetPrimaryCard(cardNo);
                CreditCard card = currentPlayer.getCreditCardByCardNo(cardNo);
                CreditCardMapper.updateCreditCard(card, currentPlayer.getUsername());
            }
        }
        else
           JOptionPane.showMessageDialog(null, "No Credit cards no set as Primary card");
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
 