package itempackage;

/**
 *
 * @author Vilius
 */
public interface Item {
    public String getDescription();
    public String getTier();
    public double getTax();
    public String getName();
    public String toStringToFile();
}
