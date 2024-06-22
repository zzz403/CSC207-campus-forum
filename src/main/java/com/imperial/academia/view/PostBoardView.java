package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.imperial.academia.interface_adapter.postboard.PostBoardController;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;

public class PostBoardView extends JPanel {
    public final String viewName = "post board";

    private final PostBoardViewModel posterViewModel;
    private final PostBoardController posterController;

    public PostBoardView(PostBoardViewModel posterViewModel, PostBoardController posterController) {
        this.posterViewModel = posterViewModel;
        this.posterController = posterController;

        setLayout(new BorderLayout());

        // Create and add the top navigation bar
        JPanel navBar = createNavBar();
        add(navBar, BorderLayout.NORTH);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Add main panel to the center of the layout
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createNavBar(){
        JPanel navBar = new JPanel();
        navBar.setLayout(new BorderLayout());
        navBar.setBackground(Color.WHITE);
        navBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Logo
        ImageIcon logo = new ImageIcon("resources/logo.png");
        logo.setImage(logo.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        JLabel logoLabel = new JLabel(logo);
        logoLabel.setPreferredSize(new Dimension(50, 50));
        navBar.add(logoLabel, BorderLayout.WEST);

        // Search Bar
        JTextField searchField = new JTextField("Search");
        searchField.setPreferredSize(new Dimension(400, 30));
        searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        navBar.add(searchField, BorderLayout.CENTER);

        // Icons and Profile Picture
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setBackground(Color.WHITE);

        JButton chatButton = new JButton("Chat");
        JButton createPostButton = new JButton("Create Post");
        createPostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                posterController.changeView("create post");
            }
        });
        JButton notificationButton = new JButton("Notification");
        JButton profileButton = new JButton("Profile");

        rightPanel.add(chatButton);
        rightPanel.add(createPostButton);
        rightPanel.add(notificationButton);
        rightPanel.add(profileButton);

        navBar.add(rightPanel, BorderLayout.EAST);
        return navBar;
    }
}
