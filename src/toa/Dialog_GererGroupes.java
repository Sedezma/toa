package toa;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class Dialog_GererGroupes  extends Dialog_Windows {

    /*private JScrollPane m_scrolltab;
    private JTable m_tabGroupes;
    
    private JButton m_enregistrermodif;
    private JButton m_supprimer;
    
    
    private JLabel m_lblNumeroGroupe;
    private JLabel m_lblNumeroEleve;
    
    private JTextField m_inputNumeroGroupe;
    private JComboBox<Etudiant> m_comboEleve;
    
    private ArrayList<Etudiant> m_listComboEleve;*/
    
    private JList<Groupe> m_JlistGroupes;
    private JScrollPane m_GroupeScrollpane;
    private JList<Etudiant> m_JlistEtudiants;
    private JScrollPane m_EtudiantScrollpane;
    private JList<Etudiant> m_JlistEtudiantsInGroupe;
    private JScrollPane m_EtudiantInGroupeScrollpane;
    
    private JPanel m_dataGroupe;
    
    private JLabel m_lblNumeroGroupe;
    private JLabel m_lblListeEtudiant;
    private JLabel m_lblListeTtEtudiants;
    
    private JButton m_butAjouterAuGroupe;
    private JButton m_butRetirerDuGroupe;
    private JButton m_butSupprimerGroupe;
    
    private ArrayList<Etudiant> m_listEtudiantInGroupe;
    private ArrayList<Etudiant> m_listEtudiant;
    private ArrayList<Groupe> m_listGroupes;
    private ArrayList<Groupe> m_listGroupesToModify;
    
    private JLabel m_lblGroupe;
    private JTextField m_inputGroupe;
    private JButton m_butAjouterGroupe;
    
    public Dialog_GererGroupes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        setTitle("Gérer les groupes");
        /*m_scrolltab = new JScrollPane();
        m_enregistrermodif = new JButton();
        m_supprimer = new JButton();
        m_tabGroupes = new JTable();
        m_lblNumeroGroupe = new JLabel();
        m_lblNumeroEleve = new JLabel();
        m_inputNumeroGroupe  = new JTextField();
        m_comboEleve = new JComboBox<>();*/
        
        m_JlistGroupes = new JList<>();
        m_GroupeScrollpane = new JScrollPane();
        m_JlistEtudiants = new JList<>();
        m_EtudiantScrollpane = new JScrollPane();
        m_JlistEtudiantsInGroupe = new JList<>();
        m_EtudiantInGroupeScrollpane = new JScrollPane();
        m_butAjouterAuGroupe = new JButton();
        m_butRetirerDuGroupe = new JButton();
        m_butSupprimerGroupe = new JButton();
        m_lblNumeroGroupe = new JLabel();
        m_lblListeEtudiant = new JLabel();
        m_lblListeTtEtudiants = new JLabel();
        m_butAjouterGroupe = new JButton();
        m_lblGroupe = new JLabel();
        m_inputGroupe = new JTextField();
        
        m_dataGroupe = new JPanel();
        
        m_listGroupes = new ArrayList<>();
        m_listEtudiant = new ArrayList<>();
        m_listGroupesToModify = new ArrayList<>();
        m_listEtudiantInGroupe = new ArrayList<>();
        
        m_lblNumeroGroupe.setBounds(10, 5, 200, 22);
        m_lblNumeroGroupe.setText("Liste des groupes");
        m_container.add(m_lblNumeroGroupe);
        
        m_lblListeEtudiant.setBounds(230, 5, 200, 22);
        m_lblListeEtudiant.setText("Liste des étudiants du groupe");
        m_container.add(m_lblListeEtudiant);
        
        m_lblListeTtEtudiants.setBounds(580, 5, 200, 22);
        m_lblListeTtEtudiants.setText("Liste de tous les étudiants");
        m_container.add(m_lblListeTtEtudiants);
        
        m_GroupeScrollpane.setBounds(10, 30, 200, 350);
        m_GroupeScrollpane.setViewportView(m_JlistGroupes);
        m_JlistGroupes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {jListGroupeSelected();}});
        m_container.add(m_GroupeScrollpane);
        
        m_EtudiantInGroupeScrollpane.setBounds(230, 30, 200, 350);
        m_EtudiantInGroupeScrollpane.setViewportView(m_JlistEtudiantsInGroupe);
        m_container.add(m_EtudiantInGroupeScrollpane);
        
        m_EtudiantScrollpane.setBounds(580, 30, 200, 350);
        m_EtudiantScrollpane.setViewportView(m_JlistEtudiants);
        m_container.add(m_EtudiantScrollpane);
        
        m_butAjouterAuGroupe.setBounds(440, 150, 130, 30);
        m_butAjouterAuGroupe.setText("< Ajouter Elève");
        m_butAjouterAuGroupe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {AjouterAuGroupe();}
        });
        m_container.add(m_butAjouterAuGroupe);
        
        m_butRetirerDuGroupe.setBounds(440, 200, 130, 30);
        m_butRetirerDuGroupe.setText("- Retirer Elève");
        m_butRetirerDuGroupe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {RetirerGroupe();}
        });
        m_container.add(m_butRetirerDuGroupe);
        
        m_butSupprimerGroupe.setBounds(440, 250, 130, 30);
        m_butSupprimerGroupe.setText("Effacer Groupe");
        m_butSupprimerGroupe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {SupprimerGroupe();}
        });
        m_container.add(m_butSupprimerGroupe);
        
        m_dataGroupe.setBounds(10, 390, m_width - 20, 170);
        m_dataGroupe.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataGroupe.setBorder(BorderFactory.createTitledBorder("Ajouter un groupe"));
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_dataGroupe);
        m_dataGroupe.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width - 20, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        m_container.add(m_dataGroupe);
        
        m_lblGroupe.setBounds(300, 60, 50, 30);
        m_lblGroupe.setText("Groupe");
        m_dataGroupe.add(m_lblGroupe);
        
        m_inputGroupe.setBounds(350, 60, 80, 30);
        m_dataGroupe.add(m_inputGroupe);
        
        m_butAjouterGroupe.setBounds(280, 110, 200, 40);
        m_butAjouterGroupe.setText("Ajouter un groupe");
        m_butAjouterGroupe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {AjouterGroupe(evt);}
        });
        m_dataGroupe.add(m_butAjouterGroupe);
        
        /*m_listComboEleve = new ArrayList<>();
        
        m_scrolltab.setBounds(10, 10, 780, 300);
        m_scrolltab.setViewportView(m_tabGroupes);
        m_container.add(m_scrolltab); 
        
        m_tabGroupes.setRowHeight(22);
        m_tabGroupes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        m_enregistrermodif.setBounds(m_width / 2 - 220, 320 , 200 , 40);
        m_enregistrermodif.setText("Enregistrer les modifications");
        m_enregistrermodif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {Enregistrer_Donnees(evt);}
        });
        m_container.add(m_enregistrermodif);
        
        m_supprimer.setBounds(m_width / 2 + 20, 320 , 200 , 40);
        m_supprimer.setText("Supprimer la sélection");
        m_supprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {Supprimer_Selection(evt);}
        });
        m_container.add(m_supprimer);
        
        m_dataGroupe.setBounds(10, 370, m_width - 20, 190);
        m_dataGroupe.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataGroupe.setBorder(BorderFactory.createTitledBorder("Ajouter un groupe"));
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_dataGroupe);
        m_dataGroupe.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width - 20, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        m_container.add(m_dataGroupe);
        
        m_lblNumeroGroupe.setBounds(190, 50, 110, 22);
        m_lblNumeroGroupe.setText("Numéro de groupe");
        m_dataGroupe.add(m_lblNumeroGroupe);
                
        m_inputNumeroGroupe.setBounds(300, 50, 100, 22);
        m_dataGroupe.add(m_inputNumeroGroupe);
        
        m_lblNumeroEleve.setBounds(245, 80, 50, 22);
        m_lblNumeroEleve.setText("Etudiant");
        m_dataGroupe.add(m_lblNumeroEleve);
        
        m_comboEleve.setBounds(300, 80, 200, 22);
        m_dataGroupe.add(m_comboEleve);
        
        m_butAjouterGroupe.setBounds(m_dataGroupe.getWidth() - 250, 130, 200, 40);
        m_butAjouterGroupe.setText("Ajouter un groupe");
        m_butAjouterGroupe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {EnregistrerGroupe();}
        });
        m_dataGroupe.add(m_butAjouterGroupe);*/
        
        Requery();
    }
    public void AjouterAuGroupe()
    {
        if (Data.AjouterEtudiantAuGroupe(m_listGroupes.get(m_JlistGroupes.getSelectedIndex()).GetNumGroupe(), m_listEtudiant.get(m_JlistEtudiants.getSelectedIndex()).GetId()))
        {
            jListGroupeSelected();
        }
        else
        {
           javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'insérer l'étudiant"); 
        }
    }
    public void Requery()
    {
        try
        {
            // Liste des groupes
            m_listGroupes.clear();
            m_listGroupesToModify.clear();
            m_listGroupesToModify = Data.GenerateGroupes();
            for(int i = 0; i < m_listGroupesToModify.size(); i++)
                {
                   boolean goOn = true;
                   for(int u = 0; u < m_listGroupes.size(); u++)
                   {
                       if (((Groupe)m_listGroupes.get(u)).GetNumGroupe() == m_listGroupesToModify.get(i).GetNumGroupe())
                       {
                             goOn = false;
                             break;
                       }
                   }
                   if (goOn)
                   {
                        m_listGroupes.add(m_listGroupesToModify.get(i));
                   }
                }
            m_listEtudiant.clear();
            m_listEtudiant = Data.GenerateEtudiants();
            
            DefaultListModel listModelEtudiants = new DefaultListModel();
            m_JlistEtudiantsInGroupe.setModel(listModelEtudiants);
            
        }
        catch(Exception e){}
        RefreshGroupList();
    }
    
    private void jListGroupeSelected() {
        m_listEtudiantInGroupe.clear();
        m_listEtudiantInGroupe = Data.GenerateEtudiantsInGroupe(m_listGroupes.get(m_JlistGroupes.getSelectedIndex()).GetNumGroupe());
        
        DefaultListModel listModelEtudiants = new DefaultListModel();
        try
        {
            for(int i = 0; i < m_listEtudiantInGroupe.size(); i++)
            {
                listModelEtudiants.addElement(m_listEtudiantInGroupe.get(i));
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        m_JlistEtudiantsInGroupe.setModel(listModelEtudiants);
    }
    
    public void RefreshGroupList()
    {
        DefaultListModel listModel = new DefaultListModel();
        try
        {
            for(int i = 0; i < m_listGroupes.size(); i++)
            {
                listModel.addElement(m_listGroupes.get(i));
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
       
        m_JlistGroupes.setModel(listModel);
        
        DefaultListModel listModelEtudiants = new DefaultListModel();
        try
        {
            for(int i = 0; i < m_listEtudiant.size(); i++)
            {
                listModelEtudiants.addElement(m_listEtudiant.get(i));
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        m_JlistEtudiants.setModel(listModelEtudiants);
    }
    
    private void Enregistrer_Groupe(java.awt.event.MouseEvent evt) {                   
        ArrayList<Groupe> groupeToModify = new ArrayList<>();
        try
        {
            //for(int ligne =0; ligne < m_tabGroupes.getModel().getRowCount(); ligne++)
            {
                //int numeroGroupe = Integer.parseInt(m_tabGroupes.getModel().getValueAt(ligne, 0).toString());
                //int numeroEleve = Integer.parseInt(m_tabGroupes.getModel().getValueAt(ligne, 1).toString());

                //groupeToModify.add(new Groupe(m_listGroupes.get(ligne).GetId(), numeroGroupe, numeroEleve));
            }
            if (Data.EntregistrerListeGroupe(groupeToModify))
            {
               Requery();
               javax.swing.JOptionPane.showMessageDialog(null, "Données enregistrées");
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Impossible d'enregistrer les données");
            }
        }
        catch(Exception exc)
        {
                javax.swing.JOptionPane.showMessageDialog(null, "Impossible d'enregistrer les données" + exc);
        }
     }
    
      private void AjouterGroupe(java.awt.event.MouseEvent evt) { 
        try
        {
            if (isNumeric(m_inputGroupe.getText()))
            {
                try
                {
                    DefaultListModel listModel = new DefaultListModel();
                    for(int i = 0; i < m_listGroupes.size(); i++)
                    {
                        listModel.addElement(m_listGroupes.get(i));
                    }
                    m_listGroupes.add(new Groupe(0, Integer.parseInt(m_inputGroupe.getText()), 0));
                    m_JlistGroupes.setModel(listModel);

                }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}

               
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null, "Impossible d'ajouter un groupe");
            }
        }
        catch(Exception exc)
        {
           javax.swing.JOptionPane.showMessageDialog(null, "Impossible d'enregistrer les données" + exc);
        }
      }
    
    public void RetirerGroupe()
    {
        try
        {
            if (Data.RetirerEtudiantAuGroupe(m_JlistGroupes.getSelectedValue().GetNumGroupe(), m_listEtudiantInGroupe.get(m_JlistEtudiantsInGroupe.getSelectedIndex()).GetId()))
            {
                jListGroupeSelected();
            }
            else
            {
               javax.swing.JOptionPane.showMessageDialog(null,"Impossible de retirer l'étudiant"); 
            }
            /*int idetudiant = ((Etudiant)(m_comboEleve.getSelectedItem())).GetId();
            Groupe currentgroup = new Groupe(0 , Integer.parseInt(m_inputNumeroGroupe.getText()), idetudiant);
            if (Data.EnregistrerGroupe(currentgroup))
            {
                m_inputNumeroGroupe.setText("");
                Requery();
                javax.swing.JOptionPane.showMessageDialog(null,"Membre du groupe enregistré");
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les données");
            }*/
        }catch(Exception e)
        {
             javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les données" + e);
        }
    }
    
    public void SupprimerGroupe()
    { 
        try
        {
            
            if (Data.SupprimerGroupe(m_listGroupes.get(m_JlistGroupes.getSelectedIndex()).GetNumGroupe()))
            {
                //jListGroupeSelected();
                Requery();
            }
            else
            {
               javax.swing.JOptionPane.showMessageDialog(null,"Impossible de supprimer le groupe"); 
            }
        }catch(Exception e)
        {
             javax.swing.JOptionPane.showMessageDialog(null,"Impossible de supprimer le groupe" + e);
        }
    }
    
    private void Supprimer_Selection(java.awt.event.MouseEvent evt) {

        //Data.SupprimerMembreGroupe(m_listGroupes.get(m_tabGroupes.getSelectedRow()));
        Requery();
    }

    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
     @Override public void SetWidthHeight()
    {
        m_width = 800;
        m_height = 600;
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 905, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 666, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
