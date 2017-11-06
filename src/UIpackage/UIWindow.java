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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import mmoauctionhouse.MMOAuctionHouseControl;

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
    private JButton registerB, loginB, registerConfirmB, loginConfirmB,auctionHouseB,adventureB,paymentDetailsB,buyCoinsB,quitB,goAdventureB,backB,buyB,sellB;
    private JPanel registerP,loginP,parentP,authenticationP,menuP,adventureP,auctionHouseP;
    private MMOAuctionHouseControl control;
    private JTextField regUsername, regPassword, regFName, regLName, logUsername, logPassword;
    private JComboBox<String> dropDown;
    
    
    
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
    public UIWindow(MMOAuctionHouseControl control){
       this.control = control;
       
       
       
       int windowWidth = 400;
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
        
        sellB = new JButton("Buy item");
        sellB.setActionCommand(SELLPANEL);
        sellB.addActionListener(this);
        auctionHouseP.add(sellB);
        
        int frameWidth = 200;
        int frameHeight = 100;
        frame.add(parentP);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
        frame.setVisible(true);
        
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
            //control.Menu(0);
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
    }
    
}
