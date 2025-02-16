package component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import saveinformation.SecurityContext;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        setOpaque(false);
        lblUserName.setText("Welcome back, " + SecurityContext.getCurrentUser().getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUserName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lblUserName.setFont(new java.awt.Font("SansSerif", 3, 12)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(127, 127, 127));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(329, Short.MAX_VALUE)
                .addComponent(lblUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUserName, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(0, 0, 25, getHeight());
        g2.fillRect(getWidth()-25, getHeight()-25, getWidth(), getHeight());
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblUserName;
    // End of variables declaration//GEN-END:variables
}
