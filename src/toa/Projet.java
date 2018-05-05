package toa;

import java.util.ArrayList;

public class Projet extends Sujet{
    private String m_classe;
    private int m_groupe;
    private String m_description;
    
    private String m_nomresponsable;
    
    public Projet(int id, String nom, int chef, int tuteur, int responsable, String date,  String description, String classe, int groupe)
    {super(id, nom, chef, tuteur,responsable, date);
        m_description = description;
        m_classe = classe;
        m_groupe = groupe;
    }
    
    public Projet(int id, String idnom, int idchef, int idtuteur, int idresponsable, String chef, String tuteur, String responsable, String date, String description, String classe, int groupe)
    {super(id, idnom, idchef, idtuteur,idresponsable, date);
        m_description = description;
        m_classe = classe;
        m_groupe = groupe;
        m_nometudiantchef = chef;
        m_nomtuteur = tuteur;
        m_nomresponsable = responsable;
        
    }
    public String GetNomEtudiantChef()
    {
        return m_nometudiantchef;
    }
    public String GetNomTuteur()
    {
        return m_nomtuteur;
    }
    public String GetNomResponsable()
    {
        return m_nomresponsable;
    }
    public String GetClasse()
    {
        return m_classe;
    }
    public int GetGroupe()
    {
        return m_groupe;
    }
     public String GetDescription()
    {
        return m_description;
    }
     
     
}
