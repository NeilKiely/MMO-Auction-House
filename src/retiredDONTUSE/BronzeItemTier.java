
package retiredDONTUSE;

import retiredDONTUSE.ItemTier;

/**
 *
 * @author Vilius
 */
public class BronzeItemTier implements ItemTier {
    private String itemTier;
    private double tax;
    
    public BronzeItemTier(String itemTier, double tax) {
        this.itemTier = itemTier;
        this.tax = tax;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String getItemTier() {
        return itemTier;
    }
    public String toString(){
        String result = ";" + itemTier + ";" + tax;
        return result;
    }
}
