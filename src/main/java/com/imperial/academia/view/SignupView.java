package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.signup.SignupController;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.signup.SignupState;

import javax.swing.*;
import java.awt.*;

public class SignupView extends JPanel {
    public final String viewName = "sign up";

    private JLabel usernameErrorLabel;
    private JLabel passwordErrorLabel;
    private JLabel repeatPasswordErrorLabel;
    private JLabel emailErrorLabel;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        setLayout(new BorderLayout());

        // 左侧面板
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

        JLabel titleLabel = new JLabel(signupViewModel.TITLE_LABEL);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180));
        leftPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel usernameLabel = new JLabel(signupViewModel.USERNAME_LABEL);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        JTextField usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 40));
        leftPanel.add(usernameField, gbc);

        gbc.gridy++;
        usernameErrorLabel = new JLabel();
        usernameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameErrorLabel.setForeground(Color.RED);
        leftPanel.add(usernameErrorLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel passwordLabel = new JLabel(signupViewModel.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 40));
        leftPanel.add(passwordField, gbc);

        gbc.gridy++;
        passwordErrorLabel = new JLabel();
        passwordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordErrorLabel.setForeground(Color.RED);
        leftPanel.add(passwordErrorLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel repeatPasswordLabel = new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(repeatPasswordLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        JPasswordField repeatPasswordField = new JPasswordField(20);
        repeatPasswordField.setPreferredSize(new Dimension(repeatPasswordField.getPreferredSize().width, 40));
        leftPanel.add(repeatPasswordField, gbc);

        gbc.gridy++;
        repeatPasswordErrorLabel = new JLabel();
        repeatPasswordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        repeatPasswordErrorLabel.setForeground(Color.RED);
        leftPanel.add(repeatPasswordErrorLabel, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel emailLabel = new JLabel(signupViewModel.EMAIL_LABEL);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(emailLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        JTextField emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(emailField.getPreferredSize().width, 40));
        leftPanel.add(emailField, gbc);

        gbc.gridy++;
        emailErrorLabel = new JLabel();
        emailErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailErrorLabel.setForeground(Color.RED);
        leftPanel.add(emailErrorLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);

        JButton signupButton = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setBackground(new Color(70, 130, 180));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 40));
        signupButton.addActionListener(e -> signupController.execute(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                new String(repeatPasswordField.getPassword()),
                emailField.getText()
        ));
        leftPanel.add(signupButton, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);

        JLabel backToLoginLabel = new JLabel("Back to Login");
        backToLoginLabel.setForeground(Color.BLUE);
        backToLoginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        backToLoginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backToLoginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ((CardLayout) getParent().getLayout()).show(getParent(), "log in");
            }
        });
        leftPanel.add(backToLoginLabel, gbc);

        // 右侧面板
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(230, 240, 255));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel imageLabel = new JLabel(new ImageIcon("/mnt/data/image.png")); // 添加你的图片路径
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        // 添加面板到主面板
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        signupViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                SignupState state = signupViewModel.getState();
                usernameErrorLabel.setText(state.getUsernameError());
                passwordErrorLabel.setText(state.getPasswordError());
                repeatPasswordErrorLabel.setText(state.getRepeatPasswordError());
                emailErrorLabel.setText(state.getEmailError());
            }
        });
    }
}