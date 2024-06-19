package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.login.RememberMeController;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.login.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    public final String viewName = "log in";

    public LoginView(LoginController loginController, LoginViewModel loginViewModel, RememberMeController rememberMeController) {
        setLayout(new BorderLayout());

        // Left panel
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        leftPanel.setPreferredSize(new Dimension(400, 600));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel(loginViewModel.TITLE_LABEL);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180));
        leftPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel usernameLabel = new JLabel(loginViewModel.USERNAME_LABEL);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0);
        JTextField usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 40));
        leftPanel.add(usernameField, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel passwordLabel = new JLabel(loginViewModel.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 40));
        leftPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 10, 0);
        gbc.gridwidth = 2;

        JCheckBox rememberMeCheckBox = new JCheckBox(loginViewModel.REMEMBER_BUTTON_LABEL);
        rememberMeCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        leftPanel.add(rememberMeCheckBox, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 20, 0);

        JButton loginButton = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 40));
        loginButton.addActionListener(e -> {
            loginController.execute(usernameField.getText(), new String(passwordField.getPassword()));
            if (rememberMeCheckBox.isSelected()) {
                rememberMeController.saveCredentials(usernameField.getText(), new String(passwordField.getPassword()));
            } else {
                rememberMeController.clearCredentials();
            }
        });
        leftPanel.add(loginButton, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0);

        JLabel forgotPasswordLabel = new JLabel(loginViewModel.FORGOT_BUTTON_LABEL);
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.add(forgotPasswordLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        JLabel signUpLabel = new JLabel(loginViewModel.SIGNUP_BUTTON_LABEL);
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ((CardLayout) getParent().getLayout()).show(getParent(), "sign up");
            }
        });
        leftPanel.add(signUpLabel, gbc);

        // Right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(230, 240, 255));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel imageLabel = new JLabel(new ImageIcon("/mnt/data/image.png"));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        String[] credentials = rememberMeController.loadCredentials();
        if (credentials[0] != null && credentials[1] != null) {
            usernameField.setText(credentials[0]);
            passwordField.setText(credentials[1]);
            rememberMeCheckBox.setSelected(true);
        }
    }
}
