package com.imperial.academia.app.components_factory;

import com.imperial.academia.app.usecase_factory.ProfileUseCaseFactory;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.view.components.AvatarComponent;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AvatarFactory {
    private AvatarFactory() {}

    public static AvatarComponent create(int userId, String avatarUrl, int height) {
        ProfileController profileController = ProfileUseCaseFactory.getProfileController();
        ImageIcon avatarIcon = createAvatarIcon(avatarUrl, height);
        return new AvatarComponent(profileController, userId, avatarIcon);
    }

    private static ImageIcon createAvatarIcon(String avatarUrl, int size) {
        ImageIcon originalIcon = new ImageIcon(avatarUrl);
        Image scaledImage = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
