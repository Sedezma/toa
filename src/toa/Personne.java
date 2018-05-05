package toa;

public abstract class Personne {
    
    protected int m_ID;
    protected String m_prenom;
    protected String m_nom;
    protected String m_email;
    
    public Personne(int id, String prenom, String nom,String mail)
    {
        m_ID = id;
        m_prenom = prenom;
        m_nom = nom;
        m_email = mail;
    }
    
    public int GetId()
    {
        return m_ID;
    }
    
     public String GetPrenom()
    {
        return m_prenom;
    }
     
    public String GetNom()
    {
        return m_nom;
    }
    
    public String GetMail()
    {
        return m_email;
    }
    
    @Override public String toString()
    {
        return m_prenom + " " + m_nom;
    }
}
