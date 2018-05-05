package toa;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;

public class Dialog_GererEvaluations extends Dialog_Windows {
    
    private int m_idSujet;
    private int m_oldSelectedRowEvaluation;
    
    private JScrollPane m_scrolltabevaluations;
    private JTable m_tabEvaluations;
    
    private JScrollPane m_scrolltabcommentaires;
    private JTable m_tabCommentaires;
    
   private JPanel m_dataEvaluations;
   private JPanel m_dataCommentaires;
   
   private JLabel m_lblNoteEval;
   private JLabel m_lblDateEval;
   private JTextField m_inputNoteEval;
   private JDateChooser m_inputDateEval;
   private JButton m_BtnAjoutEval;
   
   private JLabel m_lblDateCom;
   private JLabel m_lblTextCom;
   private JDateChooser m_inputDateCom;
   private JTextArea m_inputTextCom;
   private JButton m_BtnAjoutCom;
   
   private JButton m_BtnModifierEval;
   private JButton m_BtnSupprimerEval;
   
   private JButton m_BtnModifierCom;
   private JButton m_BtnSupprimerCom;
   
   private ArrayList<Evaluation> m_listEvaluations;
   private ArrayList<Commentaire> m_listCommentaires;

    public Dialog_GererEvaluations(java.awt.Frame parent, boolean modal, int idSujet) {
        super(parent, modal);
        
        setTitle("Gérer les évaluations");
        m_idSujet = idSujet;
        m_oldSelectedRowEvaluation = -1;
        
        m_scrolltabevaluations = new JScrollPane();
        m_scrolltabcommentaires = new JScrollPane();
        m_tabEvaluations = new JTable();
        m_tabCommentaires = new JTable();
        m_dataEvaluations = new JPanel();
        m_dataCommentaires = new JPanel();
        
        m_lblNoteEval = new JLabel();
        m_lblDateEval = new JLabel();
        m_inputNoteEval = new JTextField();
        m_inputDateEval = new JDateChooser();
        m_BtnAjoutEval = new JButton();
        
        m_lblDateCom = new JLabel();
        m_lblTextCom = new JLabel();
        m_inputDateCom = new JDateChooser();
        m_inputTextCom = new JTextArea();
        m_BtnAjoutCom = new JButton();
        
        m_BtnModifierEval = new JButton();
        m_BtnSupprimerEval = new JButton();
   
        m_BtnModifierCom = new JButton();
        m_BtnSupprimerCom = new JButton();
        
        m_listEvaluations = new  ArrayList<>(); 
        m_listCommentaires = new  ArrayList<>();
        
        m_scrolltabevaluations.setBounds(10, 10, 380, 290);
        m_scrolltabevaluations.setViewportView(m_tabEvaluations);
        m_container.add(m_scrolltabevaluations); 
        
        m_scrolltabcommentaires.setBounds(410, 10, 380, 290);
        m_scrolltabcommentaires.setViewportView(m_tabCommentaires);
        m_container.add(m_scrolltabcommentaires);
        
        m_BtnModifierEval.setText("Enregistrer modifications");
        m_BtnModifierEval.setBounds(10, 310, 200, 40);
        m_BtnModifierEval.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {SaveEvaluations();}
        });
        m_container.add(m_BtnModifierEval); 
        
        m_BtnSupprimerEval.setText("Supprimer la ligne");
        m_BtnSupprimerEval.setBounds(210, 310, 180, 40);
        m_BtnSupprimerEval.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {RemoveEvaluation();}
        });
        m_container.add(m_BtnSupprimerEval); 
        
        m_BtnModifierCom.setText("Enregistrer modifications");
        m_BtnModifierCom.setBounds(410, 310, 200, 40);
        m_BtnModifierCom.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {SaveCommentaires();}
        });
        m_container.add(m_BtnModifierCom); 
        
        m_BtnSupprimerCom.setText("Supprimer la ligne");
        m_BtnSupprimerCom.setBounds(610, 310, 180, 40);
        m_BtnSupprimerCom.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {RemoveCommentaire();}
        });
        m_container.add(m_BtnSupprimerCom);
        
        m_dataEvaluations.setBounds(10, 360, 380, 210);
        m_dataEvaluations.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataEvaluations.setBorder(BorderFactory.createTitledBorder("Ajouter une évaluation"));
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_dataEvaluations);
        m_dataEvaluations.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        m_container.add(m_dataEvaluations); 
        
        m_dataCommentaires.setBounds(410, 360, 380, 210);
        m_dataCommentaires.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        m_dataCommentaires.setBorder(BorderFactory.createTitledBorder("Ajouter un commentaire"));
        javax.swing.GroupLayout m_containerLayoutcommentaires = new javax.swing.GroupLayout(m_dataCommentaires);
        m_dataCommentaires.setLayout(m_containerLayoutcommentaires);
        m_containerLayoutcommentaires.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        m_containerLayoutcommentaires.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        m_container.add(m_dataCommentaires); 
        
        // EVALUATION/////////////////
        m_lblNoteEval.setText("Note");
        m_lblNoteEval.setBounds(80, 60, 60, 30);
        m_dataEvaluations.add(m_lblNoteEval);
        
        m_lblDateEval.setText("Date");
        m_lblDateEval.setBounds(80, 100, 60, 30);
        m_dataEvaluations.add(m_lblDateEval);
        
        m_inputNoteEval.setBounds(150, 60, 100, 30);
        m_dataEvaluations.add(m_inputNoteEval);
        
        m_inputDateEval.setBounds(150, 100, 150, 30);
        m_dataEvaluations.add(m_inputDateEval);
        
        m_BtnAjoutEval.setText("Ajouter une évaluation");
        m_BtnAjoutEval.setBounds(120, 150, 150, 40);
        m_BtnAjoutEval.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {AjouterEvaluation();}
        });
        m_dataEvaluations.add(m_BtnAjoutEval);
        //////////////////////////////
        
        //COMMENTAIRE/////////////////
        m_lblTextCom.setText("Commentaire");
        m_lblTextCom.setBounds(50, 30, 80, 30);
        m_dataCommentaires.add(m_lblTextCom);
        
        m_lblDateCom.setText("Date");
        m_lblDateCom.setBounds(100, 115, 60, 30);
        m_dataCommentaires.add(m_lblDateCom);
        
        m_inputTextCom.setBounds(150, 30, 180, 80);
        m_dataCommentaires.add(m_inputTextCom);
        
        m_inputDateCom.setBounds(150, 115, 150, 30);
        m_dataCommentaires.add(m_inputDateCom);
        
        m_BtnAjoutCom.setText("Ajouter un commentaire");
        m_BtnAjoutCom.setBounds(110, 150, 170, 40);
        m_BtnAjoutCom.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent evt) {AjouterCommentaire();}
        });
        m_dataCommentaires.add(m_BtnAjoutCom);
        /////////////////////////////
        
        m_tabEvaluations.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent evt) {TabEvaluationsOnclick();}
            });
        m_tabEvaluations.setRowHeight(22);
        m_tabEvaluations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_tabEvaluations.getTableHeader().setResizingAllowed(false);
        m_tabEvaluations.getTableHeader().setReorderingAllowed(false);
        
        m_tabCommentaires.setRowHeight(30);
        m_tabCommentaires.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        m_tabCommentaires.getTableHeader().setResizingAllowed(false);
        m_tabCommentaires.getTableHeader().setReorderingAllowed(false);
        
        Requery();
    }
    
    public void Requery()
    {
       m_listEvaluations = Data.GetEvaluationsById(m_idSujet);
        
        RefreshTableEvaluations();
    }
    
     
    public void RefreshTableEvaluations()
    {
        
        String  title[] = {"Note", "Date"};
        Object[][] data = new Object[m_listEvaluations.size()][title.length];
        
        try
        {
            for(int i = 0; i < m_listEvaluations.size(); i++)
            {
                data[i][0] = m_listEvaluations.get(i).GetNote();
                data[i][1] = m_listEvaluations.get(i).GetDate();
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        JTable tableEval = new JTable(data,title);
        m_tabEvaluations.setColumnModel(tableEval.getColumnModel());
        m_tabEvaluations.setModel(tableEval.getModel());
        if (m_oldSelectedRowEvaluation != -1)
        {
            m_tabEvaluations.setRowSelectionInterval(m_oldSelectedRowEvaluation, m_oldSelectedRowEvaluation);
            int idEval = m_listEvaluations.get(m_tabEvaluations.getSelectedRow()).GetId();
            m_listCommentaires = Data.GetCommentairesById(idEval);
        }
        RefreshTableCommentaires();
    }
    
    public void RefreshTableCommentaires()
    {
        String  title[] = {"Commentaire", "Date commentaire"};
        Object[][] data = new Object[m_listCommentaires.size()][title.length];
        
        try
        {
            for(int i = 0; i < m_listCommentaires.size(); i++)
            {
                data[i][0] = m_listCommentaires.get(i).GetCommentaire();
                data[i][1] = m_listCommentaires.get(i).GetDate();
            }
            
        }catch(Exception e){javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + e);}
        
        JTable tableCom = new JTable(data,title);
        m_tabCommentaires.setColumnModel(tableCom.getColumnModel());
        m_tabCommentaires.setModel(tableCom.getModel());
    }
    
    public void AjouterEvaluation()
    {   
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateEval = dateFormat.format(m_inputDateEval.getDate());
        if ((dateEval != "") && (IsNumeric(m_inputNoteEval.getText())))
        {
            int noteEval = Integer.parseInt(m_inputNoteEval.getText());
            Evaluation evalToRecord = new Evaluation(-1, m_idSujet, noteEval, dateEval);

            if(Data.RecordEvaluation(evalToRecord))
            {
                //m_inputDateEval.setDateFormatString(dateFormat.format(""));
                m_inputNoteEval.setText("");
                javax.swing.JOptionPane.showMessageDialog(null,"Evaluation enregistrée");
                Requery();
            }
            else
            {
                    javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'ajouter l'évaluation");
            }
        }
        else
        {
              javax.swing.JOptionPane.showMessageDialog(null,"Les champs ne sont pas correctement remplis");
        }
    }
    
    public void AjouterCommentaire()
    {
        m_oldSelectedRowEvaluation = m_tabEvaluations.getSelectedRow();
        if (m_tabEvaluations.getSelectedRow() != -1)
        {
            int idEval = m_listEvaluations.get(m_tabEvaluations.getSelectedRow()).GetId();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateEval = dateFormat.format(m_inputDateCom.getDate());

            if ((dateEval != "") && (m_inputTextCom.getText().length() > 0 ))
            {
                String comm = m_inputTextCom.getText();
                Commentaire comToRecord = new Commentaire(-1, idEval, dateEval, comm);

                if(Data.RecordCommentaire(comToRecord))
                {
                    m_inputTextCom.setText("");
                    javax.swing.JOptionPane.showMessageDialog(null,"Commentaire enregistrée");
                    Requery();
                }
                else
                {
                        javax.swing.JOptionPane.showMessageDialog(null,"Impossible d'ajouter le commentaire");
                }
            }
            else
            {
                  javax.swing.JOptionPane.showMessageDialog(null,"Les champs ne sont pas correctement remplis");
            }
        }
        else
        {
                  javax.swing.JOptionPane.showMessageDialog(null,"Aucune évaluation sélectionnée");
        }
    }
    
    public void SaveEvaluations()
    {
        ArrayList<Evaluation> evaluationsToModify = new ArrayList<>();
        
        try
        {
            for(int ligne =0; ligne < m_tabEvaluations.getModel().getRowCount(); ligne++)
            {

                int note = Integer.parseInt(m_tabEvaluations.getModel().getValueAt(ligne, 0).toString());
                String date = m_tabEvaluations.getModel().getValueAt(ligne, 1).toString();

                evaluationsToModify.add(new Evaluation(m_listEvaluations.get(ligne).GetId(), m_idSujet, note, date));
            }    
             
            if (Data.EntregistrerListeEvaluation(evaluationsToModify))
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
            javax.swing.JOptionPane.showMessageDialog(null, "Erreur de saisie dans les données");
        }
    }
    
    public void RemoveEvaluation()
    {
        if (m_tabEvaluations.getSelectedRow() != -1)
        {
            if (Data.SupprimerEvaluation(new Evaluation(m_listEvaluations.get(m_tabEvaluations.getSelectedRow()).GetId(), m_idSujet, 0, "")))
            {
               Requery();
               javax.swing.JOptionPane.showMessageDialog(null, "Ligne supprimée");
            }
            else
            {
               javax.swing.JOptionPane.showMessageDialog(null, "Impossible de supprimer la ligne");
            }
        }
    }
    
    public void SaveCommentaires()
    {
        ArrayList<Commentaire> commentairesToModify = new ArrayList<>();
        
        try
        {
            for(int ligne =0; ligne < m_tabCommentaires.getModel().getRowCount(); ligne++)
            {
                String commentaire = m_tabCommentaires.getModel().getValueAt(ligne, 0).toString();
                String date = m_tabCommentaires.getModel().getValueAt(ligne, 1).toString();

                commentairesToModify.add(new Commentaire(m_listCommentaires.get(ligne).GetId(), -1, date, commentaire));
            }    
             
            if (Data.EntregistrerListeCommentaires(commentairesToModify))
            {
                int idEval = m_listEvaluations.get(m_tabEvaluations.getSelectedRow()).GetId();
                m_listCommentaires = Data.GetCommentairesById(idEval);
                RefreshTableCommentaires();
               javax.swing.JOptionPane.showMessageDialog(null, "Données enregistrées");
            }
            else
            {
               javax.swing.JOptionPane.showMessageDialog(null, "Impossible d'enregistrer les données");
            }
        }
        catch(Exception exc)
        {
            javax.swing.JOptionPane.showMessageDialog(null, "Erreur de saisie dans les données");
        }
    }
    
    public void RemoveCommentaire()
    {
        if (m_tabEvaluations.getSelectedRow() != -1)
        {
            if (Data.SupprimerCommentaire(new Commentaire(m_listCommentaires.get(m_tabCommentaires.getSelectedRow()).GetId(), -1, "", "")))
            {
                int idEval = m_listEvaluations.get(m_tabEvaluations.getSelectedRow()).GetId();
                m_listCommentaires = Data.GetCommentairesById(idEval);
                RefreshTableCommentaires();
               javax.swing.JOptionPane.showMessageDialog(null, "Ligne supprimée");
            }
            else
            {
               javax.swing.JOptionPane.showMessageDialog(null, "Impossible de supprimer la ligne");
            }
        }
    }
    
    public boolean IsNumeric(Object obj)
    {
        boolean returnValue = false;
        
        try
        {
            int tryP = Integer.parseInt(obj.toString());
            returnValue = true;
        }
        catch(Exception e){}
        
        return returnValue;
    }
     
    public void TabEvaluationsOnclick()
    {
        int idEval = m_listEvaluations.get(m_tabEvaluations.getSelectedRow()).GetId();
        
        m_listCommentaires = Data.GetCommentairesById(idEval);
        RefreshTableCommentaires();
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
            .addGap(0, 699, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
