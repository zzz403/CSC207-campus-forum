package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.profile.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
     * @param avatarIcon The ImageIcon of the user's avatar image.
     */
    public AvatarComponent(ProfileController profileController, int userId, ImageIcon avatarIcon) {
        setIcon(avatarIcon);
        setPreferredSize(new Dimension(50, 50));

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileController.showProfile(userId);
            }
        });
    }
}
