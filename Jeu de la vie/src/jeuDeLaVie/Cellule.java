/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie;

import java.util.ArrayList;

/**
 *
 * @author Delac
 */
public class Cellule {
    public boolean vivante;
    public boolean next;
    public int x;
    public int y;
    
    public Cellule (boolean bool, int x, int y) {
        this.vivante = bool; 
        this.next = true;
        this.x = x;
        this.y = y;
    }          
}
