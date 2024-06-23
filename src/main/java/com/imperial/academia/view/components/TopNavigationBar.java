package com.imperial.academia.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarController;

public class TopNavigationBar extends JPanel {
    public TopNavigationBar(TopNavigationBarController topNavigationBarController) {
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
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        add(searchField, BorderLayout.CENTER);

        // Icons and Profile Picture
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        JButton chatButton = new JButton("Chat");
        JButton createPostButton = new JButton("Create Post");
        createPostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                topNavigationBarController.changeView("create post");
            }
        });
        JButton notificationButton = new JButton("Notification");
        JButton profileButton = new JButton("Profile");

        rightPanel.add(chatButton);
        rightPanel.add(createPostButton);
        rightPanel.add(notificationButton);
        rightPanel.add(profileButton);

        add(rightPanel, BorderLayout.EAST);
    }
}