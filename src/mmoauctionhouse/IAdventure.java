/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

import itempackage.Item;

/**
 *
 * @author Neil
 */
public interface IAdventure {
    Item createItem(String tier);
    boolean calculateRisk();
    void addTierCanPlay(String tier);
    boolean checkCanPlay();
}
