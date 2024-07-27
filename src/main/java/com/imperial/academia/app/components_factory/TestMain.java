package com.imperial.academia.app.components_factory;

import com.imperial.academia.view.components.PostProfileViewComponent;

import javax.swing.*;
import java.awt.*;

public class TestMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Post Profile View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Example usage of PostProfileViewComponent
            for (int i = 0; i < 5; i++) {
                String imageUrl = "resources/test_image/test_image_" + (i % 3 + 1) + ".jpg";
                PostProfileViewComponent post = PostProfileFactory.create(
                        "Sample Post Title " + (i + 1),
                        "This is some content of the post " + (i + 1) + ".",
                        "Author" + (i + 1),
                        "2024-07-24",
                        imageUrl,
                        100 // Image size
                );
                mainPanel.add(post);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }

            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            frame.getContentPane().add(scrollPane);
            frame.setVisible(true);
        });
    }
}
