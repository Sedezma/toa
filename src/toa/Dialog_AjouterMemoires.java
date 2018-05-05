package toa;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import toa.Data;
import toa.Dialog_Windows;
import toa.Etudiant;
public class Dialog_AjouterMemoires extends Dialog_Windows {

    // NOm du projet
    private JLabel titre = new JLabel("Ajouter un Mémoire");
    private JLabel m_labelNomMem = new JLabel("Nom du Memoire");
    private JTextField m_TextNomMem;
    // Date de soutenance du projet
    private JLabel m_labelDateSoutenance = new JLabel ("Date de soutenance");
    private JLabel m_labelDateLivrable = new JLabel ("Date de Livrable");
    private JDateChooser m_ChoixDateSoutenance;
    private JDateChooser m_ChoixDateLivrable;
    // Boutton de validation
    private JButton m_buttonAjouter;
    Calendar calendar = Calendar.getInstance();
    

    //Combobox classe
    private JComboBox<String> m_comboClasse = new JComboBox<String>();
    private JLabel m_labelClasse = new JLabel("Classe"); ;
    
    //Liste des etudiant du projet
    private JLabel m_labelEtudiants = new JLabel("Etudiant");
    private JComboBox<Etudiant> m_comboEtudiants = new JComboBox<>();
   
    private JLabel m_labelTuteur = new JLabel("Nom du Tuteur");
    private JComboBox<Professeur> m_comboTuteur = new JComboBox<>();
    ArrayList<Professeur> m_listTuteur = new ArrayList<>();
    
    private JLabel m_labelResponsable = new JLabel("Responsable d'entreprise");
    private JComboBox<ResponsableEntreprise> m_comboResponsable = new JComboBox<>();
    ArrayList<ResponsableEntreprise> m_listResponsable = new ArrayList<>();

    public Dialog_AjouterMemoires(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        setTitle("Ajouter un mémoire");
               //  (|, ligne-->, largueur, hauteur) 
       titre.setBounds(30, 20, 500, 22);
       Font font = new Font("Segoe UI Light",Font.BOLD,22);
       titre.setFont(font);
       m_container.add(titre);
       
       m_labelNomMem.setBounds(80, 60, 100, 22);
       m_container.add(m_labelNomMem);
       
       m_TextNomMem = new JTextField();
       m_TextNomMem.setBounds(230, 60, 200, 22);
       m_container.add(m_TextNomMem);    
   
        m_labelDateLivrable.setBounds(80, 90, 300, 22);
        m_container.add(m_labelDateLivrable);
        
       m_ChoixDateLivrable = new JDateChooser();
       m_ChoixDateLivrable.setBounds(230, 90, 150, 22);
       m_container.add(m_ChoixDateLivrable);
       
       m_labelDateSoutenance.setBounds(80, 120, 300, 22);
       m_container.add(m_labelDateSoutenance);
       m_ChoixDateSoutenance = new JDateChooser();
       m_ChoixDateSoutenance.setBounds(230, 120, 150, 22);
       m_container.add(m_ChoixDateSoutenance);

       //Liste de classe
       
       m_labelClasse.setBounds(80, 150, 200, 22);
       m_container.add(m_labelClasse);
       m_comboClasse.setBounds(230, 150, 200, 22);
       m_container.add(m_comboClasse);
       
       ArrayList<String> listClasse = new ArrayList<String>();
            listClasse.add("");
            listClasse.add("Licence 3 (L3)");
            listClasse.add("Master 1 (M1)");
            listClasse.add("Master 2 (M2)");
            m_comboClasse.removeAllItems();
            for(int i = 0; i < listClasse.size(); i++)
            {
                m_comboClasse.addItem(listClasse.get(i));
            }
        //******* combo d'étudiant ***********

        m_labelEtudiants.setVisible(false);
        m_labelEtudiants.setBounds(80, 180, 200, 22);       
        m_container.add(m_labelEtudiants);
        
        m_comboEtudiants.setVisible(false);
        m_comboEtudiants.setBounds(230, 180, 200, 22);
        m_container.add(m_comboEtudiants);
        
        
        m_comboClasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                refreshComboEtudiants();
            }
        });

          //******* Nom du tuteur *************
          m_labelTuteur.setVisible(true);
          m_labelTuteur.setBounds(80, 240, 200, 22);
          m_container.add(m_labelTuteur);
          
          m_comboTuteur.setVisible(true);
          m_comboTuteur.setBounds(230, 240, 200, 22);
          m_container.add(m_comboTuteur);
          
          m_listTuteur = Data.GenerateProf();
          for(int i = 0; i < m_listTuteur.size(); i++)
            { //méthode d'insertion dans une liste Ã  faire
             m_comboTuteur.addItem(m_listTuteur.get(i));
            }
          //*********** Responsable **********************
          m_labelResponsable.setVisible(true);
          m_labelResponsable.setBounds(80, 210, 200, 22);
          m_container.add(m_labelResponsable);
          
          m_comboResponsable.setVisible(true);
          m_comboResponsable.setBounds(230, 210, 200, 22);
          m_container.add(m_comboResponsable);
          
          m_listResponsable = Data.GenerateResponsables();
          for(int i = 0; i < m_listResponsable.size(); i++)
            { //méthode d'insertion dans une liste Ã  faire
             m_comboResponsable.addItem(m_listResponsable.get(i));
            }
        //*****bouton ajouter ******
        m_buttonAjouter = new JButton();
        m_buttonAjouter.setBounds(m_width / 2 + 20, 320 , 200 , 40);
       
       m_buttonAjouter.setText("Ajouter votre Mémoire");
        m_container.add(m_buttonAjouter); 
        m_buttonAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               Enregistrer_Memoire();}
        });
    }
    
    @Override public void SetWidthHeight()
    {
        m_width = 700;
        m_height = 500;
    }
    //*************************************************************************
    //************************************************************************
  
    // Méthode permettant de mettre Ã  jour la liste d'étudiant
    ArrayList<Etudiant> listEtudiants = new ArrayList<>();
    public void refreshComboEtudiants(){   
        if (m_comboClasse.getSelectedIndex()== 0)
        {  
            m_comboEtudiants.setVisible(false);
            m_labelEtudiants.setVisible(false);
        }
        else
        {
        listEtudiants =  Data.GeneratePromoEtudiants(m_comboClasse.getSelectedIndex());
        m_comboEtudiants.removeAllItems();       
        for(int i = 0; i < listEtudiants.size(); i++)
        {  
               m_comboEtudiants.addItem(listEtudiants.get(i));
            } 
        m_comboEtudiants.setVisible(true);
        m_labelEtudiants.setVisible(true);
        }
    }
    private void Enregistrer_Memoire() {

             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             String dateSoutenance = dateFormat.format(m_ChoixDateSoutenance.getDate());
             String dateLivrable = dateFormat.format(m_ChoixDateLivrable.getDate());
             int IdEtuChefProj = ((Etudiant)(m_comboEtudiants.getSelectedItem())).GetId();
             int IdProfTuteur = ((Professeur)(m_comboTuteur.getSelectedItem())).GetId();
             int IdResp=((ResponsableEntreprise)(m_comboResponsable.getSelectedItem())).GetId();
             String nomMemoire = m_TextNomMem.getText();
             
             
             Memoire currentMemoire = new Memoire( 0, nomMemoire, IdEtuChefProj, IdProfTuteur, IdResp, dateSoutenance, dateLivrable);
         if (Data.EnregistrerMemoire(currentMemoire))
         {
             if ((m_parent != null) && (m_parent instanceof Frame_MainInterface))
             {
                 ((Frame_MainInterface)(m_parent)).Requery();
             }
             m_TextNomMem.setText("");
             m_comboResponsable.setSelectedIndex(-1);
             m_comboTuteur.setSelectedIndex(-1);
             m_comboEtudiants.setSelectedIndex(-1);
             m_comboClasse.setSelectedIndex(-1);
             javax.swing.JOptionPane.showMessageDialog(null, "Données enregistrées");
         }
         else
         {
             javax.swing.JOptionPane.showMessageDialog(null, "Les champs ne sont pas correctement remplis");
         }
        //javax.swing.JOptionPane.showMessageDialog(null, m_tabEtudiants.getModel().getValueAt(m_tabEtudiants.getSelectedRow(), m_tabEtudiants.getSelectedColumn()));
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
