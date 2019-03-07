/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Delac
 */
public class Tableau extends Observable {
    public static int taille;
    public static ArrayList<ArrayList<Cellule>> plateau = new ArrayList();
    
    public Tableau (int taille) {
        this.taille = taille;
        this.plateau = new ArrayList();
        for (int i = 0 ; i < taille ; i++) {
            ArrayList<Cellule> ligne = new ArrayList();
            for (int j=0; j < taille ; j++) {
                Cellule newCellule = new Cellule(false, i, j);
                ligne.add(newCellule);
            }
            plateau.add(ligne);
        }
    }
    
    public void nextStep (int taille, ArrayList<ArrayList<Cellule>> current) {
        
        for (int i = 0 ; i < taille ; i++) {
            for (int j=0; j < taille ; j++) {
                test(taille, current, current.get(i).get(j));
            }
        }
        
        for (int i = 0 ; i < taille ; i++) {
            for (int j=0; j < taille ; j++) {
                current.get(i).get(j).vivante = current.get(i).get(j).next;
            }
        }
        setChanged();
        notifyObservers();   
    }
    
    
    public void test(int taille, ArrayList<ArrayList<Cellule>> current, Cellule cellule){
        int compteur = 0;
        
        int a = (cellule.x)+1;
        int b = (cellule.x)-1;
        int c = (cellule.y)+1;
        int d = (cellule.y)-1;
        
        if (a>=0 && a<taille && d>=0 && d<taille && current.get(a).get(d).vivante) {
            compteur += 1;
        }
        if (a>=0 && a<taille && current.get(a).get(cellule.y).vivante) {
            compteur += 1;
        }
        if (a>=0 && a<taille && c>=0 && c<taille && current.get(a).get(c).vivante) {
            compteur += 1;
        }
        if (d>=0 && d<taille && current.get(cellule.x).get(d).vivante) {
            compteur += 1;
        }
        if (c>=0 && c<taille && current.get(cellule.x).get(c).vivante) {
            compteur += 1;
        }
        if (b>=0 && b<taille && d>=0 && d<taille && current.get(b).get(d).vivante) {
            compteur += 1;
        }
        if (b>=0 && b<taille && current.get(b).get(cellule.y).vivante) {
            compteur += 1;
        }
        if (b>=0 && b<taille && c>=0 && c<taille && current.get(b).get(c).vivante) {
            compteur += 1;
        }
        
        
        if (compteur == 3 || (cellule.vivante == true && compteur == 2)){
            cellule.next = true;
        }
        else {
            cellule.next = false;
        }
    }
    
    public String toString(){
        String str = "";
        for(int i = 0; i<this.taille; i++){
            str += "|";
            for(int j = 0; j<this.taille; j++) {
                if (this.plateau.get(i).get(j).vivante){str += "X|";}
                else {str += "O|";}
            }
            str += "\r\n";
        }
        return str;
    }
    
    public void toRead(String str){
        int i =0;
        int j = 0;
        
        for(int k=0; k<str.length(); k++){
            if (str.charAt(k) == 'O'){
                this.plateau.get(i).get(j).vivante = false;
                j++;
            }
            if (str.charAt(k) == 'X'){
                this.plateau.get(i).get(j).vivante = true;
                j++;
            }
            if (j == this.taille){
                i++;
                j = 0;
            }
        }
        setChanged();
        notifyObservers();
    }
}
