package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;

import javax.swing.*;
import java.awt.*;

public class ProfileView extends JPanel {
    public final String viewName = "profile";
    // Constructor
    public ProfileView(ProfileController profileController, ProfileViewModel profileViewModel) {
        super(new BorderLayout());  // Set the layout of ProfileView to BorderLayout for simplicity

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(400, 100));

        //TODO change default image to user image
        JPanel avatarPanel = new JPanel(new BorderLayout());
        avatarPanel.setBackground(new Color(255, 225, 120));
        ImageIcon originalIcon = new ImageIcon("resources/avatar/default_avatar.png");
        Image resizedImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel imageLabel = new JLabel(resizedIcon);
        avatarPanel.add(imageLabel);
        topPanel.add(avatarPanel, BorderLayout.WEST);

        //TODO change default info to user info
        JPanel infoPanel = new JPanel(new GridLayout(0, 1));
        infoPanel.setBackground(new Color(255, 225, 120));
        JLabel nameLabel = new JLabel("          UserName: UserID");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel emailLabel = new JLabel("          Email: xxx@mail.com");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoPanel.add(nameLabel, BorderLayout.CENTER);
        infoPanel.add(emailLabel, BorderLayout.CENTER);
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
    }
}
