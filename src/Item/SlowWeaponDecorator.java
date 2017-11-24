
package Item;

/**
 *
 * @author Vilius
 */
public class SlowWeaponDecorator extends WeaponDecorator {
    
    private int slow;
    private double tax;
    
    public SlowWeaponDecorator(int lifeSteal, Item newItem) {
        super(newItem);
        this.slow = slow;
        tax = 0.5 + slow * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Slow +" + slow;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Slow=" + slow;
        
        return result;
    }
}
