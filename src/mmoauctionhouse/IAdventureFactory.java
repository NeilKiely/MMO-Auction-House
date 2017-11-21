/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import java.util.HashMap;

/**
 *
 * @author Chris Mulcahy
 */
public class IAdventureFactory {
    private static HashMap<String, IAdventure> iAdventures = new HashMap<String, IAdventure>();
    
    public static void addIAdventure(String nameID, IAdventure iAdventure)
    {
        iAdventures.put(nameID.toLowerCase(), iAdventure);
    }
    
    public static IAdventure getIAdventure(String nameID)
    {
        IAdventure IAdventureClass = (IAdventure)iAdventures.get(nameID.toLowerCase());
        return IAdventureClass;
    }
}