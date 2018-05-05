package toa;

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

public class Dialog_GererProfesseur extends Dialog_Windows {

    private JScrollPane m_scrolltab;
    private JTable m_tabProfesseurs;
    
    private JButton m_enregistrermodif;
    private JButton m_supprimer;
    
    private JPanel m_dataProfesseur;
    
    private JLabel m_lblPrenom;
    private JLabel m_lblNom;
    private JLabel m_lblMatiere;
    private JLabel m_lblMail;
    
    private JTextField m_inputPrenom;
    private JTextField m_inputNom;
    private JTextField m_inputMatiere;
    private JTextField m_inputMail;
    
    private JButton m_butAjouterProfesseur;
    
    private ArrayList<Professeur> m_listProfesseurs;
    
    public Dialog_GererProfesseur(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        setTitle("Gérer les professeurs");
        m_scrolltab = new JScrollPane();
        m_enregistrermodif = new JButton();
        m_supprimer = new JButton();
        m_tabProfesseurs = new JTable();
        m_dataProfesseur = new JPanel();
        m_lblPrenom = new JLabel();
        m_lblNom = new JLabel();
        m_lblMatiere = new JLabel();
        m_lblMail = new JLabel();
        m_inputPrenom = new JTextField();
        m_inputNom = new JTextField();
        m_inputMatiere = new JTextField();
        m_inputMail = new JTextField();
        m_butAjouterProfesseur = new JButton();
        
        m_listProfesseurs = new ArrayList<>();
        
        m_scrolltab.setBounds(10, 10, 780, 300);
        m_scrolltab.setViewportView(m_tabProfesseurs);
        m_container.add(m_scrolltab); 
        
        m_tabProfesseurs.setRowHeight(22);
        m_tabProfesseurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_tabProfesseurs.getTableHeader().setResizingAllowed(false);
        m_tabProfesseurs.getTableHeader().setReorderingAllowed(false);
        
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
        
        m_dataProfesseur.setBounds(10, 370, m_width - 20, 190);
        m_dataProfesseur.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataProfesseur.setBorder(BorderFactory.createTitledBorder("Ajouter un professeur"));
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_dataProfesseur);
        m_dataProfesseur.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width - 20, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        m_container.add(m_dataProfesseur);
        
        m_lblPrenom.setBounds(120, 30, 50, 30);
        m_lblPrenom.setText("Prénom");
        m_dataProfesseur.add(m_lblPrenom);
                
        m_inputPrenom.setBounds(170, 30, 200, 30);
        m_dataProfesseur.add(m_inputPrenom);
        
        m_lblNom.setBounds(120, 70, 50, 30);
        m_lblNom.setText("Nom");
        m_dataProfesseur.add(m_lblNom);
        
        m_inputNom.setBounds(170, 70, 200, 30);
        m_dataProfesseur.add(m_inputNom);
        
        m_lblMail.setBounds(120, 110, 50, 30);
        m_lblMail.setText("Email");
        m_dataProfesseur.add(m_lblMail);
        
        m_inputMail.setBounds(170, 110, 250, 30);
        m_dataProfesseur.add(m_inputMail);
        
        m_lblMatiere.setBounds(470, 30, 50, 30);
        m_lblMatiere.setText("Classe");
        m_dataProfesseur.add(m_lblMatiere);
        
        m_inputMatiere.setBounds(520, 30, 100, 30);
        m_dataProfesseur.add(m_inputMatiere);
        
        m_butAjouterProfesseur.setBounds(m_dataProfesseur.getWidth() - 250, 130, 200, 40);
        m_butAjouterProfesseur.setText("Ajouter un professeur");
        m_butAjouterProfesseur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {EnregistrerProfesseur();}
        });
        m_dataProfesseur.add(m_butAjouterProfesseur);
        
        Requery();
    }
    
    public void Requery()
    {
        try
        {
            m_listProfesseurs = Data.GenerateProf(); 
        }
        catch(Exception e){}
        RefreshTable();
    }
    
    public void EnregistrerProfesseur()
    {
        Professeur currentProf = new Professeur(0 , m_inputPrenom.getText(), m_inputNom.getText(), m_inputMatiere.getText(), m_inputMail.getText());
        if (Data.EnregistrerProfesseur(currentProf))
        {
            m_inputPrenom.setText("");
            m_inputNom.setText("");
            m_inputMatiere.setText("");
            m_inputMail.setText("");
            Requery();
            javax.swing.JOptionPane.showMessageDialog(null,"Etudiant enregistré");
        }
    }
    
    public void RefreshTable()
    {
        String  title[] = {"Prenom", "Nom", "Matiere", "Email"};
        Object[][] data = new Object[m_listProfesseurs.size()][title.length];
        
        try
        {
            for(int i = 0; i < m_listProfesseurs.size(); i++)
            {
                data[i][0] = m_listProfesseurs.get(i).GetPrenom();
                data[i][1] = m_listProfesseurs.get(i).GetNom();
                data[i][2] = m_listProfesseurs.get(i).GetMatiere();
                data[i][3] = m_listProfesseurs.get(i).GetMail();
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        JTable tableetud = new JTable(data,title);
        m_tabProfesseurs.setColumnModel(tableetud.getColumnModel());
        m_tabProfesseurs.setModel(tableetud.getModel());
    }
    
    private void Enregistrer_Donnees(java.awt.event.MouseEvent evt) {                   
        ArrayList<Professeur> professeursToModify = new ArrayList<>();
         for(int ligne =0; ligne < m_tabProfesseurs.getModel().getRowCount(); ligne++)
         {
             String prenom = m_tabProfesseurs.getModel().getValueAt(ligne, 0).toString();
             String nom = m_tabProfesseurs.getModel().getValueAt(ligne, 1).toString();
             String matiere = m_tabProfesseurs.getModel().getValueAt(ligne, 2).toString();
             String mail = m_tabProfesseurs.getModel().getValueAt(ligne, 3).toString();
             
             professeursToModify.add(new Professeur(m_listProfesseurs.get(ligne).GetId(), prenom, nom, matiere, mail));
         }
         if (Data.EntregistrerListeProfesseurs(professeursToModify))
         {
            Requery();
            javax.swing.JOptionPane.showMessageDialog(null, "Données enregistrées");
         }
     }
    
    private void Supprimer_Selection(java.awt.event.MouseEvent evt) {
        Data.SupprimerEnseignant(m_listProfesseurs.get(m_tabProfesseurs.getSelectedRow()));
        Requery();
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
            .addGap(0, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 584, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
