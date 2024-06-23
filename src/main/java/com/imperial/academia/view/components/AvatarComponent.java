package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.profile.ProfileController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AvatarComponent extends JLabel {


    public AvatarComponent(ProfileController profileController, int userId, String avatarUrl, String currentViewName){
        setIcon(new ImageIcon(avatarUrl));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileController.showProfile(userId,currentViewName);
            }
        });
    }
}
