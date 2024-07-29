package com.imperial.academia.view.components;

import com.imperial.academia.entity.chat_message.MapData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {

    public MapPanel(MapData mapData, BufferedImage mapImage, boolean isMe) {
        setOpaque(false);
        setLayout(new BorderLayout());

        JLabel mapPlaceholder = new JLabel(new ImageIcon(mapImage));
        mapPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        mapPlaceholder.setOpaque(false);
        mapPlaceholder.setBorder(BorderFactory.createEmptyBorder(13, 0, 0, 0));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(isMe ? "You Shared a Location" : "Location Shared");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel detailLabel = new JLabel(mapData.getLocationInfo());
        detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(detailLabel);

        // Add components to MapPanel
        add(mapPlaceholder, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.SOUTH);

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(300, 250)); // Adjust size as needed
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 30; // Arc width and height for rounded corners
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc);

        g2.setColor(Color.WHITE); // Background color
        g2.fill(roundedRect);


        g2.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Map Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            MapData mapData = new MapData(51.4988, -0.1747,"Imperial College London");
            BufferedImage mapImage = new BufferedImage(300, 200, BufferedImage.TYPE_INT_ARGB);
            MapPanel mapPanel = new MapPanel(mapData, mapImage, false);

            frame.getContentPane().add(mapPanel);
            frame.setVisible(true);
        });
    }

}
