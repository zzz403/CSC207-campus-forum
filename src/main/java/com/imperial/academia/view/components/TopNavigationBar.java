package com.imperial.academia.view.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import com.imperial.academia.app.componets_factory.AvatarFacory;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarController;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarState;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;

public class TopNavigationBar extends JPanel {
    private JPanel topNavPanel;

    public TopNavigationBar(TopNavigationBarController topNavigationBarController, TopNavigationBarViewModel topNavigationBarViewModel) throws IOException {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Create the main panel with GridBagLayout
        topNavPanel = new JPanel(new GridBagLayout());
        topNavPanel.setBackground(Color.WHITE);
        topNavPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;

        // Logo
        ImageIcon logo = new ImageIcon("resources/logo.png");
        logo.setImage(logo.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setPreferredSize(new Dimension(50, 50));
        topNavPanel.add(logoLabel, gbc);

        // Search Bar
        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        JTextField searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                searchField.setText("");
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search");
                }
            }
        });
        topNavPanel.add(searchField, gbc);

        // Icons and Profile Picture
        gbc.gridx++;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        // Chat Button
        JButton chatButton = createIconButton("resources/chat_icon.png", 40, 40);
        rightPanel.add(chatButton);

        // Create Post Button
        JButton createPostButton = createIconButton("resources/create_post_icon.png", 40, 40);
        createPostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                topNavigationBarController.changeView("create post");
            }
        });
        rightPanel.add(createPostButton);

        // Notification Button
        JButton notificationButton = createIconButton("resources/notification_icon.png", 40, 40);
        rightPanel.add(notificationButton);

        // Profile Button
        String avatarUrl = "resources/avatar/admin_avatar.png";
        AvatarComponent profileButton = AvatarFacory.create(ABORT, avatarUrl, avatarUrl);
        // profileButton.setPreferredSize(new Dimension(300,300)); 
        rightPanel.add(profileButton);

        topNavPanel.add(rightPanel, gbc);

        // Add topNavPanel to this panel
        add(topNavPanel, BorderLayout.CENTER);

        topNavigationBarViewModel.addPropertyChangeListener(e -> {
            if("state".equals(e.getPropertyName())) {
                TopNavigationBarState state = topNavigationBarViewModel.getState();
                String avatarUrlLambda = state.getAvatarUrl() != null ? state.getAvatarUrl() : "resources/default_profile_icon.png";
                profileButton.setAvatarUrl(avatarUrlLambda);
                profileButton.setCurrentViewName(state.getCurrentViewName());
                profileButton.setUserId(state.getUserId());
                System.out.println("TopNavigationBar: Avatar URL: " + state.getAvatarUrl());
                System.out.println("TopNavigationBar: Current View Name: " + state.getCurrentViewName());
            }
        });
    }

    private JButton createIconButton(String imagePath, int width, int height) {
        System.out.println(imagePath);
        ImageIcon icon = new ImageIcon(imagePath);
        icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        return button;
    }
}
