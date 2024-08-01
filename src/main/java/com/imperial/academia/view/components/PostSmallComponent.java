package com.imperial.academia.view.components;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class PostSmallComponent extends JPanel {

    public PostSmallComponent(BufferedImage image, BufferedImage avatar, String title, String content, String author, int likes, JFrame applicationFrame) {
        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5)); // 设置空白边框

        JLabel mapPlaceholder = new JLabel(new ImageIcon(image));
        mapPlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        mapPlaceholder.setOpaque(false);
        mapPlaceholder.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // Info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        infoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 调整间距

        JLabel detailLabel = new JLabel(content);
        detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        detailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailLabel.setHorizontalAlignment(SwingConstants.LEFT);
        detailLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        infoPanel.add(detailLabel);

        // Avatar and author panel
        JPanel authorLikesPanel = new JPanel();
        authorLikesPanel.setLayout(new BoxLayout(authorLikesPanel, BoxLayout.X_AXIS));
        authorLikesPanel.setOpaque(false);
        authorLikesPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        Image scaledAvatar = avatar.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        JLabel avatarLabel = new JLabel(new ImageIcon(scaledAvatar));
        avatarLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        authorLikesPanel.add(avatarLabel);

        JLabel authorLabel = new JLabel(author);
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        authorLikesPanel.add(authorLabel);

        authorLikesPanel.add(Box.createHorizontalGlue());

        // Load and scale like icon
        BufferedImage likeIcon = null;
        try {
            likeIcon = ImageIO.read(new File("resources/icons/like_icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (likeIcon != null) {
            Image scaledLikeIcon = likeIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            JLabel likeIconLabel = new JLabel(new ImageIcon(scaledLikeIcon));
            likeIconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); // Add some padding
            authorLikesPanel.add(likeIconLabel);
        }

        JLabel likesLabel = new JLabel(String.valueOf(likes));
        likesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        authorLikesPanel.add(likesLabel);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 5))); // 调整间距
        infoPanel.add(authorLikesPanel);

        // Add components to MapPanel
        add(mapPlaceholder, BorderLayout.NORTH); // 调整图片位置
        add(infoPanel, BorderLayout.SOUTH); // 调整infoPanel位置

        setCursor(new Cursor(Cursor.HAND_CURSOR));

        setPreferredSize(new Dimension(300, 370));

        // fix display issue when size changing
        applicationFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                likesLabel.setVisible(false);
                likesLabel.setVisible(true);
            }
        });
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

    private static BufferedImage scaleImage(BufferedImage image, int maxWidth, int maxHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        if (width > maxWidth || height > maxHeight) {
            double widthRatio = (double) maxWidth / width;
            double heightRatio = (double) maxHeight / height;
            double scale = Math.min(widthRatio, heightRatio);

            int newWidth = (int) Math.floor(width * scale);
            int newHeight = (int) Math.floor(height * scale);

            Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();

            // Crop the image
            int cropSize = 10;
            BufferedImage croppedImage = scaledImage.getSubimage(cropSize, cropSize, newWidth - 2 * cropSize, newHeight - 2 * cropSize);
            return croppedImage;
        } else {
            return image;
        }
    }

    private static BufferedImage imageMakeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // Enable anti-aliasing for smooth corners
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // Create a shape with rounded corners
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // Composite the image on top using the rounded shape as the alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
