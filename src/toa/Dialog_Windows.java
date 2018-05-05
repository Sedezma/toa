package toa;

import javax.swing.JPanel;

public class Dialog_Windows extends javax.swing.JDialog {

    protected int m_width;
    protected int m_height;
    
    protected JPanel m_container;
    
    protected java.awt.Frame m_parent;
    
    public Dialog_Windows(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        SetWidthHeight();
        m_parent = parent;
        setSize(m_width, m_height);
        setResizable(false);
        
        m_container = new JPanel();
        javax.swing.GroupLayout m_containerLayout = new javax.swing.GroupLayout(m_container);
        m_container.setLayout(m_containerLayout);
        m_containerLayout.setHorizontalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_width, Short.MAX_VALUE)
        );
        m_containerLayout.setVerticalGroup(
            m_containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, m_height, Short.MAX_VALUE)
        );
        m_container.setBounds(0, 0, m_width, m_height);
        add(m_container);
    }

    public void SetWidthHeight()
    {
        m_width = 800;
        m_height = 600;
    }
    
    public int GetWidth()
    {
        return m_width;
    }
    
    public int GetHeight()
    {
        return m_height;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 858, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
