package toa;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Dialog_ParametreCompte extends Dialog_Windows {

    
    private Utilisateur m_uCurrentUtilisateur;
    
    private JLabel m_lblTitre;
    private JLabel m_lblNom;
    private JLabel m_lblPrenom;
    private JLabel m_lblLogin;
    private JLabel m_lblMdp;
    private JLabel m_lblNewMdp;
    private JLabel m_lblRepeatMdp;
    private JLabel m_lblStatus;
    
    private JTextField m_inputNom;
    private JTextField m_inputPrenom;
    private JTextField m_inputLogin;
    private JPasswordField m_inputMdp;
    private JPasswordField m_inputNewMdp;
    private JPasswordField m_inputRepeatMdp;
    private JComboBox<String> m_comboStatus;
    
    private JButton m_BtnModification;
    private JButton m_BtnAnnuler;
    
    public enum Status {
        Etudiant ("Etudiant"),
        Enseignant ("Enseignant"),
        Responsable ("Responsable");

        private String name = "";

        Status(String name){
          this.name = name;
        }

        public String toString(){
          return name;
        }
    }
    
    public Dialog_ParametreCompte(java.awt.Frame parent, boolean modal, Utilisateur uCurrentUtilisateur) {
        super(parent, modal);
        
        m_uCurrentUtilisateur = uCurrentUtilisateur;
        this.setTitle("Paramètre du compte");
        
        m_lblTitre = new JLabel();
        m_lblNom = new JLabel();
        m_lblPrenom = new JLabel();
        m_lblLogin = new JLabel();
        m_lblMdp = new JLabel();
        m_lblNewMdp = new JLabel();
        m_lblRepeatMdp = new JLabel();
        m_lblStatus = new JLabel();
        
        m_inputNom = new JTextField();
        m_inputPrenom = new JTextField();
        m_inputLogin = new JTextField();
        m_inputMdp = new JPasswordField();
        m_inputNewMdp = new JPasswordField();
        m_inputRepeatMdp = new JPasswordField();
        m_comboStatus = new JComboBox();
        
        m_BtnModification = new JButton();
        m_BtnAnnuler = new JButton();
        
        m_lblTitre.setText("MODIFICATION");
        m_lblTitre.setBounds(this.getWidth() / 2 - 40, 5, 100,30);
        m_container.add(m_lblTitre);
        
        m_lblNom.setText("Nom*");
        m_lblNom.setBounds(this.getWidth() / 2 - 100, 50, 100,30);
        m_container.add(m_lblNom);
        
        m_lblPrenom.setText("Prénom*");
        m_lblPrenom.setBounds(this.getWidth() / 2 - 115, 90, 100, 30);
        m_container.add(m_lblPrenom);
        
        m_lblLogin.setText("Adresse Mail*");
        m_lblLogin.setBounds(this.getWidth() / 2 - 140, 130, 100, 30);
        m_container.add(m_lblLogin);
        
        m_lblMdp.setText("Mot de passe*");
        m_lblMdp.setBounds(this.getWidth() / 2 - 145, 170, 100, 30);
        m_container.add(m_lblMdp);
        
        m_lblNewMdp.setText("Nouveau mot de passe");
        m_lblNewMdp.setBounds(this.getWidth() / 2 - 195, 210, 200, 30);
        m_container.add(m_lblNewMdp);
        
        m_lblRepeatMdp.setText("Répéter nouveau mot de passe");
        m_lblRepeatMdp.setBounds(this.getWidth() / 2 - 240, 250, 200, 30);
        m_container.add(m_lblRepeatMdp);
        
        m_lblStatus.setText("Status*");
        m_lblStatus.setBounds(this.getWidth() / 2 - 105, 290, 150, 30);
        m_container.add(m_lblStatus);
                
        m_inputNom.setBounds(this.getWidth() / 2 - 30, 50, 180, 30);
        m_container.add(m_inputNom);
        
        m_inputPrenom.setBounds(this.getWidth() / 2 - 30, 90, 180, 30);
        m_container.add(m_inputPrenom);
        
        m_inputLogin.setBounds(this.getWidth() / 2 - 30, 130, 180, 30);
        m_container.add(m_inputLogin);
        
        m_inputMdp.setBounds(this.getWidth() / 2 - 30, 170, 180, 30);
        m_container.add(m_inputMdp);
        
        m_inputNewMdp.setBounds(this.getWidth() / 2 - 30, 210, 180, 30);
        m_container.add(m_inputNewMdp);
        
        m_inputRepeatMdp.setBounds(this.getWidth() / 2 - 30, 250, 180, 30);
        m_container.add(m_inputRepeatMdp);
        
        m_comboStatus.setBounds(this.getWidth() / 2 - 30, 290, 150, 30);
        m_container.add(m_comboStatus);
        
        m_BtnModification.setText("Modifier");
        m_BtnModification.setBounds(this.getWidth() / 2 - 170, 345, 150, 40);
        m_BtnModification.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {BtnModification();}
        });
        m_container.add(m_BtnModification);
        
        m_BtnAnnuler.setText("Annuler");
        m_BtnAnnuler.setBounds(this.getWidth() / 2 + 20, 345, 150, 40);
        m_BtnAnnuler.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {BtnAnnuler();}
        });
        m_container.add(m_BtnAnnuler);
        
        m_comboStatus.addItem(Status.Etudiant.toString());
        m_comboStatus.addItem(Status.Enseignant.toString());
        m_comboStatus.addItem(Status.Responsable.toString());
        
        if (m_uCurrentUtilisateur.GetStatus().toLowerCase().equals("admin"))
        {
            m_comboStatus.addItem("Admin");
        }
        
        
        InitializeFields();
    }
    
    private void InitializeFields()
    {
        if (m_uCurrentUtilisateur != null)
        {
            m_inputNom.setText(m_uCurrentUtilisateur.GetNom());
            m_inputPrenom.setText(m_uCurrentUtilisateur.GetPrenom());
            m_inputLogin.setText(m_uCurrentUtilisateur.GetLogin());
            for(int i =0; i < m_comboStatus.getItemCount(); i++)
            {
                if(m_comboStatus.getItemAt(i).equals(m_uCurrentUtilisateur.GetStatus()))
                {
                    m_comboStatus.setSelectedIndex(i);
                }
            }
        }
        else
        {
            
        }
    }
    
    private void BtnModification()
    {
        if (CheckParameters())
        {
            if (this.getParent() != null)
            {
                if (m_inputNewMdp.getText().length() == 0)
                {
                
                    m_uCurrentUtilisateur.SetNom(m_inputNom.getText());
                    m_uCurrentUtilisateur.SetPrenom(m_inputPrenom.getText());
                    m_uCurrentUtilisateur.SetLogin(m_inputLogin.getText());
                    m_uCurrentUtilisateur.SetStatus(m_comboStatus.getItemAt(m_comboStatus.getSelectedIndex()).toString());
         
                    if (Data.ModifyUser(m_uCurrentUtilisateur))
                    {
                        ((Frame_MainInterface)this.getParent()).SetUtilisateur(m_uCurrentUtilisateur);
                        javax.swing.JOptionPane.showMessageDialog(null,"Données enregistrées");
                        this.setVisible(false);
                    }
                    else
                    {
                        javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les données");
                    }
                
                }
                else
                {
                    m_uCurrentUtilisateur.SetNom(m_inputNom.getText());
                    m_uCurrentUtilisateur.SetPrenom(m_inputPrenom.getText());
                    m_uCurrentUtilisateur.SetLogin(m_inputLogin.getText());
                    m_uCurrentUtilisateur.SetPassword(m_inputNewMdp.getText());
                    m_uCurrentUtilisateur.SetStatus(m_comboStatus.getItemAt(m_comboStatus.getSelectedIndex()).toString());
                    
                    if (Data.ModifyUser(m_uCurrentUtilisateur))
                    {
                        ((Frame_MainInterface)this.getParent()).SetUtilisateur(m_uCurrentUtilisateur);
                         javax.swing.JOptionPane.showMessageDialog(null,"Données enregistrées");
                         this.setVisible(false);
                    }
                    else
                    {
                        javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les données");
                    }
                }    
            }
        }
    }
    
    private boolean CheckParameters()
    {
        if (m_inputNewMdp.getText().length() == 0 && m_inputRepeatMdp.getText().length() == 0)
        {
            if (m_inputNom.getText().length() > 3 && m_inputPrenom.getText().length() > 3 && m_inputLogin.getText().length() > 3 && m_inputMdp.getText().length() > 3)
            {
                if (m_inputMdp.getText().equals(m_uCurrentUtilisateur.GetPassword()))
                {
                    return true;
                }
                else
                {
                    javax.swing.JOptionPane.showMessageDialog(null,"Le mot de passe actuel ne correspond pas");
                }
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Les champs doivent être tous renseignés (de longueur > 3)");
            }
        }
        else 
        {
            if (m_inputNewMdp.getText().length() > 3 && m_inputRepeatMdp.getText().length() > 3)
            {
                if (m_inputNom.getText().length() > 3 && m_inputPrenom.getText().length() > 3 && m_inputLogin.getText().length() > 3 && m_inputMdp.getText().length() > 3)
                {
                      if (m_inputMdp.getText().equals(m_uCurrentUtilisateur.GetPassword()))
                      {
                          if (m_inputNewMdp.getText().equals(m_inputRepeatMdp.getText()))
                          {
                                return true;
                          }
                          else
                          {
                                javax.swing.JOptionPane.showMessageDialog(null,"Les nouveaux mots de passe ne correspondent pas)");
                          }
                      }
                      else
                      {
                        javax.swing.JOptionPane.showMessageDialog(null,"Le mot de passe actuel ne correspond pas");
                      }
                }
                else
                {
                    javax.swing.JOptionPane.showMessageDialog(null,"Les champs doivent être tous renseignés (de longueur > 3)");
                }
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Les nouveaux mot de passe doivent être renseignés (de longueur > 3)");
            }
        }
        return false;
    }
    
    private void BtnAnnuler()
    {
        this.setVisible(false);
    }

    @Override public void SetWidthHeight()
    {
        m_width = 500;
        m_height = 450;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 454, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
