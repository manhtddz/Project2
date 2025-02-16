package display.form.admin;

import dao.TenantDAO;
import dao.UserDAO;
import display.addnewframe.admin.UserAddFrame;
import display.editframe.admin.UserEditFrame;
import display.event.TableActionEvent;
import display.viewframe.admin.UserViewFrame;
import ex.DatabaseException;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import model.entity.Tenants;
import model.entity.Users;
import model.searchmodel.UserSearch;
import saveinformation.SecurityContext;
import swing.ScrollBar;
import swing.TableActionCellEditor;
import swing.TableActionCellRender;
import utils.FileChoose;

public class FormUser extends javax.swing.JPanel {

    private DefaultComboBoxModel comTenantModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();
    private UserDAO userDAO = new UserDAO();

    public FormUser() {
        initComponents();
        //  add row table
        List<Users> list = userDAO.findAllManagers();
        for (Users b : list) {
            Object[] row = {
                b.getId(),
                b.getName(),
                b.getTenant().getName(),
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
        filterTenant.setModel(comTenantModel);
        comTenantModel.addElement(new Tenants("All tenants"));
        tenantDAO.findAllTenants()
                .forEach(comTenantModel::addElement);

        //nut trong bang
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
//                System.out.println("Edit row: " + row);
                TableModel model = table.getModel();
                String email = (String) model.getValueAt(row, 3);
//                String name = model.getValueAt(row, 1).toString();
//                System.out.println("lay data theo ID " + id + " sau do vao DB select details de hien 1 form Edit");
                Users user = userDAO.findUserByEmail(email);
                SecurityContext.saveSercurityContext(user);
                UserEditFrame editFrame = new UserEditFrame();
                editFrame.setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int id = (int) model.getValueAt(row, 0);
                try {
                    boolean isDelete = userDAO.delete(id);
                    if (isDelete) {
                        model.removeRow(row);
                    } else {
                        JOptionPane.showMessageDialog(panelBorder1, "Action failed.");
                        System.out.println("Action failed.");
                    }

                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onView(int row) {
                TableModel model = table.getModel();
                String id = model.getValueAt(row, 0).toString();
                String email = (String) model.getValueAt(row, 3);
                Users user = userDAO.findUserByEmail(email);
                SecurityContext.saveSercurityContext(user);
                UserViewFrame viewFrame = new UserViewFrame();
                viewFrame.setVisible(true);
            }
        };
        TableColumn col = table.getColumnModel().getColumn(5);
        col.setCellRenderer(new TableActionCellRender());
        col.setCellEditor(new TableActionCellEditor(event));
    }

//    private URL loadImg(String img){
//        return getClass().getResource(img);
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder2 = new swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        filterTenant = new javax.swing.JComboBox<>();
        filter = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        panelBorder1 = new swing.PanelBorder();
        btnExpCSV = new javax.swing.JButton();
        spTable = new javax.swing.JScrollPane();
        table = new swing.Table();
        btnAddNew = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Manager List");

        filterTenant.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        filterTenant.setForeground(new java.awt.Color(102, 102, 102));
        filterTenant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Tenant" }));
        filterTenant.setAutoscrolls(true);
        filterTenant.setPreferredSize(new java.awt.Dimension(15, 22));
        filterTenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTenantActionPerformed(evt);
            }
        });

        filter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filter.setForeground(new java.awt.Color(102, 102, 102));
        filter.setText("Search");
        filter.setMargin(new java.awt.Insets(3, 12, 3, 12));
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Tenant");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Name");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(47, 47, 47)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(filterTenant, javax.swing.GroupLayout.Alignment.LEADING, 0, 149, Short.MAX_VALUE)
                            .addComponent(filter)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterTenant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filter)
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
                "ID", "Name", "Tenant", "Email", "Phone", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        table.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(table);

        btnAddNew.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddNew.setForeground(new java.awt.Color(102, 102, 102));
        btnAddNew.setText("Add new");
        btnAddNew.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnAddNew.setMaximumSize(new java.awt.Dimension(79, 25));
        btnAddNew.setMinimumSize(new java.awt.Dimension(70, 25));
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void filterTenantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTenantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTenantActionPerformed

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        // TODO add your handling code here:
        Tenants tenant = (Tenants) filterTenant.getSelectedItem();
        UserSearch userSearch = new UserSearch(tenant,txtName.getText());
        List<Users> list = userDAO.searchUser(userSearch);
        while (table.getRowCount() > 0) {
            table.removeRow();
        }
        for (Users b : list) {
            Object[] row = {
                b.getId(),
                b.getName(),
                b.getTenant().getName(),
                b.getEmail(),
                b.getPhone()
            };
            table.addRow(row);
        }
    }//GEN-LAST:event_filterActionPerformed

    private void btnExpCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpCSVActionPerformed
        // TODO add your handling code here:
        exportUser(FileChoose.fileChooser());
    }//GEN-LAST:event_btnExpCSVActionPerformed
    private void exportUser(String filePath) {
        List<Users> userList = userDAO.findAllManagers();
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
    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        // TODO add your handling code here:
        UserAddFrame addFrame = new UserAddFrame();
        addFrame.setVisible(true);
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnExpCSV;
    private javax.swing.JButton filter;
    private javax.swing.JComboBox<String> filterTenant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private swing.Table table;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
