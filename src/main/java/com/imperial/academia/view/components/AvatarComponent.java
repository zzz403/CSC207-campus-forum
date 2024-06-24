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
    private int userId;
    private String avatarUrl;
    private String currentViewName;

    /**
     * Constructor to initialize the AvatarComponent.
     *
     * @param profileController The controller to handle profile view transitions.
     * @param userId The ID of the user.
     * @param avatarUrl The URL of the user's avatar image.
     * @param currentViewName The name of the current view.
     * @throws IOException If an error occurs while loading the avatar image.
     */
    public AvatarComponent(ProfileController profileController, int userId, String avatarUrl, String currentViewName) throws IOException {
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.currentViewName = currentViewName;
        
        changeAvatar();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileController.showProfile(userId, currentViewName);
            }
        });
    }

    /**
     * Updates the avatar image.
     */
    private void changeAvatar() {
        Border originalBorder = getBorder();
        Dimension originalSize = getPreferredSize();

        ImageIcon originalIcon = new ImageIcon(avatarUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        setIcon(scaledIcon);
        setBorder(originalBorder);
        setPreferredSize(originalSize);
    }

    // Getter and Setter methods

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId The new user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the avatar URL.
     *
     * @return The avatar URL.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the avatar URL and updates the avatar image.
     *
     * @param avatarUrl The new avatar URL.
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        changeAvatar();
    }

    /**
     * Gets the current view name.
     *
     * @return The current view name.
     */
    public String getCurrentViewName() {
        return currentViewName;
    }

    /**
     * Sets the current view name.
     *
     * @param currentViewName The new current view name.
     */
    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }
}
