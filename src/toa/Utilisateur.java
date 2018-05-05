package toa;

public class Utilisateur {
    
    private int m_id;
    private String m_nom;
    private String m_prenom;
    private String m_login;
    private String m_password;
    private String m_status;
    
    public Utilisateur(int ID, String nom, String prenom,  String login, String password, String status)
    {
        m_id = ID;
        m_nom = nom;
        m_prenom = prenom;
        m_login = login;
        m_password = password;
        m_status = status;
    }
    
    public int GetId()
    {
        return m_id;
    }
    
    public void SetId(int id)
    {
        m_id = id;
    }
    
    public String GetNom()
    {
        return m_nom;
    }
    
    public void SetNom(String nom)
    {
        m_nom = nom;
    }
    
    public String GetPrenom()
    {
        return m_prenom;
    }
     
    public void SetPrenom(String prenom)
    {
        m_prenom = prenom;
    }
    
    public String GetLogin()
    {
        return m_login;
    }
    
    public void SetLogin(String login)
    {
        m_login = login;
    }
    
     public String GetPassword()
    {
        return m_password;
    }
     
    public void SetPassword(String password)
    {
        m_password = password;
    }
    
    public String GetStatus()
    {
        return m_status;
    }
    
    public void SetStatus(String status)
    {
        m_status = status;
    }
    
    public String toString()
    {
        return m_prenom + " " + m_nom;
    }
}
