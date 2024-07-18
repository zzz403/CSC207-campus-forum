package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfileState;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;

/**
 * The ProfileView class represents the UI for displaying a user's profile.
 * It includes user information, an avatar, and options to edit or chat with the user.
 */
public class ProfileView extends JPanel {

    /**
     * The name of the view.
     */
    public final String viewName = "profile";

    /**
     * A cache for storing user avatar images.
     */
    private final Map<String, ImageIcon> imageCache = new HashMap<>();

    /**
     * The controller for profile operations.
     */
    private final ProfileController profileController = new ProfileController();

    /**
     * Constructs a new ProfileView with the specified profile view model.
     *
     * @param profileViewModel the view model for the profile data
     */
    public ProfileView(ProfileViewModel profileViewModel) {
        super(new BorderLayout());  // Set the layout of ProfileView to BorderLayout for simplicity

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(400, 100));

        // default image
        JPanel avatarPanel = new JPanel(new BorderLayout());
        avatarPanel.setBackground(new Color(255, 225, 120));
        ImageIcon originalIcon = new ImageIcon("resources/avatar/default_avatar.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        avatarPanel.add(imageLabel);
        topPanel.add(avatarPanel, BorderLayout.WEST);

        JButton chatOrModify = new JButton("Edit");
        chatOrModify.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (profileViewModel.getProfileState().isMe()) {
                    profileController.edit();
                } else {
                    profileController.chat(profileViewModel.getProfileState().getId());
                }
            }
        });

        topPanel.add(chatOrModify, BorderLayout.EAST);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBackground(new Color(255, 225, 120));
        JLabel nameLabel = new JLabel("UserName: UserName");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel emailLabel = new JLabel("Email: xxx@mail.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel idLabel = new JLabel("User ID: UserId");
        idLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel roleLabel = new JLabel("Role : ");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel registrationDateLabel = new JLabel("Member since ");
        registrationDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        infoPanel.add(nameLabel, BorderLayout.CENTER);
        infoPanel.add(emailLabel, BorderLayout.CENTER);
        infoPanel.add(idLabel, BorderLayout.CENTER);
        infoPanel.add(roleLabel, BorderLayout.CENTER);
        infoPanel.add(registrationDateLabel, BorderLayout.CENTER);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        topPanel.add(infoPanel, BorderLayout.CENTER);

        // TODO change bottom to user's post
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(new Color(58, 185, 232));
        for (int i = 0; i < 3000; i++) {
            bottomPanel.add(new JLabel("Label " + i));
        }

        JScrollPane scrollPane = new JScrollPane(bottomPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Set constraints for the top panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(topPanel, constraints);

        // Set constraints for the scroll pane
        constraints.gridy = 1;
        constraints.weighty = 0.8;
        mainPanel.add(scrollPane, constraints);

        this.add(mainPanel, BorderLayout.CENTER);  // Add mainPanel to ProfileView

        profileViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                ProfileState state = (ProfileState) evt.getNewValue();
                // set the new image
                if (imageCache.containsKey(state.getAvatarUrl())) {
                    imageLabel.setIcon(imageCache.get(state.getAvatarUrl()));
                } else {
                    ImageIcon userIcon = new ImageIcon(state.getAvatarUrl());
                    Image resizedUserImage = userIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    ImageIcon resizedUserIcon = new ImageIcon(resizedUserImage);
                    imageLabel.setIcon(resizedUserIcon);
                    imageCache.put(state.getAvatarUrl(), resizedUserIcon);
                }

                // set the user info
                nameLabel.setText("UserName: " + state.getUsername());
                emailLabel.setText("Email: " + state.getEmail());
                idLabel.setText("User ID: " + state.getId());
                roleLabel.setText("Role : " + state.getRole());
                registrationDateLabel.setText("Member since " + state.getRegistrationDate().toString().substring(0, 10));
                chatOrModify.setText(state.isMe() ? "Edit" : "Chat");
                // TODO profile edit view
            }
        });
    }
}
