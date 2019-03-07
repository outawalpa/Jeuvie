/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie;

/**
 *
 * @author Delac
 */
public class Cellule {
    public boolean vivante; //Etat de la cellule
    public boolean next; //Prochain état de la cellule
    public int x; //Position x
    public int y; //Position y
    
    public Cellule (boolean bool, int x, int y) {
        this.vivante = bool; 
        this.next = true;
        this.x = x;
        this.y = y;
    }          
}
