package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfilePresenter;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInteractor;
import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.view.ProfileView;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory(){}
    private static ProfileController profileController;

    public static ProfileView create(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel){
        profileController = createController(viewManagerModel, profileViewModel);
        return new ProfileView(profileViewModel);
    }

    private static ProfileController createController(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel){
        UserService userService = ServiceFactory.getUserService();
        // ProfileOutputBoundry profileOutputBoundry = new ProfilePresenter(profileViewModel, viewManagerModel);

        ProfileInputBoundry profileInteractor = UsecaseFactory.getProfileInteractor();

        return new ProfileController();
    }
    
    public static ProfileController getProfileController(){
        return profileController;
    }
}
