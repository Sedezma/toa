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

public class Dialog_GererResponsables extends Dialog_Windows {

    private JScrollPane m_scrolltab;
    private JTable m_tabResponsables;
    
    private JButton m_enregistrermodif;
    private JButton m_supprimer;
    
    private JPanel m_dataResponsable;
    
    private JLabel m_lblNomEntreprise;
    private JLabel m_lblPrenom;
    private JLabel m_lblNom;
    private JLabel m_lblMail;
    
    private JTextField m_inputNomEntreprise;
    private JTextField m_inputPrenom;
    private JTextField m_inputNom;
    private JTextField m_inputMail;
    
     private JButton m_butAjouterResponsable;
    
    private ArrayList<ResponsableEntreprise> m_listResponsables;
    
     public Dialog_GererResponsables(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        setTitle("Gérer les responsables");
        m_scrolltab = new JScrollPane();
        m_enregistrermodif = new JButton();
        m_supprimer = new JButton();
        m_tabResponsables = new JTable();
        m_dataResponsable = new JPanel();
        m_lblPrenom = new JLabel();
        m_lblNom = new JLabel();
        m_lblNomEntreprise = new JLabel();
        m_lblMail = new JLabel();
        m_inputPrenom = new JTextField();
        m_inputNom = new JTextField();
        m_inputNomEntreprise = new JTextField();
        m_inputMail = new JTextField();
        m_butAjouterResponsable = new JButton();
        
        m_listResponsables = new ArrayList<>();
        
        m_scrolltab.setBounds(10, 10, 780, 300);
        m_scrolltab.setViewportView(m_tabResponsables);
        m_container.add(m_scrolltab); 
        
        m_tabResponsables.setRowHeight(22);
        m_tabResponsables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_tabResponsables.getTableHeader().setResizingAllowed(false);
        m_tabResponsables.getTableHeader().setReorderingAllowed(false);
        Requery();
        
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
        
        m_dataResponsable.setBounds(10, 370, m_width - 20, 190);
        m_dataResponsable.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataResponsable.setBorder(BorderFactory.createTitledBorder("Ajouter un responsable"));
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_dataResponsable);
        m_dataResponsable.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width - 20, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        m_container.add(m_dataResponsable);
        
        m_lblPrenom.setBounds(115, 40, 50, 30);
        m_lblPrenom.setText("Prénom");
        m_dataResponsable.add(m_lblPrenom);
                
        m_inputPrenom.setBounds(170, 40, 200, 30);
        m_dataResponsable.add(m_inputPrenom);
        
        m_lblNom.setBounds(135, 80, 50, 30);
        m_lblNom.setText("Nom");
        m_dataResponsable.add(m_lblNom);
        
        m_inputNom.setBounds(170, 80, 200, 30);
        m_dataResponsable.add(m_inputNom);
        
        m_lblMail.setBounds(480, 40, 100, 30);
        m_lblMail.setText("Email");
        m_dataResponsable.add(m_lblMail);
        
        m_inputMail.setBounds(520, 40, 200, 30);
        m_dataResponsable.add(m_inputMail);
        
        m_lblNomEntreprise.setBounds(425, 80, 120, 30);
        m_lblNomEntreprise.setText("Nom Entreprise");
        m_dataResponsable.add(m_lblNomEntreprise);
        
        m_inputNomEntreprise.setBounds(520, 80, 200, 30);
        m_dataResponsable.add(m_inputNomEntreprise);
        
        m_butAjouterResponsable.setBounds(m_dataResponsable.getWidth() - 250, 130, 200, 40);
        m_butAjouterResponsable.setText("Ajouter un responsable");
        m_butAjouterResponsable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {EnregistrerResponsable();}
        });
        m_dataResponsable.add(m_butAjouterResponsable);
    }
    
    public void Requery()
    {
        try
        {
            m_listResponsables = Data.GenerateResponsables();
        }
        catch(Exception e){}
        RefreshTable();
    }
    
    public void RefreshTable()
    {
        String  title[] = {"Nom entreprise", "Prénom", "Nom", "Email"};
        Object[][] data = new Object[m_listResponsables.size()][title.length];
        
        try
        {
            for(int i = 0; i < m_listResponsables.size(); i++)
            {
                data[i][0] = m_listResponsables.get(i).GetNomEntreprise();
                data[i][1] = m_listResponsables.get(i).GetPrenom();
                data[i][2] = m_listResponsables.get(i).GetNom();
                data[i][3] = m_listResponsables.get(i).GetMail();
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        JTable tableetud = new JTable(data,title);
        m_tabResponsables.setColumnModel(tableetud.getColumnModel());
        m_tabResponsables.setModel(tableetud.getModel());
    }

    private void Enregistrer_Donnees(java.awt.event.MouseEvent evt) {                   
        ArrayList<ResponsableEntreprise> responsablesToModify = new ArrayList<>();
         for(int ligne =0; ligne < m_tabResponsables.getModel().getRowCount(); ligne++)
         {
             String nomentreprise = m_tabResponsables.getModel().getValueAt(ligne, 0).toString();
             String prenom = m_tabResponsables.getModel().getValueAt(ligne, 1).toString();
             String nom = m_tabResponsables.getModel().getValueAt(ligne, 2).toString();
             String mail = m_tabResponsables.getModel().getValueAt(ligne, 3).toString();
             
             responsablesToModify.add(new ResponsableEntreprise(m_listResponsables.get(ligne).GetId(), nomentreprise, prenom, nom, mail));
         }
         if (Data.EntregistrerListeResponsables(responsablesToModify))
         {
            Requery();
            javax.swing.JOptionPane.showMessageDialog(null, "Données enregistrées");
         }
     }
    
    public void EnregistrerResponsable()
    {
        ResponsableEntreprise currentResp = new ResponsableEntreprise(0 , m_inputNomEntreprise.getText(), m_inputPrenom.getText(), m_inputNom.getText(),  m_inputMail.getText());
        if (Data.EnregistrerResponsable(currentResp))
        {
            m_inputNomEntreprise.setText("");
            m_inputPrenom.setText("");
            m_inputNom.setText("");
            m_inputMail.setText("");
            Requery();
            javax.swing.JOptionPane.showMessageDialog(null,"Responsable enregistré");
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les données");
        }
    }
    
    private void Supprimer_Selection(java.awt.event.MouseEvent evt) {

        Data.SupprimerResponsable(m_listResponsables.get(m_tabResponsables.getSelectedRow()));
        Requery();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1037, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 704, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
