package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.login.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    public final String viewName = "log in";

    private JLabel errorLabel;

    public LoginView(LoginController loginController, LoginViewModel loginViewModel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel(loginViewModel.TITLE_LABEL), gbc);

        gbc.gridy++;
        add(new JLabel(loginViewModel.USERNAME_LABEL), gbc);

        JTextField usernameField = new JTextField(20);
        gbc.gridx++;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(loginViewModel.PASSWORD_LABEL), gbc);

        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx++;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JButton loginButton = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        loginButton.addActionListener(e -> loginController.execute(
                usernameField.getText(),
                new String(passwordField.getPassword())
        ));
        add(loginButton, gbc);

        // 添加“Sign Up”按钮
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(e -> {
            // 切换到注册视图
            ((CardLayout) getParent().getLayout()).show(getParent(), "sign up");
        });
        gbc.gridx++;
        add(signUpButton, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        add(errorLabel, gbc);

        loginViewModel.addPropertyChangeListener(evt -> {
            if ("errorMessage".equals(evt.getPropertyName())) {
                errorLabel.setText(loginViewModel.getErrorMessage());
            }
        });
    }
}
