package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.signup.SignupController;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;

public class SignupView extends JPanel {
    public final String viewName = "sign up";

    private JLabel errorLabel;

    public SignupView(SignupController signupController, SignupViewModel signupViewModel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel(signupViewModel.TITLE_LABEL), gbc);

        gbc.gridy++;
        add(new JLabel(signupViewModel.USERNAME_LABEL), gbc);

        JTextField usernameField = new JTextField(20);
        Dimension fieldSize = new Dimension(200, 20); // 设置文本字段的大小
        usernameField.setMinimumSize(fieldSize);
        usernameField.setPreferredSize(fieldSize);
        gbc.gridx++;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(signupViewModel.PASSWORD_LABEL), gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setMinimumSize(fieldSize);
        passwordField.setPreferredSize(fieldSize);
        gbc.gridx++;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), gbc);

        JPasswordField repeatPasswordField = new JPasswordField(20);
        repeatPasswordField.setMinimumSize(fieldSize);
        repeatPasswordField.setPreferredSize(fieldSize);
        gbc.gridx++;
        add(repeatPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel(signupViewModel.EMAIL_LABEL), gbc);

        JTextField emailField = new JTextField(20);
        emailField.setMinimumSize(fieldSize);
        emailField.setPreferredSize(fieldSize);
        gbc.gridx++;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton signupButton = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        signupButton.addActionListener(e -> signupController.execute(
                usernameField.getText(),
                new String(passwordField.getPassword()),
                new String(repeatPasswordField.getPassword()),
                emailField.getText()
        ));
        add(signupButton, gbc);

        // 添加“Back to Login”按钮
        JButton backToLoginButton = new JButton("Back to Login");
        backToLoginButton.addActionListener(e -> {
            // 切换到登录视图
            ((CardLayout) getParent().getLayout()).show(getParent(), "log in");
        });
        gbc.gridy++;
        add(backToLoginButton, gbc);

        gbc.gridy++;
        errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        add(errorLabel, gbc);

        signupViewModel.addPropertyChangeListener(evt -> {
            if ("errorMessage".equals(evt.getPropertyName())) {
                errorLabel.setText(signupViewModel.getErrorMessage());
            }
        });
    }
}
