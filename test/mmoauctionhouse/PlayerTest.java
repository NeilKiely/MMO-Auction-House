/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import itempackage.Item;
import mmoauctionhouse.creditcardpackage.CreditCard;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chris Mulcahy
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCreditCard method, of class Player.
     */
    @Test
    public void testGetCreditCard() {
        System.out.println("getCreditCard");
        Player instance = new Bronze("Bronze", 20, 5, null, "Boberts");
        CreditCard card1 = new CreditCard("Bob", "Boberts", "4801123456789100", "09/19", "420");
        CreditCard[] expResult = new CreditCard[5];
        expResult[0] = card1;
        instance.addCreditCard(card1);
        CreditCard[] result = instance.getCreditCard();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setCreditCard method, of class Player.
     */
    @Test
    public void testSetCreditCard() {
        System.out.println("setCreditCard");
        CreditCard card1 = new CreditCard("Bob", "Boberts", "4801123456789100", "09/19", "435");
        CreditCard[] creditCard = new CreditCard[5];
        creditCard[0] = card1;
        Player instance = new Bronze("Bronze", 20, 5, null, "Boberts");
        instance.setCreditCard(creditCard);
        assertArrayEquals(creditCard, instance.getCreditCard());
    }

    /**
     * Test of getPrimaryCard method, of class Player.
     */
    @Test
    public void testGetPrimaryCard() {
        System.out.println("getPrimaryCard");
        Player instance = new Bronze("Bronze", 20, 5, null, "Boberts");
        CreditCard card1 = new CreditCard("Bob", "Boberts", "4801123456789100", "09/19", "435");
        CreditCard expResult = card1;
        instance.addCreditCard(card1);
        CreditCard result = instance.getPrimaryCard();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPrimaryCard method, of class Player.
     */
    /*
    @Test
    public void testSetPrimaryCard() {
        System.out.println("setPrimaryCard");
        CreditCard primaryCard = null;
        Player instance = new PlayerImpl();
        instance.setPrimaryCard(primaryCard);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateTax method, of class Player.
     */
    /*
    @Test
    public void testCalculateTax() {
        System.out.println("calculateTax");
        int cost = 0;
        Player instance = new PlayerImpl();
        int expResult = 0;
        int result = instance.calculateTax(cost);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWallet method, of class Player.
     */
    /*
    @Test
    public void testGetWallet() {
        System.out.println("getWallet");
        Player instance = new PlayerImpl();
        Wallet expResult = null;
        Wallet result = instance.getWallet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWallet method, of class Player.
     */
    /*
    @Test
    public void testSetWallet_Wallet() {
        System.out.println("setWallet");
        Wallet wallet = null;
        Player instance = new PlayerImpl();
        instance.setWallet(wallet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWallet method, of class Player.
     */
    /*
    @Test
    public void testSetWallet_int() {
        System.out.println("setWallet");
        int startingMoney = 0;
        Player instance = new PlayerImpl();
        instance.setWallet(startingMoney);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTier method, of class Player.
     */
    /*
    @Test
    public void testSetTier() {
        System.out.println("setTier");
        String tier = "";
        Player instance = new PlayerImpl();
        instance.setTier(tier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTier method, of class Player.
     */
    /*
    @Test
    public void testGetTier() {
        System.out.println("getTier");
        Player instance = new PlayerImpl();
        String expResult = "";
        String result = instance.getTier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBronzeCoins method, of class Player.
     */
    /*
    @Test
    public void testGetBronzeCoins() {
        System.out.println("getBronzeCoins");
        Player instance = new PlayerImpl();
        int expResult = 0;
        int result = instance.getBronzeCoins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSilverCoins method, of class Player.
     */
    /*
    @Test
    public void testGetSilverCoins() {
        System.out.println("getSilverCoins");
        Player instance = new PlayerImpl();
        int expResult = 0;
        int result = instance.getSilverCoins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGoldCoins method, of class Player.
     */
    /*
    @Test
    public void testGetGoldCoins() {
        System.out.println("getGoldCoins");
        Player instance = new PlayerImpl();
        int expResult = 0;
        int result = instance.getGoldCoins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInventory method, of class Player.
     */
    /*
    @Test
    public void testSetInventory() {
        System.out.println("setInventory");
        Inventory inventory = null;
        Player instance = new PlayerImpl();
        instance.setInventory(inventory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class Player.
     */
    /*
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Player instance = new PlayerImpl();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class Player.
     */
    /*
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        Player instance = new PlayerImpl();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTax method, of class Player.
     */
    /*
    @Test
    public void testSetTax() {
        System.out.println("setTax");
        double tax = 0.0;
        Player instance = new PlayerImpl();
        instance.setTax(tax);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTax method, of class Player.
     */
    /*
    @Test
    public void testGetTax() {
        System.out.println("getTax");
        Player instance = new PlayerImpl();
        double expResult = 0.0;
        double result = instance.getTax();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Player.
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        Player instance = new PlayerImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class Player.
     */
    /*
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        Item item = null;
        Player instance = new PlayerImpl();
        instance.addItem(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemNames method, of class Player.
     */
    /*
    @Test
    public void testGetItemNames() {
        System.out.println("getItemNames");
        String tier = "";
        String searchFilter = "";
        Player instance = new PlayerImpl();
        String[] expResult = null;
        String[] result = instance.getItemNames(tier, searchFilter);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findItemInformation method, of class Player.
     */
    /*
    @Test
    public void testFindItemInformation() {
        System.out.println("findItemInformation");
        String itemName = "";
        Player instance = new PlayerImpl();
        String expResult = "";
        String result = instance.findItemInformation(itemName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailableItemTiers method, of class Player.
     */
    /*
    @Test
    public void testGetAvailableItemTiers() {
        System.out.println("getAvailableItemTiers");
        Player instance = new PlayerImpl();
        String[] expResult = null;
        String[] result = instance.getAvailableItemTiers();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRisk method, of class Player.
     */
    /*
    @Test
    public void testGetRisk() {
        System.out.println("getRisk");
        Player instance = new PlayerImpl();
        double expResult = 0.0;
        double result = instance.getRisk();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInventory method, of class Player.
     */
    /*
    @Test
    public void testGetInventory() {
        System.out.println("getInventory");
        Player instance = new PlayerImpl();
        Inventory expResult = null;
        Inventory result = instance.getInventory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCreditCard method, of class Player.
     */
    /*
    @Test
    public void testAddCreditCard() {
        System.out.println("addCreditCard");
        CreditCard creditCard = null;
        Player instance = new PlayerImpl();
        boolean expResult = false;
        boolean result = instance.addCreditCard(creditCard);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCreditCard method, of class Player.
     */
    /*
    @Test
    public void testRemoveCreditCard() {
        System.out.println("removeCreditCard");
        String cardNo = "";
        Player instance = new PlayerImpl();
        boolean expResult = false;
        boolean result = instance.removeCreditCard(cardNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayCreditCards method, of class Player.
     */
    /*
    @Test
    public void testDisplayCreditCards() {
        System.out.println("displayCreditCards");
        Player instance = new PlayerImpl();
        instance.displayCreditCards();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreditCardsStringArray method, of class Player.
     */
    /*
    @Test
    public void testGetCreditCardsStringArray() {
        System.out.println("getCreditCardsStringArray");
        Player instance = new PlayerImpl();
        String[] expResult = null;
        String[] result = instance.getCreditCardsStringArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkHasCard method, of class Player.
     */
    /*
    @Test
    public void testCheckHasCard() {
        System.out.println("checkHasCard");
        Player instance = new PlayerImpl();
        boolean expResult = false;
        boolean result = instance.checkHasCard();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlayerImpl extends Player {

        public int calculateTax(int cost) {
            return 0;
        }

        public double getRisk() {
            return 0.0;
        }
    }
    */
    
}
