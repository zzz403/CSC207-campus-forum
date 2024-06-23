package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.profile.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AvatarComponent extends JLabel {
    private int userId;
    private String avatarUrl;
    private String currentViewName;

    public AvatarComponent(ProfileController profileController, int userId, String avatarUrl, String currentViewName) throws IOException{
        this.userId = userId;
        this.avatarUrl = avatarUrl;
        this.currentViewName = currentViewName;
        
        changeAvatar();
        // setPreferredSize(new Dimension(50,50));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileController.showProfile(userId,currentViewName);
            }
        });
    }

    private void changeAvatar() {
        ImageIcon originalIcon = new ImageIcon(avatarUrl);
        System.out.println("Avatar URL: " + avatarUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        setIcon(scaledIcon);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        changeAvatar();
    }

    public String getCurrentViewName() {
        return currentViewName;
    }

    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }
}
