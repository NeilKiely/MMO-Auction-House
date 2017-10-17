
package mmoauctionhouse;

public class Silver extends Player {
    /**
     *
     * @author Vilius
     */
    public Silver(String tier, double tax, int startingMoney, Inventory inventory, String username) {
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
}
