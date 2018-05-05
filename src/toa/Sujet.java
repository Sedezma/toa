package toa;

import java.util.ArrayList;

public class Sujet {
    
    protected int m_ID;
    protected String m_nom;
    protected int m_IDchef;
    protected int m_IDprofTuteur;
    protected int m_IDresponsable;
    protected String m_dateTot;
    protected String m_dateTard;
    protected String m_nometudiantchef;
    protected String m_nomtuteur;
    
    public Sujet(int id, String nom, int idchef, int idtuteur, int idresponsable, String date)
    {
        m_ID = id;
        m_nom = nom;
        m_IDchef = idchef;
        m_IDprofTuteur = idtuteur;
        m_IDresponsable = idresponsable;
        m_dateTot = date;
        m_dateTard = date;
    }
    
    public int GetId()
    {
        return m_ID;
    }
    
    public String GetNom()
    {
        return m_nom;
    }
    
    public int GetIDChef()
    {
        return m_IDchef;
    }
    
    public int GetIDTuteur()
    {
        return m_IDprofTuteur;
    }
    
    public int GetIDResponsable()
    {
        return m_IDresponsable;
    }
    
    public String GetDateTot()
    {
        return m_dateTot;
    }
    
    public String GetDateFin()
    {
        return m_dateTard;
    }
    
    @Override public String toString()
    {
        return m_nom;
    }
}
