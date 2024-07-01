package com.imperial.academia.app.components_factory;

import com.imperial.academia.app.usecase_factory.ProfileUseCaseFactory;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.view.components.AvatarComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AvatarFactory {
    private static final Map<String, BufferedImage> cache = new HashMap<>();

    private AvatarFactory() {}

    public static AvatarComponent create(int userId, String avatarUrl, int height) {
        ProfileController profileController = ProfileUseCaseFactory.getProfileController();
        ImageIcon avatarIcon = createAvatarIcon(avatarUrl, height);
        return new AvatarComponent(profileController, userId, avatarIcon);
    }

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

    private static BufferedImage makeRoundedCorner(BufferedImage image) {
        int cornerRadius = 275;
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = output.createGraphics();

        // This is what we want, but it only does hard-clipping, i.e. aliasing
        // g2.setClip(new RoundRectangle2D ...)

        // so instead fake soft-clipping by first drawing the desired clip shape
        // in fully opaque white with antialiasing enabled...
        g2.setComposite(AlphaComposite.Src);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

        // ... then compositing the image on top,
        // using the white shape from above as alpha source
        g2.setComposite(AlphaComposite.SrcAtop);
        g2.drawImage(image, 0, 0, null);

        g2.dispose();

        return output;
    }
}
