
package itempackage;

/**
 *
 * @author Vilius
 */
public class CritChanceWeaponDecorator extends WeaponDecorator{
    
    private int critChance;
    private double tax;
    
    public CritChanceWeaponDecorator(int critChance, Item newItem) {
        super(newItem);
        this.critChance = critChance;
        tax = 0.5 + this.critChance * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Crit Chance +" + critChance;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";CritChance=" + critChance;
        
        return result;
    }
}
