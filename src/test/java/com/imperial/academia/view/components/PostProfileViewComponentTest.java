package com.imperial.academia.view.components;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PostProfileViewComponentTest {

    @Test
    public void testPostProfileViewComponentInitialization() {
        String title = "Sample Post Title";
        String content = "This is a sample post content for testing purposes. It should be truncated.";
        String authorName = "Author123";
        String creationDate = "2024-07-31";
        ImageIcon imageIcon = new ImageIcon(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB));

        PostProfileViewComponent postProfileViewComponent = new PostProfileViewComponent(
                title, content, authorName, creationDate, imageIcon);

        assertNotNull(postProfileViewComponent);

        Component[] components = postProfileViewComponent.getComponents();
        assertEquals(4, components.length);

        JLabel imageLabel = (JLabel) components[0];
        assertNotNull(imageLabel);
        assertEquals(imageIcon, imageLabel.getIcon());

        JLabel titleLabel = (JLabel) components[1];
        assertNotNull(titleLabel);
        assertEquals(title, titleLabel.getText());

        JLabel contentLabel = (JLabel) components[2];
        assertNotNull(contentLabel);
        assertEquals("<html>" + PostProfileViewComponent.truncateContent(content) + "</html>", contentLabel.getText());

        JPanel infoPanel = (JPanel) components[3];
        assertNotNull(infoPanel);
        Component[] infoComponents = infoPanel.getComponents();
        assertEquals(2, infoComponents.length);

        JLabel authorLabel = (JLabel) infoComponents[0];
        assertEquals("Author ID: " + authorName + " | ", authorLabel.getText());

        JLabel dateLabel = (JLabel) infoComponents[1];
        assertEquals("Creation Date: " + creationDate, dateLabel.getText());

        // Ensure the panel is properly added to a frame and displayed
        JFrame frame = new JFrame();
        frame.add(postProfileViewComponent);
        frame.pack();
        frame.setVisible(false);

        // Allow some time for the GUI to render
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Dispose the frame after rendering
        frame.dispose();
    }
}
