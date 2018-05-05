package toa;

public class Evaluation {
    
    private int m_id;
    private int m_idProjet;
    private int m_note;
    private String m_date;
    
    public Evaluation(int id, int idProject, int note, String date)
    {
        m_id = id;
        m_idProjet = idProject;
        m_note = note;
        m_date = date;
    }
    
    public int GetId()
    {
        return m_id;
    }
    
    public int GetIdProjet()
    {
        return m_idProjet;
    }
    
    public int GetNote()
    {
        return m_note;
    }
    
    public String GetDate()
    {
        return m_date;
    }
    
}
