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
    private static HashMap<String, IFile> iAdventures = new HashMap<String, IFile>();
    
    public static void addIAdventure(String nameID, IFile iFile)
    {
        iAdventures.put(nameID, iFile);
    }
    
    public static IAdventure getIAdventure(String nameID)
    {
        IAdventure IAdventureClass = (IAdventure)iAdventures.get(nameID);
        return IAdventureClass;
    }
}