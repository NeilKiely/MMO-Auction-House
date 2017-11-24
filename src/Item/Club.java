
package Item;

/**
 *
 * @author Vilius
 */
public class Club implements Item{
    private String name;
    private String tier;
    private double baseTax;
    
    public Club (String name, String tier, double baseTax) {
        this.name = name;
        this.tier = tier;
        this.baseTax = baseTax;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return name;
    }
    
    public String getTier() {
        return tier;
    }
    
    public double getTax() {
        return baseTax;
    }
    
    public String toStringToFile() {
        String result = this.getClass().getSimpleName();
        result += ";" + name;
        result += ";" + tier;
        result += ";" + baseTax;
        
        return result;
    }
}
