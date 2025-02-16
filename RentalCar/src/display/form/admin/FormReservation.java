package display.form.admin;

import dao.ReservationDAO;
import dao.TenantDAO;
import display.editframe.manager.ReservationEditFrame;
import display.event.TableActionEvent;
import display.viewframe.manager.ReservationViewFrame;
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
import model.entity.Options;
import model.entity.PenaltiesPolicy;
import model.entity.Reservations;
import model.entity.Tenants;
import model.searchmodel.ReservationSearch;
import model.searchmodel.Status;
import saveinformation.SaveReservationInfo;
import swing.ScrollBar;
import swing.TableActionCellEditor;
import swing.TableActionCellRender;
import utils.FileChoose;
import utils.Format;

public class FormReservation extends javax.swing.JPanel {

    private ReservationDAO reservationDAO = new ReservationDAO();
    private DefaultComboBoxModel comTenantModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel comStatusModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();

    public FormReservation() {
        initComponents();
        //  add row table
        ReservationDAO dao = new ReservationDAO();
        List<Reservations> list = dao.findAllReservations();
        String status = "";
        for (Reservations b : list) {
            if (b.getStatus() == 1) {
                status = "Completed";
            } else {
                status = "Active";
            }
            Object[] row = {
                b.getReservationNo(),
                b.getTenant().getName(),
                b.getCar().getName(),
                b.getClientName(),
                Format.getVNCurrency(b.getRentalTotalPrice()),
                status
            };
            table.addRow(row);
        }
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        filterTenant.setModel(comTenantModel);
        comTenantModel.addElement(new Tenants("All tenants"));
        comTenantModel.setSelectedItem(new Tenants("All tenants"));
        tenantDAO.findAllTenants()
                .forEach(comTenantModel::addElement);

        filterStatus.setModel(comStatusModel);
        comStatusModel.setSelectedItem(new Status("All status"));
        comStatusModel.addElement(new Status("All status"));
        comStatusModel.addElement(new Status(0, "active"));
        comStatusModel.addElement(new Status(1, "completed"));
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
//                System.out.println("Edit row: " + row);
                TableModel model = table.getModel();
                int id = (int) model.getValueAt(row, 0);
                Reservations reserv = reservationDAO.findReservationById(id);
                SaveReservationInfo.saveInfo(reserv);
                ReservationEditFrame editFrame = new ReservationEditFrame();
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
                    boolean isDelete = reservationDAO.delete(id);
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
                int id = (int) model.getValueAt(row, 0);
                Reservations reserv = reservationDAO.findReservationById(id);
                SaveReservationInfo.saveInfo(reserv);
                ReservationViewFrame viewFrame = new ReservationViewFrame();
                viewFrame.setVisible(true);
            }
        };
        TableColumn col = table.getColumnModel().getColumn(6);
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
        jLabel4 = new javax.swing.JLabel();
        txtClientName = new javax.swing.JTextField();
        filterStatus = new javax.swing.JComboBox<>();
        panelBorder1 = new swing.PanelBorder();
        btnExpCSV = new javax.swing.JButton();
        spTable = new javax.swing.JScrollPane();
        table = new swing.Table();

        setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.setOpaque(true);

        jLabel1.setText("Reservation List");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));

        filterTenant.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Tenant" }));
        filterTenant.setAutoscrolls(true);
        filterTenant.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        filterTenant.setForeground(new java.awt.Color(102, 102, 102));
        filterTenant.setPreferredSize(new java.awt.Dimension(15, 22));
        filterTenant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTenantActionPerformed(evt);
            }
        });

        filter.setText("Search");
        filter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filter.setForeground(new java.awt.Color(102, 102, 102));
        filter.setMargin(new java.awt.Insets(3, 12, 3, 12));
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        jLabel2.setText("Tenant");
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setText("Client Name");
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel4.setText("Status");
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));

        txtClientName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClientNameActionPerformed(evt);
            }
        });

        filterStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Status" }));
        filterStatus.setAutoscrolls(true);
        filterStatus.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        filterStatus.setForeground(new java.awt.Color(102, 102, 102));
        filterStatus.setPreferredSize(new java.awt.Dimension(15, 22));
        filterStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterStatusActionPerformed(evt);
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
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(47, 47, 47)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(filterTenant, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(filter)
                            .addComponent(txtClientName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(filterStatus, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterTenant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filter)
                .addContainerGap())
        );

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        btnExpCSV.setText("Export CSV");
        btnExpCSV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExpCSV.setForeground(new java.awt.Color(102, 102, 102));
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
                "Reservation.No", "Tenant", "Car", "Client", "Total Price", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        table.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        String clientName = txtClientName.getText();
        Tenants tenant = (Tenants) filterTenant.getSelectedItem();
        Status statusId = (Status) filterStatus.getSelectedItem();
        ReservationSearch reservSearch = new ReservationSearch(clientName, tenant, statusId);
        List<Reservations> list = reservationDAO.reservationSearch(reservSearch);

        while (table.getRowCount() > 0) {
            table.removeRow();
        }

        for (Reservations b : list) {
            String status = "";
            if (b.getStatus() == 1) {
                status = "Completed";
            } else {
                status = "Active";
            }
            Object[] row = {
                b.getReservationNo(),
                b.getTenant().getName(),
                b.getCar().getName(),
                b.getClientName(),
                Format.getVNCurrency(b.getRentalTotalPrice()),
                status
            };
            table.addRow(row);
        }
    }//GEN-LAST:event_filterActionPerformed

    private void btnExpCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpCSVActionPerformed
        // TODO add your handling code here:
        exportReservation(FileChoose.fileChooser());
    }//GEN-LAST:event_btnExpCSVActionPerformed
    private void exportReservation(String filePath) {
        List<Reservations> reservList = reservationDAO.findAllReservations();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".csv"))) {
            writer.write("Reservation number");
            writer.write(",");
            writer.write("Tenant");
            writer.write(",");
            writer.write("Car");
            writer.write(",");
            writer.write("Client name");
            writer.write(",");
            writer.write("Client email");
            writer.write(",");
            writer.write("Phone number");
            writer.write(",");
            writer.write("Client address");
            writer.write(",");
            writer.write("Rental price");
            writer.write(",");
            writer.write("Late return price");
            writer.write(",");
            writer.write("Options");
            writer.write(",");
            writer.write("Options price");
            writer.write(",");
            writer.write("Penalty");
            writer.write(",");
            writer.write("Penalties price");
            writer.write(",");
            writer.write("Total price");
            writer.write(",");
            writer.write("Status");
            writer.write(",");
            writer.write("Create day");
            writer.newLine();
            for (Reservations reserv : reservList) {
                List<Options> options = reserv.getListOption();
                String optionName = "";
                for (Options option : options) {
                    optionName += option.getName() + ". ";
                }
                List<PenaltiesPolicy> penalties = reserv.getListPenaltyPolicy();
                String penaltyName = "";
                for (PenaltiesPolicy penalty : penalties) {
                    penaltyName += penalty.getName() + ", ";
                }
                if (penaltyName.length() >= 2) {
                    penaltyName = penaltyName.substring(0, penaltyName.length() - 2);
                }
                String status = "";
                if (reserv.getStatus() == 1) {
                    status = "Completed";
                } else {
                    status = "In progress";
                }
                optionName = optionName.substring(0, optionName.length() - 2);
                writer.write(reserv.getReservationNo() + "");
                writer.write(",");
                writer.write(reserv.getTenant().getName());
                writer.write(",");
                writer.write(reserv.getCar().getName());
                writer.write(",");
                writer.write(reserv.getClientName());
                writer.write(",");
                writer.write(reserv.getClientEmail());
                writer.write(",");
                writer.write(reserv.getClientPhone());
                writer.write(",");
                writer.write(reserv.getClientAddress());
                writer.write(",");
                writer.write(reserv.getRentalPrice().toString());
                writer.write(",");
                writer.write(reserv.getLateReturnPrice().toString());
                writer.write(",");
                writer.write(optionName);
                writer.write(",");
                writer.write(reserv.getOptionTotalPrice().toString());
                writer.write(",");
                writer.write(penaltyName);
                writer.write(",");
                writer.write(reserv.getPenaltiesTotalPrice().toString());
                writer.write(",");
                writer.write(reserv.getRentalTotalPrice().toString());
                writer.write(",");
                writer.write(status);
                writer.write(",");
                writer.write(reserv.getCreated() + "");
                writer.newLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void txtClientNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClientNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClientNameActionPerformed

    private void filterStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterStatusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExpCSV;
    private javax.swing.JButton filter;
    private javax.swing.JComboBox<String> filterStatus;
    private javax.swing.JComboBox<String> filterTenant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private swing.Table table;
    private javax.swing.JTextField txtClientName;
    // End of variables declaration//GEN-END:variables
}
