package com.imperial.academia.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import com.imperial.academia.app.components_factory.PostProfileFactory;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfileState;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.view.components.PostProfileViewComponent;
import com.imperial.academia.view.style.CustomScrollBarUI;

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

    private final JPanel bottomPanel;

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
        // Set preferred, minimum, and maximum sizes for top panel
        topPanel.setPreferredSize(new Dimension(400, 600));
        topPanel.setMinimumSize(new Dimension(400, 600));
        topPanel.setMaximumSize(new Dimension(400, 600));

        // Default image
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.Y_AXIS));
        avatarPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));

        ImageIcon originalIcon = new ImageIcon("resources/avatar/default_avatar.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Smaller size
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setAlignmentX(CENTER_ALIGNMENT);  // Center the image
        avatarPanel.add(imageLabel);

        avatarPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between image and button

        // Button for editing or chatting
        JButton chatOrModify = new JButton("Edit");
        chatOrModify.setAlignmentX(CENTER_ALIGNMENT);  // Center the button
        chatOrModify.setMaximumSize(new Dimension(200, 30)); // Set max width
        chatOrModify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (profileViewModel.getProfileState().isMe()) {
                    profileController.edit();
                } else {
                    profileController.chat(profileViewModel.getProfileState().getId());
                }
            }
        });

        avatarPanel.add(chatOrModify); // Add button below the avatar
        topPanel.add(avatarPanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
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

        infoPanel.add(nameLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(idLabel);
        infoPanel.add(roleLabel);
        infoPanel.add(registrationDateLabel);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));
        topPanel.add(infoPanel, BorderLayout.CENTER);

        // Logout button setup
        // Logout button setup
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 30));
        logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                profileController.logout();
                System.out.println("Logout button clicked");
            }
        });

        // Right side button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(logoutButton);  // Add logout button at the top
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(buttonPanel, BorderLayout.EAST);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        ProfileState currentState = profileViewModel.getProfileState();
        createAllPost(currentState);

        JScrollPane scrollPane = new CustomScrollBarUI.CustomScrollPane(bottomPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Set preferred, minimum, and maximum sizes for bottom panel
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setMinimumSize(new Dimension(400, 400));
        scrollPane.setMaximumSize(new Dimension(400, 400));

        // Set constraints for the top panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 0.6; // 60% height for top panel
        constraints.fill = GridBagConstraints.BOTH;
        mainPanel.add(topPanel, constraints);

        // Set constraints for the scroll pane (bottom panel)
        constraints.gridy = 1;
        constraints.weighty = 0.4; // 40% height for bottom panel
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
                roleLabel.setText("Role: " + state.getRole());
                registrationDateLabel.setText("Member since " + state.getRegistrationDate().toString().substring(0, 10));
                chatOrModify.setText(state.isMe() ? "Edit" : "Chat");

                logoutButton.setVisible(state.isMe());

                createAllPost(state);
            }
        });
    }

    private void createAllPost(ProfileState state) {
        bottomPanel.removeAll();
        for (int i = 0; i < state.getPostTitles().size(); i++) {
            PostProfileViewComponent post = PostProfileFactory.create(
                    state.getPostTitles().get(i),
                    state.getPostContents().get(i),
                    state.getUsername(),
                    state.getPostCreationDates().get(i).toString().substring(0, 10),
                    state.getPostImageUrls().get(i),
                    100 // Image size
            );
            int postID = state.getPostIds().get(i);
            post.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent evt) {
                    if (profileController.initPostById(postID)) {
                        System.out.println("init post success | postID: " + postID);
                        return;
                    }
                    System.out.println("init post unsuccessful | postID: " + postID);
                }
            });
            bottomPanel.add(post);
            bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }
}
