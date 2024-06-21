package com.imperial.academia.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;

/**
 * The ForumView class represents the forum view in the application.
 * It extends JPanel and sets up the UI components for the forum screen.
 */
public class ForumView extends JPanel {
    public final String viewName = "forum";

    /**
     * Constructs a ForumView instance and initializes the UI components.
     */

    private final ViewManagerModel viewManagerModel;
    public ForumView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        setLayout(new BorderLayout());

        // Create the main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.setPreferredSize(new Dimension(400, 600));

        // Set up GridBagConstraints for component positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        // Title label setup
        JLabel titleLabel = new JLabel("Forum View");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180));
        mainPanel.add(titleLabel, gbc);

        // Post button setup
        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 10, 0);
        JButton postButton = createButton("Post", evt -> ((CardLayout) getParent().getLayout()).show(getParent(), "post"));
        mainPanel.add(postButton, gbc);

        // Profile button setup
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        JButton profileButton = createButton("Profile", null);
        mainPanel.add(profileButton, gbc);

        // Chat button setup
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        JButton chatButton = createButton("Chat", null);
        mainPanel.add(chatButton, gbc);

        // Back button setup
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        JButton backButton = createButton("Back", evt -> ((CardLayout) getParent().getLayout()).show(getParent(), "log in"));
        mainPanel.add(backButton, gbc);

        // Add main panel to the center of the ForumView
        add(mainPanel, BorderLayout.CENTER);
    }

    private void genPostButton(JPanel mainPanel, GridBagConstraints gbc) {
        JButton postButton = new JButton("Post");
        postButton.setFont(new Font("Arial", Font.BOLD, 16));
        postButton.setBackground(new Color(70, 130, 180));
        postButton.setForeground(Color.WHITE);
        postButton.setFocusPainted(false);
        postButton.setPreferredSize(new Dimension(300, 40));
        mainPanel.add(postButton, gbc);
        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                viewManagerModel.setActiveView("poster");
                viewManagerModel.firePropertyChanged();
            }
        });

    }
}
