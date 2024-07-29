package com.imperial.academia.app.components_factory;

import com.imperial.academia.view.components.PostSmallComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PostSmallComponentFactory {

    public static PostSmallComponent createPostSmallComponent(String imageUrl, String avatarUrl, String title, String content, String author, int likes) {
        BufferedImage image = null;
        BufferedImage avatar = null;
        try {
            image = ImageIO.read(new File(imageUrl));
            avatar = ImageIO.read(new File(avatarUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        image = scaleImage(image, 300, 200);
        image = imageMakeRoundedCorner(image, 30);

        avatar = imageMakeRoundedCorner(avatar, 250);

        return new PostSmallComponent(image, avatar, title, content, author, likes);
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

            BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
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