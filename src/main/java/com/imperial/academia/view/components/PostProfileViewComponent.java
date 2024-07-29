package com.imperial.academia.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class represents a view component for displaying a post profile.
 * It extends JPanel and sets up the layout and appearance of the post profile
 * view component.
 */
public class PostProfileViewComponent extends JPanel {

    /**
     * Constructs a new PostProfileViewComponent with the specified parameters.
     *
     * @param title        The title of the post.
     * @param content      The content of the post.
     * @param authorName     The author ID of the post.
     * @param creationDate The creation date of the post.
     * @param imageIcon    The image icon of the post.
     */
    public PostProfileViewComponent(String title, String content, String authorName, String creationDate,
            ImageIcon imageIcon) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel imageLabel = new JLabel();
        if (imageIcon != null) {
            imageLabel.setIcon(imageIcon);
        } else {
            imageLabel.setText("No Image Found");
        }
        imageLabel.setPreferredSize(new Dimension(100, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        add(imageLabel, gbc);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        add(titleLabel, gbc);

        JLabel contentLabel = new JLabel("<html>" + truncateContent(content) + "</html>");
        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        add(contentLabel, gbc);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        infoPanel.setOpaque(false);

        JLabel authorLabel = new JLabel("Author ID: " + authorName + " | ");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        JLabel dateLabel = new JLabel("Creation Date: " + creationDate);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        infoPanel.add(authorLabel);
        infoPanel.add(dateLabel);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        add(infoPanel, gbc);
    }

    /**
     * Truncates the content to a maximum of 50 characters.
     *
     * @param content The content to truncate.
     * @return The truncated content.
     */
    private static String truncateContent(String content) {
        if (content.length() > 50) {
            return content.substring(0, 50) + "...";
        }
        return content;
    }
}
