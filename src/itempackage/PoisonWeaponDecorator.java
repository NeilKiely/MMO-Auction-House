
package itempackage;

/**
 *
 * @author Vilius
 */
public class PoisonWeaponDecorator extends WeaponDecorator {
    
    private int poison;
    private double tax;
    
    public PoisonWeaponDecorator(int poison, Item newItem) {
        super(newItem);
        this.poison = poison;
        tax = 0.5 + poison * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Poison +" + poison;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Poison=" + poison;
        
        return result;
    }
}
