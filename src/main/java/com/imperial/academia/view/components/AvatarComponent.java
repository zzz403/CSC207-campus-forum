package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.profile.ProfileController;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * The AvatarComponent class extends JLabel to create a user avatar component.
 * It displays the user's avatar and handles clicks to show the user's profile.
 */
public class AvatarComponent extends JLabel {
    /**
     * Constructor to initialize the AvatarComponent.
     *
     * @param profileController The controller to handle profile view transitions.
     * @param userId The ID of the user.
     * @param avatarUrl The URL of the user's avatar image.
     * @throws IOException If an error occurs while loading the avatar image.
     */
    public AvatarComponent(ProfileController profileController, int userId, String avatarUrl) throws IOException {
        Border originalBorder = getBorder();
        Dimension originalSize = getPreferredSize();

        ImageIcon originalIcon = new ImageIcon(avatarUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setIcon(scaledIcon);
        setBorder(originalBorder);
        setPreferredSize(originalSize);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileController.showProfile(userId);
            }
        });
    }
}
