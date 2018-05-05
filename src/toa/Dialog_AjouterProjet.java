package toa;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import toa.Data;
import toa.Dialog_Windows;
import toa.Etudiant;


public class Dialog_AjouterProjet extends Dialog_Windows {
   
    private JLabel titre = new JLabel("Ajouter un projet");
    // NOm du projet
    private JLabel m_labelNomPro = new JLabel("Nom du projet");
    private JTextField m_TextNomPro;
    // Date de soutenance du projet
    private JLabel m_labelDateSoutenance = new JLabel ("Date de soutenance");
    private JDateChooser m_ChoixDateSoutenance;
    // Boutton de validation
    private JButton m_buttonAjouter;
    // Description et objectif du projet
    private JLabel m_labelObjectif = new JLabel("Description du projet");
    private JTextArea m_TextObjectif;
    //Combobox classe
    private JComboBox<String> m_comboClasse = new JComboBox<String>();
    private JLabel m_labelClasse = new JLabel("Classe"); ;
    
    //Liste des etudiant du projet
    private JLabel m_labelEtudiants = new JLabel("Chef du projet");
    private JComboBox<Etudiant> m_comboEtudiants = new JComboBox<>();
    
    private JLabel m_labelGroup = new JLabel("Numéro de Groupe");
    private JTextField m_TextGroup;
   
    private JLabel m_labelTuteur = new JLabel("Nom du Tuteur");
    private JComboBox<Professeur> m_comboTuteur = new JComboBox<>();
    ArrayList<Professeur> m_listTuteur = new ArrayList<>();

    public Dialog_AjouterProjet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
       
       setTitle("Ajouter un projet");
       
       titre.setBounds(10, 0, 500, 50);
       Font font = new Font("Segoe UI Light",Font.BOLD,22);
       titre.setFont(font);
       m_container.add(titre);
       //  (|, ligne-->, largueur, hauteur) 

       m_labelNomPro.setBounds(80, 60, 100, 22);
       m_container.add(m_labelNomPro);
       
       m_TextNomPro = new JTextField();
       m_TextNomPro.setBounds(230, 60, 200, 22);
       m_container.add(m_TextNomPro);    
       
       m_labelObjectif.setBounds(80, 90, 300, 22);
        m_container.add(m_labelObjectif);
       
       m_TextObjectif = new JTextArea();
       m_TextObjectif.setBounds(230, 90, 200, 50);
       m_container.add(m_TextObjectif);
       
        m_labelDateSoutenance.setBounds(80, 150, 300, 22);
        m_container.add(m_labelDateSoutenance);
       
       m_ChoixDateSoutenance = new JDateChooser();
       m_ChoixDateSoutenance.setBounds(230, 150, 150, 22);
       m_container.add(m_ChoixDateSoutenance);
       //Liste de classe
       
       m_labelClasse.setBounds(80, 180, 200, 22);
       m_container.add(m_labelClasse);
       m_comboClasse.setBounds(230, 180, 200, 22);
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
        m_labelEtudiants.setBounds(80, 210, 200, 22);       
        m_container.add(m_labelEtudiants);
        
        m_comboEtudiants.setVisible(false);
        m_comboEtudiants.setBounds(230, 210, 200, 22);
        m_container.add(m_comboEtudiants);
        
        
        m_comboClasse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                refreshComboEtudiants();
            }
        });

        m_labelGroup.setBounds(80, 240, 200, 22);
          m_container.add(m_labelGroup);
          m_TextGroup = new JTextField();
          m_TextGroup.setBounds(230, 240, 200, 22);
          m_container.add(m_TextGroup);
          
          //******* Nom du tuteur *************
          m_labelTuteur.setVisible(true);
          m_labelTuteur.setBounds(80, 270, 200, 22);
          m_container.add(m_labelTuteur);
          
          m_comboTuteur.setVisible(true);
          m_comboTuteur.setBounds(230, 270, 200, 22);
          m_container.add(m_comboTuteur);
          
          m_listTuteur = Data.GenerateProf();
          for(int i = 0; i < m_listTuteur.size(); i++)
            { //méthode d'insertion dans une liste Ã  faire
             m_comboTuteur.addItem(m_listTuteur.get(i));
            }
        //*****bouton ajouter ******
        m_buttonAjouter = new JButton();
        m_buttonAjouter.setBounds(180, 350 , 200 , 40);
       
       m_buttonAjouter.setText("Ajouter votre projet");
        m_container.add(m_buttonAjouter); 
        m_buttonAjouter.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mouseClicked(java.awt.event.MouseEvent evt) {
               Enregistrer_Projet();}
        });
    }
    
    @Override public void SetWidthHeight()
    {
        m_width = 600;
        m_height = 500;
    }
    //*************************************************************************
    //************************************************************************
    
    // Méthode permettant de mettre Ã  jour la liste d'étudiant
    ArrayList<Etudiant> listEtudiants = new ArrayList<>();
    public void refreshComboEtudiants()
    {
        
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
        
        //m_listEtudiant.setListData(listEtudiants.toArray());
        m_comboEtudiants.setVisible(true);
        m_labelEtudiants.setVisible(true);

        }
    }
    private void Enregistrer_Projet() {
             String nomProjet = m_TextNomPro.getText();
             String description = m_TextObjectif.getText();
             String classe = m_comboClasse.getSelectedItem().toString();

             SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             String dateSoutenance = dateFormat.format(m_ChoixDateSoutenance.getDate());
             int numGroupe = Integer.parseInt(m_TextGroup.getText());
             int IdEtuChefProj = ((Etudiant)(m_comboEtudiants.getSelectedItem())).GetId();
             int IdProfTuteur = ((Professeur)(m_comboTuteur.getSelectedItem())).GetId();
             int IdResp = 1;
             
             Projet currentProjet = new Projet( 0 , nomProjet, IdEtuChefProj, IdProfTuteur, IdResp, dateSoutenance, description, classe, numGroupe);
            if (Data.EnregistrerProjet(currentProjet))
            {
                if ((m_parent != null) && (m_parent instanceof Frame_MainInterface))
                {
                    ((Frame_MainInterface)(m_parent)).Requery();
                }
                m_TextNomPro.setText("");
                m_TextGroup.setText("");
                m_TextObjectif.setText("");
                m_comboEtudiants.setSelectedIndex(-1);
                m_comboTuteur.setSelectedIndex(-1);
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

        buttonGroup1 = new javax.swing.ButtonGroup();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    // End of variables declaration//GEN-END:variables
}
