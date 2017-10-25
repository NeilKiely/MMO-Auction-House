
package mmoauctionhouse;

/**
 *
 * @author Vilius
 */
public class Weapon extends Type {
    private int magicDamage;
    private int armorDamage;
    private String typeOfWeapon;
    
    public Weapon(int magicDamage, int armorDamage, String typeOfWeapon, String effect) {
        this.magicDamage = magicDamage;
        this.armorDamage = armorDamage;
        this.typeOfWeapon = typeOfWeapon;
        setEffect(effect);
    }
    
    public String toString() {
        String result = "Magic Damage = " + magicDamage;
        result += ";Armor Damage = " + armorDamage;
        result += ";Type of Weapon = " + typeOfWeapon;
        
        return result;
    }
    public String toStringToFile() {
        String result = ";" + magicDamage;
        result += ";" + armorDamage;
        result += ";" + typeOfWeapon;
        
        return result;
    }
}
