package com.imperial.academia.app.componets_factory;

import java.io.IOException;

import com.imperial.academia.app.usecase_factory.ProfilUseCaseFactory;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.view.components.AvatarComponent;

public class AvatarFacory {
    private AvatarFacory(){}

    public static AvatarComponent create(int userId, String avatarUrl, String currentViewName) throws IOException{
        ProfileController profileController = ProfilUseCaseFactory.getProfileController();
        return new AvatarComponent(profileController, userId, avatarUrl, currentViewName);
    }
}
