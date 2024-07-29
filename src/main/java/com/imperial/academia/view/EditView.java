package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.edit.EditController;
import com.imperial.academia.interface_adapter.edit.EditState;
import com.imperial.academia.interface_adapter.edit.EditViewModel;
import com.imperial.academia.interface_adapter.profile.ProfileState;
import com.imperial.academia.interface_adapter.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EditView extends JPanel {
    public final String viewName = "edit";
    private final Map<String, ImageIcon> imageCache = new HashMap<>();
    private final EditController editController = new EditController();

    private JLabel usernameErrorLabel;
    private JLabel passwordErrorLabel;
    private JLabel repeatPasswordErrorLabel;
    private JLabel emailErrorLabel;

    public EditView(EditViewModel editViewModel){

        super(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        GridBagConstraints constraints = new GridBagConstraints();

        JLabel title = new JLabel(editViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(title);



        JPanel avatarPanel = new JPanel(new BorderLayout());
        ImageIcon originalIcon = new ImageIcon("resources/avatar/default_avatar.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        avatarPanel.add(imageLabel);
        mainPanel.add(avatarPanel);

        //TODO avatar update button

        JLabel userId = new JLabel("User ID: UserId");
        userId.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(userId);

        JPanel namePanel = new JPanel(new GridLayout());
        JLabel nameLabel = new JLabel(editViewModel.USERNAME_LABEL);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        namePanel.add(nameLabel);
        JTextField nameFiled = new JTextField(20);
        namePanel.add(nameFiled);
        mainPanel.add(namePanel);

        usernameErrorLabel = new JLabel();
        usernameErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameErrorLabel.setForeground(Color.RED);
        mainPanel.add(usernameErrorLabel);

        JPanel passwordPanel = new JPanel(new GridLayout());
        JLabel passwordLabel = new JLabel(editViewModel.PASSWORD_LABEL);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordPanel.add(passwordLabel);
        JTextField passwordFiled = new JTextField(20);
        passwordPanel.add(passwordFiled);
        mainPanel.add(passwordPanel);


        passwordErrorLabel = new JLabel();
        passwordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordErrorLabel.setForeground(Color.RED);
        mainPanel.add(passwordErrorLabel);


        JPanel repeatPasswordPanel = new JPanel(new GridLayout());
        JLabel repeatPasswordLabel = new JLabel(editViewModel.REPEAT_PASSWORD_LABEL);
        repeatPasswordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        repeatPasswordPanel.add(repeatPasswordLabel);
        JTextField repeatPasswordFiled = new JTextField(20);
        repeatPasswordPanel.add(repeatPasswordFiled);
        mainPanel.add(repeatPasswordPanel);



        repeatPasswordErrorLabel = new JLabel();
        repeatPasswordErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        repeatPasswordErrorLabel.setForeground(Color.RED);
        mainPanel.add(repeatPasswordErrorLabel);



        JPanel emailPanel = new JPanel(new GridLayout());
        JLabel emailLabel = new JLabel(editViewModel.EMAIL_LABEL);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailPanel.add(emailLabel);
        JTextField emailFiled = new JTextField(20);
        emailPanel.add(emailFiled);
        mainPanel.add(emailPanel);


        emailErrorLabel = new JLabel();
        emailErrorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        emailErrorLabel.setForeground(Color.RED);
        mainPanel.add(emailErrorLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        JButton update = new JButton(editViewModel.UPDATE_BUTTON_LABEL);
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editController.update(
                        nameFiled.getText(),
                        passwordFiled.getText(),
                        repeatPasswordFiled.getText(),
                        "resources/avatar/default_avatar.png", //TODO change to updated url
                        emailFiled.getText()
                );
            }
        });
        buttonPanel.add(update);

        JButton cancel = new JButton(editViewModel.CANCEL_BUTTON_LABEL);
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editController.cancel();
            }
        });
        buttonPanel.add(cancel);

        mainPanel.add(buttonPanel);



        editViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                EditState state = (EditState) evt.getNewValue();
                // set the new image
                if (imageCache.containsKey(state.getAvatarURL())) {
                    imageLabel.setIcon(imageCache.get(state.getAvatarURL()));
                } else {
                    ImageIcon userIcon = new ImageIcon(state.getAvatarURL());
                    Image resizedUserImage = userIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon resizedUserIcon = new ImageIcon(resizedUserImage);
                    imageLabel.setIcon(resizedUserIcon);
                    imageCache.put(state.getAvatarURL(), resizedUserIcon);
                }

                // set the user info
                userId.setText("User ID: " + state.getUserId());
                nameFiled.setText(state.getUserName());
                passwordFiled.setText(state.getPassword());
                repeatPasswordFiled.setText(state.getRepeatPassword());
                emailFiled.setText(state.getEmail());

            }else if ("error".equals(evt.getPropertyName())) {
                EditState state = (EditState) evt.getNewValue();
                usernameErrorLabel.setText(state.getUsernameError());
                passwordErrorLabel.setText(state.getPasswordError());
                repeatPasswordErrorLabel.setText(state.getRepeatPasswordError());
                emailErrorLabel.setText(state.getEmailError());
            }
        });
        add(mainPanel);
    }



}
