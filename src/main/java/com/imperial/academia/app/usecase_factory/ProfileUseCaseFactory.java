package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfilePresenter;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInteractor;
import com.imperial.academia.use_case.profile.ProfileInteractor;
import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.view.ProfileView;

import javax.swing.*;
import java.sql.SQLException;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory(){}
    private static ProfileController profileController;

    public static ProfileView create(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel){
        profileController = createController(viewManagerModel, profileViewModel);
        return new ProfileView(profileController, profileViewModel);
    }

    private static ProfileController createController(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel){
        ProfileOutputBoundry profileOutputBoundry = new ProfilePresenter(profileViewModel, viewManagerModel);

        ProfileInteractor profileInteractor = new ProfileInteractor(profileOutputBoundry);
//        ChatSideBarInputBoundary chatSideBarInteractor = new ChatSideBarInteractor(chatSideBarPresenter);
//        return new ProfileController(profileInteractor,chatSideBarIterator);
        //TODO chatSidePresenter need to be modified
    }
    public static ProfileController getProfileController(){
        return profileController;
    }
}
