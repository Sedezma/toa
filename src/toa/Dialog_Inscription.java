package toa;

import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Dialog_Inscription extends Dialog_Windows {
    
    private JLabel m_lblTitre;
    private JLabel m_lblNom;
    private JLabel m_lblPrenom;
    private JLabel m_lblLogin;
    private JLabel m_lblMdp;
    private JLabel m_lblStatus;
    
    private JTextField m_inputNom;
    private JTextField m_inputPrenom;
    private JTextField m_inputLogin;
    private JPasswordField m_inputMdp;
    private JComboBox<Status> m_comboStatus;
    
    private JButton m_BtnInscription;
    
    
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
    

    public Dialog_Inscription(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
        this.setTitle("Inscription");
        
        m_lblTitre = new JLabel();
        m_lblNom = new JLabel();
        m_lblPrenom = new JLabel();
        m_lblLogin = new JLabel();
        m_lblMdp = new JLabel();
        m_lblStatus = new JLabel();
        
        m_inputNom = new JTextField();
        m_inputPrenom = new JTextField();
        m_inputLogin = new JTextField();
        m_inputMdp = new JPasswordField();
        m_comboStatus = new JComboBox();
        
        m_BtnInscription = new JButton();
        
        m_lblTitre.setText("INSCRIPTION");
        m_lblTitre.setBounds(this.getWidth() / 2 - 40, 5, 100,30);
        m_container.add(m_lblTitre);
        
        m_lblNom.setText("Nom");
        m_lblNom.setBounds(this.getWidth() / 2 - 100, 50, 100,30);
        m_container.add(m_lblNom);
        
        m_lblPrenom.setText("Prénom");
        m_lblPrenom.setBounds(this.getWidth() / 2 - 115, 90, 100, 30);
        m_container.add(m_lblPrenom);
        
        m_lblLogin.setText("Adresse Mail");
        m_lblLogin.setBounds(this.getWidth() / 2 - 140, 130, 100, 30);
        m_container.add(m_lblLogin);
        
        m_lblMdp.setText("Mot de passe");
        m_lblMdp.setBounds(this.getWidth() / 2 - 145, 170, 100, 30);
        m_container.add(m_lblMdp);
        
        m_lblStatus.setText("Status");
        m_lblStatus.setBounds(this.getWidth() / 2 - 105, 210, 150, 30);
        m_container.add(m_lblStatus);
                
        m_inputNom.setBounds(this.getWidth() / 2 - 30, 50, 150, 30);
        m_container.add(m_inputNom);
        
        m_inputPrenom.setBounds(this.getWidth() / 2 - 30, 90, 150, 30);
        m_container.add(m_inputPrenom);
        
        m_inputLogin.setBounds(this.getWidth() / 2 - 30, 130, 150, 30);
        m_container.add(m_inputLogin);
        
        m_inputMdp.setBounds(this.getWidth() / 2 - 30, 170, 150, 30);
        m_container.add(m_inputMdp);
        
        m_comboStatus.setBounds(this.getWidth() / 2 - 30, 210, 150, 30);
        m_container.add(m_comboStatus);
        
        m_BtnInscription.setText("Inscription");
        m_BtnInscription.setBounds(this.getWidth() / 2 - 75, 270, 150, 40);
        m_BtnInscription.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {BtnInscription();}
        });
        m_container.add(m_BtnInscription);
        
        m_comboStatus.addItem(Status.Etudiant);
        m_comboStatus.addItem(Status.Enseignant);
        m_comboStatus.addItem(Status.Responsable);
    }
    
    private void BtnInscription()
    {
        String nom = m_inputNom.getText();
        String prenom = m_inputPrenom.getText();
        String mail = m_inputLogin.getText();
        String motdepasse = m_inputMdp.getText();
        String status = "" + m_comboStatus.getItemAt(m_comboStatus.getSelectedIndex());
        
        Utilisateur uUserToRegister = new Utilisateur(-1, nom, prenom, mail, motdepasse, status);
        
        if (CheckParameters())
        {
            if (Data.Register(uUserToRegister))
            {
                this.setVisible(false);
                javax.swing.JOptionPane.showMessageDialog(null,"Utilisateur enregistré");
            }
            else
            {
                javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'inscrire l'utilisateur");
            }
        }
        else
        {
           javax.swing.JOptionPane.showMessageDialog(null,"Les champs doivent être tous renseignés (de longueur > 3)");
        }
    }
    
    private boolean CheckParameters()
    {
        if (m_inputNom.getText().length() > 3 && m_inputPrenom.getText().length() > 3 && m_inputLogin.getText().length() > 3 && m_inputMdp.getText().length() > 3)
        {
            return true;
        }
        return false;
    }

    @Override public void SetWidthHeight()
    {
        m_width = 500;
        m_height = 350;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
