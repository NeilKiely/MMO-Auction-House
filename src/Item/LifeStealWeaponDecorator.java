
package Item;

/**
 *
 * @author Vilius
 */
public class LifeStealWeaponDecorator extends WeaponDecorator{
    
    private int lifeSteal;
    private double tax;
    
    public LifeStealWeaponDecorator(int lifeSteal, Item newItem) {
        super(newItem);
        this.lifeSteal = lifeSteal;
        tax = 0.5 + lifeSteal * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Life Steal +" + lifeSteal;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";LifeSteal=" + lifeSteal;
        
        return result;
    }
}
