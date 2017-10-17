
package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public class GoldItemTier implements ItemTier {
    private String itemTier;
    private double tax;
    
    public GoldItemTier(String itemTier, double tax) {
        this.itemTier = itemTier;
        this.tax = tax;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String getItemTier() {
        return itemTier;
    }
}
