
package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public class Wallet {
    
    private int balance;
    
    /**
     *
     * @author Vilius
     */
    public Wallet(int startingAmount) {
        balance = startingAmount;
    }
    
    /**
     *
     * @author Vilius
     */
    public void reduceAmount(int bronzeCoins) {
        balance -= bronzeCoins;
    }
    
    /**
     *
     * @author Vilius
     */
    public void reduceAmount(int bronzeCoins, int silverCoins) {
        balance -= bronzeCoins;
        balance -= silverCoins * 100;
    }
    
    /**
     *
     * @author Vilius
     */
    public void reduceAmount(int bronzeCoins, int silverCoins, int goldCoins) {
        balance -= bronzeCoins;
        balance -= silverCoins * 100;
        balance -= goldCoins * 10000;
    }
    
    /**
     *
     * @author Vilius
     */
    public void increaseAmount(int bronzeCoins) {
        balance += bronzeCoins;
    }
    
    /**
     *
     * @author Vilius
     */
    public void increaseAmount(int bronzeCoins, int silverCoins) {
        balance += bronzeCoins;
        balance += silverCoins * 100;
    }
    
    /**
     *
     * @author Vilius
     */
    public void increaseAmount(int bronzeCoins, int silverCoins, int goldCoins) {
        balance += bronzeCoins;
        balance += silverCoins * 100;
        balance += goldCoins * 10000;
    }
    
    /**
     * Checks if there's enough specified bronze, silver and gold coins
     * in the wallet.
     * @author Vilius
     */
    public boolean hasEnough(int bronzeCoins, int silverCoins, int goldCoins) {
        int totalSum = bronzeCoins + (silverCoins * 100) + (goldCoins * 10000);
        if (balance - totalSum >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     *
     * @author Vilius
     */
    public int getBronzeCoins() {
        return balance % 100;
    }
    
    /**
     *
     * @author Vilius
     */
    public int getSilverCoins() {
        int totalSilver = balance / 100;
        return totalSilver % 100;
    }
    
    /**
     *
     * @author Vilius
     */
    public int getGoldCoins() {
        int totalGold = balance / 100 / 100;
        return totalGold;
    }
}
