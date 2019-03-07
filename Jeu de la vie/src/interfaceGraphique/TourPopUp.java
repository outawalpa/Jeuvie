/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import jeuDeLaVie.Main;
import static jeuDeLaVie.Main.current;
import jeuDeLaVie.Tableau;

/**
 *
 * @author Delac
 */
public class TourPopUp extends JFrame implements Observer, ActionListener, Runnable {
    JButton start = new JButton("START"); //Bouton "start"
    JButton stop = new JButton("STOP"); //Bouton "stop"
    JButton step = new JButton("NEXT"); //Bouton "next"
    JButton print = new JButton("PRINT"); //Bouton "print"
    JButton load = new JButton("LOAD"); //Bouton "load"
    Container container = new Container(); //Conteneur pour les boutons
    
    public TourPopUp(Tableau tableau) {
        super("Jeu de la vie"); //Nom de la fenêtre
        
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Ferme la fenêtre quand on veux la fermer...
        setSize(500, 500); //Taille de la fenêtre
        setResizable(false); //On ne peut pas changer la taille de la fenêtre dynamiquement
        
        tableau.addObserver(this); //Observe "Tableau"
        
        JTable table = new JTable(new Plateau(tableau.plateau)) ; //Créé un tableau
        for (int i = 0 ; i < tableau.taille ; i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new MyRenderer()); //Change le rendu des cellules
            table.getColumnModel().getColumn(i).setPreferredWidth(10); //Change le longueur des colones
            table.setRowHeight(i, 22); //Change la hauteur des lignes
        }
        add(table, BorderLayout.CENTER); //Ajoute le tableau à la fenêtre
        
        table.setBorder(BorderFactory.createLineBorder(Color.black)); //Ajoute une bordure au tableau
        
        container.setLayout(new GridLayout(1, 5)); //Ajoute une grille au conteneur
        container.add(step); //Ajoute le bouton au conteneur
        step.addActionListener(this); //Surveille le clic
        container.add(start); //Ajoute le bouton au conteneur
        start.addActionListener(this); //Surveille le clic
        container.add(stop); //Ajoute le bouton au conteneur
        stop.addActionListener(this); //Surveille le clic
        container.add(print); //Ajoute le bouton au conteneur
        print.addActionListener(this); //Surveille le clic
        container.add(load); //Ajoute le bouton au conteneur
        load.addActionListener(this); //Surveille le clic
        add(container, BorderLayout.SOUTH); //Ajoute le conteneur à la fenêtre
       
        //Change l'état de la cellule au clic
        table.addMouseListener(new java.awt.event.MouseAdapter(){
                @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                Tableau.plateau.get(row).get(col).vivante = !Tableau.plateau.get(row).get(col).vivante;
                repaint();
            }
        });

        setVisible(true);   
    }
    
        @Override   
    public void update(Observable o, Object arg) {
        repaint();
    }

        @Override
    public void actionPerformed(ActionEvent event){
        //Joue un tour au clic
        if(event.getSource().equals(step)) {
            Main.current.nextStep(current.taille, current.plateau);
        }
        //Joue des tours jusqu'au stop
        if(event.getSource().equals(start)) {                                   
            if (Main.game == false) {
                Main.game = true;
                Thread t = new Thread(this);
                t.start();
            }
        }
        //Stop le jeu et enregistre dans la sauvegarde l'état des cellules
        if(event.getSource().equals(stop)) {
            Main.game = false;
            try {
                fileWriter("sauvegarde.txt");
            } catch (Exception ex) {
                Logger.getLogger(TourPopUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Sauvegarde l'état des cellules dans "jeuDeLaVie.txt"
        if(event.getSource().equals(print)) {
            try {
                fileWriter("jeuDeLaVie.txt");
            } catch (Exception ex) {
                Logger.getLogger(TourPopUp.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
        //Charge "jeuDeLaVie.txt"
        if(event.getSource().equals(load)) {
            try {
                fileLoader("jeuDeLaVie.txt");
            } catch (Exception ex) {
                Logger.getLogger(TourPopUp.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }

    @Override //Fait tourner le jeu en boucle
    public void run() {
        while(Main.game == true) {
            Main.current.nextStep(current.taille, current.plateau);
            try {
                Thread.sleep(150);
            }   
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    //Enregistre dans un fichier l'état des cellules
    public void fileWriter(String str) throws Exception {
        FileWriter fileWriter = new FileWriter(str);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        writer.write(Main.current.toString());
        writer.close();
    }
    
    //Lit un fichier et le convertit en string
    public void fileLoader(String str) throws Exception {
        FileReader fileReader = new FileReader(str);
        BufferedReader reader = new BufferedReader(fileReader);
        String ligne = "";
        String text = "";
        while ((ligne=reader.readLine())!=null){
            for(int i = 0; i < ligne.length(); i++){
                char c = ligne.charAt(i);
            }
            text += ligne;
        }
        Main.current.toRead(text);
    }

}
