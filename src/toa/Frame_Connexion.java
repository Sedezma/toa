
package toa;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Frame_Connexion extends javax.swing.JFrame {

    private int m_globalheight;
    private int m_globalwidth;
    protected JPanel m_container;
    protected int m_width;
    protected int m_height;
    
    private JLabel m_lblLogin;
    private JLabel m_lblMdp;
    private JLabel m_lblInscription;
    private JTextField m_inputLogin;
    private JPasswordField m_inputMdp;
    private JButton m_BtnConnexion;
    
    public Frame_Connexion() {
        initComponents();
        
        this.setTitle("Connexion");
        m_width = 500;
        m_height = 200;
        setSize(m_width, m_height);
        setResizable(false);
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e){}
        
        try
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle bounds = ge.getMaximumWindowBounds();
            m_globalheight = (int)bounds.height;
            m_globalwidth  = (int)bounds.width;
        }
        catch(Exception e){
            Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            m_globalheight = (int)dimension.getHeight();
            m_globalwidth  = (int)dimension.getWidth();
        }
        this.setLocation(m_globalwidth / 2 - this.getWidth() / 2, m_globalheight / 2 - this.getHeight() / 2);
        
        m_lblLogin = new JLabel();
        m_lblMdp = new JLabel();
        m_inputLogin = new JTextField();
        m_inputMdp = new JPasswordField();
        m_BtnConnexion = new JButton();
        m_lblInscription = new JLabel();
        
        m_container = new JPanel();
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_container);
        m_container.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_height, Short.MAX_VALUE)
        );
        m_container.setBounds(0, 0, m_width, m_height);
        add(m_container);
        
        m_lblLogin.setText("Login");
        m_lblLogin.setBounds(100, 15,100,30);
        m_container.add(m_lblLogin);
        
        m_lblMdp.setText("Mot de passe");
        m_lblMdp.setBounds(100, 55,100,30);
        m_container.add(m_lblMdp);
        
        m_inputLogin.setBounds(200, 15,180,30);
        m_container.add(m_inputLogin);
        
        m_inputMdp.setBounds(200, 55,180,30);
        m_container.add(m_inputMdp);
        
        m_BtnConnexion.setText("Connexion");
        m_BtnConnexion.setBounds(200, 120,150,40);
        m_BtnConnexion.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {ShowMainFrame();}
        });
        m_container.add(m_BtnConnexion);
        
        m_lblInscription.setText("Pas de compte ?");
        m_lblInscription.setBounds(230, 95,150,15);
        m_lblInscription.setForeground(Color.red);
        m_lblInscription.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {ShowInscriptionDialog();}
        });
        m_container.add(m_lblInscription);
        
    }
    
    private void ShowInscriptionDialog()
    {
         Dialog_Inscription dInscription = new Dialog_Inscription(this, true);
         dInscription.setLocation(m_globalwidth / 2 - dInscription.getWidth() / 2, m_globalheight / 2 - dInscription.getHeight() / 2);
         dInscription.setVisible(true);
    }
    
    private void ShowMainFrame()
    {
        String mail = m_inputLogin.getText();
        String password = m_inputMdp.getText();
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Utilisateur uUserLoginIn = Data.TryConnection(mail ,password);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        
        if (uUserLoginIn != null)
        {
            Frame_MainInterface fInterface = new Frame_MainInterface(uUserLoginIn);
            Data.SetMainInterface(fInterface);
            fInterface.setVisible(true); 
            this.setVisible(false);
        }
        else
        {
            javax.swing.JOptionPane.showMessageDialog(null,"Le compte n'est pas valide");
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 594, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
