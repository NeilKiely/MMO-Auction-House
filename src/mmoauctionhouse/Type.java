
package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public abstract class Type {
    private String effect;
    
    public abstract String toString();
    public abstract String toStringToFile();
    public String getEffect() {
        return effect;
    }
    
    public void setEffect(String effect) {
        this.effect = effect;
    }
}
