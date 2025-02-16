package component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import display.model.Model_Card;

public class Card extends javax.swing.JPanel {

    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    private Color color1;
    private Color color2;

    public Card() {
        initComponents();
        setOpaque(false);
        color1 = Color.BLACK;
        color2 = Color.WHITE;
    }

    public void setData(Model_Card data) {
        cardIcon.setIcon(data.getIcon());
        cardTitle.setText(data.getTitle());
        cardValue.setText(data.getValue());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardIcon = new javax.swing.JLabel();
        cardValue = new javax.swing.JLabel();
        cardTitle = new javax.swing.JLabel();

        cardIcon.setForeground(new java.awt.Color(255, 255, 255));
        cardIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/stock.png"))); // NOI18N

        cardValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cardValue.setForeground(new java.awt.Color(255, 255, 255));
        cardValue.setText("Value");

        cardTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cardTitle.setForeground(new java.awt.Color(255, 255, 255));
        cardTitle.setText("Title");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cardValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(cardIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cardTitle)
                .addGap(33, 33, 33)
                .addComponent(cardValue)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gg = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2.setPaint(gg);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.setColor(new Color(255, 255, 255, 50));
        g2.fillOval(getWidth() - (getHeight() / 2), 10, getHeight(), getHeight());
        g2.fillOval(getWidth() - (getHeight() / 2) - 20, getHeight() / 2 + 20, getHeight(), getHeight());
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cardIcon;
    private javax.swing.JLabel cardTitle;
    private javax.swing.JLabel cardValue;
    // End of variables declaration//GEN-END:variables
}
