package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TopNavigationBar extends JPanel {

    public TopNavigationBar() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Logo
        ImageIcon logo = new ImageIcon("resources/logo.png");
        logo.setImage(logo.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setPreferredSize(new Dimension(50, 50));
        add(logoLabel, BorderLayout.WEST);

        // Search Bar
        JTextField searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(400, 30));
        searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        add(searchField, BorderLayout.CENTER);

        // Icons and Profile Picture
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        JButton chatButton = new JButton("Chat");
        JButton createButton = new JButton("Create Post");
        JButton notificationButton = new JButton("Notification");
        JButton profileButton = new JButton("Profile");

        rightPanel.add(chatButton);
        rightPanel.add(createButton);
        rightPanel.add(notificationButton);
        rightPanel.add(profileButton);

        add(rightPanel, BorderLayout.EAST);
    }
}