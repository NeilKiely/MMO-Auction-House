/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Item;

/**
 *
 * @author Vilius
 */
public class DexterityArmorDecorator extends ArmorDecorator{
    
    private int dexterity;
    private double tax;
    
    public DexterityArmorDecorator(int dexterity, Item newItem) {
        super(newItem);
        this.dexterity = dexterity;
        tax = 0.5 + dexterity * 0.1;
    }
    
    public String getDescription() {
        return nextItemNode.getDescription() + ";Dexterity +" + dexterity;
    }
    
    public double getTax() {
        return tax;
    }
    
    public String toStringToFile() {
        String result = nextItemNode.toStringToFile();
        result += ";Dexterity=" + dexterity;
        
        return result;
    }
}
