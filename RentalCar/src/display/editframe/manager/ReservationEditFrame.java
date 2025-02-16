package display.editframe.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import dao.PenaltiesPolicyDAO;
import dao.ReservationDAO;
import ex.DatabaseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import model.entity.Cars;
import model.entity.Options;
import model.entity.PenaltiesPolicy;
import model.entity.Reservations;
import model.newentity.NewOrUpdateReservations;
import saveinformation.SaveReservationInfo;
import org.bson.conversions.Bson;
import utils.DBUtil;
import utils.Validation;

public class ReservationEditFrame extends javax.swing.JFrame {

    private DefaultComboBoxModel comPen = new DefaultComboBoxModel();
    private ReservationDAO reservDAO = new ReservationDAO();
    private Reservations reserv = SaveReservationInfo.getCurrentReservation();
    private PenaltiesPolicyDAO policyDAO = new PenaltiesPolicyDAO();

    public ReservationEditFrame() {
        initComponents();
        policyChoose.setModel(comPen);
        comPen.setSelectedItem(reserv.getListPenaltyPolicy().get(0));
        comPen.addElement(new PenaltiesPolicy(0, ""));
        policyDAO.findAllPolicies()
                .forEach(comPen::addElement);

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
        realReturnDate.setDate(reserv.getRealReturnDate());
        lblOption.setText(getOptionsName());
        if (reserv.getStatus() == 1) {
            btnComplete.setSelected(true);
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
        btnSave = new javax.swing.JButton();
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
        realReturnDate = new com.github.lgooddatepicker.components.DatePicker();
        policyChoose = new javax.swing.JComboBox<>();
        btnComplete = new javax.swing.JCheckBox();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        lblMessage.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblMessage.setForeground(java.awt.Color.red);

        jLabel1.setText("Reservation Edit");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));

        btnBack.setText("Back");
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(102, 102, 102));
        btnBack.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnBack.setMaximumSize(new java.awt.Dimension(79, 25));
        btnBack.setMinimumSize(new java.awt.Dimension(70, 25));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(102, 102, 102));
        btnSave.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnSave.setMaximumSize(new java.awt.Dimension(79, 25));
        btnSave.setMinimumSize(new java.awt.Dimension(70, 25));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setText("Client Name");
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel4.setText("Client Email");
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setText("Car");
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));

        jLabel7.setText("Client Address");
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));

        jLabel8.setText("Client Phone");
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));

        jLabel9.setText("Birthday");
        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));

        jLabel14.setText("Gender");
        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));

        jLabel16.setText("Lent date");
        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));

        jLabel18.setText("Return date");
        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));

        jLabel20.setText("Option");
        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));

        jLabel21.setText("Real Return Date");
        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));

        jLabel22.setText("Penalty Policy");
        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));

        policyChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Policy" }));
        policyChoose.setAutoscrolls(true);
        policyChoose.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        policyChoose.setForeground(new java.awt.Color(102, 102, 102));
        policyChoose.setPreferredSize(new java.awt.Dimension(15, 22));

        btnComplete.setText("Completed");
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });

        jLabel23.setText("Status");
        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));

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

        jLabel5.setText("Tenant Name");
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));

        lblOption.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblOption.setForeground(new java.awt.Color(102, 102, 102));

        lblTenantName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTenantName.setForeground(new java.awt.Color(102, 102, 102));

        lblRealReturnDate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblRealReturnDate.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel16)
                            .addComponent(jLabel23)
                            .addComponent(jLabel18)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14)
                            .addComponent(jLabel9)))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addComponent(jLabel1))))
                .addGap(103, 103, 103)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(policyChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnComplete, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOption, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(realReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientGender, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientBOD, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenantName, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRealReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 50, Short.MAX_VALUE))))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel5))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTenantName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel22)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel23)
                        .addGap(28, 28, 28))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(lblClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(lblClientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(lblClientPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15)
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel16))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                .addComponent(lblClientAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34))
                                            .addComponent(lblClientBOD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(lblClientGender, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(34, 34, 34))
                                            .addComponent(lblCar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblLentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18))
                            .addComponent(lblReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel21))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(realReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblRealReturnDate, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(lblOption, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(policyChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnComplete)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        return optionName.substring(0, optionName.length() - 2);
    }
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        lblRealReturnDate.setText("");
        NewOrUpdateReservations newReserv = new NewOrUpdateReservations();
        PenaltiesPolicy penalty = (PenaltiesPolicy) policyChoose.getSelectedItem();
        List<Integer> penList = new ArrayList<>();
        penList.add(penalty.getId());
        newReserv.setCarId(reserv.getCarId());
        Bson byCarId = Filters.eq("id", reserv.getCarId());
        try (MongoClient client = DBUtil.open()) {

            Cars car = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .find(byCarId)
                    .first();
            newReserv.setCar(car);
        }
        if (null != realReturnDate.getDate()) {
            newReserv.setClientAddress(reserv.getClientAddress());
            newReserv.setTenantId(reserv.getTenantId());
            newReserv.setClientGender(reserv.getClientGender());
            newReserv.setLentDate(reserv.getLentDate());
            newReserv.setReturnDate(reserv.getReturnDate());
            if (Validation.validateDate(reserv.getLentDate(), realReturnDate.getDate())) {
                newReserv.setRealReturnDate(realReturnDate.getDate());
            } else {
                lblRealReturnDate.setText("Return date must be > lent date.");
            }
            newReserv.setClientBirthDay(reserv.getClientBirthDay());
            newReserv.setClientEmail(reserv.getClientEmail());
            newReserv.setClientPhone(reserv.getClientPhone());
            newReserv.setClientName(reserv.getClientName());
            newReserv.setListOptionId(reserv.getListOptionId());
            newReserv.setStatus(isCompleted());
            newReserv.setDelFlag(0);
            newReserv.setCreated(reserv.getCreated());
            newReserv.setListPenaltyPoliciesId(penList);
            if (newReserv.lateReturnPrice() != null) {
                try {
                    reservDAO.update(reserv.getReservationNo(), newReserv);
                    try (MongoClient client = DBUtil.open()) {

                        DBUtil.getDatabase(client)
                                .getCollection(Cars.COLLECTION, Cars.class)
                                .updateOne(byCarId, Updates.set("status", 0));
                    }
                    this.dispose();
                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            lblRealReturnDate.setText("Please fill out this field");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private int isCompleted() {
        if (btnComplete.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCompleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JCheckBox btnComplete;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JLabel lblRealReturnDate;
    private javax.swing.JLabel lblReturnDate;
    private javax.swing.JLabel lblTenantName;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JComboBox<String> policyChoose;
    private com.github.lgooddatepicker.components.DatePicker realReturnDate;
    // End of variables declaration//GEN-END:variables
}
