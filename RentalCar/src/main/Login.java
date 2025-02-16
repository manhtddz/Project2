package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.entity.Users;
import net.miginfocom.swing.MigLayout;
import saveinformation.Authentication;
import saveinformation.SecurityContext;
import swing.Button;
import swing.ButtonOutLine;
import swing.MyPasswordField;
import swing.MyTextField;

public class Login extends javax.swing.JFrame {
    
    private Authentication au = new Authentication();
    private ActionListener event;
    private MigLayout layout;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private final double addSize = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    
    public Login() {
        initComponents();
        initLeft();
        initRight();
    }
    
    private void initLeft() {
        layout = new MigLayout("fill, insets 0");
        leftPanel = new JPanel();
//        rightPanel = new JPanel();
        bg.setLayout(layout);
        bg.add(leftPanel, "width " + coverSize + "%, pos 0al 0 n 100%");
//        bg.add(rightPanel, "width " + loginSize + "%, pos 1al 0 n 100%");

//        leftPanel.setOpaque(false);
        MigLayout layoutLeft = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        leftPanel.setLayout(layoutLeft);
        leftPanel.setBackground(new Color(24, 90, 157));
        
        JLabel titleLeft = new JLabel("New manager?");
        titleLeft.setFont(new Font("sansserif", 1, 30));
        titleLeft.setForeground(new Color(245, 245, 245));
        leftPanel.add(titleLeft);
        JLabel description = new JLabel("Enter your personal details");
        description.setForeground(new Color(245, 245, 245));
        leftPanel.add(description);
        JLabel description1 = new JLabel("to start using our system");
        description1.setForeground(new Color(245, 245, 245));
        leftPanel.add(description1);
        ButtonOutLine buttonLeft = new ButtonOutLine();
        buttonLeft.setBackground(new Color(255, 255, 255));
        buttonLeft.setForeground(new Color(255, 255, 255));
        buttonLeft.setText("SIGN UP");
        buttonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SignUp signUpFrame = new SignUp();
                signUpFrame.setVisible(true);
                dispose(Login.this);
            }
            
            private void dispose(Login login) {
                login.dispose();
            }
        });
        leftPanel.add(buttonLeft, "w 60%, h 40");
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

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables

    private void initRight() {
        rightPanel = new JPanel();
        bg.setLayout(layout);
//        bg.add(leftPanel, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(rightPanel, "width " + loginSize + "%, pos 1al 0 n 100%");
        rightPanel.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        rightPanel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(24, 90, 157));
        rightPanel.add(label);
        MyTextField txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        
        rightPanel.add(txtEmail, "w 60%");
        MyPasswordField txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Password");

        //start: for test only
        txtEmail.setText("admin");
        txtPass.setText("1234");
        //End: for test only

        rightPanel.add(txtPass, "w 60%");
        JLabel message = new JLabel("");
        message.setForeground(Color.red);
        message.setFont(new Font("sansserif", 1, 12));
        rightPanel.add(message);
        
        Button btnRight = new Button();
        btnRight.setBackground(new Color(24, 90, 157));
        btnRight.setForeground(new Color(250, 250, 250));
        btnRight.setText("SIGN IN");
        btnRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (this.isFieldEmpty()) {
                    return;
                }
                Users user = au.Login(txtEmail.getText(), new String(txtPass.getPassword()));
                if (user == null) {
                    message.setText("Wrong email or password!");
                } else {
                    //            Home2 home2 = new Home2();

                    if (user.getRole() == 0) {
                        SecurityContext.saveSercurityContext(user);
                        AdminMain home = new AdminMain();
                        home.setVisible(true);
                        dispose(Login.this);
                    } else {
                        au.setTenant(user);
                        ManagerMain home = new ManagerMain();
                        home.setVisible(true);
                        dispose(Login.this);
                    }
                }
            }
            
            private void dispose(Login login) {
                login.dispose();
            }
            
            private boolean isFieldEmpty() {
                return false;
            }
        });
        rightPanel.add(btnRight, "w 40%, h 40");
    }
    
}
