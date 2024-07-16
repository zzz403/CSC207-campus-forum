package com.imperial.academia.app;

import com.imperial.academia.data_access.RememberMeDAO;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;
import com.imperial.academia.entity.chat_message.CommonChatMessageFactory;
import com.imperial.academia.entity.user.CommonUserFactory;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.interface_adapter.changeview.ChangeViewPresenter;
import com.imperial.academia.interface_adapter.chat.ChatSideBarPresenter;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowPresenter;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostPresenter;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.login.LoginPresenter;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.profile.ProfilePresenter;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupPresenter;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInteractor;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;
import com.imperial.academia.use_case.chat.*;
import com.imperial.academia.use_case.createpost.CreatePostInputBoundary;
import com.imperial.academia.use_case.createpost.CreatePostInteractor;
import com.imperial.academia.use_case.createpost.CreatePostOutputBoundary;
import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInteractor;
import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundry;
import com.imperial.academia.use_case.profile.ProfileInteractor;
import com.imperial.academia.use_case.profile.ProfileOutputBoundry;
import com.imperial.academia.use_case.session.SessionInputBoundary;
import com.imperial.academia.use_case.session.SessionInteractor;
import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInteractor;
import com.imperial.academia.use_case.signup.SignupOutputBoundary;

/**
 * The UsecaseFactory class is responsible for initializing and providing instances of various use case interactors.
 * It follows the Singleton pattern to ensure that only one instance of each interactor is created.
 * The factory also provides getter methods to access the instances of the interactors.
 */
public class UsecaseFactory {

    private static CreatePostInputBoundary createPostInteractor;
    private static ChatSideBarInputBoundary chatSideBarInteractor;
    private static LoginInputBoundary loginInteractor;
    private static ChangeViewInputBoundary changeViewInteractor;
    private static SignupInputBoundary signupInteractor;
    private static SessionInputBoundary sessionInteractor;
    private static ProfileInputBoundry profileInteractor;
    private static ChatWindowInputBoundary chatWindowInteractor;
    private static ChatCoordinatorInputBoundary chatCoordinatorInteractor;

    /** Prevents instantiation of this utility class. */
    private UsecaseFactory() {
    }

    /**
     * Initializes the UsecaseFactory by creating and initializing the necessary view models, presenters, and interactors.
     * 
     * @param viewManagerModel The view manager model used for changing views.
     */
    public static void initialize(ViewManagerModel viewManagerModel, ViewModels viewModels) {
        
        // init change view usecase
        ChangeViewOutputBoundary changeViewPresenter = new ChangeViewPresenter(viewManagerModel);
        changeViewInteractor = new ChangeViewInteractor(changeViewPresenter);
        
        
        LoginViewModel loginViewModel = viewModels.getLoginViewModel();
        SignupViewModel signupViewModel = viewModels.getSignupViewModel();
        CreatePostViewModel createPostViewModel = viewModels.getCreatePostViewModel();
        ChatSideBarViewModel chatSideBarViewModel = viewModels.getChatSideBarViewModel();
        ChatWindowViewModel chatWindowViewModel = viewModels.getChatWindowViewModel();
        TopNavigationBarViewModel topNavigationBarViewModel = viewModels.getTopNavigationBarViewModel();
        ProfileViewModel profileViewModel = viewModels.getProfileViewModel();
        
        
        CreatePostOutputBoundary createPostPresenter = new CreatePostPresenter(createPostViewModel);
        createPostInteractor = new CreatePostInteractor(createPostPresenter);

        UserFactory userFactory = new CommonUserFactory();
        SignupOutputBoundary signupPresenter = new SignupPresenter(signupViewModel, loginViewModel);
        signupInteractor = new SignupInteractor(signupPresenter, userFactory);

        ProfileOutputBoundry profileOutputBoundry = new ProfilePresenter(profileViewModel);
        profileInteractor = new ProfileInteractor(profileOutputBoundry);

        ChatSideBarOutputBoundary chatSideBarPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        chatSideBarInteractor = new ChatSideBarInteractor(chatSideBarPresenter);

        ChatWindowOutputBoundary chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);
        ChatMessageFactory chatMessageFactory = new CommonChatMessageFactory();
        chatWindowInteractor = new ChatWindowInteractor(chatWindowPresenter, chatMessageFactory);

        chatCoordinatorInteractor = new ChatCoordinatorInteractor();

        RememberMeDAO rememberMeDAO = new RememberMeDAO();
        LoginOutputBoundary loginPresenter = new LoginPresenter(loginViewModel, topNavigationBarViewModel);
        loginInteractor = new LoginInteractor(loginPresenter, rememberMeDAO);

        sessionInteractor = new SessionInteractor();

        System.out.println("init seccuss for usecase!!!!");
    }

    /**
     * Returns the CreatePostInputBoundary interactor.
     *
     * @return The CreatePostInputBoundary interactor.
     */
    public static CreatePostInputBoundary getCreatePostInteractor() {
        return createPostInteractor;
    }

    /**
     * Returns the ChatSideBarInputBoundary interactor.
     *
     * @return The ChatSideBarInputBoundary interactor.
     */
    public static ChatSideBarInputBoundary getChatSideBarInteractor() {
        return chatSideBarInteractor;
    }

    /**
     * Returns the LoginInputBoundary interactor.
     *
     * @return The LoginInputBoundary interactor.
     */
    public static LoginInputBoundary getLoginInteractor() {
        return loginInteractor;
    }

    /**
     * Returns the ChangeViewInputBoundary interactor.
     *
     * @return The ChangeViewInputBoundary interactor.
     */
    public static ChangeViewInputBoundary getChangeViewInteractor() {
        if(changeViewInteractor == null){
            System.out.println("get changeview interactor");
        }
        return changeViewInteractor;
    }

    /**
     * Returns the SignupInputBoundary interactor.
     *
     * @return The SignupInputBoundary interactor.
     */
    public static SignupInputBoundary getSignupInteractor() {
        return signupInteractor;
    }

    /**
     * Returns the SessionInputBoundary interactor.
     *
     * @return The SessionInputBoundary interactor.
     */
    public static SessionInputBoundary getSessionInteractor() {
        return sessionInteractor;
    }

    /**
     * Returns the ProfileInputBoundry interactor.
     *
     * @return The ProfileInputBoundry interactor.
     */
    public static ProfileInputBoundry getProfileInteractor() {
        return profileInteractor;
    }

    /**
     * Returns the ChatWindowInputBoundary interactor.
     *
     * @return The ChatWindowInputBoundary interactor.
     */
    public static ChatWindowInputBoundary getChatWindowInteractor() {
        return chatWindowInteractor;
    }

    public static ChatCoordinatorInputBoundary getChatCoordinatorInteractor(){return chatCoordinatorInteractor;}
}
