package display.addnewframe.manager;

import dao.CarDAO;
import dao.ClientDAO;
import dao.OptionDAO;
import dao.ReservationDAO;
import dao.TenantDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import ex.DatabaseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import model.newentity.NewOrUpdateClients;
import model.newentity.NewOrUpdateReservations;
import model.entity.Cars;
import model.entity.Clients;
import model.entity.Options;
import model.entity.Users;
import saveinformation.SecurityContext;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;
import utils.DBUtil;
import utils.Validation;

public class ReservationAddFrame extends javax.swing.JFrame {

    private DefaultComboBoxModel comCarModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();
    private CarDAO carDAO = new CarDAO();
    private ReservationDAO reservDAO = new ReservationDAO();
    private Users user = SecurityContext.getCurrentUser();
    private OptionDAO optionDAO = new OptionDAO();
    private DefaultListModel<String> listModel = new DefaultListModel<>();

    public ReservationAddFrame() {
        initComponents();
        carChoose.setModel(comCarModel);
        carDAO.findAllNotOrderedCarsWithTenant(user.getTenantId())
                .forEach(comCarModel::addElement);

        List<Options> options = optionDAO.findAllOptions();
        List<String> optionsName = new ArrayList<>();
        for (Options option : options) {
            optionsName.add(option.getName());
        }
        optionsName.forEach(listModel::addElement);
        jList1.setModel(listModel);

        clientBirthdayField.setDate(LocalDate.now());
        lentDate.setDate(LocalDate.now());
        returnDate.setDate(LocalDate.now());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        panelBorder1 = new swing.PanelBorder();
        lblMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        clientBirthdayField = new com.github.lgooddatepicker.components.DatePicker();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        carChoose = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lentDate = new com.github.lgooddatepicker.components.DatePicker();
        returnDate = new com.github.lgooddatepicker.components.DatePicker();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnFemale = new javax.swing.JRadioButton();
        btnMale = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        lblEmail = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(675, 488));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        lblMessage.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblMessage.setForeground(java.awt.Color.red);

        jLabel1.setText("Reservation Create");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));

        btnClose.setText("Close");
        btnClose.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClose.setForeground(new java.awt.Color(102, 102, 102));
        btnClose.setMargin(new java.awt.Insets(3, 12, 3, 12));
        btnClose.setMaximumSize(new java.awt.Dimension(79, 25));
        btnClose.setMinimumSize(new java.awt.Dimension(70, 25));
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
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

        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Client Name");
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel4.setText("Client Email");
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel6.setText("Choose Car");
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));

        addressField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressFieldActionPerformed(evt);
            }
        });

        jLabel7.setText("Client Address");
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));

        jLabel2.setText("*");
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));

        jLabel10.setText("*");
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));

        jLabel8.setText("Client Phone");
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));

        phoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneFieldActionPerformed(evt);
            }
        });

        jLabel12.setText("*");
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));

        jLabel9.setText("Birthday");
        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));

        jLabel5.setText("*");
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));

        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        jLabel11.setText("*");
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));

        jLabel13.setText("*");
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));

        carChoose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose Car" }));
        carChoose.setAutoscrolls(true);
        carChoose.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        carChoose.setForeground(new java.awt.Color(102, 102, 102));
        carChoose.setPreferredSize(new java.awt.Dimension(15, 22));

        jLabel14.setText("Choose gender");
        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));

        jLabel15.setText("*");
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));

        jLabel16.setText("Lent date");
        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));

        jLabel17.setText("*");
        jLabel17.setForeground(new java.awt.Color(255, 51, 51));

        jLabel18.setText("Return date");
        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));

        jLabel19.setText("*");
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));

        jLabel20.setText("Option");
        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));

        buttonGroup1.add(btnFemale);
        btnFemale.setText("Female");
        btnFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFemaleActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnMale);
        btnMale.setSelected(true);
        btnMale.setText("Male");
        btnMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaleActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        lblEmail.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblEmail.setForeground(java.awt.Color.red);

        lblPhone.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblPhone.setForeground(java.awt.Color.red);

        lblDate.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblDate.setForeground(java.awt.Color.red);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 6, Short.MAX_VALUE)))
                        .addGap(82, 82, 82))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnFemale)
                        .addComponent(clientBirthdayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnMale, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(addressField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                        .addComponent(phoneField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(emailField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                    .addComponent(returnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addGap(15, 15, 15)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(clientBirthdayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(btnFemale)
                    .addComponent(btnMale))
                .addGap(18, 18, 18)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(2, 2, 2)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(67, Short.MAX_VALUE))))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        lblEmail.setText("");
        lblPhone.setText("");
        lblMessage.setText("");
        lblDate.setText("");
        Cars car = (Cars) carChoose.getSelectedItem();
        List<Integer> optionList = new ArrayList<>();
        int[] opIndices = jList1.getSelectedIndices();
        for (Integer opIndex : opIndices) {
            optionList.add(opIndex + 1);
        }

        NewOrUpdateReservations newReserv = new NewOrUpdateReservations();

        ButtonGroup bg = new ButtonGroup();
        bg.add(btnMale);
        bg.add(btnFemale);
        if (btnMale.isSelected()) {
            newReserv.setClientGender(0);
        }
        if (btnFemale.isSelected()) {
            newReserv.setClientGender(1);
        }
        if ("".equals(nameField.getText())
                || "".equals(emailField.getText())
                || "".equals(phoneField.getText())
                || "".equals(addressField.getText())
                || null == returnDate.getDate()
                || null == lentDate.getDate()
                || null == clientBirthdayField.getDate()) {
            lblMessage.setText("Please fill out all fields.");
        } else {
            newReserv.setCarId(car.getId());
            if (!Validation.validateDate(lentDate.getDate(), returnDate.getDate())) {

                lblDate.setText("Return date must be > lent date.");

            }
            newReserv.setLentDate(lentDate.getDate());
            newReserv.setReturnDate(returnDate.getDate());
            newReserv.setTenantId(car.getTenantId());
            newReserv.setRealReturnDate(returnDate.getDate());
            newReserv.setClientBirthDay(clientBirthdayField.getDate());
            newReserv.setClientAddress(addressField.getText());
            newReserv.setCar(car);
            newReserv.setStatus(0);
            if (Validation.validatePhone(phoneField.getText())) {
                newReserv.setClientPhone(phoneField.getText());
            } else {
                lblPhone.setText("Please enter 10-digits number.");
            }
            if (Validation.validateEmail(emailField.getText())) {
                newReserv.setClientEmail(emailField.getText());
            } else {
                lblEmail.setText("Wrong email format");
            }
            newReserv.setClientName(nameField.getText());
            newReserv.setListOptionId(optionList);
            List<Integer> listPenaltyPoliciesId = new ArrayList<>();
            listPenaltyPoliciesId.add(0);
            newReserv.setListPenaltyPoliciesId(listPenaltyPoliciesId);
            if (!StringUtils.isEmpty(newReserv.getClientName())
                    && !StringUtils.isEmpty(newReserv.getClientEmail())
                    && !StringUtils.isEmpty(newReserv.getClientPhone())
                    && !StringUtils.isEmpty(newReserv.getClientAddress())
                    && newReserv.rentalPrice() != 0F) {
                try {
                    reservDAO.addNew(newReserv);
                    Bson byCarId = Filters.eq("id", car.getId());
                    try (MongoClient client = DBUtil.open()) {
                        DBUtil.getDatabase(client)
                                .getCollection(Cars.COLLECTION, Cars.class)
                                .updateOne(byCarId, Updates.set("status", 1));

                        Bson byTenantId = Filters.eq("tenant_id", newReserv.getTenantId());
                        Bson byClientName = Filters.eq("name", newReserv.getClientName());
                        Bson byClientEmail = Filters.eq("email", newReserv.getClientEmail());
                        Bson filter = Filters.and(byTenantId, byClientName, byClientEmail);

                        Long count = DBUtil.getDatabase(client)
                                .getCollection(Clients.COLLECTION, Clients.class)
                                .countDocuments(filter);
                        if (count == 0) {
                            NewOrUpdateClients newClient = new NewOrUpdateClients(
                                    newReserv.getTenantId(),
                                    newReserv.getClientName(),
                                    newReserv.getClientPhone(),
                                    newReserv.getClientAddress(),
                                    newReserv.getClientEmail(),
                                    newReserv.getClientGender(),
                                    newReserv.getClientBirthDay(),
                                    newReserv.getCreated(),
                                    0
                            );
                            ClientDAO addClient = new ClientDAO();
                            addClient.addNew(newClient);
                        }
                        this.dispose();
                    }
                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void addressFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressFieldActionPerformed

    private void phoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneFieldActionPerformed

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void btnFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFemaleActionPerformed

    private void btnMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMaleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressField;
    private javax.swing.JButton btnClose;
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.JButton btnSave;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> carChoose;
    private com.github.lgooddatepicker.components.DatePicker clientBirthdayField;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblPhone;
    private com.github.lgooddatepicker.components.DatePicker lentDate;
    private javax.swing.JTextField nameField;
    private swing.PanelBorder panelBorder1;
    private javax.swing.JTextField phoneField;
    private com.github.lgooddatepicker.components.DatePicker returnDate;
    // End of variables declaration//GEN-END:variables
}
