package toa;

public class Commentaire {
    
    private int m_id;
    private int m_idEvaluation;
    private String m_sDate;
    private String m_sCommentaire;
    
    public Commentaire(int id, int idEval, String date, String commentaire)
    {
        m_id = id;
        m_idEvaluation = idEval;
        m_sDate = date;
        m_sCommentaire = commentaire;
    }
    
    public int GetId()
    {
        return m_id;
    }
    
    public int GetIdEvaluation()
    {
        return m_idEvaluation;
    }
    
    public String GetDate()
    {
        return m_sDate;
    }
    
    public String GetCommentaire()
    {
        return m_sCommentaire;
    }
}
