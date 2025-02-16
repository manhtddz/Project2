package display.form.manager;

import dao.ReservationDAO;
import dao.TenantDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.ImageIcon;
import display.model.Model_Card;
import model.entity.Cars;
import model.entity.Reservations;
import model.entity.Tenants;
import model.entity.Users;
import saveinformation.SecurityContext;
import org.bson.conversions.Bson;
import utils.DBUtil;

public class FormHome extends javax.swing.JPanel {

    private TenantDAO tenantDAO = new TenantDAO();
    private ReservationDAO reservDAO = new ReservationDAO();
    private Users user = SecurityContext.getCurrentUser();

    private int findFreeActiveCarQty() {
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson statusFilter = Filters.eq("status", 0);
            Bson thisTenant = Filters.eq("tenant_id", user.getTenantId());
            Bson filter = Filters.and(statusFilter, delFilter, thisTenant);

            long count = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .countDocuments(filter);
            return (int) count;
        }
    }

    public static String getVNCurrency(Float idx) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getCurrencyInstance(localeVN);
        return vn.format(idx);
    }

    private Float findTotalProfit() {

        List<Reservations> listReserv = reservDAO.findAllReservationsWithTenant(user.getTenantId());
        Float profit = 0F;
        for (Reservations reservation : listReserv) {
            Float eachProfit = reservation.getRentalTotalPrice() - reservation.getPenaltiesTotalPrice();
            profit += eachProfit;
        }
        return profit;
    }

    private int findReservationQty() {
        List<Reservations> listReservations = reservDAO.findAllReservationsWithTenant(user.getTenantId());
        return listReservations.size();
    }

    public FormHome() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(this.loadImg("/icon/stock.png")), "Remaining Car", String.valueOf(findFreeActiveCarQty()), "Increased by 60%"));
        card2.setData(new Model_Card(new ImageIcon(this.loadImg("/icon/profit.png")), "Total Profit", getVNCurrency(findTotalProfit()), "Increased by 25%"));
        card3.setData(new Model_Card(new ImageIcon(this.loadImg("/icon/flag.png")), "Reservations Quantity", String.valueOf(findReservationQty()), "Increased by 70%"));
        //  add row table
//        TenantDAO tenantDAO = new TenantDAO();
        Tenants tenant = tenantDAO.findTenantById(user.getTenantId());
        jLabel1.setText("Welcome to " + tenant.getName() + " rental car management system.");
        jLabel2.setText("Here are some of our information:" );

        lblAddressValue.setText(tenant.getAddress());
        openHour.setText(String.valueOf(tenant.getOpenHours()));
        closeHour.setText(String.valueOf(tenant.getCloseHours()));
        lblEmailValue.setText(tenant.getEmail());
        lblPhoneValue.setText(tenant.getPhone());
    }

    private URL loadImg(String img) {
        return getClass().getResource(img);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JLayeredPane();
        card1 = new component.Card();
        card2 = new component.Card();
        card3 = new component.Card();
        panelBorder1 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblEmailValue = new javax.swing.JLabel();
        lblAddressValue = new javax.swing.JLabel();
        lblPhoneValue = new javax.swing.JLabel();
        openHour = new javax.swing.JLabel();
        closeHour = new javax.swing.JLabel();
        name6 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(675, 488));

        cardPanel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(143, 143, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        cardPanel.add(card1);

        card2.setColor1(new java.awt.Color(167, 94, 236));
        card2.setColor2(new java.awt.Color(186, 123, 247));
        cardPanel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        cardPanel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Email");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Address");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Phone");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Open Time");

        lblEmailValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEmailValue.setForeground(new java.awt.Color(102, 102, 102));
        lblEmailValue.setMaximumSize(new java.awt.Dimension(77, 20));
        lblEmailValue.setName(""); // NOI18N

        lblAddressValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAddressValue.setForeground(new java.awt.Color(102, 102, 102));
        lblAddressValue.setMaximumSize(new java.awt.Dimension(77, 20));
        lblAddressValue.setName(""); // NOI18N

        lblPhoneValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblPhoneValue.setForeground(new java.awt.Color(102, 102, 102));
        lblPhoneValue.setMaximumSize(new java.awt.Dimension(77, 20));
        lblPhoneValue.setName(""); // NOI18N

        openHour.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        openHour.setForeground(new java.awt.Color(102, 102, 102));
        openHour.setMaximumSize(new java.awt.Dimension(77, 20));
        openHour.setName(""); // NOI18N

        closeHour.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        closeHour.setForeground(new java.awt.Color(102, 102, 102));
        closeHour.setMaximumSize(new java.awt.Dimension(77, 20));
        closeHour.setName(""); // NOI18N

        name6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        name6.setForeground(new java.awt.Color(102, 102, 102));
        name6.setText("~");
        name6.setMaximumSize(new java.awt.Dimension(77, 20));
        name6.setName(""); // NOI18N

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddressValue, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhoneValue, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(openHour, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(name6, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(closeHour, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(159, 159, 159))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(lblEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addComponent(jLabel8))
                            .addComponent(lblAddressValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9))
                    .addComponent(lblPhoneValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel7))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(openHour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(closeHour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.Card card1;
    private component.Card card2;
    private component.Card card3;
    private javax.swing.JLayeredPane cardPanel;
    private javax.swing.JLabel closeHour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAddressValue;
    private javax.swing.JLabel lblEmailValue;
    private javax.swing.JLabel lblPhoneValue;
    private javax.swing.JLabel name6;
    private javax.swing.JLabel openHour;
    private swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
