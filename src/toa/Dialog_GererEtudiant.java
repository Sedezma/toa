package toa;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class Dialog_GererEtudiant extends Dialog_Windows {
    
    private JScrollPane m_scrolltab;
    private JTable m_tabEtudiants;
    
    private JButton m_enregistrermodif;
    private JButton m_supprimer;
    
    private JPanel m_dataEtudiant;
    
    private JLabel m_lblPrenom;
    private JLabel m_lblNom;
    private JLabel m_lblMail;
    private JLabel m_lblClasse;
    private JLabel m_lblPromotion;
    
    private JTextField m_inputPrenom;
    private JTextField m_inputNom;
    private JTextField m_inputMail;
    private JTextField m_inputClasse;
    private JTextField m_inputPromotion;
    
    private JButton m_butAjouterEtudiant;
    
    private ArrayList<Etudiant> m_listEtudiant;
    
    public Dialog_GererEtudiant(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        setTitle("Gérer les étudiants");
        m_scrolltab = new JScrollPane();
        m_enregistrermodif = new JButton();
        m_supprimer = new JButton();
        m_tabEtudiants = new JTable();
        m_dataEtudiant = new JPanel();
        m_lblPrenom = new JLabel();
        m_lblNom = new JLabel();
        m_lblMail = new JLabel();
        m_lblClasse = new JLabel();
        m_lblPromotion = new JLabel();
        m_inputPrenom = new JTextField();
        m_inputNom = new JTextField();
        m_inputMail = new JTextField();
        m_inputClasse = new JTextField();
        m_inputPromotion = new JTextField();
        m_butAjouterEtudiant = new JButton();
        
        m_listEtudiant = new ArrayList<>();
        
        m_scrolltab.setBounds(10, 10, 780, 300);
        m_scrolltab.setViewportView(m_tabEtudiants);
        m_container.add(m_scrolltab); 
        
        m_tabEtudiants.setRowHeight(22);
        m_tabEtudiants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_tabEtudiants.getTableHeader().setResizingAllowed(false);
        m_tabEtudiants.getTableHeader().setReorderingAllowed(false);
        Requery();
        
        m_enregistrermodif.setBounds(m_width / 2 - 220, 320 , 200 , 40);
        m_enregistrermodif.setText("Enregistrer les modifications");
        m_enregistrermodif.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {Enregistrer_Donnees(evt);}
        });
        m_container.add(m_enregistrermodif);
        
        m_supprimer.setBounds(m_width / 2 + 20, 320 , 200 , 40);
        m_supprimer.setText("Supprimer la sélection");
        m_supprimer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {Supprimer_Selection(evt);}
        });
        m_container.add(m_supprimer);
        
        m_dataEtudiant.setBounds(10, 370, m_width - 20, 190);
        m_dataEtudiant.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataEtudiant.setBorder(BorderFactory.createTitledBorder("Ajouter un étudiant"));
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_dataEtudiant);
        m_dataEtudiant.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width - 20, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        m_container.add(m_dataEtudiant);
        
        m_lblPrenom.setBounds(120, 30, 50, 30);
        m_lblPrenom.setText("Prénom");
        m_dataEtudiant.add(m_lblPrenom);
                
        m_inputPrenom.setBounds(170, 30, 200, 30);
        m_dataEtudiant.add(m_inputPrenom);
        
        m_lblNom.setBounds(120, 70, 50, 30);
        m_lblNom.setText("Nom");
        m_dataEtudiant.add(m_lblNom);
        
        m_inputNom.setBounds(170, 70, 200, 30);
        m_dataEtudiant.add(m_inputNom);
        
        m_lblMail.setBounds(120, 110, 50, 30);
        m_lblMail.setText("Email");
        m_dataEtudiant.add(m_lblMail);
        
        m_inputMail.setBounds(170, 110, 250, 30);
        m_dataEtudiant.add(m_inputMail);
        
        m_lblClasse.setBounds(470, 30, 50, 30);
        m_lblClasse.setText("Classe");
        m_dataEtudiant.add(m_lblClasse);
        
        m_inputClasse.setBounds(520, 30, 100, 30);
        m_dataEtudiant.add(m_inputClasse);
        
        m_lblPromotion.setBounds(450, 70, 70, 30);
        m_lblPromotion.setText("Promotion");
        m_dataEtudiant.add(m_lblPromotion);
        
        m_inputPromotion.setBounds(520, 70, 100, 30);
        m_dataEtudiant.add(m_inputPromotion);
        
        m_butAjouterEtudiant.setBounds(m_dataEtudiant.getWidth() - 250, 130, 200, 40);
        m_butAjouterEtudiant.setText("Ajouter un étudiant");
        m_butAjouterEtudiant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {EnregistrerEtudiant();}
        });
        m_dataEtudiant.add(m_butAjouterEtudiant);
    }
    
    public void Requery()
    {
        m_listEtudiant = Data.GenerateEtudiants();
        RefreshTable();
    }
    
    public void EnregistrerEtudiant()
    {
        Etudiant currentEtudiant = new Etudiant(0 , m_inputPrenom.getText(), m_inputNom.getText(), m_inputMail.getText(), m_inputClasse.getText(), m_inputPromotion.getText());
        if (Data.EnregistrerEtudiant(currentEtudiant))
        {
            m_inputPrenom.setText("");
            m_inputNom.setText("");
            m_inputMail.setText("");
            m_inputClasse.setText("");
            m_inputPromotion.setText("");
            Requery();
            javax.swing.JOptionPane.showMessageDialog(null,"Etudiant enregistré");
        }
    }
    
    public void RefreshTable()
    {
        String  title[] = {"Prénom", "Nom", "Mail", "Classe", "Promotion"};
        Object[][] data = new Object[m_listEtudiant.size()][title.length];
        
        try
        {
            for(int i = 0; i < m_listEtudiant.size(); i++)
            {
                data[i][0] = m_listEtudiant.get(i).GetPrenom();
                data[i][1] = m_listEtudiant.get(i).GetNom();
                data[i][2] = m_listEtudiant.get(i).GetMail();
                data[i][3] = m_listEtudiant.get(i).GetClasse();
                data[i][4] = m_listEtudiant.get(i).GetPromotion();
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        JTable tableetud = new JTable(data,title);
        m_tabEtudiants.setColumnModel(tableetud.getColumnModel());
        m_tabEtudiants.setModel(tableetud.getModel());
    }
    
     private void Enregistrer_Donnees(java.awt.event.MouseEvent evt) {
         ArrayList<Etudiant> etudiantsToModify = new ArrayList<>();
         for(int ligne =0; ligne < m_tabEtudiants.getModel().getRowCount(); ligne++)
         {
             String prenom = m_tabEtudiants.getModel().getValueAt(ligne, 0).toString();
             String nom = m_tabEtudiants.getModel().getValueAt(ligne, 1).toString();
             String mail = m_tabEtudiants.getModel().getValueAt(ligne, 2).toString();
             String classe = m_tabEtudiants.getModel().getValueAt(ligne, 3).toString();
             String promotion = m_tabEtudiants.getModel().getValueAt(ligne, 4).toString();
             
             etudiantsToModify.add(new Etudiant(m_listEtudiant.get(ligne).GetId(), prenom, nom, mail, classe, promotion));
         }
         if (Data.EntregistrerListeEtudiants(etudiantsToModify))
         {
            Requery();
            javax.swing.JOptionPane.showMessageDialog(null, "Données enregistrées");
         }
        //javax.swing.JOptionPane.showMessageDialog(null, m_tabEtudiants.getModel().getValueAt(m_tabEtudiants.getSelectedRow(), m_tabEtudiants.getSelectedColumn()));
     }
    
    private void Supprimer_Selection(java.awt.event.MouseEvent evt) {
        Data.SupprimerEtudiant(m_listEtudiant.get(m_tabEtudiants.getSelectedRow()));
        Requery();
        //m_listEtudiant.remove(m_tabEtudiants.getSelectedRow());
        //RefreshTable();
    }

    @Override public void SetWidthHeight()
    {
        m_width = 800;
        m_height = 600;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 932, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 652, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
