package com.imperial.academia.view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PostProfileViewComponent extends JPanel {

    public PostProfileViewComponent(String title, String content, String authorId, String creationDate, ImageIcon imageIcon) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                showPostDetailDialog(title, content, authorId, creationDate);
            }
        });

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

        JLabel authorLabel = new JLabel("Author ID: " + authorId + " | ");
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

    private static String truncateContent(String content) {
        if (content.length() > 50) {
            return content.substring(0, 50) + "...";
        }
        return content;
    }

    private static void showPostDetailDialog(String title, String content, String authorId, String creationDate) {
        JOptionPane.showMessageDialog(null,
                "Title: " + title + "\n" +
                        "Content: " + content + "\n" +
                        "Author ID: " + authorId + "\n" +
                        "Creation Date: " + creationDate,
                "Post Detail",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

