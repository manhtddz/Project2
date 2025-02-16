package display.form.manager;

import model.searchmodel.CarSearch;
import dao.CarDAO;
import dao.CarTypeDAO;
import dao.ManufacturerDAO;
import dao.TenantDAO;
import display.event.TableActionEvent;
import display.viewframe.manager.CarViewFrame;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import model.entity.CarType;
import model.entity.Cars;
import model.entity.Manufacturers;
import model.entity.Tenants;
import model.entity.Users;
import saveinformation.SaveCarInfo;
import saveinformation.SecurityContext;
import swing.ScrollBar;
import swing.TableActionCellEditor;
import swing.TableActionCellRender;
import utils.FileChoose;
import utils.Format;

public class FormCar extends javax.swing.JPanel {

    private Users user = SecurityContext.getCurrentUser();

    private DefaultComboBoxModel comTenantModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel comCarTypeModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel comManufacturerModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();
    private CarTypeDAO carTypeDAO = new CarTypeDAO();
    private ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
    private CarDAO carDAO = new CarDAO();

    public FormCar() {
        initComponents();
        //  add row table
        List<Cars> list = carDAO.findAllCarsWithTenant(user.getTenantId());
        for (Cars b : list) {
            String carType = b.getCarType().getName();
            String manu = b.getManufacturer().getName();
            String status = "";
            if (b.getStatus() == 0) {
                status = "Available";
            } else if (b.getStatus() == 1) {
                status = "Rented";
            }
            Object[] row = {
                b.getId(),
                b.getName(),
                carType,
                manu,
                Format.getVNCurrency(b.getPriceByDay()),
                status
            };
            tableCar.addRow(row);
        }

        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        //search bar
        filterCarType.setModel(comCarTypeModel);
        comCarTypeModel.addElement(new CarType("All types"));
        comCarTypeModel.setSelectedItem(new CarType("All types"));
        carTypeDAO.findAllCarTypes()
                .forEach(comCarTypeModel::addElement);

        filterManufacturer.setModel(comManufacturerModel);
        comManufacturerModel.addElement(new Manufacturers("All manufacturers"));
        comManufacturerModel.setSelectedItem(new Manufacturers("All manufacturers"));
        manufacturerDAO.findAllManufacturers()
                .forEach(comManufacturerModel::addElement);

        //nut trong bang
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                TableModel model = tableCar.getModel();
                JOptionPane.showMessageDialog(panelBorder1, "Action failed . Contact your system administrator for more info.");
            }

            @Override
            public void onDelete(int row) {
                JOptionPane.showMessageDialog(panelBorder1, "Action failed . Contact your system administrator for more info.");
            }

            @Override
            public void onView(int row) {
                TableModel model = tableCar.getModel();
                int id = (int) model.getValueAt(row, 0);
                Cars car = carDAO.findCarById(id);
                SaveCarInfo.saveInfo(car);
                CarViewFrame viewFrame = new CarViewFrame();
                viewFrame.setVisible(true);
            }
        };
        TableColumn col = tableCar.getColumnModel().getColumn(6);
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
        filterCarType = new javax.swing.JComboBox<>();
        filterManufacturer = new javax.swing.JComboBox<>();
        filter = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelBorder1 = new swing.PanelBorder();
        btnExpCSV = new javax.swing.JButton();
        spTable = new javax.swing.JScrollPane();
        tableCar = new swing.Table();

        setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder2.setOpaque(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Car List");

        filterCarType.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        filterCarType.setForeground(new java.awt.Color(102, 102, 102));
        filterCarType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Cartype" }));
        filterCarType.setToolTipText("");
        filterCarType.setAutoscrolls(true);
        filterCarType.setPreferredSize(new java.awt.Dimension(15, 22));

        filterManufacturer.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        filterManufacturer.setForeground(new java.awt.Color(102, 102, 102));
        filterManufacturer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Manufaturer" }));
        filterManufacturer.setAutoscrolls(true);
        filterManufacturer.setPreferredSize(new java.awt.Dimension(15, 22));

        filter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filter.setForeground(new java.awt.Color(102, 102, 102));
        filter.setText("Search");
        filter.setMargin(new java.awt.Insets(3, 12, 3, 12));
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Manufacturer");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Car Type");

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
                        .addGap(154, 154, 154)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(47, 47, 47)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(filterCarType, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter))))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(filterManufacturer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterCarType, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(filter)
                .addGap(19, 19, 19))
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

        tableCar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 12), java.awt.Color.lightGray)); // NOI18N
        tableCar.setForeground(new java.awt.Color(102, 102, 102));
        tableCar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Car Type", "Manufacturer", "Daily Price", "Status", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tableCar.getTableHeader().setReorderingAllowed(false);
        spTable.setViewportView(tableCar);

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
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        // TODO add your handling code here:
        Tenants tenant = user.getTenant();
        CarType carType = (CarType) filterCarType.getSelectedItem();
        Manufacturers manu = (Manufacturers) filterManufacturer.getSelectedItem();
        CarSearch carSearch = new CarSearch(tenant, carType, manu);
        List<Cars> list = carDAO.searchCar(carSearch);
        while (tableCar.getRowCount() > 0) {
            tableCar.removeRow();
        }

        for (Cars b : list) {
            String status = "";
            if (b.getStatus() == 0) {
                status = "Available";
            } else if (b.getStatus() == 1) {
                status = "Rented";
            }
            Object[] row = {
                b.getId(),
                b.getName(),
                b.getCarType().getName(),
                b.getManufacturer().getName(),
                Format.getVNCurrency(b.getPriceByDay()),
                status
            };
            tableCar.addRow(row);
        }
    }//GEN-LAST:event_filterActionPerformed

    private void btnExpCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpCSVActionPerformed
        exportCar(FileChoose.fileChooser());

    }//GEN-LAST:event_btnExpCSVActionPerformed

    private void exportCar(String filePath) {
        List<Cars> carList = carDAO.findAllCarsWithTenant(user.getTenantId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + ".csv"))) {
            writer.write("id");
            writer.write(",");
            writer.write("name");
            writer.write(",");
            writer.write("tenant");
            writer.write(",");
            writer.write("type");
            writer.write(",");
            writer.write("manufacturer");
            writer.write(",");
            writer.write("price");
            writer.write(",");
            writer.write("late price");
            writer.write(",");
            writer.write("Status");
            writer.write(",");
            writer.write("Created day");

            writer.newLine();
            String status = "";
            for (Cars car : carList) {
                writer.write(car.getId() + "");
                writer.write(",");
                writer.write(car.getName());
                writer.write(",");
                writer.write(car.getTenant().getName());
                writer.write(",");
                writer.write(car.getCarType().getName());
                writer.write(",");
                writer.write(car.getManufacturer().getName());
                writer.write(",");
                writer.write(car.getPriceByDay().toString());
                writer.write(",");
                writer.write(car.getLateReturnPrice().toString());
                if (car.getStatus() == 1) {
                    status = "Rented";
                } else {
                    status = "Available";
                }
                writer.write(",");
                writer.write(status);
                writer.write(",");
                writer.write(car.getCreated().toString());
                writer.newLine();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExpCSV;
    private javax.swing.JButton filter;
    private javax.swing.JComboBox<String> filterCarType;
    private javax.swing.JComboBox<String> filterManufacturer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private swing.PanelBorder panelBorder1;
    private swing.PanelBorder panelBorder2;
    private javax.swing.JScrollPane spTable;
    private swing.Table tableCar;
    // End of variables declaration//GEN-END:variables
}
