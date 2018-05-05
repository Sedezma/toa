package toa;


public class ResponsableEntreprise extends Personne{
    
    private String m_nomEntreprise;
    
    public ResponsableEntreprise(int id, String nomentreprise, String prenom, String nom, String mail)
    {super(id, prenom, nom, mail);
        m_nomEntreprise = nomentreprise;
    }
    
    public String GetNomEntreprise()
    {
        return m_nomEntreprise;
    }
    
    
}
