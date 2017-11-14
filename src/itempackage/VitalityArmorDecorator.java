
package itempackage;

/**
 *
 * @author Vilius
 */
public class VitalityArmorDecorator extends ArmorDecorator {
    
    private int vitality;
    private double tax;
    
    public VitalityArmorDecorator(int vitality, Item newItem) {
        super(newItem);
        this.vitality = vitality;
        tax = 0.5 + vitality * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Vitality +" + vitality;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Vitality=" + vitality;
        
        return result;
    }
}
