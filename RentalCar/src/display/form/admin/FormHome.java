package display.form.admin;

import dao.ReservationDAO;
import dao.TenantDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import display.addnewframe.admin.TenantAddFrame;
import display.editframe.admin.TenantEditFrame;
import display.event.TableActionEvent;
import ex.DatabaseException;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import display.model.Model_Card;
import model.entity.Cars;
import model.entity.Reservations;
import model.entity.Tenants;
import saveinformation.SaveTenantInfo;
import org.bson.conversions.Bson;
import swing.ScrollBar;
import swing.TableActionCellEditor;
import swing.TableActionCellRender;
import utils.DBUtil;
import utils.FileChoose;
import utils.Format;
import display.viewframe.admin.TenantViewFrame;

public class FormHome extends javax.swing.JPanel {

    private TenantDAO tenantDAO = new TenantDAO();
    private ReservationDAO reservDAO = new ReservationDAO();

    private int findFreeActiveCarQty() {
        try (MongoClient client = DBUtil.open()) {
            Bson delFilter = Filters.eq("del_flag", 0);
            Bson statusFilter = Filters.eq("status", 0);
            Bson filter = Filters.and(statusFilter, delFilter);

            long count = DBUtil.getDatabase(client)
                    .getCollection(Cars.COLLECTION, Cars.class)
                    .countDocuments(filter);
            return (int) count;
        }
    }

    

    private Float findTotalProfit() {

        List<Reservations> listReserv = reservDAO.findAllReservations();
        Float profit = 0F;
        for (Reservations reservation : listReserv) {
            Float eachProfit = reservation.getRentalTotalPrice() - reservation.getPenaltiesTotalPrice();
            profit += eachProfit;
        }
        return profit;
    }

    private int findReservationQty() {
        List<Reservations> listReservations = reservDAO.findAllReservations();
        return listReservations.size();
    }

    public FormHome() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(this.loadImg("/icon/stock.png")), "Remaining Car", String.valueOf(findFreeActiveCarQty()), "Increased by 60%"));
        card2.setData(new Model_Card(new ImageIcon(this.loadImg("/icon/profit.png")), "Total Profit", Format.getVNCurrency(findTotalProfit()), "Increased by 25%"));
        card3.setData(new Model_Card(new ImageIcon(this.loadImg("/icon/flag.png")), "Reservations Quantity", String.valueOf(findReservationQty()), "Increased by 70%"));
        //  add row table
        TenantDAO tenantDAO = new TenantDAO();
        List<Tenants> list = tenantDAO.findAllTenants();
        for (Tenants b : list) {
            Object[] row = {
                b.getId(),
                b.getName(),
                b.getAddress(),
                b.getEmail(),
                b.getPhone()
            };
            tableTenant.addRow(row);
        }

        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        //nut trong bang
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
//                System.out.println("Edit row: " + row);
                TableModel model = tableTenant.getModel();
                int id = (int) model.getValueAt(row, 0);
                Tenants tenant = tenantDAO.findTenantById(id);
                SaveTenantInfo.saveInfo(tenant);
                TenantEditFrame editFrame = new TenantEditFrame();
                editFrame.setVisible(true);
            }

            @Override
            public void onDelete(int row) {
                if (tableTenant.isEditing()) {
                    tableTenant.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) tableTenant.getModel();
                int id = (int) model.getValueAt(row, 0);
                try {
                    boolean isDelete = tenantDAO.delete(id);
                    if (isDelete) {
                        model.removeRow(row);
                    } else {
                        JOptionPane.showMessageDialog(panelBorder1, "Can't delete tenant assigned to cars or not existed. ");
                    }

                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onView(int row) {
                TableModel model = tableTenant.getModel();
                int id = (int) model.getValueAt(row, 0);
                Tenants tenant = tenantDAO.findTenantById(id);
                SaveTenantInfo.saveInfo(tenant);
                TenantViewFrame viewFrame = new TenantViewFrame();
                viewFrame.setVisible(true);
            }
        };
        TableColumn col = tableTenant.getColumnModel().getColumn(5);
        col.setCellRenderer(new TableActionCellRender());
        col.setCellEditor(new TableActionCellEditor(event));
//        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
//        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
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
        spTable = new javax.swing.JScrollPane();
        tableTenant = new swing.Table();
        btnAddNew = new javax.swing.JButton();
        btnExpCSV = new javax.swing.JButton();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Tenant List");

        spTable.setBorder(null);

        tableTenant.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), java.awt.Color.lightGray)); // NOI18N
        tableTenant.setForeground(new java.awt.Color(102, 102, 102));
        tableTenant.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Email", "Phone", "Action"
            }
        ));
        tableTenant.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tableTenant.getTableHeader().setResizingAllowed(false);
        tableTenant.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(tableTenant);

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

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExpCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        TenantAddFrame addFrame = new TenantAddFrame();
        addFrame.setVisible(true);
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnExpCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpCSVActionPerformed
        // TODO add your handling code here:
        exportTenant(FileChoose.fileChooser());
    }//GEN-LAST:event_btnExpCSVActionPerformed

    private void exportTenant(String filePath) {
        List<Tenants> tenantList = tenantDAO.findAllTenants();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".csv"))) {
            writer.write("Id");
            writer.write(",");
            writer.write("Name");
            writer.write(",");
            writer.write("Email");
            writer.write(",");
            writer.write("Phone number");
            writer.write(",");
            writer.write("Address");
            writer.write(",");
            writer.write("Open hour");
            writer.write(",");
            writer.write("Close hour");
            writer.write(",");
            writer.write("Create day");
            writer.newLine();
            for (Tenants tenant : tenantList) {
                writer.write(tenant.getId() + "");
                writer.write(",");
                writer.write(tenant.getName());
                writer.write(",");
                writer.write(tenant.getEmail());
                writer.write(",");
                writer.write(tenant.getPhone());
                writer.write(",");
                writer.write(tenant.getAddress());
                writer.write(",");
                writer.write(tenant.getOpenHours() + "");
                writer.write(",");
                writer.write(tenant.getCloseHours() + "");
                writer.write(",");
                writer.write(tenant.getCreated() + "");
                writer.newLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnExpCSV;
    private component.Card card1;
    private component.Card card2;
    private component.Card card3;
    private javax.swing.JLayeredPane cardPanel;
    private javax.swing.JLabel jLabel1;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private swing.Table tableTenant;
    // End of variables declaration//GEN-END:variables
}
