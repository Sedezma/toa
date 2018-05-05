package toa;

import java.util.ArrayList;


public class Memoire extends Sujet{
    
    private String m_dateLivrable;
    
    
    public Memoire(int id, String nom, int idchef, int idtuteur, int idresponsable, String date, String dateLivrable )
    {super(id, nom, idchef, idtuteur,idresponsable, date);
        m_dateLivrable = dateLivrable;
    }
    
     public Memoire(int id, String idnom, int idchef, int idtuteur, int responsable, String date, String dateLivrable, String chef, String tuteur)
    {super(id, idnom, idchef, idtuteur,responsable, date);
        m_dateLivrable = dateLivrable;
        m_nometudiantchef = chef;
        m_nomtuteur = tuteur;
    }
     
    public String GetDateLivrable()
    {
        return m_dateLivrable;
    }
    
    public String GetNomEtudiantChef()
    {
        return m_nometudiantchef;
    }
    public String GetNomTuteur()
    {
        return m_nomtuteur;
    }
}
    
