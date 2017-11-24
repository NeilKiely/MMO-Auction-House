package Item;

/**
 *
 * @author Vilius
 */
public class ItemFactory implements ItemFactoryInterface {
    
    public Item createItemFromString(String itemInfo) {
        String[] temp = itemInfo.split(";");
        Item item = null;
        
        String name, type;
        double baseTax = Double.parseDouble(temp[3]);
        name = temp[1];
        type = temp[2];
        
        switch (temp[0]) {
            case "Chest":
                item = new Chest(name, type, baseTax);
                break;
            case "Shoes":
                item = new Shoes(name, type, baseTax);
                break;
            case "Head":
                item = new Head(name, type, baseTax);
                break;
            case "Gloves":
                item = new Gloves(name, type, baseTax);
                break;
            case "Club":
                item = new Club(name, type, baseTax);
                break;
            case "Hammer":
                item = new Hammer(name, type, baseTax);
                break;
            case "Sword":
                item = new Sword(name, type, baseTax);
                break;
            case "Dagger":
                item = new Dagger(name, type, baseTax);
                break;
            default:
                System.out.println("ERROR UNKNOWN ITEM TYPE");
                break;
        }
        
        // Add decorators onto the item
        // Start at 4, because the first 5 entries are non-effect
        for (int i = 4; i < temp.length; i++) {
            String[] tempDecorator = temp[i].split("=");
            int statNum = Integer.parseInt(tempDecorator[1]);
            
            switch (tempDecorator[0]) {
                case "Dexterity":
                    item = new DexterityArmorDecorator(statNum, item);
                    break;
                case "Reflect":
                    item = new ReflectArmorDecorator(statNum, item);
                    break;
                case "Regeneration":
                    item = new RegenerationArmorDecorator(statNum, item);
                    break;
                case "Sneak":
                    item = new SneakArmorDecorator(statNum, item);
                    break;
                case "Vitality":
                    item = new VitalityArmorDecorator(statNum, item);
                    break;
                case "CritChance":
                    item = new CritChanceWeaponDecorator(statNum, item);
                    break;
                case "CritDamage":
                    item = new CritDamageWeaponDecorator(statNum, item);
                    break;
                case "LifeSteal":
                    item = new LifeStealWeaponDecorator(statNum, item);
                    break;
                case "Poison":
                    item = new PoisonWeaponDecorator(statNum, item);
                    break;
                case "Slow":
                    item = new SlowWeaponDecorator(statNum, item);
                    break;
                default:
                    System.out.println("UNKOWN DECORATOR");
                    break;
            }
        }
        
        return item;
    }
    
    public Item createRandomItem(String tier) {
        Item item;
        int randomSelection = (int) (Math.random() * 2);
        
        // Generate armor
        if (randomSelection == 0)  {
            item = createRandomArmor(tier);
        } else {
            item = createRandomWeapon(tier);
        }
        
        return item;
    }
    
    private String randomTier() {
        int randomTier = (int) (Math.random() * 3);
        String result;
        
        switch (randomTier) {
            case 0:
                result = "Bronze";
                break;
            case 1:
                result = "Silver";
                break;
            case 2:
                result = "Bronze";
                break;
            default:
                result = "ERROR TIER";
                break;
        }
        
        return result;
    }
    
    public Item createRandomWeapon(String tier) {
        Item item;
        String newTier;
        
        if (tier.equals("Random")) {
            newTier = randomTier();
        } else {
            newTier = tier;
        }
        
        int randomCategorySelection = (int) (Math.random() * 4);
        
        switch (randomCategorySelection) {
            case 0:
                item = new Club("Club", newTier, 4);
                break;
            case 1:
                item = new Dagger("Dagger", newTier, 3);
                break;
            case 2:
                item = new Sword("Sword", newTier, 2);
                break;
            case 3:
                item = new Hammer("Hammer", newTier, 1);
                break;
            default:
                item = null;
                break;
        }
        
        int numOfDecorators = (int) (Math.random() * 5 + 1);
        
        item = addRandomArmorDecorators(item, numOfDecorators);
        
        return item;
    }
    
    public Item createRandomArmor(String tier) {
        Item item;
        String newTier;
        
        if (tier.equals("Random")) {
            newTier = randomTier();
        } else {
            newTier = tier;
        }
        
        int randomCategorySelection = (int) (Math.random() * 4);
        
        switch (randomCategorySelection) {
            case 0:
                item = new Chest("Chest Piece", newTier, 4);
                break;
            case 1:
                item = new Shoes("Shoes", newTier, 3);
                break;
            case 2:
                item = new Head("Helmet", newTier, 2);
                break;
            case 3:
                item = new Gloves("Gloves", newTier, 1);
                break;
            default:
                System.out.println("ERROR CREATING ARMOR BASE");
                item = null;
                break;
        }
        
        int numOfDecorators = (int) (Math.random() * 5 + 1);
        System.out.println(numOfDecorators);
        
        item = addRandomArmorDecorators(item, numOfDecorators);
        
        return item;
    }
    
    private Item addRandomArmorDecorators(Item item, int numOfDecorators) {
        int randomDecorator = 0;
        int randomStat = 0;
        
        for (int i = 0; i < numOfDecorators; i++) {
            randomDecorator = (int) (Math.random() * 5);
            randomStat = (int) (Math.random() * 5 + 1);
            
            switch (randomDecorator) {
                case 0:
                    item = new DexterityArmorDecorator(randomStat, item);
                    break;
                case 1:
                    item = new ReflectArmorDecorator(randomStat, item);
                    break;
                case 2:
                    item = new RegenerationArmorDecorator(randomStat, item);
                    break;
                case 3:
                    item = new SneakArmorDecorator(randomStat, item);
                    break;
                case 4:
                    item = new VitalityArmorDecorator(randomStat, item);
                    break;
                default:
                    System.out.println("ERROR GENERATING ARMOR DECORATOR");
                    break;
            }
        }
        
        return item;
    }
    
    private Item addRandomWeaponDecorators(Item item, int numOfDecorators) {
        int randomDecorator = 0;
        int randomStat = 0;
        
        for (int i = 0; i < numOfDecorators; i++) {
            randomDecorator = (int) (Math.random() * 5);
            randomStat = (int) (Math.random() * 5 + 1);
            
            switch (randomDecorator) {
                case 0:
                    item = new CritChanceWeaponDecorator(randomStat, item);
                    break;
                case 1:
                    item = new CritDamageWeaponDecorator(randomStat, item);
                    break;
                case 2:
                    item = new LifeStealWeaponDecorator(randomStat, item);
                    break;
                case 3:
                    item = new PoisonWeaponDecorator(randomStat, item);
                    break;
                case 4:
                    item = new SlowWeaponDecorator(randomStat, item);
                    break;
                default:
                    System.out.println("ERROR GENERATING Weapon DECORATOR");
                    break;
            }
        }
        
        return item;
    }
}
