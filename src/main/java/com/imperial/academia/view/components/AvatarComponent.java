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
     * The controller for profile operations.
     */
    private final ProfileController profileController;

    /**
     * Constructs a new AvatarComponent with the specified user ID and avatar icon.
     *
     * @param userId the ID of the user
     * @param avatarIcon the avatar icon to be displayed
     */
    public AvatarComponent(int userId, ImageIcon avatarIcon) {
        profileController = new ProfileController();
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

    /**
     * Constructs a new AvatarComponent with the specified user ID and avatar icon.
     *
     * @param userId the ID of the user
     * @param avatarIcon the avatar icon to be displayed
     */
    public AvatarComponent(int userId, ImageIcon avatarIcon, ProfileController profileController) {
        this.profileController = profileController;
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
