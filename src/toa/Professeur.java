package toa;

public class Professeur extends Personne{
    
    private String m_matiere;
    private String m_mail;
    
    public Professeur(int id, String prenom, String nom, String matiere, String mail)
    {super(id, prenom, nom, mail);
        m_matiere = matiere;
        m_mail = mail;
    }

    
    public String GetMatiere()
    {
        return m_matiere;
    }
    
    
}
