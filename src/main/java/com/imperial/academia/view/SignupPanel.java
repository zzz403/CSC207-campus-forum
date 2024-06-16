package com.imperial.academia.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPanel extends JPanel {
    private PanelController panelController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignupPanel(PanelController panelController) {
        this.panelController = panelController;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new RegisterButtonListener());

        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(e -> panelController.showPanel("login"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(confirmPasswordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(confirmPasswordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(registerButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(backButton, gbc);
    }

    private class RegisterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (validateRegistration(username, password, confirmPassword)) {
                // 注册逻辑，成功后跳转到登录页面
                JOptionPane.showMessageDialog(SignupPanel.this, "Registration successful", "Register", JOptionPane.INFORMATION_MESSAGE);
                panelController.showPanel("login");
            } else {
                JOptionPane.showMessageDialog(SignupPanel.this, "Registration failed", "Register Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private boolean validateRegistration(String username, String password, String confirmPassword) {
            // 注册验证逻辑
            return !username.isEmpty() && password.equals(confirmPassword);
        }
    }
}
