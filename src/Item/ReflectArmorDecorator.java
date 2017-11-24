
package Item;

/**
 *
 * @author Vilius
 */
public class ReflectArmorDecorator extends ArmorDecorator {
    
    private int reflective;
    private double tax;
    
    public ReflectArmorDecorator(int reflective, Item newItem) {
        super(newItem);
        this.reflective = reflective;
        tax = 0.5 + reflective * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Reflect +" + reflective;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Reflect=" + reflective;
        
        return result;
    }
}
