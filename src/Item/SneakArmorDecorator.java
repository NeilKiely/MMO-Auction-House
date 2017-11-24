
package Item;

/**
 *
 * @author Vilius
 */
public class SneakArmorDecorator extends ArmorDecorator {
    
    private int sneak;
    private double tax;
    
    public SneakArmorDecorator(int sneak, Item newItem) {
        super(newItem);
        this.sneak = sneak;
        tax = 0.5 + sneak * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Sneak +" + sneak;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Sneak=" + sneak;
        
        return result;
    }
}
