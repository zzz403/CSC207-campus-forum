package com.imperial.academia.app.components_factory;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.imperial.academia.view.components.AvatarComponent;

/**
 * The AvatarFactory class is responsible for creating avatar components with rounded corners.
 * It uses caching to improve performance by reusing previously loaded avatar images.
 */
public class AvatarFactory {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    // Private constructor to prevent instantiation
    private AvatarFactory() {}

    /**
     * Creates an AvatarComponent for a user with the specified user ID, avatar URL, and height.
     *
     * @param userId The user ID of the avatar owner.
     * @param avatarUrl The URL of the avatar image.
     * @param height The height of the avatar component.
     * @return An AvatarComponent for the specified user.
     */
    public static AvatarComponent create(int userId, String avatarUrl, int height) {
        ImageIcon avatarIcon = createAvatarIcon(avatarUrl, height);
        return new AvatarComponent(userId, avatarIcon);
    }

    /**
     * Creates an ImageIcon from the specified avatar URL and size.
     * If the avatar image is already cached, it uses the cached version.
     * Otherwise, it loads the image, makes it rounded, and caches it.
     *
     * @param avatarUrl The URL of the avatar image.
     * @param size The size of the avatar image.
     * @return An ImageIcon representing the avatar image.
     */
    private static ImageIcon createAvatarIcon(String avatarUrl, int size) {
        try {
            BufferedImage icon;
            if (cache.containsKey(avatarUrl)) {
                icon = cache.get(avatarUrl);
            } else {
                icon = ImageIO.read(new File(avatarUrl));
                BufferedImage rounded = makeRoundedCorner(icon);
                cache.put(avatarUrl, rounded);
            }
            Image scaledImage = icon.getScaledInstance(size, size, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Creates a rounded corner version of the specified BufferedImage.
     *
     * @param image The original BufferedImage.
     * @return A BufferedImage with rounded corners.
     */
    private static BufferedImage makeRoundedCorner(BufferedImage image) {
        int cornerRadius = 275; // The radius for the rounded corners
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // Enable anti-aliasing for smooth corners
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // Composite the image on top using the white shape as the alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
