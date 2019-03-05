/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeuDeLaVie;

import interfaceGraphique.TourPopUp;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

/**
 *
 * @author Delac
 */
public class Main {

    public static Tableau current;
    public static TourPopUp tourPopUp;
    public static boolean game = false;
    
    public static void main(String[] args) throws InterruptedException {
        current = new Tableau(50);
        tourPopUp = new TourPopUp(current);
        
        current.plateau.get(0).get(1).vivante = true;
        current.plateau.get(1).get(1).vivante = true;
        current.plateau.get(2).get(1).vivante = true;
        
        var compteur = 0;
        
        while(game) {
                 
            //Display.Affichage(current.taille, current.plateau);
            
            //current.nextStep(current.taille, current.plateau);

            compteur += 1;
            sleep(1000);
        }
    }
}
