
package Item;

/**
 *
 * @author Vilius
 */
public class CritDamageWeaponDecorator extends WeaponDecorator {
    
    private int critDmg;
    private double tax;
    
    public CritDamageWeaponDecorator(int critDmg, Item newItem) {
        super(newItem);
        this.critDmg = critDmg;
        tax = 0.5 + this.critDmg * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Crit Dmg +" + critDmg;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";CritDamage=" + critDmg;
        
        return result;
    }
}
