
package itempackage;

/**
 *
 * @author Vilius
 */
public class RegenerationArmorDecorator extends ArmorDecorator {
    
    private int regeneration;
    private double tax;
    
    public RegenerationArmorDecorator(int regeneration, Item newItem) {
        super(newItem);
        this.regeneration = regeneration;
        tax = 0.5 + regeneration * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Regeneration +" + regeneration;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Regeneration=" + regeneration;
        
        return result;
    }
}
