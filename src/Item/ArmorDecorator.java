
package Item;

/**
 *
 * @author Vilius
 */
public abstract class ArmorDecorator implements Item {
    
    protected Item nextItemNode;
    
    public ArmorDecorator(Item newItem) {
        nextItemNode = newItem;
    }
    
    public abstract String getDescription();
    public abstract double getTax();
    
    public String getName() {
        return nextItemNode.getName();
    }
    
    public String getTier() {
        return nextItemNode.getTier();
    }
    
}
