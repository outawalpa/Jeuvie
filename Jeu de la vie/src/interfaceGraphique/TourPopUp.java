/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import jeuDeLaVie.Cellule;
import jeuDeLaVie.Main;
import static jeuDeLaVie.Main.current;
import jeuDeLaVie.Tableau;

/**
 *
 * @author Delac
 */
public class TourPopUp extends JFrame implements Observer, ActionListener, Runnable{
    JButton start = new JButton("START");
    JButton stop = new JButton("STOP");
    JButton step = new JButton("NEXT");
    Container container = new Container();
    
    public TourPopUp(Tableau tableau) {
        super("Jeu de la vie");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        
        tableau.addObserver(this);
        
        JTable table = new JTable(new Plateau(tableau.plateau)) ;
        for (int i = 0 ; i < tableau.taille ; i++){
            table.getColumnModel().getColumn(i).setCellRenderer(new MyRenderer());
            table.getColumnModel().getColumn(i).setPreferredWidth(10);
            table.setRowHeight(i, 10);
        }
        add(table, BorderLayout.CENTER);
        
        container.setLayout(new GridLayout(1, 3));
        container.add(step);
        step.addActionListener(this);
        container.add(start);
        start.addActionListener(this);
        container.add(stop);
        stop.addActionListener(this);
        add(container, BorderLayout.SOUTH);
       
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
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(step)) {
            System.out.println("Step");
            Main.current.nextStep(current.taille, current.plateau);
        }
        if(event.getSource().equals(start)) {                                   // Using multi-thread to exectute a separate thread
            System.out.println("Start");
            if (Main.game == false) {
                Main.game = true;
            }
        }
        if(event.getSource().equals(stop)) {
            System.out.println("Stop");
            Main.game = false;
        }
    }

    @Override
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

}
