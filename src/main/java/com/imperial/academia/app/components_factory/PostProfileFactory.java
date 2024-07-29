package com.imperial.academia.app.components_factory;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.imperial.academia.view.components.PostProfileViewComponent;

/**
 * The PostProfileFactory class is responsible for creating PostProfileViewComponent components.
 * It uses caching to improve performance by reusing previously loaded images.
 */
public class PostProfileFactory {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    // Private constructor to prevent instantiation
    private PostProfileFactory() {}

    /**
     * Creates a PostProfileViewComponent with the specified parameters.
     *
     * @param title The title of the post.
     * @param content The content of the post.
     * @param authorId The ID of the author.
     * @param creationDate The creation date of the post.
     * @param imageUrl The URL of the image.
     * @param imageSize The size of the image (width and height).
     * @return A PostProfileViewComponent with the specified parameters.
     */
    public static PostProfileViewComponent create(String title, String content, String authorId, String creationDate, String imageUrl, int imageSize) {
        ImageIcon imageIcon = createPostImageIcon(imageUrl, imageSize);
        return new PostProfileViewComponent(title, content, authorId, creationDate, imageIcon);
    }

    /**
     * Creates an ImageIcon from the specified image URL and size.
     * If the image is already cached, it uses the cached version.
     * Otherwise, it loads the image, makes it square, and caches it.
     *
     * @param imageUrl The URL of the image.
     * @param size The size of the image (width and height).
     * @return An ImageIcon representing the image.
     */
    private static ImageIcon createPostImageIcon(String imageUrl, int size) {
        try {
            BufferedImage icon;
            if (cache.containsKey(imageUrl)) {
                icon = cache.get(imageUrl);
            } else {
                icon = ImageIO.read(new File(imageUrl));
                BufferedImage squareImage = makeSquareImage(icon);
                cache.put(imageUrl, squareImage);
                icon = squareImage;
            }
            Image scaledImage = icon.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Makes the specified BufferedImage square by adding white padding if necessary.
     *
     * @param image The original BufferedImage.
     * @return A square BufferedImage.
     */
    private static BufferedImage makeSquareImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int newSize = Math.max(width, height);
        BufferedImage squareImage = new BufferedImage(newSize, newSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = squareImage.createGraphics();

        // Enable anti-aliasing
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill the background with white
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, newSize, newSize);

        // Draw the original image centered in the new square image
        int x = (newSize - width) / 2;
        int y = (newSize - height) / 2;
        g2.drawImage(image, x, y, null);

        g2.dispose();
        return squareImage;
    }
}
