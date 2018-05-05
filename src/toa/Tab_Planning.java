package toa;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Tab_Planning extends JTable{
    
    private Object[][] m_donnee;
    
    public Tab_Planning()
    {
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getTableHeader().setResizingAllowed(false);
        this.getTableHeader().setReorderingAllowed(false);
        this.setRowHeight(30);
        //this.getColumnModel().getColumn(0).setMinWidth(50);
        //m_tabjanvier.getColumnModel().getColumn(0).setMaxWidth(colonne1);
    }
    
    @Override public boolean isCellEditable(int rowIndex, int colIndex) {
      return false; 
    }
    
    public Object[][] GetDonnee()
    {
        return m_donnee;
    }
    
    public void SetDonnee(Object[][] data)
    {
        m_donnee = data;
    }
}
