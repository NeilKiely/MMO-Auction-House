
package mmoauctionhouse;

public class Bronze extends Player {
    /**
     *
     * @author Vilius
     */
    public Bronze(String tier, double tax, int startingMoney, Inventory inventory, String username) {
        setTax(tax);
        setWallet(startingMoney);
        setTier(tier);
        setInventory(inventory);
        setUsername(username);
    }
    
    @Override
    public int calculateTax(int cost) {
        return (int) (cost / 100.0 * getTax());
    }
    
    @Override
    public double getRisk()
    {
        return 0.5;
    }
}
