package com.imperial.academia.view.components;

import com.imperial.academia.entity.chat_message.FileData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FilePanel extends JPanel {

    public FilePanel(FileData fileData) {
        setOpaque(false);
        setLayout(new BorderLayout());

        // Extract file extension
        String fileName = fileData.getFileName();
        String fileExtension = getFileExtension(fileName);

        // Load and resize file icon based on file extension
        BufferedImage fileIconImage = loadIcon(fileExtension);
        if (fileIconImage == null) {
            fileIconImage = loadIcon("unknown");
        }
        JLabel fileIconLabel = new JLabel(new ImageIcon(resizeImage(fileIconImage, 50, 50)));
        fileIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fileIconLabel.setOpaque(false);
        fileIconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Adjust top margin to move down

        JLabel nameLabel = new JLabel(fileData.getFileName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        infoPanel.add(nameLabel);

        JLabel sizeLabel = new JLabel(fileData.getFileSize());
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        sizeLabel.setForeground(Color.GRAY);
        sizeLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Add margin to create space between name and size
        infoPanel.add(sizeLabel);

        // Add components to FilePanel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(infoPanel, BorderLayout.CENTER);
        contentPanel.add(fileIconLabel, BorderLayout.EAST);

        add(contentPanel, BorderLayout.CENTER);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(300, 100)); // Adjust size as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 15; // Arc width and height for rounded corners
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc);

        g2.setColor(Color.WHITE); // Background color
        g2.fill(roundedRect);

        g2.dispose();
    }

    // Helper method to extract file extension from file name
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1).toLowerCase();
        }
        return "unknown"; // Default case if no extension found
    }

    // Helper method to load an image icon
    private BufferedImage loadIcon(String fileExtension) {
        String iconPath = "resources/icons/file/" + fileExtension + ".png";
        try {
            return ImageIO.read(new File(iconPath));
        } catch (IOException e) {
            System.out.println("Failed to load file icon: " + iconPath);
            return null;
        }
    }

    // Helper method to resize an image
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return outputImage;
    }
}
