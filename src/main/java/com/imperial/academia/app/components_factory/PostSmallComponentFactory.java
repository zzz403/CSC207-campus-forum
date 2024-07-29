package com.imperial.academia.app.components_factory;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.imperial.academia.view.components.PostSmallComponent;

/**
 * Factory class to create instances of PostSmallComponent.
 * Provides methods to load, scale, and round corners of images.
 */
public class PostSmallComponentFactory {

    /**
     * Creates a PostSmallComponent with the specified parameters.
     *
     * @param imageUrl The file path to the post image.
     * @param avatarUrl The file path to the author's avatar image.
     * @param title The title of the post.
     * @param content The content of the post.
     * @param author The author of the post.
     * @param likes The number of likes the post has.
     * @return A new PostSmallComponent instance or null if an error occurred.
     */
    public static PostSmallComponent createPostSmallComponent(String imageUrl, String avatarUrl, String title, String content, String author, int likes, JFrame applicationFrame) {
        BufferedImage image = null;
        BufferedImage avatar = null;
        try {
            // Load images from file paths
            image = ImageIO.read(new File(imageUrl));
            avatar = ImageIO.read(new File(avatarUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Scale and round corners of the images
        image = scaleImage(image, 300, 200);
        image = imageMakeRoundedCorner(image, 30);
        avatar = imageMakeRoundedCorner(avatar, 250);

        // Return a new PostSmallComponent with the processed images and provided data
        return new PostSmallComponent(image, avatar, title, content, author, likes, applicationFrame);
    }

    /**
     * Scales the given image to fit within the specified dimensions, maintaining aspect ratio.
     * Crops the image slightly to ensure it fits exactly within the dimensions.
     *
     * @param image The image to scale.
     * @param maxWidth The maximum width of the scaled image.
     * @param maxHeight The maximum height of the scaled image.
     * @return The scaled and cropped image.
     */
    private static BufferedImage scaleImage(BufferedImage image, int maxWidth, int maxHeight) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Scale the image if it exceeds the specified dimensions
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

            // Crop the image slightly to fit within the dimensions
            int cropSize = 10;
            BufferedImage croppedImage = scaledImage.getSubimage(cropSize, cropSize, newWidth - 2 * cropSize, newHeight - 2 * cropSize);
            return croppedImage;
        } else {
            return image;
        }
    }

    /**
     * Creates a new image with rounded corners from the given image.
     *
     * @param image The original image.
     * @param cornerRadius The radius of the corners.
     * @return The image with rounded corners.
     */
    private static BufferedImage imageMakeRoundedCorner(BufferedImage image, int cornerRadius) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // Enable anti-aliasing for smooth corners
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        // Create a shape with rounded corners and fill it
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // Composite the image on top using the rounded shape as the alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
