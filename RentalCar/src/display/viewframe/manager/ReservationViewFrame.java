package display.viewframe.manager;

import display.editframe.manager.ReservationEditFrame;
import java.util.List;
import model.entity.Options;
import model.entity.PenaltiesPolicy;
import model.entity.Reservations;
import saveinformation.SaveReservationInfo;
import utils.Format;

public class ReservationViewFrame extends javax.swing.JFrame {

    private Reservations reserv = SaveReservationInfo.getCurrentReservation();

    public ReservationViewFrame() {
        initComponents();

        lblCar.setText(reserv.getCar().getName());
        lblTenantName.setText(reserv.getTenant().getName());
        lblClientName.setText(reserv.getClientName());
        lblClientEmail.setText(reserv.getClientEmail());
        lblClientAddr.setText(reserv.getClientAddress());
        lblClientPhone.setText(reserv.getClientPhone());
        lblClientBOD.setText(String.valueOf(reserv.getClientBirthDay()));
        if (reserv.getClientGender() == 0) {
            lblClientGender.setText("male");
        } else {
            lblClientGender.setText("female");
        }
        lblLentDate.setText(String.valueOf(reserv.getLentDate()));
        lblReturnDate.setText(String.valueOf(reserv.getReturnDate()));
        lblRealReturnDate.setText(String.valueOf(reserv.getRealReturnDate()));
        lblTotalPrice.setText(Format.getVNCurrency(reserv.getRentalTotalPrice()));
        lblOption.setText(getOptionsName());
        lblPenalty.setText(getPolicyName());
        if (reserv.getStatus() == 0) {
            lblStatus.setText("Active");
        } else {
            lblStatus.setText("Completed");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelBorder1 = new swing.PanelBorder();
        lblMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblClientPhone = new javax.swing.JLabel();
        lblClientEmail = new javax.swing.JLabel();
        lblClientName = new javax.swing.JLabel();
        lblClientAddr = new javax.swing.JLabel();
        lblClientBOD = new javax.swing.JLabel();
        lblClientGender = new javax.swing.JLabel();
        lblLentDate = new javax.swing.JLabel();
        lblCar = new javax.swing.JLabel();
        lblReturnDate = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblOption = new javax.swing.JLabel();
        lblTenantName = new javax.swing.JLabel();
        lblRealReturnDate = new javax.swing.JLabel();
        lblPenalty = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        lblMessage.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMessage.setForeground(java.awt.Color.red);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Reservation Detail");

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(102, 102, 102));
        btnBack.setText("Back");
        btnBack.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnBack.setMaximumSize(new java.awt.Dimension(79, 25));
        btnBack.setMinimumSize(new java.awt.Dimension(70, 25));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(102, 102, 102));
        btnEdit.setText("Edit");
        btnEdit.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnEdit.setMaximumSize(new java.awt.Dimension(79, 25));
        btnEdit.setMinimumSize(new java.awt.Dimension(70, 25));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Client Name");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Client Email");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Car");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Client Address");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Client Phone");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Birthday");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Gender");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Lent date");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Return date");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Option");

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Real Return Date");

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Penalty Policy");

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Status");

        lblClientPhone.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblClientPhone.setForeground(new java.awt.Color(102, 102, 102));

        lblClientEmail.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblClientEmail.setForeground(new java.awt.Color(102, 102, 102));

        lblClientName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblClientName.setForeground(new java.awt.Color(102, 102, 102));

        lblClientAddr.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblClientAddr.setForeground(new java.awt.Color(102, 102, 102));

        lblClientBOD.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblClientBOD.setForeground(new java.awt.Color(102, 102, 102));

        lblClientGender.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblClientGender.setForeground(new java.awt.Color(102, 102, 102));

        lblLentDate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblLentDate.setForeground(new java.awt.Color(102, 102, 102));

        lblCar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblCar.setForeground(new java.awt.Color(102, 102, 102));

        lblReturnDate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblReturnDate.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Tenant Name");

        lblOption.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblOption.setForeground(new java.awt.Color(102, 102, 102));

        lblTenantName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTenantName.setForeground(new java.awt.Color(102, 102, 102));

        lblRealReturnDate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblRealReturnDate.setForeground(new java.awt.Color(102, 102, 102));

        lblPenalty.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblPenalty.setForeground(new java.awt.Color(102, 102, 102));

        lblStatus.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(102, 102, 102));

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Total price");

        lblTotalPrice.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTotalPrice.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientBOD, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblClientGender, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenantName, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel21)
                                            .addComponent(jLabel22)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel23))
                                        .addGap(103, 103, 103)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblLentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblRealReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblOption, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblPenalty, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 43, Short.MAX_VALUE)))
                        .addGap(125, 125, 125))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(lblTenantName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelBorder1Layout.createSequentialGroup()
                                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel3)
                                                            .addComponent(lblClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(15, 15, 15)
                                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                    .addComponent(lblClientEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jLabel8))
                                                                            .addComponent(lblClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGap(18, 18, 18)
                                                                        .addComponent(jLabel7))
                                                                    .addComponent(lblClientAddr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jLabel9))
                                                            .addComponent(lblClientBOD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel14))
                                                    .addComponent(lblClientGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6))
                                            .addComponent(lblCar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16))
                                    .addComponent(lblLentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18))
                            .addComponent(lblReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel21))
                    .addComponent(lblRealReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOption, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblPenalty, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(lblTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed
    private String getOptionsName() {
        List<Options> options = reserv.getListOption();
        String optionName = "";
        for (Options option : options) {
            optionName += option.getName() + ", ";
        }
        if (optionName.length() >= 2) {
            return optionName.substring(0, optionName.length() - 2);
        } else {
            return optionName;
        }
    }

    private String getPolicyName() {
        List<PenaltiesPolicy> policy = reserv.getListPenaltyPolicy();
        String policyName = "";
        for (PenaltiesPolicy penaltiesPolicy : policy) {
            policyName += penaltiesPolicy.getName();
        }
        return policyName;
    }
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        SaveReservationInfo.saveInfo(reserv);
        ReservationEditFrame editFrame = new ReservationEditFrame();
        editFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEditActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCar;
    private javax.swing.JLabel lblClientAddr;
    private javax.swing.JLabel lblClientBOD;
    private javax.swing.JLabel lblClientEmail;
    private javax.swing.JLabel lblClientGender;
    private javax.swing.JLabel lblClientName;
    private javax.swing.JLabel lblClientPhone;
    private javax.swing.JLabel lblLentDate;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblOption;
    private javax.swing.JLabel lblPenalty;
    private javax.swing.JLabel lblRealReturnDate;
    private javax.swing.JLabel lblReturnDate;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTenantName;
    private javax.swing.JLabel lblTotalPrice;
    private swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
