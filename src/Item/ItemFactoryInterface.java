
package Item;

/**
 *
 * @author Vilius
 */
public interface ItemFactoryInterface {
    public Item createRandomItem(String tier);
    public Item createRandomWeapon(String tier);
    public Item createRandomArmor(String tier);
    public Item createItemFromString(String itemInfo);
}
