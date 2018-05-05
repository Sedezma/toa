package toa;

import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JTable;

public final class Data {
    static private Connection connexion;
    static private Statement instruction = null;

    static JFrame m_parentInterface;
    
    static public void SetMainInterface(JFrame frame)
    {
        m_parentInterface = frame;
    }
    
    static public boolean Connexion()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //////////////////////////////LOCAL////////////////////////////////////////////////////
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/toa", "root", "");
            ///////////////////////////////////////////////////////////////////////////////////////
            
            //////////////////////////////HEBERGEMENT//////////////////////////////////////////////
            //db4.net
            //connexion = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/toaonline", "toazrwyu", "apzoeiru");
            //sql5.freemysqlhosting.net
            //connexion = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql579916", "sql579916", "bZ1%hC8!");
            ///////////////////////////////////////////////////////////////////////////////////////
            
            instruction = connexion.createStatement();
            return true;
        }
        catch (Exception exc)
        {
            javax.swing.JOptionPane.showMessageDialog(null,"Impossible de se connecter au serveur : " + exc);
        }
        
        return false;
        
    }
    
    /*public String[] AfficherParties () throws SQLException
    {
        String[] ListeParties;
        try {        
            Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
            }
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/echecs", "root", "");
            instruction = connexion.createStatement();
            ResultSet NbrParties = instruction.executeQuery("SELECT COUNT(*) FROM sauvegardepartie");
            int intParties=0;
            while (NbrParties.next()) 
            {
                intParties = NbrParties.getInt(1);
            }
            NbrParties.close();
            ListeParties = new String[intParties]; 
            nbrParties = intParties;
            ResultSet SelectParties = instruction.executeQuery("SELECT Sa_Nom FROM sauvegardepartie");
            int i = 0;
            while (SelectParties.next()) 
            {
                ListeParties[i] = SelectParties.getString("Sa_Nom");
                i++;
            }
            SelectParties.close();
        
        return ListeParties;
    }*/
    
    static public ArrayList<Projet> GenerateProjects(int iYearSelected)
    {
        ArrayList<Projet> returnList = new ArrayList();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet Selectprojets = instruction.executeQuery("SELECT P.Pro_idProjet, P.Pro_Intitule, P.Pro_IdEtuChefProj, P.Pro_IdProfTuteur, P.Pro_IdResp, "
                        + "(SELECT  CONCAT(E.Etu_Prenom,  CONCAT(' ' ,E.Etu_Nom)) FROM t_etudiant E "
                        + " WHERE E.Etu_idEtudiant = P.Pro_IdEtuChefProj) as NomEtu, (SELECT  CONCAT(En.Ens_Prenom,  CONCAT(' ', En.Ens_Nom)) FROM t_enseignant En"
                        + " WHERE En.Ens_IdEnseignant = P.Pro_IdProfTuteur) as NomEns,(SELECT  CONCAT(R.Res_Prenom,  CONCAT(' ', R.Res_Nom)) FROM t_responsable R"
                        + " WHERE R.Res_idResponsable = P.Pro_IdResp) as NomResp, P.Pro_DateSoutenance, P.Pro_Objectif, P.Pro_IdGroupe  FROM t_projet P "
                        + " WHERE YEAR(P.Pro_DateSoutenance) = " + iYearSelected
                        + " ORDER BY P.Pro_DateSoutenance");
                while (Selectprojets.next()) 
                {
                    int idProj = Selectprojets.getInt("P.Pro_idProjet");
                    String nomProj = Selectprojets.getString("P.Pro_Intitule");
                    int idetuChef = Selectprojets.getInt("P.Pro_IdEtuChefProj");
                    int idTuteur = Selectprojets.getInt("P.Pro_IdProfTuteur");
                    int idResponsable = Selectprojets.getInt("P.Pro_IdResp");
                    String etuChef = Selectprojets.getString("NomEtu");
                    String tuteur = Selectprojets.getString("NomEns");
                    String responsable = Selectprojets.getString("NomResp");
                    String dateSoutenance = new SimpleDateFormat("dd/MM/yyyy").format(Selectprojets.getDate("P.Pro_DateSoutenance"));
                    String description = Selectprojets.getString("P.Pro_Objectif");
                    String classe = "";
                    int groupe= Selectprojets.getInt("P.Pro_IdGroupe");
                    
                    returnList.add(new Projet(idProj, nomProj,idetuChef,idTuteur,idResponsable, etuChef, tuteur, responsable, dateSoutenance,description, classe, groupe));
                }
                
                        
                instruction.close();
                connexion.close();
            }
        } catch (SQLException ex) {
            javax.swing.JOptionPane.showMessageDialog(null,"" + ex);}
        
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
    static public ArrayList<Memoire> GenerateMemoires(int iYearSelected)
    {
        ArrayList<Memoire> returnList = new ArrayList();
        try {
            if (Connexion())
            {
                
                ResultSet Selectmemoires = instruction.executeQuery("SELECT M.Mem_idMemoire, M.Mem_Intitule, M.Mem_IdEtuChefProj, M.Mem_IdProfTuteur, M.Mem_IdResp, "                                        
                + " (SELECT  CONCAT(E.Etu_Prenom,  CONCAT(' ' ,E.Etu_Nom)) FROM "                    
                + " t_etudiant E   WHERE E.Etu_idEtudiant = M.Mem_IdEtuChefProj) as NomEtu, "
                + " (SELECT  CONCAT(En.Ens_Prenom,  CONCAT(' ', En.Ens_Nom)) FROM t_enseignant En "
                + " WHERE En.Ens_IdEnseignant = M.Mem_IdProfTuteur) as NomEns, "
                + "(SELECT  CONCAT(R.Res_Prenom,  CONCAT(' ', R.Res_Nom)) FROM t_responsable R  WHERE R.Res_idResponsable = M.Mem_IdResp) as NomResp, "
                + " M.Mem_DateLivrable,M.Mem_DateSoutenance  FROM t_memoire M "
                + " WHERE YEAR(M.Mem_DateSoutenance) = " + iYearSelected
                + " ORDER BY M.Mem_DateSoutenance");
                while (Selectmemoires.next()) 
                {
                    int idMem = Selectmemoires.getInt("M.Mem_idMemoire");
                    String nomMem = Selectmemoires.getString("M.Mem_Intitule");
                    int idetuChef = Selectmemoires.getInt("M.Mem_IdEtuChefProj");
                    int idTuteur = Selectmemoires.getInt("M.Mem_IdProfTuteur");
                    int idResponsable = Selectmemoires.getInt("M.Mem_IdResp");
                    String etuChef = Selectmemoires.getString("NomEtu");
                    String tuteur = Selectmemoires.getString("NomEns");
                    String dateSoutenance = new SimpleDateFormat("dd/MM/yyyy").format(Selectmemoires.getDate("M.Mem_DateSoutenance"));
                    String dateLivrable = new SimpleDateFormat("dd/MM/yyyy").format(Selectmemoires.getDate("M.Mem_DateLivrable"));
                    
                    returnList.add(new Memoire(idMem, nomMem, idetuChef,idTuteur,idResponsable, dateSoutenance,dateLivrable, etuChef, tuteur));
                }
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        
        return returnList;
            
    }
    
    static public ArrayList<Etudiant> GenerateEtudiants()
    {
        ArrayList<Etudiant> returnList = new ArrayList();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet selectetudiants = instruction.executeQuery("SELECT * FROM t_etudiant");

                while (selectetudiants.next()) 
                {
                    int idetudiant = selectetudiants.getInt("Etu_idEtudiant");
                    String nometu =  selectetudiants.getString("Etu_Nom");
                    String prenometu = selectetudiants.getString("Etu_Prenom");
                    String mailetu = selectetudiants.getString("Etu_Email");
                    String classeetu = selectetudiants.getString("Etu_Classe");
                    String promotionetu = selectetudiants.getString("Etu_Promotion");
                    
                    returnList.add(new Etudiant(idetudiant, prenometu, nometu, mailetu, classeetu, promotionetu));
                }
                selectetudiants.close();
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
    // Génération des étudiant par classe
     static public ArrayList<Etudiant> GeneratePromoEtudiants(int promo)
    {
        ArrayList<Etudiant> returnList = new ArrayList();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        String classe="";
        try {
            if (Connexion())
            {   
                if (promo == 1)
                { classe = "L3"; }
                else if (promo == 2)
                { classe = "M1";}
                else if (promo == 3)
                { classe = "M2"; }
                // resultset est le resultat de la requete select
                ResultSet selectetudiants = instruction.executeQuery("SELECT * FROM t_etudiant WHERE Etu_Classe ='" + classe + "'");
                //parcours du tableau revoyait par la requete
                while (selectetudiants.next()) 
                {
                    int idetudiant = selectetudiants.getInt("Etu_idEtudiant");
                    String nometu =  selectetudiants.getString("Etu_Nom");
                    String prenometu = selectetudiants.getString("Etu_Prenom");
                    String mailetu = selectetudiants.getString("Etu_Email");
                    String classeetu = selectetudiants.getString("Etu_Classe");
                    String promotionetu = selectetudiants.getString("Etu_Promotion");
                    //ak=jout dans liste courante
                    returnList.add(new Etudiant(idetudiant, prenometu, nometu, mailetu, classeetu, promotionetu));
                }
                selectetudiants.close();
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
        return returnList;
    }
    static public ArrayList<Professeur> GenerateProf()
    {
        ArrayList<Professeur> returnList = new ArrayList();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet selectenseignants = instruction.executeQuery("SELECT * FROM t_enseignant");

                while (selectenseignants.next()) 
                {
                    int idprof = selectenseignants.getInt("Ens_IdEnseignant");
                    String nomprof =  selectenseignants.getString("Ens_Nom");
                    String prenomprof = selectenseignants.getString("Ens_Prenom");
                    String matprof = selectenseignants.getString("Ens_Matiere");
                    String mailprof = selectenseignants.getString("Ens_Email");
                    
                    returnList.add(new Professeur(idprof, prenomprof, nomprof, matprof, mailprof));
                }
                selectenseignants.close();
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
    
    static public boolean EnregistrerEtudiant(Etudiant etu)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("INSERT INTO t_etudiant(Etu_Nom, Etu_Prenom, Etu_Email, Etu_Classe, Etu_Promotion) VALUES " +
                "('"+ etu.GetNom() + "', '" + etu.GetPrenom()+ "', '"+ etu.GetMail()+"', '"+etu.GetClasse() + "', '" + etu.GetPromotion() + "')");
                returnValue = true;
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    static public boolean EnregistrerProjet(Projet proj)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        String req = "INSERT INTO t_projet( Pro_Intitule, Pro_DateSoutenance, Pro_IdEtuChefProj, Pro_IdProfTuteur, Pro_IdResp, Pro_DateAuPlusTôt, Pro_DateAuPlusTard, Pro_Objectif, Pro_IdGroupe) VALUES " +
                "( '"+ proj.GetNom() + "', '" + proj.GetDateFin()+ "', " + proj.GetIDChef() + ", " + proj.GetIDTuteur() + ", " +proj.GetIDResponsable() +",'"+ proj.GetDateFin() + "', '" + proj.GetDateFin() + "','"+ proj.GetDescription()+"', "+ proj.GetGroupe()+")";
        try {
            if (Connexion())
            {
                instruction.executeUpdate(req);
                returnValue = true;
            }
        } catch (SQLException ex) {javax.swing.JOptionPane.showMessageDialog(null,"Erreur : " + ex + "/"+ req);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    static public boolean EnregistrerMemoire(Memoire memo)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        String req ="INSERT INTO t_memoire( Mem_Intitule, Mem_IdEtuChefProj, Mem_IdProfTuteur, Mem_IdResp, Mem_DateLivrable, Mem_DateSoutenance) VALUES " +
                "( '"+ memo.GetNom() + "', '" + memo.GetIDChef() + "', '" + memo.GetIDTuteur() + "', '" +memo.GetIDResponsable() +"','"+ memo.GetDateLivrable() + "', '" + memo.GetDateFin()+"')";
        try {
            if (Connexion())
            {
                instruction.executeUpdate(req);
                returnValue = true;
            }
        } catch (SQLException ex) {javax.swing.JOptionPane.showMessageDialog(null,"Erreur : " + ex + "/"+ req);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        
        return returnValue;
    }
    
    static public boolean SupprimerEtudiant(Etudiant etu)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_etudiant WHERE Etu_IdEtudiant = "+ etu.GetId() + "");
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
    //************************  Recherche  **************************************************
    static public boolean RechercheEtudiant(Etudiant etu)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_etudiant WHERE Etu_IdEtudiant = "+ etu.GetId() + "");
                //instruction.executeUpdate("SELECT * FROM t_etudiant WHERE Etu_IdEtudiant = "+ etu.GetId() + "");
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    //***************************** Recherche pour ajout **************************************
    
    static public boolean RecupEtudiant(Etudiant etu)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("SELECT * FROM t_etudiant WHERE Etu_Nom = "+ etu.GetId() + "");
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
    //***********************************************************************************
    
    static public boolean EntregistrerListeEtudiants(ArrayList<Etudiant> listeEtu)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                for(int i = 0; i < listeEtu.size(); i++)
                {
                    instruction.executeUpdate("UPDATE t_etudiant SET Etu_Nom = '" + listeEtu.get(i).GetNom() +"', Etu_Prenom = '" + listeEtu.get(i).GetPrenom() + "', " +
                    "Etu_Email = '" + listeEtu.get(i).GetMail() + "', Etu_Classe = '" + listeEtu.get(i).GetClasse() + "', Etu_Promotion = '" + listeEtu.get(i).GetPromotion() + "' WHERE " +
                    "Etu_idEtudiant = " + listeEtu.get(i).GetId() + "");
                }
                
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
    static public boolean EntregistrerListeProfesseurs(ArrayList<Professeur> listeProf)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                for(int i = 0; i < listeProf.size(); i++)
                {
                    instruction.executeUpdate("UPDATE t_enseignant SET Ens_Nom = '" + listeProf.get(i).GetNom() +"', Ens_Prenom = '" + listeProf.get(i).GetPrenom() + "', " +
                    "Ens_Email = '" + listeProf.get(i).GetMail() + "', Ens_Matiere = '" + listeProf.get(i).GetMatiere() + "' WHERE Ens_IdEnseignant = " + listeProf.get(i).GetId() + "");
                }
                
                returnValue = true;
            }
        } catch (Exception ex) {
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
     static public boolean SupprimerEnseignant(Professeur prof)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_enseignant WHERE Ens_IdEnseignant = "+ prof.GetId() + "");
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
     
     static public boolean EnregistrerProfesseur(Professeur prof)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("INSERT INTO t_enseignant(Ens_Nom, Ens_Prenom, Ens_Matiere, Ens_Email) VALUES " +
                "('"+ prof.GetNom() + "', '" + prof.GetPrenom()+ "', '"+ prof.GetMatiere()+"', '"+prof.GetMail() + "')");
                returnValue = true;
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
     static public ArrayList<ResponsableEntreprise> GenerateResponsables()
    {
        ArrayList<ResponsableEntreprise> returnList = new ArrayList();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet selectenseignants = instruction.executeQuery("SELECT * FROM t_responsable");

                while (selectenseignants.next()) 
                {
                    int idresp = selectenseignants.getInt("Res_idResponsable");
                    String nomentreprise =  selectenseignants.getString("Res_NomEntreprise");
                    String prenomresp = selectenseignants.getString("Res_Prenom");
                    String nomresp = selectenseignants.getString("Res_Nom");
                    String mailresp = selectenseignants.getString("Res_Email");
                    
                    returnList.add(new ResponsableEntreprise(idresp, nomentreprise, prenomresp, nomresp, mailresp));
                }
                selectenseignants.close();
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
     
    static public boolean SupprimerResponsable(ResponsableEntreprise responsable)
    {
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_responsable WHERE Res_idResponsable = "+ responsable.GetId() + "");
                return true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return false;
    }
    
    static public boolean EnregistrerResponsable(ResponsableEntreprise responsable)
    {
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("INSERT INTO t_responsable(Res_NomEntreprise, Res_Nom, Res_Prenom, Res_Email) VALUES " +
                "('"+ responsable.GetNomEntreprise()+"', '"+ responsable.GetNom() + "', '" + responsable.GetPrenom()+ "', '"+responsable.GetMail() + "')");
                return true;
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return false;
    }
    
    static public boolean EntregistrerListeResponsables(ArrayList<ResponsableEntreprise> listeResp)
    {
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                for(int i = 0; i < listeResp.size(); i++)
                {
                    instruction.executeUpdate("UPDATE t_responsable SET Res_NomEntreprise = '" + listeResp.get(i).GetNomEntreprise() +"', Res_Nom = '" + listeResp.get(i).GetNom() + "', " +
                    "Res_Prenom = '" + listeResp.get(i).GetPrenom() + "', Res_Email = '" + listeResp.get(i).GetMail() + "' WHERE Res_IdResponsable = " + listeResp.get(i).GetId() + "");
                }
                
                return true;
            }
        } catch (Exception ex) {
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return false;
    }
    
    static public ArrayList<Groupe> GenerateGroupes()
    {
        ArrayList<Groupe> returnList = new ArrayList();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet selectenseignants = instruction.executeQuery("SELECT * FROM t_groupe");

                while (selectenseignants.next()) 
                {
                    int idgroupe = selectenseignants.getInt("Gr_Id");
                    int numgroupe = selectenseignants.getInt("Gr_NumGroupe");
                    int idetudiant = selectenseignants.getInt("Gr_IdEtudiant");
                    
                    returnList.add(new Groupe(idgroupe, numgroupe, idetudiant));
                }
                selectenseignants.close();
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
     
    static public boolean EnregistrerGroupe(Groupe group)
    {
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("INSERT INTO t_groupe(Gr_NumGroupe, Gr_IdEtudiant) VALUES " +
                "("+ group.GetNumGroupe() + ", " + group.GetIdEtudiant()+ ")");
                return true;
            }
        } catch (SQLException ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return false;
    }
     
    static public boolean SupprimerMembreGroupe(Groupe groupe)
    {
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_groupe WHERE Gr_Id = "+ groupe.GetId() + "");
                return true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return false;
    }
     
    static public boolean EntregistrerListeGroupe(ArrayList<Groupe> listegroupe)
    {
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                for(int i = 0; i < listegroupe.size(); i++)
                {
                    instruction.executeUpdate("UPDATE t_groupe SET Gr_NumGroupe = '" + listegroupe.get(i).GetNumGroupe() +"', Gr_IdEtudiant = '" + listegroupe.get(i).GetIdEtudiant() + "' " +
                    "WHERE Gr_Id = " + listegroupe.get(i).GetId() + "");
                }
                
                return true;
            }
        } catch (Exception ex) {
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return false;
    }
     
    static public ArrayList<Etudiant> GenerateEtudiantsInGroupe(int numgroupe)
    {
        ArrayList<Etudiant> returnList = new ArrayList();
        try {
            if (Connexion())
            {
                ResultSet selectEtudiants = instruction.executeQuery("SELECT E.Etu_idEtudiant, E.Etu_Nom ,E.Etu_Prenom FROM t_groupe Gr, t_etudiant E WHERE E.Etu_idEtudiant = Gr.Gr_IdEtudiant AND Gr_NumGroupe = " + numgroupe);

                while (selectEtudiants.next()) 
                {
                    int id = selectEtudiants.getInt("E.Etu_idEtudiant");
                    String nom = selectEtudiants.getString("E.Etu_Nom");
                    String prenom = selectEtudiants.getString("E.Etu_Prenom");
                    
                    returnList.add(new Etudiant(id, prenom, nom, "","",""));
                }
                selectEtudiants.close();
            }
        } catch (Exception ex) {javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + ex);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        
        return returnList;
    }
     
    static public boolean AjouterEtudiantAuGroupe(int groupeNumber, int numEtudiant)
    {
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("INSERT INTO t_groupe (Gr_NumGroupe, Gr_IdEtudiant) VALUES (" + groupeNumber + ", " + numEtudiant + ")");
                
                return true;
            }
        } catch (Exception ex) {
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return false;
    }
    
    static public boolean RetirerEtudiantAuGroupe(int groupeNumber, int numEtudiant)
    {
        try {
            if (Connexion())
            {
               
                instruction.executeUpdate("DELETE FROM t_groupe WHERE Gr_NumGroupe = " + groupeNumber + " AND Gr_IdEtudiant = " + numEtudiant);
                return true;
            }
        } catch (Exception ex) {
             javax.swing.JOptionPane.showMessageDialog(null,"" + ex);
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return false;
    }
    
    static public boolean SupprimerGroupe(int groupeNumber)
    {
        try {
            if (Connexion())
            {
               
                instruction.executeUpdate("DELETE FROM t_groupe WHERE Gr_NumGroupe = " + groupeNumber);
                return true;
            }
        } catch (Exception ex) {
             javax.swing.JOptionPane.showMessageDialog(null,"" + ex);
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return false;
    }
    
    
    static public Etudiant GenerateChefProject(int numProject, int numgroupe)
    {
        Etudiant returnEtu = null;
        try {
            if (Connexion())
            {
                ResultSet selectEtudiants = instruction.executeQuery("SELECT E.Etu_idEtudiant, E.Etu_Nom ,E.Etu_Prenom FROM t_groupe Gr, t_etudiant E , t_projet P WHERE E.Etu_idEtudiant = Gr.Gr_IdEtudiant AND P.Pro_IdEtuChefProj = E.Etu_idEtudiant "
                        + " AND Gr.Gr_NumGroupe = " + numgroupe + " AND P.Pro_idProjet = " + numProject);

                if (selectEtudiants.next()) 
                {
                    int id = selectEtudiants.getInt("E.Etu_idEtudiant");
                    String nom = selectEtudiants.getString("E.Etu_Nom");
                    String prenom = selectEtudiants.getString("E.Etu_Prenom");
                    
                    returnEtu = new Etudiant(id, prenom, nom, "","","");
                }
                selectEtudiants.close();
            }
        } catch (Exception ex) {javax.swing.JOptionPane.showMessageDialog(null,"Erreur :" + ex);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(SQLException e) {}
        }
        
        return returnEtu;
    }
    
    static public Projet GenerateProject(int ID)
    {
        Projet returnProjet = null;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet Selectprojet = instruction.executeQuery("SELECT P.Pro_Intitule, P.Pro_IdEtuChefProj, P.Pro_IdProfTuteur, P.Pro_IdResp, "
                        + " P.Pro_DateSoutenance, P.Pro_Objectif, P.Pro_IdGroupe  FROM t_projet P "
                        + " WHERE P.Pro_idProjet = " + ID);
                while (Selectprojet.next()) 
                {
                    String nomProj = Selectprojet.getString("P.Pro_Intitule");
                    int idetuChef = Selectprojet.getInt("P.Pro_IdEtuChefProj");
                    int idTuteur = Selectprojet.getInt("P.Pro_IdProfTuteur");
                    int idResponsable = Selectprojet.getInt("P.Pro_IdResp");
                    String dateSoutenance = new SimpleDateFormat("dd/MM/yyyy").format(Selectprojet.getDate("P.Pro_DateSoutenance"));
                    String description = Selectprojet.getString("P.Pro_Objectif");
                    String classe = "";
                    int groupe= Selectprojet.getInt("P.Pro_IdGroupe");
                    
                    //returnProjet  = new Projet(idProj, nomProj,idetuChef,idTuteur,idResponsable, etuChef, tuteur, responsable, dateSoutenance,description, classe, groupe);
                    returnProjet  = new Projet(ID, nomProj,idetuChef,idTuteur,idResponsable, dateSoutenance,description, classe, groupe);
                }
                
                        
                instruction.close();
                connexion.close();
            }
        } catch (SQLException ex) {}
        
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnProjet;
    }
    
    static public Memoire GenerateMemoire(int ID)
    {
        Memoire returnMemoire = null;
        try {
            if (Connexion())
            {
                
                ResultSet Selectmemoires = instruction.executeQuery("SELECT M.Mem_idMemoire, M.Mem_Intitule, M.Mem_IdEtuChefProj, M.Mem_IdProfTuteur, M.Mem_IdResp, "
                + " M.Mem_DateLivrable,M.Mem_DateSoutenance  FROM t_memoire M "
                + " WHERE M.Mem_idMemoire = " + ID);
                while (Selectmemoires.next()) 
                {
                    String nomMem = Selectmemoires.getString("M.Mem_Intitule");
                    int idetuChef = Selectmemoires.getInt("M.Mem_IdEtuChefProj");
                    int idTuteur = Selectmemoires.getInt("M.Mem_IdProfTuteur");
                    int idResponsable = Selectmemoires.getInt("M.Mem_IdResp");
                    String dateSoutenance = new SimpleDateFormat("dd/MM/yyyy").format(Selectmemoires.getDate("M.Mem_DateSoutenance"));
                    String dateLivrable = new SimpleDateFormat("dd/MM/yyyy").format(Selectmemoires.getDate("M.Mem_DateLivrable"));
                    
                    returnMemoire = new Memoire(ID, nomMem, idetuChef,idTuteur,idResponsable, dateSoutenance,dateLivrable);
                }
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        return returnMemoire;
            
    }
     
    static public boolean Register(Utilisateur user)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet preventDoublon = instruction.executeQuery("SELECT Use_Login FROM t_user WHERE Use_Login = '" + user.GetLogin() + "'");
                if (preventDoublon.next()) 
                {
                    javax.swing.JOptionPane.showMessageDialog(null,"Le compte est déjà utilisé");
                }
                else
                {
                    instruction.executeUpdate("INSERT INTO t_user (Use_Nom, Use_Prenom, Use_Login, Use_Mdp, Use_Statut) VALUES ('" + user.GetNom() + "', '" + user.GetPrenom() + "', '"
                        + user.GetLogin() + "', '" + user.GetPassword() + "', '" + user.GetStatus() + "')");
                    returnValue = true;
                }
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
     
     
    static public Utilisateur TryConnection(String login, String password)
    {
        Utilisateur returnUser = null;
        try {
            if (Connexion())
            {
                
                ResultSet rSearchUser = instruction.executeQuery("SELECT Use_Id, Use_Nom, Use_Prenom, Use_Login, Use_Mdp, Use_Statut FROM t_user WHERE Use_Login ='"
                        + login + "' AND Use_Mdp = '" + password + "'");
                if (rSearchUser.next()) 
                {
                    int idUser = rSearchUser.getInt("Use_Id");
                    String sUserNom = rSearchUser.getString("Use_Nom");
                    String sUderPrenom = rSearchUser.getString("Use_Prenom");
                    String sUserLogin = rSearchUser.getString("Use_Login");
                    String sUserPassword = rSearchUser.getString("Use_Mdp");
                    String sUserStatus = rSearchUser.getString("Use_Statut");
                    
                    returnUser = new Utilisateur(idUser, sUserNom, sUderPrenom, sUserLogin, sUserPassword, sUserStatus);
                }
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
     
       return returnUser;
    }
    
    static public boolean ModifyUser(Utilisateur user)
    {
        boolean returnValue = false;
        
        try {
            if (Connexion())
            {
                
                instruction.executeUpdate("UPDATE t_user SET Use_Nom = '" + user.GetNom() + "'," +
                        " Use_Prenom = '" + user.GetPrenom() + "'," +
                        " Use_Login = '" + user.GetLogin() + "'," +
                        " Use_Mdp = '" + user.GetPassword() + "'," +
                        " Use_Statut = '" + user.GetStatus() + "'" +
                        "WHERE Use_Id = " + user.GetId());
                
                returnValue = true;
                
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
     
       return returnValue;
    }
    
    static public ArrayList<Evaluation> GetEvaluationsById(int idProjet)
    {
        ArrayList<Evaluation> returnList = new ArrayList<>();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet rSelectEvaluations = instruction.executeQuery("SELECT Eva_idEvaluation, Eva_IdProjet, Eva_Note, Eva_Date FROM t_evaluation " 
                        + " WHERE Eva_IdProjet = " + idProjet + " ORDER BY Eva_Note DESC");
                while (rSelectEvaluations.next()) 
                {
                    int id = rSelectEvaluations.getInt("Eva_idEvaluation");
                    int intIdProjet = rSelectEvaluations.getInt("Eva_IdProjet");
                    int note = rSelectEvaluations.getInt("Eva_Note");
                    String date = rSelectEvaluations.getString("Eva_Date");
                    
                    returnList.add(new Evaluation(id, intIdProjet, note, date));
                }
                
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
    
    static public ArrayList<Commentaire> GetCommentairesById(int idEval)
    {
        ArrayList<Commentaire> returnList = new ArrayList<>();
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                ResultSet rSelectEvaluations = instruction.executeQuery("SELECT Com_idCommentaire, Com_IdEvaluation, Com_DateCommentaire, Com_Commentaire FROM t_commentaire "
                        + "WHERE Com_IdEvaluation = " + idEval + " ORDER BY Com_DateCommentaire DESC");
                while (rSelectEvaluations.next()) 
                {
                    int id = rSelectEvaluations.getInt("Com_idCommentaire");
                    int idEvaluation = rSelectEvaluations.getInt("Com_IdEvaluation");
                    String dateCom = rSelectEvaluations.getString("Com_DateCommentaire");
                    String commentaire = rSelectEvaluations.getString("Com_Commentaire");
                    
                    returnList.add(new Commentaire(id, idEvaluation, dateCom, commentaire));
                }
                
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnList;
    }
    
    static public boolean RecordEvaluation(Evaluation eval)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                
                instruction.executeUpdate("INSERT INTO t_evaluation (Eva_IdProjet, Eva_Note, Eva_Date) VALUES (" + eval.GetIdProjet() + ", " + eval.GetNote() + ", '"
                        + eval.GetDate() + "')");
                returnValue = true;
                
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
    static public boolean RecordCommentaire(Commentaire com)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                instruction.executeUpdate("INSERT INTO t_commentaire (Com_IdEvaluation, Com_DateCommentaire, Com_Commentaire) VALUES (" + com.GetIdEvaluation() + ", '" + com.GetDate() + "', '"
                        + com.GetCommentaire() + "')");
                returnValue = true;
                
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {}
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
    static public ArrayList<AffichageEvaluation> VisualizeEvaluations(int idProjet)
    {
        ArrayList<AffichageEvaluation> returnList = new ArrayList<>();
        
        try {
            if (Connexion())
            {
                ResultSet rSelectAffichageEvaluations = instruction.executeQuery("SELECT E.Eva_Note, E.Eva_Date, "
                    + "(SELECT C.Com_DateCommentaire FROM t_commentaire C WHERE E.Eva_idEvaluation = C.Com_IdEvaluation ORDER BY C.Com_DateCommentaire DESC LIMIT 1) AS Com_DateCommentaire, "
                    + "(SELECT C.Com_Commentaire FROM t_commentaire C WHERE E.Eva_idEvaluation = C.Com_IdEvaluation ORDER BY C.Com_DateCommentaire DESC LIMIT 1) AS Com_Commentaire "
                    + "FROM t_evaluation E "
                    + "WHERE  E.Eva_IdProjet = " + idProjet + " ORDER BY E.Eva_Date DESC");
                while (rSelectAffichageEvaluations.next()) 
                {
                    int iNote = rSelectAffichageEvaluations.getInt("E.Eva_Note");
                    String sDateEval = rSelectAffichageEvaluations.getString("E.Eva_Date");
                    String sCom = rSelectAffichageEvaluations.getString("Com_Commentaire");
                    String sDateCom = rSelectAffichageEvaluations.getString("Com_DateCommentaire");
                    
                    returnList.add(new AffichageEvaluation(iNote, sDateEval, sCom, sDateCom));
                }
                
                instruction.close();
                connexion.close();
            }
        } catch (Exception ex) {
           javax.swing.JOptionPane.showMessageDialog(null,"" + ex);}
        
        return returnList;
    }
    
    static public boolean EntregistrerListeEvaluation(ArrayList<Evaluation> listeEval)
    {
        boolean returnValue = false;
        
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                for(int i = 0; i < listeEval.size(); i++)
                {
                    instruction.executeUpdate("UPDATE t_evaluation SET Eva_Note = " + listeEval.get(i).GetNote() +", Eva_Date = '" + listeEval.get(i).GetDate() + "' "
                            + " WHERE Eva_idEvaluation = " + listeEval.get(i).GetId() + "");
                }
                
                returnValue = true;
            }
        } catch (Exception ex) {
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
    static public boolean SupprimerEvaluation(Evaluation eval)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_evaluation WHERE Eva_idEvaluation = "+ eval.GetId() + "");
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
    
    static public boolean EntregistrerListeCommentaires(ArrayList<Commentaire> listeCom)
    {
        boolean returnValue = false;
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        }
        try {
            if (Connexion())
            {
                for(int i = 0; i < listeCom.size(); i++)
                {
                    instruction.executeUpdate("UPDATE t_commentaire SET Com_DateCommentaire = '" + listeCom.get(i).GetDate() +"', Com_Commentaire = '" + listeCom.get(i).GetCommentaire() + "' "
                            + " WHERE Com_idCommentaire = " + listeCom.get(i).GetId() + "");
                }
                
                returnValue = true;
            }
        } catch (Exception ex) {
        }
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        if (m_parentInterface != null)
        {
            m_parentInterface.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        return returnValue;
    }
    
    static public boolean SupprimerCommentaire(Commentaire commentaire)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_commentaire WHERE Com_idCommentaire = "+ commentaire.GetId() + "");
                returnValue = true;
            }
        } catch (Exception ex) {}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
    
    static public boolean UpdateProject(Projet proj)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("UPDATE t_projet SET Pro_Intitule = '" + proj.GetNom() + "',"
                + "Pro_DateSoutenance = '" + proj.GetDateFin() + "', Pro_IdEtuChefProj = " + proj.GetIDChef() + ", "
                + "Pro_IdProfTuteur = " + proj.GetIDTuteur() + ", Pro_IdResp = " + proj.GetIDResponsable() +","
                + "Pro_Objectif = '" + proj.GetDescription() + "', Pro_IdGroupe = " + proj.GetGroupe()
                + " WHERE Pro_idProjet = " + proj.GetId());
                returnValue = true;
            }
        } catch (Exception ex) {
                 javax.swing.JOptionPane.showMessageDialog(null,"" + ex);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
    
    static public boolean UpdateMemoire(Memoire mem)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("UPDATE t_memoire SET  Mem_Intitule = '" + mem.GetNom() + "', "
                + "Mem_IdEtuChefProj = " + mem.GetIDChef() + ", "
                + "Mem_IdProfTuteur = " + mem.GetIDTuteur() + ", Mem_IdResp = " + mem.GetIDResponsable() +","
                + "Mem_DateLivrable = '" + mem.GetDateLivrable() + "', Mem_DateSoutenance = '" + mem.GetDateFin() + "'"
                + " WHERE Mem_idMemoire = " + mem.GetId());
                returnValue = true;
            }
        } catch (Exception ex) {
                 javax.swing.JOptionPane.showMessageDialog(null,"" + ex);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
    
    static public boolean RemoveProjet(int idprojet)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_projet WHERE Pro_idProjet = " + idprojet + "");
                returnValue = true;
            }
        } catch (Exception ex) {
                 javax.swing.JOptionPane.showMessageDialog(null,"" + ex);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
    
    static public boolean RemoveMemoire(int idMemoire)
    {
        boolean returnValue = false;
        try {
            if (Connexion())
            {
                instruction.executeUpdate("DELETE FROM t_memoire WHERE Mem_idMemoire = " + idMemoire + "");
                returnValue = true;
            }
        } catch (Exception ex) {
                 javax.swing.JOptionPane.showMessageDialog(null,"" + ex);}
        finally{
            try {
                instruction.close();
                connexion.close();
            }catch(Exception e) {}
        }
        return returnValue;
    }
}
