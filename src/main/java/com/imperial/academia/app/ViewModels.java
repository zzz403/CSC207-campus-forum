package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.edit.EditViewModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.post.PostViewModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;

/**
 * The ViewModels class acts as a container for all ViewModel instances used in the application.
 * It initializes and provides access to various ViewModels.
 */
public class ViewModels {
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private CreatePostViewModel createPostViewModel;
    private ChatSideBarViewModel chatSideBarViewModel;
    private ChatWindowViewModel chatWindowViewModel;
    private TopNavigationBarViewModel topNavigationBarViewModel;
    private ProfileViewModel profileViewModel;
    private PostBoardViewModel postBoardViewModel;
    private PostViewModel postViewModel;
    private EditViewModel editViewModel;

    /**
     * Constructs a new ViewModels container and initializes all ViewModel instances.
     */
    public ViewModels() {
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        createPostViewModel = new CreatePostViewModel();
        chatSideBarViewModel = new ChatSideBarViewModel();
        chatWindowViewModel = new ChatWindowViewModel();
        topNavigationBarViewModel = new TopNavigationBarViewModel();
        profileViewModel = new ProfileViewModel();
        postBoardViewModel = new PostBoardViewModel();
        postViewModel = new PostViewModel();
        editViewModel = new EditViewModel();
    }

    public EditViewModel getEditViewModel() {
        return editViewModel;
    }

    /**
     * Gets the PostViewModel instance.
     * 
     * @return the PostViewModel instance.
     */
    public PostViewModel getPostViewModel() {
        return postViewModel;
    }

    /**
     * Gets the LoginViewModel instance.
     * 
     * @return the LoginViewModel instance.
     */
    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    /**
     * Gets the SignupViewModel instance.
     * 
     * @return the SignupViewModel instance.
     */
    public SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }

    /**
     * Gets the CreatePostViewModel instance.
     * 
     * @return the CreatePostViewModel instance.
     */
    public CreatePostViewModel getCreatePostViewModel() {
        return createPostViewModel;
    }

    /**
     * Gets the ChatSideBarViewModel instance.
     * 
     * @return the ChatSideBarViewModel instance.
     */
    public ChatSideBarViewModel getChatSideBarViewModel() {
        return chatSideBarViewModel;
    }

    /**
     * Gets the ChatWindowViewModel instance.
     * 
     * @return the ChatWindowViewModel instance.
     */
    public ChatWindowViewModel getChatWindowViewModel() {
        return chatWindowViewModel;
    }

    /**
     * Gets the TopNavigationBarViewModel instance.
     * 
     * @return the TopNavigationBarViewModel instance.
     */
    public TopNavigationBarViewModel getTopNavigationBarViewModel() {
        return topNavigationBarViewModel;
    }

    /**
     * Gets the ProfileViewModel instance.
     * 
     * @return the ProfileViewModel instance.
     */
    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    /**
     * Gets the PostBoardViewModel instance.
     * 
     * @return the PostBoardViewModel instance.
     */
    public PostBoardViewModel getPostBoardViewModel() {
        return postBoardViewModel;
    }
}