package display.editframe.admin;

import dao.CarDAO;
import dao.CarTypeDAO;
import dao.ManufacturerDAO;
import dao.TenantDAO;
import ex.DatabaseException;
import javax.swing.DefaultComboBoxModel;
import model.newentity.NewOrUpdateCars;
import model.entity.CarType;
import model.entity.Cars;
import model.entity.Manufacturers;
import model.entity.Tenants;
import saveinformation.SaveCarInfo;
import org.apache.commons.lang3.StringUtils;
import utils.Validation;

public class CarEditFrame extends javax.swing.JFrame {

    private Cars car = SaveCarInfo.getCurrentCar();
    private DefaultComboBoxModel comCarTypeModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel comTenantModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel comManuModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();
    private CarDAO carDAO = new CarDAO();
    private CarTypeDAO carTypeDAO = new CarTypeDAO();
    private ManufacturerDAO manuDAO = new ManufacturerDAO();

    public CarEditFrame() {
        initComponents();
        tenantChoose.setModel(comTenantModel);
        comTenantModel.setSelectedItem(car.getTenant());
        tenantDAO.findAllTenants()
                .forEach(comTenantModel::addElement);

        carTypeChoose.setModel(comCarTypeModel);
        comCarTypeModel.setSelectedItem(car.getCarType());
        carTypeDAO.findNotDeletedCarTypes()
                .forEach(comCarTypeModel::addElement);

        manuChoose.setModel(comManuModel);
        comManuModel.setSelectedItem(car.getManufacturer());
        manuDAO.findNotDeletedManufacturers()
                .forEach(comManuModel::addElement);

        nameField.setText(car.getName());
        priceByDayField.setText(String.valueOf(car.getPriceByDay()).replace(".0", ""));
        lateReturnPriceField.setText(String.valueOf(car.getLateReturnPrice()).replace(".0", ""));
        descriptionField.setText(car.getDescription());

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
        tenantChoose = new javax.swing.JComboBox<>();
        manuChoose = new javax.swing.JComboBox<>();
        carTypeChoose = new javax.swing.JComboBox<>();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        priceByDayField = new javax.swing.JTextField();
        lateReturnPriceField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextField();
        lblValName = new javax.swing.JLabel();
        lblValADayPrice = new javax.swing.JLabel();
        lblValLatePrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        lblMessage.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblMessage.setForeground(java.awt.Color.red);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Car Edit");

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

        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(102, 102, 102));
        btnSave.setText("Save");
        btnSave.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnSave.setMaximumSize(new java.awt.Dimension(79, 25));
        btnSave.setMinimumSize(new java.awt.Dimension(70, 25));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        tenantChoose.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tenantChoose.setForeground(new java.awt.Color(102, 102, 102));
        tenantChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Tenant" }));
        tenantChoose.setAutoscrolls(true);
        tenantChoose.setPreferredSize(new java.awt.Dimension(15, 22));
        tenantChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tenantChooseActionPerformed(evt);
            }
        });

        manuChoose.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        manuChoose.setForeground(new java.awt.Color(102, 102, 102));
        manuChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Manufaturer" }));
        manuChoose.setAutoscrolls(true);
        manuChoose.setPreferredSize(new java.awt.Dimension(15, 22));
        manuChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manuChooseActionPerformed(evt);
            }
        });

        carTypeChoose.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        carTypeChoose.setForeground(new java.awt.Color(102, 102, 102));
        carTypeChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Cartype" }));
        carTypeChoose.setAutoscrolls(true);
        carTypeChoose.setPreferredSize(new java.awt.Dimension(15, 22));

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Manufacturer");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Car Type");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Tenant");

        priceByDayField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceByDayFieldActionPerformed(evt);
            }
        });

        lateReturnPriceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lateReturnPriceFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Price By Day");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Late Return Unit Price");

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Description");

        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("*");

        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("*");

        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("*");

        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("*");

        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("*");

        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("*");

        lblValName.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblValName.setForeground(java.awt.Color.red);

        lblValADayPrice.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblValADayPrice.setForeground(java.awt.Color.red);

        lblValLatePrice.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblValLatePrice.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblValLatePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValADayPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenantChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carTypeChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manuChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lateReturnPriceField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceByDayField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValName, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2))
                    .addComponent(lblValName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manuChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carTypeChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenantChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addGap(12, 12, 12)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceByDayField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(4, 4, 4)
                .addComponent(lblValADayPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lateReturnPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13))
                .addGap(4, 4, 4)
                .addComponent(lblValLatePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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
                .addGap(20, 20, 20))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        lblMessage.setText("");
        lblValADayPrice.setText("");
        lblValLatePrice.setText("");
        lblValName.setText("");

        Tenants tenant = (Tenants) tenantChoose.getSelectedItem();
        CarType carType = (CarType) carTypeChoose.getSelectedItem();
        Manufacturers manu = (Manufacturers) manuChoose.getSelectedItem();

        NewOrUpdateCars newCar = new NewOrUpdateCars();
        if ("".equals(nameField.getText())
                || "".equals(priceByDayField.getText())
                || "".equals(lateReturnPriceField.getText())) {
            lblMessage.setText("Please fill out all fields.");
        } else {
            newCar.setName(nameField.getText());
            newCar.setDescription(descriptionField.getText());
            newCar.setCarTypeId(carType.getId());
            newCar.setTenantId(tenant.getId());
            newCar.setManufacturerId(manu.getId());
            if (Validation.validatePrice(priceByDayField.getText())) {
                newCar.setPriceByDay(Float.valueOf(priceByDayField.getText()));
            } else {
                lblValADayPrice.setText("Price by day numeric and <= 100.000.000.");
            }
            if (Validation.validatePrice(lateReturnPriceField.getText())) {
                newCar.setLateReturnPrice(Float.valueOf(lateReturnPriceField.getText()));
            } else {
                lblValLatePrice.setText("Late return day numeric and <= 100.000.000.");
            }
            newCar.setStatus(0);
            newCar.setDelFlag(0);
            newCar.setCreated(car.getCreated());
            if (!StringUtils.isEmpty(newCar.getName())
                    && newCar.getPriceByDay() != null
                    && newCar.getLateReturnPrice() != null) {
                try {
                    carDAO.update(car.getId(), newCar);
                    this.dispose();
                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tenantChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tenantChooseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tenantChooseActionPerformed

    private void manuChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manuChooseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manuChooseActionPerformed

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void priceByDayFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceByDayFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceByDayFieldActionPerformed

    private void lateReturnPriceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lateReturnPriceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lateReturnPriceFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> carTypeChoose;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lateReturnPriceField;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblValADayPrice;
    private javax.swing.JLabel lblValLatePrice;
    private javax.swing.JLabel lblValName;
    private javax.swing.JComboBox<String> manuChoose;
    private javax.swing.JTextField nameField;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JTextField priceByDayField;
    private javax.swing.JComboBox<String> tenantChoose;
    // End of variables declaration//GEN-END:variables
}
