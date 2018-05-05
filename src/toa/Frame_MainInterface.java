package toa;

import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.util.Scanner;
import java.awt.*;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.JTextComponent.KeyBinding;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyName;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import javax.swing.JTextArea;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.SystemTray;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class Frame_MainInterface extends javax.swing.JFrame {

    private int m_globalheight;
    private int m_globalwidth;
    private int m_yearSelected;
    private Utilisateur m_uMainUtilisateur;
  
    private JTabbedPane m_tabPlanning;
    private JButton m_butNextYear;
    private JButton m_butLastYear;
    private JButton m_butAjoutProj;
    private JButton m_butAjoutMem;
    private JButton m_butSuppProj;
    private JLabel m_lblYearSelected;
  
    private JScrollPane m_janvier;
    private JScrollPane m_fevrier;
    private JScrollPane m_mars;
    private JScrollPane m_avril;
    private JScrollPane m_mai;
    private JScrollPane m_juin;
    private JScrollPane m_juillet;
    private JScrollPane m_aout;
    private JScrollPane m_septembre;
    private JScrollPane m_octobre;
    private JScrollPane m_novembre;
    private JScrollPane m_decembre;
    
    private Tab_Planning m_tabjanvier;
    private Tab_Planning m_tabfevrier;
    private Tab_Planning m_tabmars;
    private Tab_Planning m_tabavril;
    private Tab_Planning m_tabmai;
    private Tab_Planning m_tabjuin;
    private Tab_Planning m_tabjuillet;
    private Tab_Planning m_tabaout;
    private Tab_Planning m_tabseptembre;
    private Tab_Planning m_taboctobre;
    private Tab_Planning m_tabnovembre;
    private Tab_Planning m_tabdecembre;
    
    private JScrollPane m_scrollpdata;
    private JPanel m_paneldata;
    
    private JButton m_gererEvaluations;
    private JButton m_enregistrer;
    private JScrollPane m_scrollEvalutations;
    private Tab_Planning m_tabEvalutations;
    
    private ArrayList<Projet> m_listProjects;
    private ArrayList<Memoire> m_listMemoires;
    ArrayList<Etudiant> m_listEtudiants = new ArrayList<>();
    ArrayList<Professeur> m_listProfesseurs = new ArrayList<>();
    
    
    ///// PARTIE DESCRIPTION PROJET /////
    
    private int m_idProjectSelected;
    private int m_idGroupeSelected;
    private String m_sType;
    
    private JTextPane m_infoProjet;
    
    private JLabel m_lblRessources;
    private JLabel m_lblDates;
    private JLabel m_lblIntitule;
    private JLabel m_lblDateSoutenance;
    private JLabel m_lblGroupe;
    private JLabel m_lblEtuInGroupe;
    private JLabel m_lblEtuChefProj;
    private JLabel m_lblProfTuteur;
    private JLabel m_lblResponsable;
    private JLabel m_lblDateAuPlusTot;
    private JLabel m_lblObjectif;
    
    private JTextArea m_inputIntitule;
    private JDateChooser m_inputDateSoutenance;
    private JComboBox<Groupe> m_comboGroupe;
    private JList<Groupe> m_JlistEtudiantsInGroupes;
    private JComboBox<Etudiant> m_comboEtuChefProj;
    private JComboBox<Professeur> m_comboProfTuteur;
    private JComboBox<ResponsableEntreprise> m_comboResponsable;
    private JDateChooser m_inputDateAuPlusTot;
    private JTextArea m_inputObjectif;
    
    
    
    /////////////////////////////////////
    
    private boolean IsEtudiant;
    private boolean IsEnseignant;
    private boolean IsResponsable;
    private boolean IsAdmin;
    
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
        
    public Frame_MainInterface(Utilisateur uMainUtilisateur) {
        initComponents();
        
        try
        {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
        
        //m_globalwidth = 1260;
        
        m_uMainUtilisateur = uMainUtilisateur;
        SetDroits(m_uMainUtilisateur);
        setTitle("B&G Project");
        setSize(m_globalwidth, m_globalheight);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        Data.SetMainInterface(this);
        
         m_yearSelected = 2015;
        
        m_janvier = new JScrollPane();
        m_fevrier= new JScrollPane();
        m_mars = new JScrollPane();
        m_avril = new JScrollPane();
        m_mai = new JScrollPane();
        m_juin = new JScrollPane();
        m_juillet = new JScrollPane();
        m_aout = new JScrollPane();
        m_septembre = new JScrollPane();
        m_octobre = new JScrollPane();
        m_novembre = new JScrollPane();
        m_decembre = new JScrollPane();
        
        m_tabPlanning = new JTabbedPane();
        m_scrollpdata = new JScrollPane();
        m_paneldata = new JPanel(new BorderLayout());
        
        m_butNextYear = new JButton();
        m_butLastYear = new JButton();
        m_butAjoutProj = new JButton();
        m_butAjoutMem = new JButton();
        m_butSuppProj = new JButton();
        m_lblYearSelected = new JLabel();
        
        m_gererEvaluations = new JButton();
        m_enregistrer = new JButton();
        m_comboProfTuteur = new JComboBox<>();
        m_scrollEvalutations = new JScrollPane();
        m_tabEvalutations = new Tab_Planning();
        
        m_listProjects = new ArrayList<>();
        m_listMemoires = new ArrayList<>();
        
        ///Partie Description///
        
        m_lblRessources = new JLabel();
        m_lblDates = new JLabel();
        m_lblIntitule = new JLabel();
        m_lblDateSoutenance = new JLabel();
        m_lblGroupe = new JLabel();
        m_lblEtuInGroupe = new JLabel();
        m_lblEtuChefProj = new JLabel();
        m_lblProfTuteur = new JLabel();
        m_lblResponsable = new JLabel();
        m_lblDateAuPlusTot = new JLabel();
        m_lblObjectif = new JLabel();
    
        m_infoProjet = new JTextPane();
        m_inputIntitule = new JTextArea();
        m_inputDateSoutenance = new JDateChooser();
        m_JlistEtudiantsInGroupes = new JList<>();
        m_comboGroupe = new JComboBox<>();
        m_comboEtuChefProj= new JComboBox<>();
        m_comboProfTuteur= new JComboBox<>();
        m_comboResponsable = new JComboBox<>();
        m_inputDateAuPlusTot = new JDateChooser();
        m_inputObjectif = new JTextArea();
        
        ///////////////////////
        
        m_container.setSize(m_globalwidth, m_globalheight);
        m_container.setPreferredSize(new Dimension(m_globalwidth-22, m_globalheight-100));
        m_container.setBackground(Color.WHITE);
        
        m_tabPlanning.setLocation(150, m_globalheight / 20);
        m_tabPlanning.setSize(m_globalwidth - 50 - 150, m_globalheight / 2 - m_globalheight / 14);
        m_container.add(m_tabPlanning);
        
        m_scrollpdata.setBounds(10 , (m_globalheight / 2), m_globalwidth - 60, m_globalheight / 2 - m_globalheight / 14);
        m_scrollpdata.add(m_paneldata);
        m_scrollpdata.setViewportView(m_paneldata);
        m_container.add(m_scrollpdata);
        
        m_paneldata.setBounds(0 , 0, m_scrollpdata.getWidth(), m_scrollpdata.getHeight());
        
        m_lblYearSelected.setBounds(60, m_globalheight / 20 , 30, 30);
        m_lblYearSelected.setText("" + m_yearSelected);
        m_container.add(m_lblYearSelected);
                
        m_butLastYear.setBounds(10, m_globalheight / 20 , 40, 30);
        m_butLastYear.setText("<");
        m_butLastYear.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {BtnRemoveYear();}
        });
        m_container.add(m_butLastYear);
        
        m_butNextYear.setBounds(100, m_globalheight / 20 , 40, 30);
        m_butNextYear.setText(">");
        m_butNextYear.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {BtnAddYear();}
        });
        m_container.add(m_butNextYear);
        
        
        m_butAjoutProj.setBounds(10, m_globalheight / 20 + 40, 130, 40);
        m_butAjoutProj.setText("Ajouter projet");
        m_butAjoutProj.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {PopDialogAjouterProjet();}
        });
        m_container.add(m_butAjoutProj);
        
        m_butAjoutMem.setBounds(10, m_globalheight / 20 + 90, 130, 40);
        m_butAjoutMem.setText("Ajouter mémoire");
        m_butAjoutMem.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {PopDialogAjouterMemoire();}
        });
        m_container.add(m_butAjoutMem);
                
        m_butSuppProj.setBounds(10, m_globalheight / 20 + 140, 130, 40);
        m_butSuppProj.setText("Supprimer");
        m_butSuppProj.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {BtnRemoveSubject();}
        });
        m_container.add(m_butSuppProj);
        
        menuitemGererEtudiant.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {PopDialogGererEtudiant();}
        });
        
        menuitemGererProf.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {PopDialogGererProfesseur();}
        });
        
        menuitemGererResponsable.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {PopDialogGererResponsables();}
        });
        
        menuitemGererGroupes.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {PopDialogGererGroupes();}
        });
        
        menuitemAjouterMemoire.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {PopDialogAjouterMemoire();}
        });
        
        menuitemParametreCompte.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {PopParametreCompte();}
        });
        
        menuItemDisconnection.addActionListener(new java.awt.event.ActionListener() {
            @Override public void actionPerformed(java.awt.event.ActionEvent evt) {OnDisconnection();}
        });
                
        int itabwith = (m_tabPlanning.getWidth() - 300) / 12;
        
        
        
        //JANVIER
        try{
            m_tabjanvier = new Tab_Planning();
            m_tabjanvier.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabjanvier);} 
            });
            m_janvier.setViewportView(m_tabjanvier);
        }catch(Exception e){}
        
        //FEVRIER
        try{
            m_tabfevrier = new Tab_Planning();
            m_tabfevrier.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabfevrier);}
            });
            
            //m_fevrier.add(m_tabfevrier);
            m_fevrier.setViewportView(m_tabfevrier);
        }catch(Exception e){}
        
        //MARS
        try{
            m_tabmars = new Tab_Planning();
            m_tabmars.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabmars);}
            });
            
            //m_mars.add(m_tabmars);
            m_mars.setViewportView(m_tabmars);
        }catch(Exception e){}
        
        //AVRIL
        try{
            m_tabavril = new Tab_Planning();
            m_tabavril.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabavril);}
            });
            
            //m_avril.add(m_tabavril);
            m_avril.setViewportView(m_tabavril);
        }catch(Exception e){}
        
        //MAI
        try{
            m_tabmai = new Tab_Planning();
            m_tabmai.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabmai);}
            });
            
            //m_mai.add(m_tabmai);
            m_mai.setViewportView(m_tabmai);
        }catch(Exception e){}
        
        //JUIN
        try{
            m_tabjuin = new Tab_Planning();
            m_tabjuin.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabjuin);}
            });
            
            //m_juin.add(m_tabjuin);
            m_juin.setViewportView(m_tabjuin);
        }catch(Exception e){}
        
        //JUILLET
        try{
            m_tabjuillet = new Tab_Planning();
            m_tabjuillet.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabjuillet);}
            });
            
            //m_juillet.add(m_tabjuillet);
            m_juillet.setViewportView(m_tabjuillet);
        }catch(Exception e){}
        
        //AOUT
        try{
            m_tabaout = new Tab_Planning();
            m_tabaout.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabaout);}
            });
            
            //m_aout.add(m_tabaout);
            m_aout.setViewportView(m_tabaout);
        }catch(Exception e){}
        
        //SEPTEMBRE
        try{
            m_tabseptembre = new Tab_Planning();
            m_tabseptembre.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabseptembre);}
            });
            
            //m_septembre.add(m_tabseptembre);
            m_septembre.setViewportView(m_tabseptembre);
        }catch(Exception e){}
        
        //OCTOBRE
        try{
            m_taboctobre = new Tab_Planning();
            m_taboctobre.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_taboctobre);}
            });
            
            //m_octobre.add(m_taboctobre);
            m_octobre.setViewportView(m_taboctobre);
        }catch(Exception e){}
        
        //NOVEMBRE
        try{
            m_tabnovembre = new Tab_Planning();
            m_tabnovembre.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabnovembre);}
            });
            
            //m_novembre.add(m_tabnovembre);
            m_novembre.setViewportView(m_tabnovembre);
        }catch(Exception e){}
        
        //DECEMBRE
        try{
            m_tabdecembre = new Tab_Planning();
            m_tabdecembre.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabMois_onClick(evt, m_tabdecembre);}
            });
            
            //m_decembre.add(m_tabdecembre);
            m_decembre.setViewportView(m_tabdecembre);
        }catch(Exception e){}
        
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Janvier</table></body></html>", m_janvier);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Février</table></body></html>", m_fevrier);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Mars</table></body></html>", m_mars);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Avril</table></body></html>", m_avril);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Mai</table></body></html>", m_mai);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Juin</table></body></html>", m_juin);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Juillet</table></body></html>", m_juillet);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Août</table></body></html>", m_aout);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Septembre</table></body></html>", m_septembre);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Octobre</table></body></html>", m_octobre);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Novembre</table></body></html>", m_novembre);
        m_tabPlanning.addTab("<html><body><table width='" + itabwith + "'>Décembre</table></body></html>", m_decembre);
        
        // Partie Description///////
        m_infoProjet.setBounds(0, 0, m_paneldata.getWidth(), 30);
        m_infoProjet.setBackground(Color.GRAY);
        m_infoProjet.setEditable(false);
        m_paneldata.add(m_infoProjet);
        
        m_idProjectSelected = -1;
        m_idGroupeSelected = -1;
        
        int rapportGestionTab = m_paneldata.getHeight() / 5;
        int tierGestionTab = m_paneldata.getWidth() / 3;
        
        m_lblRessources.setText("Ressources");
        m_paneldata.add(m_lblRessources);
        
        m_lblDates.setText("Dates");
        m_paneldata.add(m_lblDates);
        
        m_lblGroupe.setText("Groupe");
        m_paneldata.add(m_lblGroupe);
        
        m_lblEtuChefProj.setText("Chef de projet");
        m_paneldata.add(m_lblEtuChefProj);
        
        m_lblEtuInGroupe.setText("Liste des étudiants");
        m_paneldata.add(m_lblEtuInGroupe);
        
        m_lblProfTuteur.setText("Tuteur");
        m_paneldata.add(m_lblProfTuteur);
        
        m_lblResponsable.setText("Responsable");
        m_paneldata.add(m_lblResponsable);
        
        m_lblObjectif.setText("Objectif");
        m_paneldata.add(m_lblObjectif);
        
        m_lblIntitule.setText("Intitulé du projet");
        m_paneldata.add(m_lblIntitule);
        
        m_paneldata.add(m_lblDateSoutenance);
        
        m_paneldata.add(m_lblDateAuPlusTot);
        
        m_comboGroupe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboGroupe_ItemStateChanged(evt);
            }
        });
        m_paneldata.add(m_comboGroupe);
        
        m_paneldata.add(m_comboEtuChefProj);
        
        m_paneldata.add(m_JlistEtudiantsInGroupes);
        
        m_paneldata.add(m_comboProfTuteur);
        
        m_paneldata.add(m_comboResponsable);
        
        m_paneldata.add(m_inputObjectif);
        
        m_paneldata.add(m_inputIntitule);
        
        m_paneldata.add(m_inputDateSoutenance);
        
        m_paneldata.add(m_inputDateAuPlusTot);
        
        m_gererEvaluations.setBounds(m_paneldata.getWidth() - tierGestionTab - 20, m_paneldata.getHeight() - 50, 180, 40);
        m_gererEvaluations.setText("Gérer les évaluations");
        m_gererEvaluations.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {PopDialogGererEvaluations();}
        });
        m_paneldata.add(m_gererEvaluations);
        
        m_enregistrer.setBounds(m_paneldata.getWidth() - 250, m_paneldata.getHeight() - 50, 200, 40);
        m_enregistrer.setText("Enregistrer les modifications");
        m_enregistrer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {Enregistrer_Modifications(evt);}
        });
        m_paneldata.add(m_enregistrer);
        
        
        m_scrollEvalutations.setViewportView(m_tabEvalutations);
        m_paneldata.add(m_scrollEvalutations);
        
        m_paneldata.setBackground(Color.lightGray);
        m_paneldata.setLayout(new BorderLayout());
       // m_paneldata.add(new JLabel(""));
        
        ///////////////////////////
        
        m_gererEvaluations.setVisible(false);
        m_enregistrer.setVisible(false);
        
        Requery();
    }
    
    public void SetDroits(Utilisateur uti)
    {
        if (uti.GetStatus().toLowerCase().contains("etudiant"))
        {
            IsEtudiant = true;
            IsEnseignant = false;
            IsResponsable = false;
            IsAdmin = false;
        }
        else if (uti.GetStatus().toLowerCase().contains("enseignant"))
        {
            IsEtudiant = false;
            IsEnseignant = true;
            IsResponsable = false;
            IsAdmin = false;
        }
        else if (uti.GetStatus().toLowerCase().contains("responsable"))
        {
            IsEtudiant = false;
            IsEnseignant = false;
            IsResponsable = true;
            IsAdmin = false;
        }
       else if (uti.GetStatus().toLowerCase().contains("admin"))
        {
            IsEtudiant = false;
            IsEnseignant = false;
            IsResponsable = false;
            IsAdmin = true;
        }
    }
 
    public void Requery()
    {
        try
        {
            /*JTable tableprojects = new JTable();
            tableprojects = Data.GenerateProjects();
            m_tabjanvier.setColumnModel(tableprojects.getColumnModel());
            //m_tabjanvier.setTableHeader(tableprof.getTableHeader());
            m_tabjanvier.setModel(tableprojects.getModel());*/
            m_listProjects.clear();
            m_listMemoires.clear();
            
            m_listEtudiants = Data.GenerateEtudiants();
            m_listProfesseurs = Data.GenerateProf();
            m_listProjects = Data.GenerateProjects(m_yearSelected);
            m_listMemoires = Data.GenerateMemoires(m_yearSelected);

            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            int iNbJanvier = 0;
            int iNbFevrier = 0;
            int iNbMars = 0;
            int iNbAvril = 0;
            int iNbMai = 0;
            int iNbJuin = 0;
            int iNbJuillet = 0;
            int iNbAout = 0;
            int iNbSeptembre = 0;
            int iNbOctobre = 0;
            int iNbNovembre = 0;
            int iNbDecembre = 0;
            
            for(int i = 0; i < m_listProjects.size(); i++)
            {
                Date currentDate = format.parse(m_listProjects.get(i).GetDateFin());
                
                switch(currentDate.getMonth())
                {
                    case 0:iNbJanvier++;break;
                    case 1:iNbFevrier++;break;
                    case 2:iNbMars++;break;
                    case 3:iNbAvril++;break;
                    case 4:iNbMai++;break;
                    case 5:iNbJuin++;break;
                    case 6:iNbJuillet++;break;
                    case 7:iNbAout++;break;
                    case 8:iNbSeptembre++;break;
                    case 9:iNbOctobre++;break;
                    case 10:iNbNovembre++;break;
                    case 11:iNbDecembre++;break;
                    default:break;
                }
            }
            for(int i = 0; i < m_listMemoires.size(); i++)
            {
                Date currentDate = format.parse(m_listMemoires.get(i).GetDateFin());
                
                switch(currentDate.getMonth())
                {
                    case 0:iNbJanvier++;break;
                    case 1:iNbFevrier++;break;
                    case 2:iNbMars++;break;
                    case 3:iNbAvril++;break;
                    case 4:iNbMai++;break;
                    case 5:iNbJuin++;break;
                    case 6:iNbJuillet++;break;
                    case 7:iNbAout++;break;
                    case 8:iNbSeptembre++;break;
                    case 9:iNbOctobre++;break;
                    case 10:iNbNovembre++;break;
                    case 11:iNbDecembre++;break;
                    default:break;
                }
            }
            
            String  title[] = {"Jour", "Type du sujet", "Nom du sujet",  "Elève responsable", "Tuteur", "Date de soutenance", "Id", "Gr"};
            Object[][] tableauJanvier = new Object[iNbJanvier][title.length];
            Object[][] tableauFevrier = new Object[iNbFevrier][title.length];
            Object[][] tableauMars = new Object[iNbMars][title.length];
            Object[][] tableauAvril = new Object[iNbAvril][title.length];
            Object[][] tableauMai = new Object[iNbMai][title.length];
            Object[][] tableauJuin = new Object[iNbJuin][title.length];
            Object[][] tableauJuillet = new Object[iNbJuillet][title.length];
            Object[][] tableauAout = new Object[iNbAout][title.length];
            Object[][] tableauSeptembre = new Object[iNbSeptembre][title.length];
            Object[][] tableauOctobre = new Object[iNbOctobre][title.length];
            Object[][] tableauNovembre = new Object[iNbNovembre][title.length];
            Object[][] tableauDecembre = new Object[iNbDecembre][title.length];
            
            iNbJanvier = 0;
            iNbFevrier = 0;
            iNbMars = 0;
            iNbAvril = 0;
            iNbMai = 0;
            iNbJuin = 0;
            iNbJuillet = 0;
            iNbAout = 0;
            iNbSeptembre = 0;
            iNbOctobre = 0;
            iNbNovembre = 0;
            iNbDecembre = 0;
            
            try
            {
                /////////////////////////////////////////////PROJETS////////
                for(int i = 0; i < m_listProjects.size(); i++)
                {
                    
                    Date currentDate = format.parse(m_listProjects.get(i).GetDateFin());
                     
                    switch(currentDate.getMonth())
                    {
                        case 0:
                            tableauJanvier[iNbJanvier][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauJanvier[iNbJanvier][1] = "Projet";
                            tableauJanvier[iNbJanvier][2] = m_listProjects.get(i).GetNom();
                            tableauJanvier[iNbJanvier][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauJanvier[iNbJanvier][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauJanvier[iNbJanvier][5] = m_listProjects.get(i).GetDateFin();
                            tableauJanvier[iNbJanvier][6] = m_listProjects.get(i).GetId();
                            tableauJanvier[iNbJanvier][7] = m_listProjects.get(i).GetGroupe();
                            iNbJanvier++;
                            break;
                        case 1:
                            tableauFevrier[iNbFevrier][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauFevrier[iNbFevrier][1] = "Projet";
                            tableauFevrier[iNbFevrier][2] = m_listProjects.get(i).GetNom();
                            tableauFevrier[iNbFevrier][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauFevrier[iNbFevrier][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauFevrier[iNbFevrier][5] = m_listProjects.get(i).GetDateFin();
                            tableauFevrier[iNbFevrier][6] = m_listProjects.get(i).GetId();
                            tableauFevrier[iNbFevrier][7] = m_listProjects.get(i).GetGroupe();
                            iNbFevrier++;
                            break;
                        case 2:
                            tableauMars[iNbMars][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauMars[iNbMars][1] = "Projet";
                            tableauMars[iNbMars][2] = m_listProjects.get(i).GetNom();
                            tableauMars[iNbMars][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauMars[iNbMars][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauMars[iNbMars][5] = m_listProjects.get(i).GetDateFin();
                            tableauMars[iNbMars][6] = m_listProjects.get(i).GetId();
                            tableauMars[iNbMars][7] = m_listProjects.get(i).GetGroupe();
                            iNbMars++;
                            break;
                        case 3:
                            tableauAvril[iNbAvril][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauAvril[iNbAvril][1] = "Projet";
                            tableauAvril[iNbAvril][2] = m_listProjects.get(i).GetNom();
                            tableauAvril[iNbAvril][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauAvril[iNbAvril][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauAvril[iNbAvril][5] = m_listProjects.get(i).GetDateFin();
                            tableauAvril[iNbAvril][6] = m_listProjects.get(i).GetId();
                            tableauAvril[iNbAvril][7] = m_listProjects.get(i).GetGroupe();
                            iNbAvril++;
                            break;
                        case 4:
                            tableauMai[iNbMai][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauMai[iNbMai][1] = "Projet";
                            tableauMai[iNbMai][2] = m_listProjects.get(i).GetNom();
                            tableauMai[iNbMai][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauMai[iNbMai][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauMai[iNbMai][5] = m_listProjects.get(i).GetDateFin();
                            tableauMai[iNbMai][6] = m_listProjects.get(i).GetId();
                            tableauMai[iNbMai][7] = m_listProjects.get(i).GetGroupe();
                            iNbMai++;
                            break;
                        case 5:
                            tableauJuin[iNbJuin][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauJuin[iNbJuin][1] = "Projet";
                            tableauJuin[iNbJuin][2] = m_listProjects.get(i).GetNom();
                            tableauJuin[iNbJuin][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauJuin[iNbJuin][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauJuin[iNbJuin][5] = m_listProjects.get(i).GetDateFin();
                            tableauJuin[iNbJuin][6] = m_listProjects.get(i).GetId();
                            tableauJuin[iNbJuin][7] = m_listProjects.get(i).GetGroupe();
                            iNbJuin++;
                            break;
                        case 6:
                            tableauJuillet[iNbJuillet][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauJuillet[iNbJuillet][1] = "Projet";
                            tableauJuillet[iNbJuillet][2] = m_listProjects.get(i).GetNom();
                            tableauJuillet[iNbJuillet][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauJuillet[iNbJuillet][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauJuillet[iNbJuillet][5] = m_listProjects.get(i).GetDateFin();
                            tableauJuillet[iNbJuillet][6] = m_listProjects.get(i).GetId();
                            tableauJuillet[iNbJuillet][7] = m_listProjects.get(i).GetGroupe();
                            iNbJuillet++;
                            break;
                        case 7:
                            tableauAout[iNbAout][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauAout[iNbAout][1] = "Projet";
                            tableauAout[iNbAout][2] = m_listProjects.get(i).GetNom();
                            tableauAout[iNbAout][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauAout[iNbAout][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauAout[iNbAout][5] = m_listProjects.get(i).GetDateFin();
                            tableauAout[iNbAout][6] = m_listProjects.get(i).GetId();
                            tableauAout[iNbAout][7] = m_listProjects.get(i).GetGroupe();
                            iNbAout++;
                        case 8:
                            tableauSeptembre[iNbSeptembre][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauSeptembre[iNbSeptembre][1] = "Projet";
                            tableauSeptembre[iNbSeptembre][2] = m_listProjects.get(i).GetNom();
                            tableauSeptembre[iNbSeptembre][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauSeptembre[iNbSeptembre][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauSeptembre[iNbSeptembre][5] = m_listProjects.get(i).GetDateFin();
                            tableauSeptembre[iNbSeptembre][6] = m_listProjects.get(i).GetId();
                            tableauSeptembre[iNbSeptembre][7] = m_listProjects.get(i).GetGroupe();
                            iNbSeptembre++;
                            break;
                        case 9:
                            tableauOctobre[iNbOctobre][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauOctobre[iNbOctobre][1] = "Projet";
                            tableauOctobre[iNbOctobre][2] = m_listProjects.get(i).GetNom();
                            tableauOctobre[iNbOctobre][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauOctobre[iNbOctobre][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauOctobre[iNbOctobre][5] = m_listProjects.get(i).GetDateFin();
                            tableauOctobre[iNbOctobre][6] = m_listProjects.get(i).GetId();
                            tableauOctobre[iNbOctobre][7] = m_listProjects.get(i).GetGroupe();
                            iNbOctobre++;
                            break;
                        case 10:
                            tableauNovembre[iNbNovembre][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauNovembre[iNbNovembre][1] = "Projet";
                            tableauNovembre[iNbNovembre][2] = m_listProjects.get(i).GetNom();
                            tableauNovembre[iNbNovembre][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauNovembre[iNbNovembre][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauNovembre[iNbNovembre][5] = m_listProjects.get(i).GetDateFin();
                            tableauNovembre[iNbNovembre][6] = m_listProjects.get(i).GetId();
                            tableauNovembre[iNbNovembre][7] = m_listProjects.get(i).GetGroupe();
                            iNbNovembre++;
                        case 11:
                            tableauDecembre[iNbDecembre][0] = m_listProjects.get(i).GetDateFin().substring(0, 2);
                            tableauDecembre[iNbDecembre][1] = "Projet";
                            tableauDecembre[iNbDecembre][2] = m_listProjects.get(i).GetNom();
                            tableauDecembre[iNbDecembre][3] = m_listProjects.get(i).GetNomEtudiantChef();
                            tableauDecembre[iNbDecembre][4] = m_listProjects.get(i).GetNomTuteur();
                            tableauDecembre[iNbDecembre][5] = m_listProjects.get(i).GetDateFin();
                            tableauDecembre[iNbDecembre][6] = m_listProjects.get(i).GetId();
                            tableauDecembre[iNbDecembre][7] = m_listProjects.get(i).GetGroupe();
                            iNbDecembre++;
                            break;
                        default:break;
                    }
                }
                ///////////////////////////////MEMOIRES//
                for(int i = 0; i < m_listMemoires.size(); i++)
                {
                    Date currentDate = format.parse(m_listMemoires.get(i).GetDateFin());
                     
                    switch(currentDate.getMonth())
                    {
                        case 0:
                            tableauJanvier[iNbJanvier][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauJanvier[iNbJanvier][1] = "Memoire";
                            tableauJanvier[iNbJanvier][2] = m_listMemoires.get(i).GetNom();
                            tableauJanvier[iNbJanvier][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauJanvier[iNbJanvier][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauJanvier[iNbJanvier][5] = m_listMemoires.get(i).GetDateFin();
                            tableauJanvier[iNbJanvier][6] = m_listMemoires.get(i).GetId();
                            tableauJanvier[iNbJanvier][7] = -1;
                            iNbJanvier++;
                            break;
                        case 1:
                            tableauFevrier[iNbFevrier][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauFevrier[iNbFevrier][1] = "Memoire";
                            tableauFevrier[iNbFevrier][2] = m_listMemoires.get(i).GetNom();
                            tableauFevrier[iNbFevrier][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauFevrier[iNbFevrier][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauFevrier[iNbFevrier][5] = m_listMemoires.get(i).GetDateFin();
                            tableauFevrier[iNbFevrier][6] = m_listMemoires.get(i).GetId();
                            tableauFevrier[iNbFevrier][7] = -1;
                            iNbFevrier++;
                            break;
                        case 2:
                            tableauMars[iNbMars][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauMars[iNbMars][1] = "Memoire";
                            tableauMars[iNbMars][2] = m_listMemoires.get(i).GetNom();
                            tableauMars[iNbMars][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauMars[iNbMars][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauMars[iNbMars][5] = m_listMemoires.get(i).GetDateFin();
                            tableauMars[iNbMars][6] = m_listMemoires.get(i).GetId();
                            tableauMars[iNbMars][7] = -1;
                            iNbMars++;
                            break;
                        case 3:
                            tableauAvril[iNbAvril][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauAvril[iNbAvril][1] = "Memoire";
                            tableauAvril[iNbAvril][2] = m_listMemoires.get(i).GetNom();
                            tableauAvril[iNbAvril][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauAvril[iNbAvril][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauAvril[iNbAvril][5] = m_listMemoires.get(i).GetDateFin();
                            tableauAvril[iNbAvril][6] = m_listMemoires.get(i).GetId();
                            tableauAvril[iNbAvril][7] = -1;
                            iNbAvril++;
                            break;
                        case 4:
                            tableauMai[iNbMai][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauMai[iNbMai][1] = "Memoire";
                            tableauMai[iNbMai][2] = m_listMemoires.get(i).GetNom();
                            tableauMai[iNbMai][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauMai[iNbMai][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauMai[iNbMai][5] = m_listMemoires.get(i).GetDateFin();
                            tableauMai[iNbMai][6] = m_listMemoires.get(i).GetId();
                            tableauMai[iNbMai][7] = -1;
                            iNbMai++;
                            break;
                        case 5:
                            tableauJuin[iNbJuin][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauJuin[iNbJuin][1] = "Memoire";
                            tableauJuin[iNbJuin][2] = m_listMemoires.get(i).GetNom();
                            tableauJuin[iNbJuin][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauJuin[iNbJuin][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauJuin[iNbJuin][5] = m_listMemoires.get(i).GetDateFin();
                            tableauJuin[iNbJuin][6] = m_listMemoires.get(i).GetId();
                            tableauJuin[iNbJuin][7] = -1;
                            iNbJuin++;
                            break;
                        case 6:
                            tableauJuillet[iNbJuillet][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauJuillet[iNbJuillet][1] = "Memoire";
                            tableauJuillet[iNbJuillet][2] = m_listMemoires.get(i).GetNom();
                            tableauJuillet[iNbJuillet][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauJuillet[iNbJuillet][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauJuillet[iNbJuillet][5] = m_listMemoires.get(i).GetDateFin();
                            tableauJuillet[iNbJuillet][6] = m_listMemoires.get(i).GetId();
                            tableauJuillet[iNbJuillet][7] = -1;
                            iNbJuillet++;
                            break;
                        case 7:
                            tableauAout[iNbAout][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauAout[iNbAout][1] = "Memoire";
                            tableauAout[iNbAout][2] = m_listMemoires.get(i).GetNom();
                            tableauAout[iNbAout][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauAout[iNbAout][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauAout[iNbAout][5] = m_listMemoires.get(i).GetDateFin();
                            tableauAout[iNbAout][6] = m_listMemoires.get(i).GetId();
                            tableauAout[iNbAout][7] = -1;
                            iNbAout++;
                        case 8:
                            tableauSeptembre[iNbSeptembre][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauSeptembre[iNbSeptembre][1] = "Memoire";
                            tableauSeptembre[iNbSeptembre][2] = m_listMemoires.get(i).GetNom();
                            tableauSeptembre[iNbSeptembre][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauSeptembre[iNbSeptembre][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauSeptembre[iNbSeptembre][5] = m_listMemoires.get(i).GetDateFin();
                            tableauSeptembre[iNbSeptembre][6] = m_listMemoires.get(i).GetId();
                            tableauSeptembre[iNbSeptembre][7] = -1;
                            iNbSeptembre++;
                            break;
                        case 9:
                            tableauOctobre[iNbOctobre][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauOctobre[iNbOctobre][1] = "Memoire";
                            tableauOctobre[iNbOctobre][2] = m_listMemoires.get(i).GetNom();
                            tableauOctobre[iNbOctobre][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauOctobre[iNbOctobre][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauOctobre[iNbOctobre][5] = m_listMemoires.get(i).GetDateFin();
                            tableauOctobre[iNbOctobre][6] = m_listMemoires.get(i).GetId();
                            tableauOctobre[iNbOctobre][7] = -1;
                            iNbOctobre++;
                            break;
                        case 10:
                            tableauNovembre[iNbNovembre][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauNovembre[iNbNovembre][1] = "Memoire";
                            tableauNovembre[iNbNovembre][2] = m_listMemoires.get(i).GetNom();
                            tableauNovembre[iNbNovembre][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauNovembre[iNbNovembre][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauNovembre[iNbNovembre][5] = m_listMemoires.get(i).GetDateFin();
                            tableauNovembre[iNbNovembre][6] = m_listMemoires.get(i).GetId();
                            tableauNovembre[iNbNovembre][7] = -1;
                            iNbNovembre++;
                        case 11:
                            tableauDecembre[iNbDecembre][0] = m_listMemoires.get(i).GetDateFin().substring(0, 2);
                            tableauDecembre[iNbDecembre][1] = "Memoire";
                            tableauDecembre[iNbDecembre][2] = m_listMemoires.get(i).GetNom();
                            tableauDecembre[iNbDecembre][3] = m_listMemoires.get(i).GetNomEtudiantChef();
                            tableauDecembre[iNbDecembre][4] = m_listMemoires.get(i).GetNomTuteur();
                            tableauDecembre[iNbDecembre][5] = m_listMemoires.get(i).GetDateFin();
                            tableauDecembre[iNbDecembre][6] = m_listMemoires.get(i).GetId();
                            tableauDecembre[iNbDecembre][7] = -1;
                            iNbDecembre++;
                            break;
                        default:break;
                    }
                }

            }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}

            JTable tableJanvier = new JTable(tableauJanvier,title);
            JTable tableFevrier = new JTable(tableauFevrier,title);
            JTable tableMars = new JTable(tableauMars,title);
            JTable tableAvril = new JTable(tableauAvril,title);
            JTable tableMai = new JTable(tableauMai,title);
            JTable tableJuin = new JTable(tableauJuin,title);
            JTable tableJuillet = new JTable(tableauJuillet,title);
            JTable tableAout = new JTable(tableauAout,title);
            JTable tableSeptembre = new JTable(tableauSeptembre,title);
            JTable tableOctobre = new JTable(tableauOctobre,title);
            JTable tableNovembre = new JTable(tableauNovembre,title);
            JTable tableDecembre = new JTable(tableauDecembre,title);
            
            SortTable(tableauJanvier, title, tableJanvier, m_tabjanvier);
            SortTable(tableauFevrier, title, tableFevrier, m_tabfevrier);
            SortTable(tableauMars, title, tableMars, m_tabmars);
            SortTable(tableauAvril, title, tableAvril, m_tabavril);
            SortTable(tableauMai, title, tableMai, m_tabmai);
            SortTable(tableauJuin, title, tableJuin, m_tabjuin);
            SortTable(tableauJuillet, title, tableJuillet, m_tabjuillet);
            SortTable(tableauAout, title, tableAout, m_tabaout);
            SortTable(tableauSeptembre, title, tableSeptembre, m_tabseptembre);
            SortTable(tableauOctobre, title, tableOctobre, m_taboctobre);
            SortTable(tableauNovembre, title, tableNovembre, m_tabnovembre);
            SortTable(tableauDecembre, title, tableDecembre, m_tabdecembre);
    
            AjdustColumns(m_tabjanvier, tableJanvier);
            AjdustColumns(m_tabfevrier, tableFevrier);
            AjdustColumns(m_tabmars, tableMars);
            AjdustColumns(m_tabavril, tableAvril);
            AjdustColumns(m_tabmai, tableMai);
            AjdustColumns(m_tabjuin, tableJuin);
            AjdustColumns(m_tabjuillet, tableJuillet);
            AjdustColumns(m_tabaout, tableAout);
            AjdustColumns(m_tabseptembre, tableSeptembre);
            AjdustColumns(m_taboctobre, tableOctobre);
            AjdustColumns(m_tabnovembre, tableNovembre);
            AjdustColumns(m_tabdecembre, tableDecembre);
        }
        catch(Exception e){}
        
    }
    
    private void SortTable(Object[][] donneeDuMois, String[]  titleDuMois, JTable TableauDuMois, Tab_Planning tabTableau)
    {
        DefaultTableModel modelDuMois = new DefaultTableModel(donneeDuMois,titleDuMois);
        TableauDuMois.setModel(modelDuMois);
        Vector data = modelDuMois.getDataVector();
        Collections.sort(data, new ColumnSorter(5));
       //modelDuMois.fireTableStructureChanged();
        modelDuMois.fireTableDataChanged();
        
       /* Object[][] newData = new Object[TableauDuMois.getRowCount()][TableauDuMois.getColumnCount()] ;
       for(int row = 0; row < TableauDuMois.getRowCount(); row++)
       {
           for(int column = 0; column < TableauDuMois.getColumnCount(); column++)
            {
               // newData[column][row] = TableauDuMois[column][row];
            }
       }*/
        tabTableau.SetDonnee(donneeDuMois);
       
        
    }
    
    private void AjdustColumns(Tab_Planning tabMois, JTable tableMois)
    {
        tabMois.setColumnModel(tableMois.getColumnModel());
        tabMois.setModel(tableMois.getModel());
        tabMois.getColumnModel().getColumn(0).setMinWidth(50);
        tabMois.getColumnModel().getColumn(0).setMaxWidth(50);
        tabMois.getColumnModel().getColumn(1).setMinWidth(175);
        tabMois.getColumnModel().getColumn(1).setMaxWidth(175);
        tabMois.getColumnModel().getColumn(6).setMinWidth(0);
        tabMois.getColumnModel().getColumn(6).setMaxWidth(0);
        tabMois.getColumnModel().getColumn(7).setMinWidth(0);
        tabMois.getColumnModel().getColumn(7).setMaxWidth(0);
    }
    
    private void RequeryCurrentSelectedProject(int idSelected, int idGroupe)
    {
        
            //m_comboGroupe.removeAllItems();
            ArrayList<Groupe> tempListGroupes = Data.GenerateGroupes();
            
            for(int i = 0; i < tempListGroupes.size(); i++)
                {
                   boolean goOn = true;
                   
                   for(int u = 0; u < m_comboGroupe.getItemCount(); u++)
                   {
                       if (m_comboGroupe.getItemAt(u).GetNumGroupe() == tempListGroupes.get(i).GetNumGroupe())
                       {
                             goOn = false;
                             break;
                       }
                   }
                   if (goOn)
                   {
                        m_comboGroupe.addItem(tempListGroupes.get(i));
                   }
                }
                for(int i = 0; i < m_comboGroupe.getItemCount(); i++)
                {
                    if (m_comboGroupe.getItemAt(i).GetNumGroupe() == idGroupe)
                    {
                        m_comboGroupe.setSelectedIndex(i);
                    }
                }

            
    }
    
    private void RequeryGroupeCombo(int idSelected, int idGroupe)
    {
            m_comboEtuChefProj.removeAllItems();
            int currentGroupe = ((Groupe)m_comboGroupe.getSelectedItem()).GetNumGroupe();
            ArrayList<Etudiant> tempListEtudiantsChef = Data.GenerateEtudiantsInGroupe(currentGroupe);
                DefaultListModel listModel = new DefaultListModel();
                
                for(int i = 0; i < tempListEtudiantsChef.size(); i++)
                {
                     m_comboEtuChefProj.addItem(tempListEtudiantsChef.get(i));
                     listModel.addElement(tempListEtudiantsChef.get(i));
                }
                m_JlistEtudiantsInGroupes.setModel(listModel);
                
                 Etudiant etuChef = Data.GenerateChefProject(idSelected, currentGroupe);
                 if (etuChef != null)
                 {
                   for(int i = 0; i < m_comboEtuChefProj.getItemCount(); i++)
                   {
                       if (m_comboEtuChefProj.getItemAt(i).GetId() == etuChef.GetId())
                       {
                           m_comboEtuChefProj.setSelectedIndex(i);
                       }
                   }
                }
    }
    
    private void RequeryCommonProjetAndMemoire()
    {
            m_comboResponsable.removeAllItems();
            ArrayList<ResponsableEntreprise> rResponsables = Data.GenerateResponsables();
                 
            for(int i = 0; i < rResponsables.size(); i++)
            {
                m_comboResponsable.addItem(rResponsables.get(i));
                 // if (m_comboResponsable.getItemAt(i).GetId() == etuChef.GetId())
                 //{
                 //    m_comboEtuChefProj.setSelectedIndex(i);
                 //}
            }
            
             ArrayList<Professeur> listProfesseurs = Data.GenerateProf();
            m_comboProfTuteur.removeAllItems();
            for(int i = 0; i < listProfesseurs.size(); i++)
            {
                m_comboProfTuteur.addItem(listProfesseurs.get(i));
            }
            
            ArrayList<AffichageEvaluation> listEvaluations  = new ArrayList<>();
            listEvaluations = Data.VisualizeEvaluations(m_idProjectSelected);
            String  title[] = {"Note", "Date evaluation", "Dernier commentaire", "Dernière date"};
            Object[][] data = new Object[listEvaluations.size()][title.length];
            
            try
            {
                for(int i = 0; i < listEvaluations.size(); i++)
                {
                    data[i][0] = listEvaluations.get(i).GetNoteEval();
                    data[i][1] = listEvaluations.get(i).GetDateEval();
                    data[i][2] = listEvaluations.get(i).GetLastCom();
                    data[i][3] = listEvaluations.get(i).GetLastDateCom();
                }

            }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
            /*Object[][] data = {
            {"Cysboy", "14","Bien", "16/03/2015"},
            {"BZHHydde", "15","Bien", "16/03/2015"},
            {"IamBow", "16","", "16/03/2015"},
            {"IamBow", "10","", "16/03/2015"},
            {"IamBow", "12","", "16/03/2015"},
            {"IamBow", "20","", "16/03/2015"},
            {"IamBow", "18","", "16/03/2015"},
            {"IamBow", "11","", "16/03/2015"},
            {"FunMan", "10","", "16/03/2015"}
            };*/
            
            JTable tableEval = new JTable(data, title);
            m_tabEvalutations.setColumnModel(tableEval.getColumnModel());
            m_tabEvalutations.setModel(tableEval.getModel());
    }
    
    private void SetProjectBounds(int idSelected, int idGroupe)
    {
        int rapportGestionTab = m_paneldata.getHeight() / 5;
        int iHauteurTab = m_paneldata.getHeight();
        int tierGestionTab = m_paneldata.getWidth() / 3;
        int deuxtiersGestionTab = 2 * tierGestionTab;
        
        int iRapHauteur = iHauteurTab / 6;
        
        m_lblRessources.setBounds(tierGestionTab / 6 + 120, iRapHauteur - 10, 100, 22);
        
        m_lblGroupe.setBounds(tierGestionTab / 6 + 60, iRapHauteur + 20, 100, 22);
        m_lblEtuChefProj.setBounds(tierGestionTab / 6 + 30, iRapHauteur + 50, 100, 22);
        m_lblEtuInGroupe.setBounds(tierGestionTab / 6,  iRapHauteur + 80, 150, 22);
        
        m_comboGroupe.setBounds(tierGestionTab / 6 + 120, iRapHauteur + 20, 100, 22);
        m_comboEtuChefProj.setBounds(tierGestionTab / 6 + 120, iRapHauteur + 50, 150, 22);
        m_JlistEtudiantsInGroupes.setBounds(tierGestionTab / 6 + 120, iRapHauteur + 80, 150, 100);
        
        int hauteurDown = 4 * iRapHauteur;
        if (hauteurDown < m_JlistEtudiantsInGroupes.getY() + m_JlistEtudiantsInGroupes.getHeight())
        {
                    hauteurDown = m_JlistEtudiantsInGroupes.getY() + m_JlistEtudiantsInGroupes.getHeight() +10;
        }
        m_lblProfTuteur.setBounds(tierGestionTab / 6 + 60, hauteurDown, 100, 22);
        m_lblResponsable.setBounds(tierGestionTab / 6 + 20, hauteurDown + 30, 100, 22);
        
        m_comboProfTuteur.setBounds(tierGestionTab / 6 + 120, hauteurDown, 150, 22);
        m_comboResponsable.setBounds(tierGestionTab / 6 + 120, hauteurDown + 30, 150, 22);
        
        
        m_lblDates.setBounds(tierGestionTab + 140, iRapHauteur - 10, 100, 22);
        
        m_lblDateSoutenance.setText("Date de Soutenance");
        m_lblDateSoutenance.setBounds(tierGestionTab + 10, iRapHauteur + 20, 150, 22);
        
         m_inputDateSoutenance.setBounds(tierGestionTab + 140, iRapHauteur + 20, 150, 22);
         m_inputDateAuPlusTot.setBounds(tierGestionTab + 140, iRapHauteur + 50, 150, 22);
         
        m_lblIntitule.setBounds(tierGestionTab + 35, iRapHauteur + 50, 100, 22);
        m_lblObjectif.setBounds(tierGestionTab + 80, iRapHauteur + 110, 100, 22);
        
        m_inputIntitule.setBounds(tierGestionTab + 140, iRapHauteur + 50, 200, 50);
        m_inputObjectif.setBounds(tierGestionTab + 140, iRapHauteur + 110, 200, 70);
        
        m_tabEvalutations.setBounds(0, 0, m_scrollEvalutations.getWidth(), m_scrollEvalutations.getHeight());
        m_scrollEvalutations.setBounds(m_paneldata.getWidth() - tierGestionTab - 20, 40, tierGestionTab, m_paneldata.getHeight() - 95);
        
        
        
        m_lblDateAuPlusTot.setText("Date au plus tôt");
        m_lblDateAuPlusTot.setBounds(tierGestionTab, iRapHauteur + 50, 100, 22);
        m_lblDateAuPlusTot.setVisible(false);
        m_inputDateAuPlusTot.setVisible(false);
        m_lblObjectif.setVisible(true);
        m_lblGroupe.setVisible(true);
        m_lblEtuInGroupe.setVisible(true);
        m_JlistEtudiantsInGroupes.setVisible(true);
        m_comboGroupe.setVisible(true);
        m_lblIntitule.setVisible(true);
        m_inputObjectif.setVisible(true);
        if (IsEnseignant || IsAdmin || IsResponsable)
        {
            m_gererEvaluations.setVisible(true);  
        }
        else
        {
            m_gererEvaluations.setVisible(false);
        }
        m_enregistrer.setVisible(true);
        
         RequeryCurrentSelectedProject(idSelected, idGroupe);
         RequeryGroupeCombo(idSelected, idGroupe);
         RequeryCommonProjetAndMemoire();
         
         //REQUETE
         Projet currentProjet = Data.GenerateProject(idSelected);
         if (currentProjet != null)
         {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                m_inputDateSoutenance.setDate(format.parse(currentProjet.GetDateTot()));
            } catch (Exception ex) {}
            m_inputIntitule.setText(currentProjet.GetNom());
            m_inputObjectif.setText(currentProjet.GetDescription());
            
            
            for(int i =0 ; i < m_comboProfTuteur.getItemCount() ; i++)
            {
                if( m_comboProfTuteur.getItemAt(i).GetId() == currentProjet.GetIDTuteur())
                {
                    m_comboProfTuteur.setSelectedIndex(i);
                    break;
                }
            }
            
            for(int i =0 ; i < m_comboResponsable.getItemCount() ; i++)
            {
                if( m_comboResponsable.getItemAt(i).GetId() == currentProjet.GetIDResponsable())
                {
                    m_comboResponsable.setSelectedIndex(i);
                    break;
                }
            }
            
            
         }
         
         
    }
    
    private void SetMemoireBounds(int idSelected)
    {
        int rapportGestionTab = m_paneldata.getHeight() / 5;
        int iHauteurTab = m_paneldata.getHeight();
        int tierGestionTab = m_paneldata.getWidth() / 3;
        int deuxtiersGestionTab = 2 * tierGestionTab;
        
        ArrayList<Etudiant> tempListEtudiants = Data.GenerateEtudiants();
        DefaultListModel listModel = new DefaultListModel();
        m_comboEtuChefProj.removeAllItems();
        for(int i = 0; i < tempListEtudiants.size(); i++)
        {
                m_comboEtuChefProj.addItem(tempListEtudiants.get(i));
        }
                 
        
        int iRapHauteur = iHauteurTab / 4;
        
        m_lblRessources.setBounds(tierGestionTab / 6 + 120, iRapHauteur - 10, 100, 22);
        
        m_lblGroupe.setVisible(false);
        m_lblEtuChefProj.setBounds(tierGestionTab / 6 + 30, iRapHauteur + 20, 100, 22);
        m_lblEtuInGroupe.setVisible(false);
        
        m_comboGroupe.setVisible(false);
        m_comboEtuChefProj.setBounds(tierGestionTab / 6 + 120, iRapHauteur + 20, 150, 22);
        m_JlistEtudiantsInGroupes.setVisible(false);
        
       
        m_lblProfTuteur.setBounds(tierGestionTab / 6 + 60, iRapHauteur + 50, 100, 22);
        m_lblResponsable.setBounds(tierGestionTab / 6 + 20, iRapHauteur + 80, 100, 22);
        
        m_comboProfTuteur.setBounds(tierGestionTab / 6 + 120, iRapHauteur + 50, 150, 22);
        m_comboResponsable.setBounds(tierGestionTab / 6 + 120, iRapHauteur + 80, 150, 22);
        
         int hauteurDown = 2 * iRapHauteur;
        if (hauteurDown < m_comboResponsable.getY() + m_comboResponsable.getHeight())
        {
                    hauteurDown = m_comboResponsable.getY() + m_comboResponsable.getHeight() +10;
        }
        
        m_lblIntitule.setBounds(tierGestionTab / 6 + 20, hauteurDown, 100, 22);
        m_inputIntitule.setBounds(tierGestionTab / 6 + 120, hauteurDown, 200, 70);
        
        m_lblDates.setBounds(tierGestionTab + 140, iRapHauteur - 10, 100, 22);
        
        m_lblDateSoutenance.setText("Date de Soutenance");
        m_lblDateAuPlusTot.setText("Date livrable");
        m_lblDateSoutenance.setBounds(tierGestionTab, iRapHauteur + 20, 150, 22);
        m_lblDateAuPlusTot.setBounds(tierGestionTab, iRapHauteur + 50, 100, 22);
        
         m_inputDateSoutenance.setBounds(tierGestionTab + 140, iRapHauteur + 20, 150, 22);
         m_inputDateAuPlusTot.setBounds(tierGestionTab + 140, iRapHauteur + 50, 150, 22);
         
        m_tabEvalutations.setBounds(0, 0, m_scrollEvalutations.getWidth(), m_scrollEvalutations.getHeight());
        m_scrollEvalutations.setBounds(m_paneldata.getWidth() - tierGestionTab - 20, 40, tierGestionTab, m_paneldata.getHeight() - 95);
        
        m_lblDateAuPlusTot.setVisible(true);
        m_inputDateAuPlusTot.setVisible(true);
        m_lblObjectif.setVisible(false);
        m_inputObjectif.setVisible(false);
        if (IsEnseignant || IsAdmin || IsResponsable)
        {
            m_gererEvaluations.setVisible(true);  
        }
        else
        {
            m_gererEvaluations.setVisible(false);
        }
        m_enregistrer.setVisible(true);
        
         RequeryCommonProjetAndMemoire();
         
         Memoire currentMemoire = Data.GenerateMemoire(idSelected);
         if (currentMemoire != null)
         {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                m_inputDateSoutenance.setDate(format.parse(currentMemoire.GetDateTot()));
                m_inputDateAuPlusTot.setDate(format.parse(currentMemoire.GetDateLivrable()));
            } catch (Exception ex) {}
            m_inputIntitule.setText(currentMemoire.GetNom());
            
            for(int i =0 ; i < m_comboProfTuteur.getItemCount() ; i++)
            {
                if( m_comboProfTuteur.getItemAt(i).GetId() == currentMemoire.GetIDTuteur())
                {
                    m_comboProfTuteur.setSelectedIndex(i);
                    break;
                }
            }
            
            for(int i =0 ; i < m_comboResponsable.getItemCount() ; i++)
            {
                if( m_comboResponsable.getItemAt(i).GetId() == currentMemoire.GetIDResponsable())
                {
                    m_comboResponsable.setSelectedIndex(i);
                    break;
                }
            }
         }
    }
    
    private void PopDialogAjouterProjet()
    {
       Dialog_AjouterProjet diagAddPro = new Dialog_AjouterProjet(this, true);
       /*diagAddPro.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                Requery();
            }
        });*/
        diagAddPro.setLocation(m_globalwidth / 2 - diagAddPro.GetWidth() / 2, m_globalheight / 2 - diagAddPro.getHeight() / 2);
        diagAddPro.setVisible(true);
    }
    
    private void PopDialogAjouterMemoire()
    {
        Dialog_AjouterMemoires diagAddMem = new Dialog_AjouterMemoires(this, true);
        /*diagAddMem.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                Requery();
            }
        });*/
        diagAddMem.setLocation(m_globalwidth / 2 - diagAddMem.GetWidth() / 2, m_globalheight / 2 - diagAddMem.getHeight() / 2);
        diagAddMem.setVisible(true);
    }
    
    private void PopDialogGererGroupes()
    {
        Dialog_GererGroupes diagGererGr = new Dialog_GererGroupes(this, true);
        diagGererGr.setLocation(m_globalwidth / 2 - diagGererGr.GetWidth() / 2, m_globalheight / 2 - diagGererGr.getHeight() / 2);
        diagGererGr.setVisible(true);
    }
    
    private void PopDialogGererEtudiant()
    {
        Dialog_GererEtudiant diagGererEt = new Dialog_GererEtudiant(this, true);
        diagGererEt.setLocation(m_globalwidth / 2 - diagGererEt.GetWidth() / 2, m_globalheight / 2 - diagGererEt.getHeight() / 2);
        diagGererEt.setVisible(true);
    }
    
    private void PopDialogGererProfesseur()
    {
        Dialog_GererProfesseur diagGererProf = new Dialog_GererProfesseur(this, true);
        diagGererProf.setLocation(m_globalwidth / 2 - diagGererProf.GetWidth() / 2, m_globalheight / 2 - diagGererProf.getHeight() / 2);
        diagGererProf.setVisible(true);
    }
    
    private void PopDialogGererResponsables()
    {
        Dialog_GererResponsables diagGererResp = new Dialog_GererResponsables(this, true);
        diagGererResp.setLocation(m_globalwidth / 2 - diagGererResp.GetWidth() / 2, m_globalheight / 2 - diagGererResp.getHeight() / 2);
        diagGererResp.setVisible(true);
    }
    
    private void PopDialogGererMemoire()
    {
        Dialog_AjouterMemoires diagGererMemoire = new Dialog_AjouterMemoires(this, true);
        diagGererMemoire.setLocation(m_globalwidth / 2 - diagGererMemoire.GetWidth() / 2, m_globalheight / 2 - diagGererMemoire.getHeight() / 2);
        diagGererMemoire.setVisible(true);
    }
    
    private void PopDialogGererEvaluations()
    {
        Dialog_GererEvaluations diagGererEval = new Dialog_GererEvaluations(this, true, m_idProjectSelected);
        diagGererEval.setLocation(m_globalwidth / 2 - diagGererEval.GetWidth() / 2, m_globalheight / 2 - diagGererEval.getHeight() / 2);
        diagGererEval.setVisible(true);
    }
    
     private void PopParametreCompte()
     {
        Dialog_ParametreCompte dParametre = new Dialog_ParametreCompte(this, true, m_uMainUtilisateur);
        dParametre.setLocation(m_globalwidth / 2 - dParametre.getWidth() / 2, m_globalheight / 2 - dParametre.getHeight() / 2);
        dParametre.setVisible(true);
     }
    
    private void BtnAddYear()
    {
        m_yearSelected++;
        m_lblYearSelected.setText("" + m_yearSelected);
        Requery();
    }
    
    private void BtnRemoveYear()
    {
        m_yearSelected--;
        m_lblYearSelected.setText("" + m_yearSelected);
        Requery();
    }
    
    private void BtnRemoveSubject()
    {
        if (m_idProjectSelected > -1)
        {
            if(m_sType.equals("projet"))
            {
                if (Data.RemoveProjet(m_idProjectSelected))
                {
                    Requery();
                    javax.swing.JOptionPane.showMessageDialog(null,"Le projet a été supprimé");
                }
            }
            else if(m_sType.equals("memoire"))
            {
                if (Data.RemoveMemoire(m_idProjectSelected))
                {
                    Requery();
                    javax.swing.JOptionPane.showMessageDialog(null,"Le mémoire a été supprimé");
                }
            }
        }
    }
        
     private void TabMois_onClick(java.awt.event.MouseEvent evt, Tab_Planning table) {
        if (evt.getButton() == MouseEvent.BUTTON1)
        {
            int idSujet = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 6).toString());
            int iGroupe = Integer.parseInt(table.getModel().getValueAt(table.getSelectedRow(), 7).toString());
            String sSujet = (table.getModel().getValueAt(table.getSelectedRow(), 1)).toString();
            String sNomSUjet = (table.getModel().getValueAt(table.getSelectedRow(), 2)).toString();
            
            
            m_idProjectSelected = idSujet;
            m_idGroupeSelected = iGroupe;
            
            
            this.setCursor(Cursor.WAIT_CURSOR);
            if (sSujet.toLowerCase().equals("projet"))
            {
                m_infoProjet.setText("Projet numéro " + idSujet + " (" + sNomSUjet + ")");
                SetProjectBounds(idSujet, iGroupe);
                m_sType = "projet";
            }
            else
            {
                m_infoProjet.setText("Mémoire numéro " + idSujet + " (" + sNomSUjet + ")");
                SetMemoireBounds(idSujet);
                m_sType = "memoire";
            }
            this.setCursor(Cursor.DEFAULT_CURSOR);
        }
        else if (evt.getButton() == MouseEvent.BUTTON3)
        {
            JPopupMenu popupmenuplanning = new JPopupMenu();
            JMenuItem actualiser = new JMenuItem();
            actualiser.setText("Actualiser");
            actualiser.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    Requery();
                }
              });
            popupmenuplanning.add(actualiser);
            JMenuItem disable = new JMenuItem();
            disable.setText("Supprimer la sélection");
            disable.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    BtnRemoveSubject();
                }
              });
            popupmenuplanning.add(disable);
            popupmenuplanning.show(table, evt.getX(), evt.getY());
        }
    }   
     
     private void Enregistrer_Modifications(java.awt.event.MouseEvent evt) {   
         
         if(m_sType.equals("projet"))
         {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             int idGroupe = m_comboGroupe.getItemAt(m_comboGroupe.getSelectedIndex()).GetId();
             int idEleveResponsable = m_comboEtuChefProj.getItemAt(m_comboEtuChefProj.getSelectedIndex()).GetId();
             int idTuteur = m_comboProfTuteur.getItemAt(m_comboProfTuteur.getSelectedIndex()).GetId();
             int idResponsable  = m_comboResponsable.getItemAt(m_comboResponsable.getSelectedIndex()).GetId();
             String sDateSoutenance = dateFormat.format(m_inputDateSoutenance.getDate());
             String sIntitule = m_inputIntitule.getText();
             String sObjectif = m_inputObjectif.getText();
             //REQUETE UPDATE PROJET
             if (Data.UpdateProject(new Projet(m_idProjectSelected, sIntitule, idEleveResponsable, idTuteur, idResponsable, sDateSoutenance, sObjectif, "", idGroupe)))
             {
                 Requery();
                 javax.swing.JOptionPane.showMessageDialog(null,"Modification du projet enregistré !");
             }
             else
             {
                 javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les modifications !");
             }
             
         }
         else if(m_sType.equals("memoire"))
         {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
             int idEleveResponsable = m_comboEtuChefProj.getItemAt(m_comboEtuChefProj.getSelectedIndex()).GetId();
             int idTuteur = m_comboProfTuteur.getItemAt(m_comboProfTuteur.getSelectedIndex()).GetId();
             int idResponsable  = m_comboResponsable.getItemAt(m_comboResponsable.getSelectedIndex()).GetId();
             String sIntitule = m_inputIntitule.getText();
             String sDateSoutenance = dateFormat.format(m_inputDateSoutenance.getDate());
             String sDateLivrable = dateFormat.format(m_inputDateAuPlusTot.getDate());
             // REQUETE UPDATE MEMOIRE
             
             if (Data.UpdateMemoire(new Memoire(m_idProjectSelected, sIntitule, idEleveResponsable, idTuteur, idResponsable, sDateSoutenance, sDateLivrable)))
             {
                   Requery();
                   javax.swing.JOptionPane.showMessageDialog(null,"Modification du mémoire enregistré !");
             }
             else
             {
                 javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'enregistrer les modifications !");
             }
         }
     }
     
     private void comboGroupe_ItemStateChanged(java.awt.event.ItemEvent evt) {
        RequeryGroupeCombo(m_idProjectSelected, m_idGroupeSelected);
    }
     
     private void OnDisconnection() {   
        Frame_Connexion fConnexion = new Frame_Connexion();
        fConnexion.setVisible(true);                              
        this.setVisible(false);
    }
     
     public void SetUtilisateur(Utilisateur user)
     {
         m_uMainUtilisateur.SetId(user.GetId());
         m_uMainUtilisateur.SetNom(user.GetNom());
         m_uMainUtilisateur.SetPrenom(user.GetPrenom());
         m_uMainUtilisateur.SetLogin(user.GetLogin());
         m_uMainUtilisateur.SetPassword(user.GetPassword());
         m_uMainUtilisateur.SetStatus(user.GetStatus());
     }
     
     public Utilisateur GetUtilisateur()
     {
         return m_uMainUtilisateur;
     }
  //////////////////////// POUR TRIER LES JTABLE////////////////////////////
    class ColumnSorter implements Comparator {
    int colIndex;

    ColumnSorter(int colIndex) {
      this.colIndex = colIndex;
    }

    public int compare(Object a, Object b) {
      Vector v1 = (Vector) a;
      Vector v2 = (Vector) b;
      Object o1 = v1.get(colIndex);
      Object o2 = v2.get(colIndex);

      if (o1 instanceof String && ((String) o1).length() == 0) {
        o1 = null;
      }
      if (o2 instanceof String && ((String) o2).length() == 0) {
        o2 = null;
      }

      if (o1 == null && o2 == null) {
        return 0;
      } else if (o1 == null) {
        return 1;
      } else if (o2 == null) {
        return -1;
      } else if (o1 instanceof Comparable) {

        return ((Comparable) o1).compareTo(o2);
      } else {

        return o1.toString().compareTo(o2.toString());
      }
    }
  }
 //////////////////////////////////////////////////////////////////////////
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_globalcontainer = new javax.swing.JScrollPane();
        m_container = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        menuitemAjouterProjet = new javax.swing.JMenuItem();
        menuitemAjouterMemoire = new javax.swing.JMenuItem();
        menuitemQuitter = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuitemGererGroupes = new javax.swing.JMenuItem();
        menuitemGererEtudiant = new javax.swing.JMenuItem();
        menuitemGererProf = new javax.swing.JMenuItem();
        menuitemGererResponsable = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuitemParametreCompte = new javax.swing.JMenuItem();
        menuItemDisconnection = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        m_globalcontainer.setToolTipText("");

        m_container.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_container);
        m_container.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1036, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
        );

        m_globalcontainer.setViewportView(m_container);

        jMenuFichier.setText("Fichier");

        menuitemAjouterProjet.setText("Ajouter un projet");
        menuitemAjouterProjet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemAjouterProjetActionPerformed(evt);
            }
        });
        jMenuFichier.add(menuitemAjouterProjet);

        menuitemAjouterMemoire.setText("Ajouter un mémoire");
        jMenuFichier.add(menuitemAjouterMemoire);

        menuitemQuitter.setText("Quitter");
        menuitemQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuitemQuitterActionPerformed(evt);
            }
        });
        jMenuFichier.add(menuitemQuitter);

        jMenuBar1.add(jMenuFichier);

        jMenu2.setText("Gestion");

        menuitemGererGroupes.setText("Gérer les groupes");
        jMenu2.add(menuitemGererGroupes);

        menuitemGererEtudiant.setText("Gérer les étudiants");
        jMenu2.add(menuitemGererEtudiant);

        menuitemGererProf.setText("Gérer les professeurs");
        jMenu2.add(menuitemGererProf);

        menuitemGererResponsable.setText("Gérer les responsables");
        jMenu2.add(menuitemGererResponsable);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Paramètres");

        menuitemParametreCompte.setText("Modifier Compte");
        jMenu1.add(menuitemParametreCompte);

        menuItemDisconnection.setText("Déconnexion");
        jMenu1.add(menuItemDisconnection);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_globalcontainer)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(m_globalcontainer)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuitemAjouterProjetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemAjouterProjetActionPerformed
        PopDialogAjouterProjet();
    }//GEN-LAST:event_menuitemAjouterProjetActionPerformed

    private void menuitemQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuitemQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuitemQuitterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JPanel m_container;
    private javax.swing.JScrollPane m_globalcontainer;
    private javax.swing.JMenuItem menuItemDisconnection;
    private javax.swing.JMenuItem menuitemAjouterMemoire;
    private javax.swing.JMenuItem menuitemAjouterProjet;
    private javax.swing.JMenuItem menuitemGererEtudiant;
    private javax.swing.JMenuItem menuitemGererGroupes;
    private javax.swing.JMenuItem menuitemGererProf;
    private javax.swing.JMenuItem menuitemGererResponsable;
    private javax.swing.JMenuItem menuitemParametreCompte;
    private javax.swing.JMenuItem menuitemQuitter;
    // End of variables declaration//GEN-END:variables
}
