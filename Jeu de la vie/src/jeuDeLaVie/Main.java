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
    
    public static void main(String[] args) throws InterruptedException, Exception {
        current = new Tableau(20);
        tourPopUp = new TourPopUp(current);
        
        tourPopUp.fileLoader("sauvegarde.txt");

        var compteur = 0;
        
        while(game) {
            compteur += 1;
            sleep(1000);
        }
    }
}
