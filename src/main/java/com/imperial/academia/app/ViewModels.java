package com.imperial.academia.app;

import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;

public class ViewModels {
    private LoginViewModel loginViewModel;
    private SignupViewModel signupViewModel;
    private CreatePostViewModel createPostViewModel;
    private ChatSideBarViewModel chatSideBarViewModel;
    private ChatWindowViewModel chatWindowViewModel;
    private TopNavigationBarViewModel topNavigationBarViewModel;
    private ProfileViewModel profileViewModel;
    private PostBoardViewModel postBoardViewModel;

    public ViewModels() {
        loginViewModel = new LoginViewModel();
        signupViewModel = new SignupViewModel();
        createPostViewModel = new CreatePostViewModel();
        chatSideBarViewModel = new ChatSideBarViewModel();
        chatWindowViewModel = new ChatWindowViewModel();
        topNavigationBarViewModel = new TopNavigationBarViewModel();
        profileViewModel = new ProfileViewModel();
        postBoardViewModel = new PostBoardViewModel();
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public SignupViewModel getSignupViewModel() {
        return signupViewModel;
    }

    public CreatePostViewModel getCreatePostViewModel() {
        return createPostViewModel;
    }

    public ChatSideBarViewModel getChatSideBarViewModel() {
        return chatSideBarViewModel;
    }

    public ChatWindowViewModel getChatWindowViewModel() {
        return chatWindowViewModel;
    }

    public TopNavigationBarViewModel getTopNavigationBarViewModel() {
        return topNavigationBarViewModel;
    }

    public ProfileViewModel getProfileViewModel() {
        return profileViewModel;
    }

    public PostBoardViewModel getPostBoardViewModel() {
        return postBoardViewModel;
    }

}
