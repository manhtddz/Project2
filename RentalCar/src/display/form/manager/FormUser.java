package display.form.manager;

import dao.TenantDAO;
import dao.UserDAO;
import display.editframe.manager.UserEditFrame;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.entity.Users;
import saveinformation.SecurityContext;
import swing.ScrollBar;
import utils.FileChoose;

public class FormUser extends javax.swing.JPanel {

    private Users user = SecurityContext.getCurrentUser();

    private DefaultComboBoxModel comTenantModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();
    private UserDAO userDAO = new UserDAO();

    public FormUser() {
        initComponents();

        lblNameValue.setText(user.getName());
        lbEmailValue.setText(user.getEmail());
        lbPhoneValue.setText(user.getPhone());
        //  add row table
        List<Users> list = userDAO.findAllManagers();
        for (Users b : list) {
            Object[] row = {
                b.getId(),
                b.getName(),
                b.getEmail(),
                b.getPhone()
            };
            table.addRow(row);
        }

        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        //search bar

        //nut trong bang
    }

//    private URL loadImg(String img){
//        return getClass().getResource(img);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder2 = new swing.PanelBorder();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        lblNameValue = new javax.swing.JLabel();
        lbPhoneValue = new javax.swing.JLabel();
        lbEmailValue = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelBorder1 = new swing.PanelBorder();
        btnExpCSV = new javax.swing.JButton();
        spTable = new javax.swing.JScrollPane();
        table = new swing.Table();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Name");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Phone");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Email");

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

        lblNameValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblNameValue.setForeground(new java.awt.Color(102, 102, 102));

        lbPhoneValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbPhoneValue.setForeground(new java.awt.Color(102, 102, 102));

        lbEmailValue.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lbEmailValue.setForeground(new java.awt.Color(102, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Your Information");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(96, 96, 96)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPhoneValue, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnEdit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                                .addComponent(lblNameValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbEmailValue, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7))
                    .addComponent(lbPhoneValue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        btnExpCSV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExpCSV.setForeground(new java.awt.Color(102, 102, 102));
        btnExpCSV.setText("Export CSV");
        btnExpCSV.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnExpCSV.setMaximumSize(new java.awt.Dimension(79, 25));
        btnExpCSV.setMinimumSize(new java.awt.Dimension(70, 25));
        btnExpCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpCSVActionPerformed(evt);
            }
        });

        spTable.setBorder(null);

        table.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), java.awt.Color.lightGray)); // NOI18N
        table.setForeground(new java.awt.Color(102, 102, 102));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        table.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Manager List");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExpCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpCSVActionPerformed
        // TODO add your handling code here:
        exportUser(FileChoose.fileChooser());
    }//GEN-LAST:event_btnExpCSVActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        UserEditFrame editFrame = new UserEditFrame();
        SecurityContext.saveSercurityContext(user);
        editFrame.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed
    private void exportUser(String filePath) {
        List<Users> userList = userDAO.findAllManagersWithTenant(user.getTenantId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".csv"))) {
            writer.write("Id");
            writer.write(",");
            writer.write("Tenant");
            writer.write(",");
            writer.write("Name");
            writer.write(",");
            writer.write("Email");
            writer.write(",");
            writer.write("Phone number");
            writer.write(",");
            writer.write("Create day");
            writer.newLine();
            for (Users user : userList) {

                writer.write(user.getId() + "");
                writer.write(",");
                writer.write(user.getTenant().getName());
                writer.write(",");
                writer.write(user.getName());
                writer.write(",");
                writer.write(user.getEmail());
                writer.write(",");
                writer.write(user.getPhone());
                writer.write(",");
                writer.write(user.getCreated() + "");
                writer.newLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExpCSV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbEmailValue;
    private javax.swing.JLabel lbPhoneValue;
    private javax.swing.JLabel lblNameValue;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private swing.Table table;
    // End of variables declaration//GEN-END:variables
}
