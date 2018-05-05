package toa;

public class Groupe implements java.lang.Comparable<Groupe>{
    
    private int m_ID;
    private int m_numGroupe;
    private int m_idEtudiant;
    
    public Groupe(int id, int numgroupe, int idEtudiant)
    {
        m_ID = id;
        m_numGroupe = numgroupe;
        m_idEtudiant = idEtudiant;
    }
    
   public int GetId()
   {
       return m_ID;
   }
   
   public int GetNumGroupe()
   {
      return m_numGroupe;   
   }
   
   public int GetIdEtudiant()
   {
       return m_idEtudiant;
   }
    
   @Override public String toString()
   {
       return "" + m_numGroupe;
   }
   
   @Override public int compareTo(Groupe arg0)
   {
       if (m_numGroupe == arg0.GetNumGroupe())
       {
           return 0;
       }
       else if (m_numGroupe > arg0.GetNumGroupe())
       {
           return 1;
       }
       return -1;
   }
}
