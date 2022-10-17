/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.offbreach;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author rafae
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
        loadingIcon.setVisible(false);
    }


    static MainMenu mainMenu = new MainMenu();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        mainView = new javax.swing.JPanel();
        logo = new javax.swing.JPanel();
        iconLogo = new javax.swing.JLabel();
        formView = new javax.swing.JPanel();
        inputView = new javax.swing.JPanel();
        emailView = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        passwordView = new javax.swing.JPanel();
        passwordLabel = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JPasswordField();
        buttonView = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        loadingView = new javax.swing.JPanel();
        loadingIcon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));

        header.setBackground(new java.awt.Color(9, 80, 111));
        header.setPreferredSize(new java.awt.Dimension(800, 50));
        header.setLayout(new java.awt.BorderLayout());
        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        mainView.setBackground(new java.awt.Color(230, 230, 230));
        mainView.setPreferredSize(new java.awt.Dimension(800, 500));
        mainView.setLayout(new java.awt.BorderLayout());

        logo.setBackground(new java.awt.Color(230, 230, 230));
        logo.setMinimumSize(new java.awt.Dimension(300, 400));
        logo.setPreferredSize(new java.awt.Dimension(800, 200));
        logo.setLayout(new java.awt.BorderLayout());

        iconLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/offBreach300x172.png"))); // NOI18N
        iconLogo.setRequestFocusEnabled(false);
        logo.add(iconLogo, java.awt.BorderLayout.CENTER);

        mainView.add(logo, java.awt.BorderLayout.PAGE_START);

        formView.setBackground(new java.awt.Color(230, 230, 230));
        formView.setLayout(new javax.swing.BoxLayout(formView, javax.swing.BoxLayout.Y_AXIS));

        inputView.setBackground(new java.awt.Color(230, 230, 230));
        inputView.setLayout(new javax.swing.BoxLayout(inputView, javax.swing.BoxLayout.Y_AXIS));

        emailView.setBackground(new java.awt.Color(230, 230, 230));
        emailView.setMaximumSize(new java.awt.Dimension(393, 50));
        emailView.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 12, 5));

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        emailLabel.setText("Email");
        emailView.add(emailLabel);

        emailTextField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        emailTextField.setMinimumSize(new java.awt.Dimension(300, 40));
        emailTextField.setPreferredSize(new java.awt.Dimension(300, 40));
        emailView.add(emailTextField);

        inputView.add(emailView);

        passwordView.setBackground(new java.awt.Color(230, 230, 230));
        passwordView.setMaximumSize(new java.awt.Dimension(381, 50));

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        passwordLabel.setText("Senha");
        passwordView.add(passwordLabel);

        passwordTextField.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordTextField.setPreferredSize(new java.awt.Dimension(300, 40));
        passwordView.add(passwordTextField);

        inputView.add(passwordView);

        buttonView.setBackground(new java.awt.Color(230, 230, 230));

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        loginButton.setText("Login");
        loginButton.setPreferredSize(new java.awt.Dimension(100, 40));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        buttonView.add(loginButton);

        inputView.add(buttonView);

        formView.add(inputView);

        loadingView.setBackground(new java.awt.Color(230, 230, 230));
        loadingView.setMaximumSize(new java.awt.Dimension(800, 150));
        loadingView.setPreferredSize(new java.awt.Dimension(800, 128));
        loadingView.setLayout(new java.awt.BorderLayout());

        loadingIcon.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loadingIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadingIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MnyxU.gif"))); // NOI18N
        loadingIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadingIcon.setMaximumSize(new java.awt.Dimension(150, 128));
        loadingIcon.setMinimumSize(new java.awt.Dimension(150, 128));
        loadingIcon.setPreferredSize(new java.awt.Dimension(200, 128));
        loadingView.add(loadingIcon, java.awt.BorderLayout.CENTER);

        formView.add(loadingView);

        jPanel1.setBackground(new java.awt.Color(230, 230, 230));
        jPanel1.setMinimumSize(new java.awt.Dimension(10, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(10, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        formView.add(jPanel1);

        mainView.add(formView, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainView, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(9, 80, 111));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 50));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(816, 569));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void changeColor(JPanel hover, Color color) {
        hover.setBackground(color);
    }

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {
        HardwareData data = new HardwareData();
        DatabaseConnection dbConnection = new DatabaseConnection();
        MainPage mainPage = new MainPage();
        mainPage.setLocationRelativeTo(null);


        String email = emailTextField.getText();
        String senha = String.valueOf(passwordTextField.getPassword());

        dbConnection.setConnection(email, senha);
        String emailUser = dbConnection.getEmail();
        String senhaUser = dbConnection.getSenha();
        String nomeUser = dbConnection.getNome();


        if (emailUser.equals(email) && senhaUser.equals(senha)) {
            User user = new User(email, senha, nomeUser);
            mainPage.setUser(user);
            mainPage.setUserName(user);
            dbConnection.saveHardwareData();
            mainPage.getLoocaData(dbConnection);
            mainPage.setVisible(true);
            mainMenu.dispose();
            data.cadastrarSistema();
            mainPage.trySaveInLoop();
        }else {
        }
        loadingIcon.setVisible(true);
    }//GEN-LAST:event_loginButtonMouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonView;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel emailView;
    private javax.swing.JPanel formView;
    private javax.swing.JPanel header;
    private javax.swing.JLabel iconLogo;
    private javax.swing.JPanel inputView;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel loadingIcon;
    private javax.swing.JPanel loadingView;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel logo;
    private javax.swing.JPanel mainView;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JPanel passwordView;
    // End of variables declaration//GEN-END:variables
}
