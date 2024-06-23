package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.login.LoginState;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.login.LoginController;
import com.imperial.academia.view.components.AvatarComponent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * LoginView class represents the login interface of the application.
 */
public class LoginView extends JPanel {
    public final String viewName = "log in";

    private JLabel usernameErrorLabel;
    private JLabel passwordErrorLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Constructs a LoginView instance and initializes the UI components.
     *
     * @param loginController the login controller
     * @param loginViewModel the login view model
     */
    public LoginView(LoginController loginController, LoginViewModel loginViewModel) {
        setLayout(new BorderLayout());

        // Left panel setup
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

        // Username field setup
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel usernameLabel = new JLabel(loginViewModel.USERNAME_LABEL);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, 40));
        leftPanel.add(usernameField, gbc);

        gbc.gridy++;
        usernameErrorLabel = new JLabel();
        usernameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameErrorLabel.setForeground(Color.RED);
        leftPanel.add(usernameErrorLabel, gbc);

        // Password field setup
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel passwordLabel = new JLabel(loginViewModel.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        leftPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 5, 0);
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, 40));
        leftPanel.add(passwordField, gbc);

        gbc.gridy++;
        passwordErrorLabel = new JLabel();
        passwordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordErrorLabel.setForeground(Color.RED);
        leftPanel.add(passwordErrorLabel, gbc);

        // Remember me checkbox setup
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 0, 10, 0);
        gbc.gridwidth = 2;

        JCheckBox rememberMeCheckBox = new JCheckBox(loginViewModel.REMEMBER_BUTTON_LABEL);
        rememberMeCheckBox.setFont(new Font("Arial", Font.PLAIN, 14));
        leftPanel.add(rememberMeCheckBox, gbc);

        // Login button setup
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
            loginController.execute(usernameField.getText(), new String(passwordField.getPassword()), rememberMeCheckBox.isSelected());
        });
        leftPanel.add(loginButton, gbc);

        // Forgot password label setup
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 0, 0, 0);

        JLabel forgotPasswordLabel = new JLabel(loginViewModel.FORGOT_BUTTON_LABEL);
        forgotPasswordLabel.setForeground(Color.BLUE);
        forgotPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftPanel.add(forgotPasswordLabel, gbc);

        // Sign up label setup
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 0, 0);

        JLabel signUpLabel = new JLabel(loginViewModel.SIGNUP_BUTTON_LABEL);
        signUpLabel.setForeground(Color.BLUE);
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginController.navigateToSignup();
            }
        });
        leftPanel.add(signUpLabel, gbc);

        // Right panel setup
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBackground(new Color(230, 240, 255));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel imageLabel = new JLabel(new ImageIcon("/mnt/data/image.png"));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        rightPanel.add(imageLabel, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        // Load saved credentials if available
        String[] credentials = loginController.loadCredentials();
        if (credentials[0] != null && credentials[1] != null) {
            usernameField.setText(credentials[0]);
            passwordField.setText(credentials[1]);
            rememberMeCheckBox.setSelected(true);
        }

        // Add DocumentListener to update state
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }

            private void updateState() {
                loginViewModel.setStateUsername(usernameField.getText());
            }
        });

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateState();
            }

            private void updateState() {
                loginViewModel.setStatePassword(new String(passwordField.getPassword()));
            }
        });

        // Add property change listener to update the view when the model changes
        loginViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                LoginState state = loginViewModel.getState();
                usernameField.setText(state.getUsername());
                passwordField.setText(state.getPassword());
                usernameErrorLabel.setText(state.getUsernameError());
                passwordErrorLabel.setText(state.getPasswordError());
            }
        });
    }
}
