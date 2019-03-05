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
public class Display {
    
    /**
     *
     */
    public static void Affichage(int taille, ArrayList<ArrayList<Cellule>> tableau){
        for (int i = 0; i < taille; i++){
            ArrayList<Cellule> ligne = tableau.get(i);
            for (int j = 0; j < taille; j++){
                Cellule titi = ligne.get(j);
                
                if (titi.vivante) {
                    System.out.print("X" + " | ");
                }
                else {
                    System.out.print("0" + " | ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
