package com.imperial.academia.view;

import javax.swing.*;
import java.awt.*;

/**
 * The ForumView class represents the forum view in the application.
 * It extends JPanel and sets up the UI components for the forum screen.
 */
public class ForumView extends JPanel {
    public final String viewName = "forum";

    /**
     * Constructs a ForumView instance and initializes the UI components.
     */
    public ForumView() {
        // Set the layout of the main panel
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

    /**
     * Creates a JButton with specified text and action listener.
     *
     * @param text the text to display on the button
     * @param actionListener the action listener to attach to the button
     * @return the created JButton
     */
    private JButton createButton(String text, java.awt.event.ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 40));
        if (actionListener != null) {
            button.addActionListener(actionListener);
        }
        return button;
    }
}
