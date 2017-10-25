
package mmoauctionhouse;

public class Item {
    private int durability; // 0-100 durability. 0 is broken, 100 fully repaired
    private Type type;
    private String itemTypeString;
    private ItemTier itemTier;
    private String name;
    
    /**
     *
     * @author Vilius
     */
    public Item(int durability, int armorPoints, int magicPoints, String effect, String itemTier, double tax, String name) {
        this.durability = durability;
        type = (Type) new Armor(armorPoints, magicPoints, effect);
        createItemTier(itemTier, tax);
        itemTypeString = "Armor";
        this.name = name;
    }
    
    /**
     *
     * @author Vilius
     */
    public Item(int durability, int armorDamage, int magicDamage, String typeOfWeapon, String effect, String itemTier, double tax, String name) {
        this.durability = durability;
        type = (Type) new Weapon(armorDamage, magicDamage, typeOfWeapon, effect);
        createItemTier(itemTier, tax);
        itemTypeString = "Weapon";
        this.name = name;
    }
    
    /**
     * Creates an item tier based on the item tier string
     * @author Vilius
     */
    private void createItemTier(String itemTier, double tax) {
        switch (itemTier) {
            case "Bronze":
                this.itemTier = (ItemTier) new BronzeItemTier(itemTier, tax);
                break;
            case "Silver":
                this.itemTier = (ItemTier) new BronzeItemTier(itemTier, tax);
                break;
            case "Gold":
                this.itemTier = (ItemTier) new BronzeItemTier(itemTier, tax);
                break;
            default:
                System.out.println("Unknown item tier when creating item");
                break;
        }
    }
    public double getTax(){
        return itemTier.getTax();
        
    }
    public String getItemTier() {
        return itemTier.getItemTier();
    }
    
    public int getDurability() {
        return durability;
    }
    
    public String getType() {
        return itemTypeString;
    }
    
    public Type getTypeStats() {
        return type;
    }
    
    public String toString() {
        String result = name + ";";
        result += type.toString();
        
        return result;
    }
    public String toStringToFile() {
        String result = name ;
        result += ";" + itemTypeString;
        result += type.toStringToFile();
        result += itemTier.toString();
        result += ";" + durability ;
        
        
        return result;
    }
    
    public static Item generateItem(String tier)
    {
        //this is placeholder for a more robust item generator

        //int durablitiy = (int)Math.random() * 10 + 1;
        String itemType[] = {"Weapon", "Armour"};
        int choice = (int) Math.random() * itemType.length;
        String itemTypeChoice = itemType[choice];
        Item item = null;
        if (itemTypeChoice.compareTo("Weapon") == 0)
        {
            item = new Item(2, 5, 5, "", "Staff", tier, 20, "Chris's Gordon Ramsey Staff of Pleasure");
        }
        if (itemTypeChoice.compareTo("Armour") == 0)
        {
            item = new Item(2, 5, 5, "", tier, 20, "Chris's Gordon Ramsey Robe of Pleasure");
        }
        
        return item;
    }
}
