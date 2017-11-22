/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIpackage;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mmoauctionhouse.MMOAuctionHouseControl;
import mmoauctionhouse.SellControl;

/**
 *
 * @author jhonata
 */

class WindowEventHandler extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
        System.exit(0);
    }
}


public class UIWindow extends JFrame implements ActionListener {
    private JFrame frame;
    private JButton registerB,loginB,registerConfirmB,loginConfirmB,auctionHouseB,adventureB,paymentDetailsB,buyCoinsB,quitB,goAdventureB,backB,buyB,sellB,sellSearchB;
    private JButton sellConfirmB;
    private JPanel registerP,loginP,parentP,authenticationP,menuP,adventureP,auctionHouseP,sellP,sellBronzeP,sellSilverP,sellGoldP;
    private JPanel buyCoinsP, paymentDetailsP, addCreditCardP;
    private JButton showCB, addCB, removeCB, changePrimaryCardB, CardBack;
    private JButton buyBronzeCoinsB, buySilverCoinsB, buyGoldCoinsB, CoinsBack;
    private JButton cardConfirmB, cardBackB;
    private JLabel sellItemInfo;
    private JLabel[] sellItemWallet;
    private MMOAuctionHouseControl control;
    private JTextField regUsername, regPassword, regFName, regLName, logUsername, logPassword, sellSearch, sellPriceGold, sellPriceSilver, sellPriceBronze;
    private JTextField cardFirstName, cardLastName, cardNo, cardDate, cardCSV;
    private JComboBox<String> dropDown;
    private String[] sellTiers;
    private JTabbedPane sellTabs;
    private JList[] sellItemList;
    private DefaultListModel[] sellItemListModel;
    private int sellItemListLastSelection;
    private SellControl sell;
    
    
    final static String AUTHENTICATION = "AUTHENTICATION";
    final static String REGISTER = "REGISTER";
    final static String LOGIN = "LOGIN";
    final static String MENU = "MENU";
    final static String AUCTIONHOUSE = "AUCTIONHOUSE";
    final static String ADVENTURE = "ADVENTURE" ;
    final static String PAYMENTDETAILS = "PAYMENTDETAILS";
    final static String BUYCOINS = "BUYCOINS";
    final static String QUIT = "QUIT";
    final static String BUYPANEL = "BUYPANEL";
    final static String SELLPANEL = "SELLPANEL";
    final static String ADDCREDITCARD = "ADDCREDITCARD";
    public UIWindow(MMOAuctionHouseControl control){
       this.control = control;
       
       
       
        int windowWidth = 800;
        int windowHeight = 400;
        
        frame = new JFrame();
        frame.setSize(windowWidth, windowHeight);
        
        parentP = new JPanel(new CardLayout());
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        authenticationP = new JPanel(new GridLayout(2,1));
        parentP.add(authenticationP,AUTHENTICATION);
        
        // Authentication page fields
        registerB = new JButton("Register"); 
        registerB.setActionCommand(REGISTER);
        registerB.addActionListener(this);
        authenticationP.add(registerB);
        loginB = new JButton("Login");
        loginB.setActionCommand(LOGIN);
        loginB.addActionListener(this);
        authenticationP.add(loginB);
        
        
        
        registerP = new JPanel(new GridLayout(5,2));
        parentP.add(registerP,REGISTER);
        
        // Register page fields
        registerP.add(new JLabel("Username:"));
        regUsername = new JTextField();
        registerP.add(regUsername);
        registerP.add(new JLabel("Password:"));
        regPassword = new JTextField();
        registerP.add(regPassword);
        registerP.add(new JLabel("First Name:"));
        regFName = new JTextField();
        registerP.add(regFName);
        registerP.add(new JLabel("Last Name:"));
        regLName = new JTextField();
        registerP.add(regLName);
        registerConfirmB = new JButton("Register");
        registerConfirmB.setActionCommand(AUTHENTICATION);
        registerConfirmB.addActionListener(this);
        registerP.add(registerConfirmB);
        
        
        
        loginP = new JPanel(new GridLayout(3,2));
        parentP.add(loginP,LOGIN);
        
        // Login page fields
        loginP.add(new JLabel("User Name:"));
        logUsername = new JTextField();
        loginP.add(logUsername);
        loginP.add(new JLabel("Password:"));
        logPassword = new JTextField();
        loginP.add(logPassword);
        loginConfirmB = new JButton("Login");
        loginConfirmB.setActionCommand(MENU);
        loginConfirmB.addActionListener(this);
        loginP.add(loginConfirmB);
        
        menuP =new JPanel(new GridLayout(5,1)); 
        parentP.add(menuP,MENU);
        
        // Main menu page fields
        auctionHouseB = new JButton("Auction house"); 
        auctionHouseB.setActionCommand(AUCTIONHOUSE);
        auctionHouseB.addActionListener(this);
        menuP.add(auctionHouseB);
        adventureB = new JButton("Adventure");
        adventureB.setActionCommand(ADVENTURE);
        adventureB.addActionListener(this);
        menuP.add(adventureB);
        paymentDetailsB = new JButton("Payment details");
        paymentDetailsB.setActionCommand(PAYMENTDETAILS);
        paymentDetailsB.addActionListener(this);
        menuP.add(paymentDetailsB);
        buyCoinsB = new JButton("Buy coins");
        buyCoinsB.setActionCommand(BUYCOINS);
        buyCoinsB.addActionListener(this);
        menuP.add(buyCoinsB);
        quitB = new JButton("Quit");
        quitB.setActionCommand(QUIT);
        quitB.addActionListener(this);
        menuP.add(quitB);
        
        adventureP = new JPanel(new GridLayout(5,1)); 
        parentP.add(adventureP,ADVENTURE);
        
        String [] choices = {"Gold","Silver","Bronze"};
        dropDown = new JComboBox<String>(choices);
        dropDown.setMaximumSize(dropDown.getPreferredSize()); // added code
        dropDown.setAlignmentX(Component.CENTER_ALIGNMENT);
        adventureP.add(dropDown);
         
        goAdventureB = new JButton("Find Adventure");
        goAdventureB.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
        //goAdventureB.setActionCommand(PAYMENTDETAILS);
        goAdventureB.addActionListener(this);
        adventureP.add(goAdventureB);
        backB = new JButton("Back");
        backB.setActionCommand(MENU);
        backB.addActionListener(this);
        adventureP.add(backB);
        
        auctionHouseP = new JPanel(new GridLayout(2,1));
        parentP.add(auctionHouseP,AUCTIONHOUSE);
        
        buyB = new JButton("Buy item");
        buyB.setActionCommand(BUYPANEL);
        buyB.addActionListener(this);
        auctionHouseP.add(buyB);
        
        sellB = new JButton("Sell item");
        sellB.setActionCommand(SELLPANEL);
        sellB.addActionListener(this);
        auctionHouseP.add(sellB);
        
        //PaymentDetailsPanel
        paymentDetailsP = new JPanel(new GridLayout(5, 1));
        parentP.add(paymentDetailsP, PAYMENTDETAILS);
        showCB = new JButton("Show Credit Cards");
        showCB.addActionListener(this);
        addCB = new JButton("Add Credit Card");
        addCB.addActionListener(this);
        addCB.setActionCommand(ADDCREDITCARD);
        removeCB = new JButton("Remove Credit Card");
        removeCB.addActionListener(this);
        changePrimaryCardB = new JButton("Change Primary Card");
        changePrimaryCardB.addActionListener(this);
        CardBack = new JButton("Back");
        CardBack.addActionListener(this);
        CardBack.setActionCommand(MENU);
        paymentDetailsP.add(showCB);
        paymentDetailsP.add(addCB);
        paymentDetailsP.add(removeCB);
        paymentDetailsP.add(changePrimaryCardB);
        paymentDetailsP.add(CardBack);
        
        //BuyCoinsPanel
        buyCoinsP = new JPanel(new GridLayout(4,1));
        parentP.add(buyCoinsP, BUYCOINS);
        buyBronzeCoinsB = new JButton("Buy Bronze Coins");
        buyBronzeCoinsB.addActionListener(this);
        buySilverCoinsB = new JButton("Buy Silver Coins");
        buySilverCoinsB.addActionListener(this);
        buyGoldCoinsB = new JButton("Buy Gold Coins");
        buyGoldCoinsB.addActionListener(this);
        CoinsBack = new JButton("Back");
        CoinsBack.addActionListener(this);
        CoinsBack.setActionCommand(MENU);
        buyCoinsP.add(buyBronzeCoinsB);
        buyCoinsP.add(buySilverCoinsB);
        buyCoinsP.add(buyGoldCoinsB);
        buyCoinsP.add(CoinsBack);
        
        //addCreditCardPanel
        addCreditCardP = new JPanel(new GridLayout(6, 2));
        cardFirstName = new JTextField();
        cardLastName = new JTextField(); 
        cardNo = new JTextField();
        cardDate = new JTextField(); 
        cardCSV = new JTextField();
        addCreditCardP.add(new JLabel("First Name:"));
        addCreditCardP.add(cardFirstName);
        addCreditCardP.add(new JLabel("Last Name:"));
        addCreditCardP.add(cardLastName);
        addCreditCardP.add(new JLabel("Card No:"));
        addCreditCardP.add(cardNo);
        addCreditCardP.add(new JLabel("Expiry Date:"));
        addCreditCardP.add(cardDate);
        addCreditCardP.add(new JLabel("CSV No:"));
        addCreditCardP.add(cardCSV);
        cardConfirmB = new JButton("Confirm");
        cardConfirmB.addActionListener(this);
        cardBackB = new JButton("Back");
        cardBackB.addActionListener(this);
        cardBackB.setActionCommand(PAYMENTDETAILS);
        addCreditCardP.add(cardBackB);
        addCreditCardP.add(cardConfirmB);
        parentP.add(addCreditCardP,ADDCREDITCARD);
        
        
        // Initialize sell panel
        sellP = new JPanel(new GridLayout(2,2));
        parentP.add(sellP, SELLPANEL);
        
        // Sell panel page fields
        JPanel sellPSearchP = new JPanel(new GridLayout(1,2));
        sellSearch = new JTextField();
        sellPSearchP.add(sellSearch);
        sellSearchB = new JButton("Search");
        sellSearchB.addActionListener(this);
        sellP.add(sellPSearchP);
        sellPSearchP.add(sellSearchB);
        sellItemInfo = new JLabel("Item info");
        sellP.add(sellItemInfo);
        sellTabs = new JTabbedPane();
        sellTabs.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                displaySellItems("");
            }
        });
        sellP.add(sellTabs);
        JPanel itemSellDetails = new JPanel(new GridLayout(6,4));
        itemSellDetails.add(new JPanel());
        itemSellDetails.add(new JLabel("Gold"));
        itemSellDetails.add(new JLabel("Silver"));
        itemSellDetails.add(new JLabel("Bronze"));
        itemSellDetails.add(new JLabel("Wallet:"));
        sellItemWallet = new JLabel[3];
        sellItemWallet[0] = new JLabel("0");
        sellItemWallet[1] = new JLabel("0");
        sellItemWallet[2] = new JLabel("0");
        itemSellDetails.add(sellItemWallet[0]);
        itemSellDetails.add(sellItemWallet[1]);
        itemSellDetails.add(sellItemWallet[2]);
        itemSellDetails.add(new JLabel("Sell for:"));
        sellPriceGold = new JTextField();
        sellPriceSilver = new JTextField();
        sellPriceBronze = new JTextField();
        itemSellDetails.add(sellPriceGold);
        itemSellDetails.add(sellPriceSilver);
        itemSellDetails.add(sellPriceBronze);
        itemSellDetails.add(new JLabel("Taxes:"));
        itemSellDetails.add(new JLabel("0"));
        itemSellDetails.add(new JLabel("0"));
        itemSellDetails.add(new JLabel("0"));
        itemSellDetails.add(new JLabel("Total:"));
        itemSellDetails.add(new JLabel("0"));
        itemSellDetails.add(new JLabel("0"));
        itemSellDetails.add(new JLabel("0"));
        sellConfirmB = new JButton("Sell");
        itemSellDetails.add(sellConfirmB);
        itemSellDetails.add(new JPanel());
        itemSellDetails.add(new JPanel());
        itemSellDetails.add(new JPanel());
        sellP.add(itemSellDetails);
        
        
        frame.add(parentP);
        frame.setVisible(true);
        
    }
    
    private void displaySellItems(String searchFilter) {
        int tabIndex = sellTabs.getSelectedIndex();
        String selectedTier = sellTiers[tabIndex];
        String[] items = sell.getItemNames(selectedTier, searchFilter);
        sellItemListModel[tabIndex].clear();
        
        for (int i = 0; i < items.length; i++) {
            sellItemListModel[tabIndex].addElement(items[i]);
        }
    }
    
    private void createSellTabs() {
        sellTabs.removeAll();
        sell = control.retrieveSell(); 
        sellTiers = sell.getAvailableItemTiers();
        
        sellItemListModel = new DefaultListModel[sellTiers.length];
        sellItemList = new JList[sellTiers.length];
        JScrollPane[] scrollPanes = new JScrollPane[sellTiers.length];
        for (int i = 0; i < sellTiers.length; i++) {
            sellItemListModel[i] = new DefaultListModel();
            sellItemList[i] = new JList(sellItemListModel[i]);
            
            // List selection even
            sellItemList[i].addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        String itemInfo;
                        if (e.getFirstIndex() == sellItemListLastSelection) {
                            sellItemListLastSelection = e.getLastIndex();
                            itemInfo = sell.findItemInformation((String) sellItemListModel[sellTabs.getSelectedIndex()].getElementAt(e.getLastIndex()));
                        } else {
                            sellItemListLastSelection = e.getFirstIndex();
                            itemInfo = sell.findItemInformation((String) sellItemListModel[sellTabs.getSelectedIndex()].getElementAt(e.getFirstIndex()));
                        }
                        itemInfo = "<html>" + itemInfo + "</html>";
                        itemInfo = itemInfo.replaceAll(";", "<br>");
                        sellItemInfo.setText(itemInfo);
                    }
                }
            });
            
            scrollPanes[i] = new JScrollPane(sellItemList[i]);
        }
        
        for (int i = 0; i < sellTiers.length; i++) {
            sellTabs.addTab(sellTiers[i], scrollPanes[i]);
        }
    }
    
    private void updateSellWallet() {
        sellItemWallet[0].setText(Integer.toString(sell.getGoldCoins()));
        sellItemWallet[1].setText(Integer.toString(sell.getSilverCoins()));
        sellItemWallet[2].setText(Integer.toString(sell.getBronzeCoins()));
    }

    public void actionPerformed(ActionEvent e) {
        
        JButton source = (JButton) e.getSource();
        CardLayout c1 = (CardLayout)(parentP.getLayout());
        if(source.equals(registerB)){
             c1.show(parentP, e.getActionCommand());
        }
        else if (source.equals(registerConfirmB)) {
            String username = regUsername.getText();
            String password = regPassword.getText();
            String fName = regFName.getText();
            String lName = regLName.getText();
            
            if (control.registerUser(username, password, fName, lName)) {
                c1.show(parentP, e.getActionCommand());
            }
        }
        else if(source.equals(loginB)){ 
            c1.show(parentP, e.getActionCommand()); 
        }
        else if (source.equals(loginConfirmB)) {
            String username = logUsername.getText();
            String password = logPassword.getText();
            
            if (control.loginUser(username, password)) {
                c1.show(parentP, e.getActionCommand());
            }
        }
        else if(source.equals(adventureB)) {
            c1.show(parentP, e.getActionCommand());
        
        }
        else if(source.equals(goAdventureB)){
            String tierSelected = (String)dropDown.getSelectedItem();
            control.getAdventure(tierSelected);
        }
        else if(source.equals(backB)){
            c1.show(parentP, e.getActionCommand());
            
        }
        else if(source.equals(auctionHouseB)){
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(sellB)) {
            createSellTabs();
            updateSellWallet();
            c1.show(parentP, e.getActionCommand());
        }
        else if (source.equals(sellSearchB)) {
            displaySellItems(sellSearch.getText());
        }
        else if(source.equals(paymentDetailsB)){
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(CardBack)){
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(CoinsBack)){
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(buyCoinsB)){
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(showCB)){
            control.showCreditCards();
        }
        else if(source.equals(cardBackB)){
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(addCB)){    
            c1.show(parentP, e.getActionCommand());
        }
        else if(source.equals(removeCB)){
            control.removeCreditCard();
        }
        else if(source.equals(changePrimaryCardB)){
            control.changePrimaryCard();
        }
        else if(source.equals(buyBronzeCoinsB)){
            control.buyBronzeCoins();
        }
        else if(source.equals(buySilverCoinsB)){
            control.buySilverCoins();
        }
        else if(source.equals(buyGoldCoinsB)){
            control.buyGoldCoins();
        }
        else if(source.equals(cardConfirmB)){
            //JOptionPane.showMessageDialog(null, "Confirming Card");
            boolean successfulCard = control.checkCreditCard(cardFirstName.getText(),cardLastName.getText(),cardNo.getText(),cardDate.getText(),cardCSV.getText());
            if (successfulCard)
            {
                cardFirstName.setText("");
                cardLastName.setText("");
                cardNo.setText("");
                cardDate.setText("");
                cardCSV.setText("");
            }
        }
    }
    
}
