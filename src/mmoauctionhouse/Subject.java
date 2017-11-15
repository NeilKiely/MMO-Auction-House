/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmoauctionhouse;

/**
 *
 * @author Chris Mulcahy
 */
public interface Subject {
    void addObservser(Observer o);
    void detachObserver(Observer o);
    void notifyObserver(String string);
}
