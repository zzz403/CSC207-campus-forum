package com.imperial.academia.app;

import com.imperial.academia.data_access.RememberMeDAO;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;
import com.imperial.academia.entity.chat_message.CommonChatMessageFactory;
import com.imperial.academia.entity.user.CommonUserFactory;
import com.imperial.academia.entity.user.UpdateUserFactory;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.entity.user.UpdatedUserFactoryImpl;
import com.imperial.academia.interface_adapter.changeview.ChangeViewPresenter;
import com.imperial.academia.interface_adapter.chat.ChatSideBarPresenter;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowPresenter;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostPresenter;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.edit.EditPresenter;
import com.imperial.academia.interface_adapter.edit.EditViewModel;
import com.imperial.academia.interface_adapter.login.LoginPresenter;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.post.PostPresenter;
import com.imperial.academia.interface_adapter.post.PostViewModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardPresenter;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.profile.ProfilePresenter;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupPresenter;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.use_case.ASR.ASRInputBoundary;
import com.imperial.academia.use_case.ASR.IBMInteractor;
import com.imperial.academia.use_case.LLM.ChatGPTInteractor;
import com.imperial.academia.use_case.LLM.LLMInputBoundary;
import com.imperial.academia.use_case.Translator.DeepLInteractor;
import com.imperial.academia.use_case.Translator.TranslatorInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInteractor;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;
import com.imperial.academia.use_case.chat.ChatCoordinatorInputBoundary;
import com.imperial.academia.use_case.chat.ChatCoordinatorInteractor;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInteractor;
import com.imperial.academia.use_case.chat.ChatSideBarOutputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInteractor;
import com.imperial.academia.use_case.chat.ChatWindowOutputBoundary;
import com.imperial.academia.use_case.createpost.CreatePostInputBoundary;
import com.imperial.academia.use_case.createpost.CreatePostInteractor;
import com.imperial.academia.use_case.createpost.CreatePostOutputBoundary;
import com.imperial.academia.use_case.edit.EditInputBoundary;
import com.imperial.academia.use_case.edit.EditInteractor;
import com.imperial.academia.use_case.edit.EditOutputBoundary;
import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInteractor;
import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;
import com.imperial.academia.use_case.post.PostInteractor;
import com.imperial.academia.use_case.post.PostOutputBoundary;
import com.imperial.academia.use_case.postBoard.PostBoardInputBoundary;
import com.imperial.academia.use_case.postBoard.PostBoardInteractor;
import com.imperial.academia.use_case.postBoard.PostBoardOutputBoundary;
import com.imperial.academia.use_case.profile.ProfileInputBoundary;
import com.imperial.academia.use_case.profile.ProfileInteractor;
import com.imperial.academia.use_case.profile.ProfileOutputBoundary;
import com.imperial.academia.use_case.session.SessionInputBoundary;
import com.imperial.academia.use_case.session.SessionInteractor;
import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInteractor;
import com.imperial.academia.use_case.signup.SignupOutputBoundary;
import com.imperial.academia.use_case.topnav.TopNavInputBoundary;
import com.imperial.academia.use_case.topnav.TopNavInteractor;

/**
 * The UsecaseFactory class is responsible for initializing and providing
 * instances of various use case interactors.
 * It follows the Singleton pattern to ensure that only one instance of each
 * interactor is created.
 * The factory also provides getter methods to access the instances of the
 * interactors.
 */
public class UsecaseFactory {

    private static CreatePostInputBoundary createPostInteractor;
    private static ChatSideBarInputBoundary chatSideBarInteractor;
    private static LoginInputBoundary loginInteractor;
    private static ChangeViewInputBoundary changeViewInteractor;
    private static SignupInputBoundary signupInteractor;
    private static SessionInputBoundary sessionInteractor;
    private static ProfileInputBoundary profileInteractor;
    private static ChatWindowInputBoundary chatWindowInteractor;
    private static ChatCoordinatorInputBoundary chatCoordinatorInteractor;
    private static PostInputBoundary postInteractor;
    private static LLMInputBoundary LLMInteractor;
    private static ASRInputBoundary ASRInteractor;
    private static TranslatorInputBoundary translatorInteractor;
    private static PostBoardInputBoundary postBoardInteractor;
    private static EditInputBoundary editInteractor;
    private static TopNavInputBoundary topNavInteractor;

    /** Prevents instantiation of this utility class. */
    private UsecaseFactory() {
    }

    /**
     * Initializes the UsecaseFactory by creating and initializing the necessary
     * view models, presenters, and interactors.
     * 
     * @param viewManagerModel The view manager model used for changing views.
     */
    public static void initialize(ViewManagerModel viewManagerModel, ViewModels viewModels) {

        // init change view useCase
        ChangeViewOutputBoundary changeViewPresenter = new ChangeViewPresenter(viewManagerModel);
        changeViewInteractor = new ChangeViewInteractor(changeViewPresenter);

        sessionInteractor = new SessionInteractor();

        LLMInteractor = new ChatGPTInteractor();
        ASRInteractor = new IBMInteractor();
        translatorInteractor = new DeepLInteractor();

        LoginViewModel loginViewModel = viewModels.getLoginViewModel();
        SignupViewModel signupViewModel = viewModels.getSignupViewModel();
        CreatePostViewModel createPostViewModel = viewModels.getCreatePostViewModel();
        ChatSideBarViewModel chatSideBarViewModel = viewModels.getChatSideBarViewModel();
        ChatWindowViewModel chatWindowViewModel = viewModels.getChatWindowViewModel();
        TopNavigationBarViewModel topNavigationBarViewModel = viewModels.getTopNavigationBarViewModel();
        ProfileViewModel profileViewModel = viewModels.getProfileViewModel();
        PostViewModel postViewModel = viewModels.getPostViewModel();
        EditViewModel editViewModel = viewModels.getEditViewModel();
        PostBoardViewModel postBoardViewModel = viewModels.getPostBoardViewModel();

        PostOutputBoundary postPresenter = new PostPresenter(postViewModel, postBoardViewModel);
        postInteractor = new PostInteractor(postPresenter);

        UserFactory userFactory = new CommonUserFactory();
        SignupOutputBoundary signupPresenter = new SignupPresenter(signupViewModel, loginViewModel);
        signupInteractor = new SignupInteractor(signupPresenter, userFactory);

        ProfileOutputBoundary profilePresenter = new ProfilePresenter(profileViewModel);
        profileInteractor = new ProfileInteractor(profilePresenter);

        ChatSideBarOutputBoundary chatSideBarPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        chatSideBarInteractor = new ChatSideBarInteractor(chatSideBarPresenter);

        ChatWindowOutputBoundary chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);
        ChatMessageFactory chatMessageFactory = new CommonChatMessageFactory();
        chatWindowInteractor = new ChatWindowInteractor(chatWindowPresenter, chatMessageFactory);

        chatCoordinatorInteractor = new ChatCoordinatorInteractor();

        UpdateUserFactory updateUserFactory = new UpdatedUserFactoryImpl();
        EditOutputBoundary editPresenter = new EditPresenter(editViewModel);
        editInteractor = new EditInteractor(editPresenter, updateUserFactory);

        PostBoardOutputBoundary postBoardPresenter = new PostBoardPresenter(postBoardViewModel);
        postBoardInteractor = new PostBoardInteractor(postBoardPresenter);

        CreatePostOutputBoundary createPostPresenter = new CreatePostPresenter(createPostViewModel);
        createPostInteractor = new CreatePostInteractor(createPostPresenter, postBoardPresenter);

        RememberMeDAO rememberMeDAO = new RememberMeDAO();
        LoginOutputBoundary loginPresenter = new LoginPresenter(loginViewModel, topNavigationBarViewModel);
        loginInteractor = new LoginInteractor(loginPresenter, rememberMeDAO);

        topNavInteractor = new TopNavInteractor();

        System.out.println("init seccuss for usecase!!!!");

    }

    /**
     * Returns the PostBoardInteractor
     *
     * @return the postBoardInteractor
     */
    public static PostBoardInputBoundary getPostBoardInteractor() {
        return postBoardInteractor;
    }

    /**
     * Returns the LLMInteractor
     *
     * @return the LLMInteractor
     */
    public static LLMInputBoundary getLLMInteractor() {
        return LLMInteractor;
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
        if (changeViewInteractor == null) {
            System.out.println("get changeView interactor");
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
     * Returns the ProfileInputBoundary interactor.
     *
     * @return The ProfileInputBoundary interactor.
     */
    public static ProfileInputBoundary getProfileInteractor() {
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

    /**
     * Returns the chatCoordinator Interactor
     * 
     * @return the chatCoordinator Interactor
     */
    public static ChatCoordinatorInputBoundary getChatCoordinatorInteractor() {
        return chatCoordinatorInteractor;
    }

    /**
     * Returns the post Interactor
     * 
     * @return the post Interactor
     */
    public static PostInputBoundary getPostInteractor() {
        return postInteractor;
    }

    public static EditInputBoundary getEditInteractor() {
        return editInteractor;
    }

    /**
     * Returns the ASR Interactor
     * 
     * @return the ASR Interactor
     */
    public static ASRInputBoundary getASRInteractor() {
        return ASRInteractor;
    }

    /**
     * Returns the Translator Interactor
     * 
     * @return the Translator Interactor
     */
    public static TranslatorInputBoundary getTranslatorInteractor() {
        return translatorInteractor;
    }

    /**
     * Returns the TopNavInteractor
     * 
     * @return the TopNavInteractor
     */
    public static TopNavInputBoundary getTopNavInteractor() {
        return topNavInteractor;
    }
}
