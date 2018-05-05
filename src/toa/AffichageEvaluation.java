
package toa;


public class AffichageEvaluation {
    
    private int m_iNote;
    private String m_sDateEval;
    private String m_sDernierCommentaire;
    private String m_sDateDernierCommentaire;
    
    public AffichageEvaluation(int note, String dateEvaluation, String dernierCommentaire, String dateDernierCommentaire)
    {
        m_iNote = note;
        m_sDateEval = dateEvaluation;
        m_sDernierCommentaire = dernierCommentaire;
        m_sDateDernierCommentaire = dateDernierCommentaire;
    }
    
    
    public int GetNoteEval()
    {
        return m_iNote;
    }
    
    public String GetDateEval()
    {
        return m_sDateEval;
    }
    
    public String GetLastCom()
    {
        return m_sDernierCommentaire;
    }
    
    public String GetLastDateCom()
    {
        return m_sDateDernierCommentaire;
    }
    
}
