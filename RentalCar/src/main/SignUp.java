package main;

import dao.TenantDAO;
import dao.UserDAO;
import ex.DatabaseException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.newentity.NewOrUpdateUsers;
import model.entity.Tenants;
import model.entity.Users;
import net.miginfocom.swing.MigLayout;
import org.apache.commons.lang3.StringUtils;
import saveinformation.Authentication;
import swing.Button;
import swing.ButtonOutLine;
import swing.MyPasswordField;
import swing.MyTextField;
import utils.Validation;

public class SignUp extends javax.swing.JFrame {

    private Authentication au = new Authentication();
    private DefaultComboBoxModel comModel = new DefaultComboBoxModel();
    private TenantDAO tenantDAO = new TenantDAO();
    private UserDAO userDAO = new UserDAO();
    private ActionListener event;
    private MigLayout layout;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;

    public SignUp() {
        initComponents();
        initLeft();
        initRight();
    }

    private void initLeft() {
        layout = new MigLayout("fill, insets 0");
        leftPanel = new JPanel();
        bg.setLayout(layout);
        bg.add(leftPanel, "width " + coverSize + "%, pos 0al 0 n 100%");

        MigLayout layoutLeft = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        leftPanel.setLayout(layoutLeft);
        leftPanel.setBackground(new Color(24, 90, 157));

        JLabel titleLeft = new JLabel("Already member?");
        titleLeft.setFont(new Font("sansserif", 1, 30));
        titleLeft.setForeground(new Color(245, 245, 245));
        leftPanel.add(titleLeft);

        JLabel description = new JLabel("greate to see you again");
        description.setForeground(new Color(245, 245, 245));
        leftPanel.add(description);

        JLabel description1 = new JLabel("please login to start");
        description1.setForeground(new Color(245, 245, 245));
        leftPanel.add(description1);

        ButtonOutLine buttonLeft = new ButtonOutLine();
        buttonLeft.setBackground(new Color(255, 255, 255));
        buttonLeft.setForeground(new Color(255, 255, 255));
        buttonLeft.setText("LOGIN");
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Login loginFrame = new Login();
                loginFrame.setVisible(true);
                dispose(SignUp.this);
            }

            private void dispose(SignUp signup) {
                signup.dispose();
            }
        });
        leftPanel.add(buttonLeft, "w 60%, h 40");
    }

    private void initRight() {
        rightPanel = new JPanel();
        bg.setLayout(layout);
        bg.add(rightPanel, "width " + loginSize + "%, pos 1al 0 n 100%");
        rightPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]5[]5[]5[]5[]5[]5[]5[]5[]25[]push"));
        rightPanel.setBackground(new Color(255, 255, 255));

        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(24, 90, 157));
        rightPanel.add(label, "w 60%");

        JLabel message = new JLabel("");
        message.setFont(new Font("sansserif", 1, 12));
        message.setForeground(Color.RED);
        rightPanel.add(message);

        MyTextField txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUser.setHint("Username");
        rightPanel.add(txtUser, "w 60%");

        JLabel lblUser = new JLabel("");
        lblUser.setFont(new Font("sansserif", 0, 10));
        lblUser.setForeground(Color.RED);
        rightPanel.add(lblUser, "w 60%");

        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        rightPanel.add(txtEmail, "w 60%");

        JLabel lblEmail = new JLabel("");
        lblEmail.setFont(new Font("sansserif", 0, 10));
        lblEmail.setForeground(Color.RED);
        rightPanel.add(lblEmail, "w 60%");

        MyTextField txtPhone = new MyTextField();
        txtPhone.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/phone.png")));
        txtPhone.setHint("Phone");
        rightPanel.add(txtPhone, "w 60%");

        JLabel lblPhone = new JLabel("");
        lblPhone.setFont(new Font("sansserif", 0, 10));
        lblPhone.setForeground(Color.RED);
        rightPanel.add(lblPhone, "w 60%");

        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");
        rightPanel.add(txtPass, "w 60%");

        JLabel lblPwd = new JLabel("");
        lblPwd.setFont(new Font("sansserif", 0, 10));
        lblPwd.setForeground(Color.RED);
        rightPanel.add(lblPwd, "w 60%");

        JComboBox combobox = new JComboBox();
        combobox.setBackground(new Color(240, 255, 240));
        combobox.setFont(new Font("sansserif", 1, 12));
        rightPanel.add(combobox, "w 60%, h 40");
        combobox.setModel(comModel);
        tenantDAO.findAllTenants()
                .forEach(comModel::addElement);

        Button btnRight = new Button();
        btnRight.setBackground(new Color(24, 90, 157));
        btnRight.setForeground(new Color(250, 250, 250));
        btnRight.setText("SIGN UP");
        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lblEmail.setText("");
                lblPhone.setText("");
                lblPwd.setText("");
                message.setText("");

                Tenants tenant = (Tenants) combobox.getSelectedItem();
                Login login = new Login();
                NewOrUpdateUsers newUser = new NewOrUpdateUsers();

                try {
                    Users existedUser = userDAO.findUserByEmailForReg(txtEmail.getText());
                    if ("".equals(txtUser.getText()) || "".equals(txtEmail.getText()) || "".equals(txtPhone.getText()) || "".equals(new String(txtPass.getPassword()))) {
                        message.setText("Please fill out all fields.");
                    } else if (existedUser != null) {
                        message.setText("Email has been registered.");
                    } else {
//                        if (Validation.validateName(txtUser.getText())) {
                            newUser.setName(txtUser.getText());
//                        } else {
//                            lblUser.setText("Wrong name");
//                        }
                        if (Validation.validateEmail(txtEmail.getText())) {
                            newUser.setEmail(txtEmail.getText());
                        } else {
                            lblEmail.setText("Wrong email format.");
                        }
                        if (Validation.validatePhone(txtPhone.getText())) {
                            newUser.setPhone(txtPhone.getText());
                        } else {
                            lblPhone.setText("Phone number must be 10 digits number.");
                        }
                        if (Validation.validatePassword(new String(txtPass.getPassword()))) {
                            newUser.setPassword(Authentication.getMd5Password(new String(txtPass.getPassword())));
                        } else {
                            lblPwd.setText("Password must be at least 8 characters.");
                        }
                        if (!StringUtils.isEmpty(newUser.getEmail())
                                && !StringUtils.isEmpty(newUser.getPhone())
                                && !StringUtils.isEmpty(newUser.getPassword())) {
                            newUser.setTenantId(tenant.getId());
                            userDAO.addNewManager(newUser);
                            dispose(SignUp.this);
                            login.setVisible(true);
                        }
                    }
                } catch (DatabaseException ex) {
                    ex.printStackTrace();
                }
//                Login loginFrame = new Login();
//                loginFrame.setVisible(true);
//                dispose(NewSignUp.this);
            }

            private void dispose(SignUp signup) {
                signup.dispose();
            }
        });
        rightPanel.add(btnRight, "w 40%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables

}
