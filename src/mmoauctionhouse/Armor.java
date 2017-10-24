
package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public class Armor extends Type {
    
    private int armorPoints;
    private int magicPoints;
    
    public Armor(int armorPoints, int magicPoints, String effect) {
        this.armorPoints = armorPoints;
        this.magicPoints = magicPoints;
        setEffect(effect);
    }
    
    public String toString() {
        String result = "Armor Points = " + armorPoints;
        result += ";Magic Points = " + magicPoints;
        
        return result;
    }
    public String toStringToFile(){
        String result = ";" + armorPoints;
        result += ";" + magicPoints;
        
        return result;
    }
}
