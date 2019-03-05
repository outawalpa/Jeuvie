package interfaceGraphique;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import jeuDeLaVie.Tableau;
 
/**
 * Définir l'affichage dans un JTable
 * @author Fobec 2010
 */
public class MyRenderer extends DefaultTableCellRenderer {
 
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (Tableau.plateau.get(row).get(column).vivante) {
            Color clr = new Color(0, 0, 0);
            component.setBackground(clr);
        } else {
            Color clr = new Color(255, 255, 255);
            component.setBackground(clr);
        }
        return component;
    }
}
