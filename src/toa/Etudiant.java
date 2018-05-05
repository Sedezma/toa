package toa;

public class Etudiant extends Personne{
    
    private String m_classe;
    private String m_promotion;
    
    public Etudiant(int id, String prenom, String nom, String mail, String classe, String promotion)
    {super(id, prenom, nom, mail);
        m_classe = classe;
        m_promotion = promotion;
    }
    
    public Etudiant(String prenom, String nom, String mail, String classe, String promotion)
    {super(-1, prenom, nom, mail);
        m_classe = classe;
        m_promotion = promotion;
    }
    
    public String GetClasse()
    {
        return m_classe;
    }
    
    public String GetPromotion()
    {
        return m_promotion;
    }
    
    
}
