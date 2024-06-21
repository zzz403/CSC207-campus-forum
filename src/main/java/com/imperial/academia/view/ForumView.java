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

public class ForumView extends JPanel {
    public final String viewName = "forum";
    private final ViewManagerModel viewManagerModel;
    public ForumView(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.setPreferredSize(new Dimension(400, 600));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Forum View");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180));
        mainPanel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 0, 10, 0);

        JButton postButton = new JButton("Post");
        postButton.setFont(new Font("Arial", Font.BOLD, 16));
        postButton.setBackground(new Color(70, 130, 180));
        postButton.setForeground(Color.WHITE);
        postButton.setFocusPainted(false);
        postButton.setPreferredSize(new Dimension(300, 40));
        postButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ((CardLayout) getParent().getLayout()).show(getParent(), "post board");
            }
        });
        mainPanel.add(postButton, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JButton profileButton = new JButton("Profile");
        profileButton.setFont(new Font("Arial", Font.BOLD, 16));
        profileButton.setBackground(new Color(70, 130, 180));
        profileButton.setForeground(Color.WHITE);
        profileButton.setFocusPainted(false);
        profileButton.setPreferredSize(new Dimension(300, 40));
        
        mainPanel.add(profileButton, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);

        JButton chatButton = new JButton("Chat");
        chatButton.setFont(new Font("Arial", Font.BOLD, 16));
        chatButton.setBackground(new Color(70, 130, 180));
        chatButton.setForeground(Color.WHITE);
        chatButton.setFocusPainted(false);
        chatButton.setPreferredSize(new Dimension(300, 40));
        mainPanel.add(chatButton, gbc);
        

        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);  // Update the insets here

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(300, 40));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ((CardLayout) getParent().getLayout()).show(getParent(), "log in");
            }
        });
        mainPanel.add(backButton, gbc);

        add(mainPanel, BorderLayout.CENTER);
    }
}
