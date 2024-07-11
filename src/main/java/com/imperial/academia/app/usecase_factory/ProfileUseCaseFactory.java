package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;
import com.imperial.academia.entity.chat_message.CommonChatMessageFactory;
import com.imperial.academia.interface_adapter.chat.ChatSideBarPresenter;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowPresenter;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.profile.ProfileController;
import com.imperial.academia.interface_adapter.profile.ProfilePresenter;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.chat.*;
import com.imperial.academia.use_case.profile.ProfileInteractor;
import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.view.ProfileView;

public class ProfileUseCaseFactory {
    private ProfileUseCaseFactory(){}
    private static ProfileController profileController;

    public static ProfileView create(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel,
                                     ChatSideBarViewModel chatSideBarViewModel, ChatWindowViewModel chatWindowViewModel){
        profileController = createController(viewManagerModel, profileViewModel,chatSideBarViewModel,chatWindowViewModel);
        return new ProfileView(profileController, profileViewModel);
    }

    private static ProfileController createController(ViewManagerModel viewManagerModel, ProfileViewModel profileViewModel,
                                                      ChatSideBarViewModel chatSideBarViewModel, ChatWindowViewModel chatWindowViewModel){
        ProfileOutputBoundry profileOutputBoundry = new ProfilePresenter(profileViewModel, viewManagerModel);

        ProfileInteractor profileInteractor = new ProfileInteractor(profileOutputBoundry);

        // Create presenters
        ChatSideBarOutputBoundary chatSideBarPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        ChatWindowOutputBoundary chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);

        // Create interactors
        ChatMessageFactory chatMessageFactory = new CommonChatMessageFactory();
        ChatWindowInputBoundary chatWindowInteractor = new ChatWindowInteractor(
                chatWindowPresenter, chatMessageFactory);
        ChatSideBarInputBoundary chatSideBarInteractor = new ChatSideBarInteractor(
                chatSideBarPresenter);
        ChatCoordinatorInputBoundary chatCoordinatorInteractor =
                new ChatCoordinatorInteractor(chatSideBarInteractor,chatWindowInteractor, viewManagerModel);
        return new ProfileController(profileInteractor,chatCoordinatorInteractor);
    }
    public static ProfileController getProfileController(){
        return profileController;
    }
}




