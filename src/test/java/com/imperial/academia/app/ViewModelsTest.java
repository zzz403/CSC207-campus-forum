package com.imperial.academia.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

public class ViewModelsTest {

    private ViewModels viewModels;

    @BeforeEach
    public void setUp() {
        viewModels = new ViewModels();
    }

    @Test
    public void testLoginViewModelInitialization() {
        LoginViewModel loginViewModel = viewModels.getLoginViewModel();
        assertNotNull(loginViewModel, "LoginViewModel should not be null");
    }

    @Test
    public void testSignupViewModelInitialization() {
        SignupViewModel signupViewModel = viewModels.getSignupViewModel();
        assertNotNull(signupViewModel, "SignupViewModel should not be null");
    }

    @Test
    public void testCreatePostViewModelInitialization() {
        CreatePostViewModel createPostViewModel = viewModels.getCreatePostViewModel();
        assertNotNull(createPostViewModel, "CreatePostViewModel should not be null");
    }

    @Test
    public void testChatSideBarViewModelInitialization() {
        ChatSideBarViewModel chatSideBarViewModel = viewModels.getChatSideBarViewModel();
        assertNotNull(chatSideBarViewModel, "ChatSideBarViewModel should not be null");
    }

    @Test
    public void testChatWindowViewModelInitialization() {
        ChatWindowViewModel chatWindowViewModel = viewModels.getChatWindowViewModel();
        assertNotNull(chatWindowViewModel, "ChatWindowViewModel should not be null");
    }

    @Test
    public void testTopNavigationBarViewModelInitialization() {
        TopNavigationBarViewModel topNavigationBarViewModel = viewModels.getTopNavigationBarViewModel();
        assertNotNull(topNavigationBarViewModel, "TopNavigationBarViewModel should not be null");
    }

    @Test
    public void testProfileViewModelInitialization() {
        ProfileViewModel profileViewModel = viewModels.getProfileViewModel();
        assertNotNull(profileViewModel, "ProfileViewModel should not be null");
    }

    @Test
    public void testPostBoardViewModelInitialization() {
        PostBoardViewModel postBoardViewModel = viewModels.getPostBoardViewModel();
        assertNotNull(postBoardViewModel, "PostBoardViewModel should not be null");
    }

    @Test
    public void testPostViewModelInitialization() {
        PostViewModel postViewModel = viewModels.getPostViewModel();
        assertNotNull(postViewModel, "PostViewModel should not be null");
    }

    @Test
    public void testEditViewModelInitialization() {
        EditViewModel editViewModel = viewModels.getEditViewModel();
        assertNotNull(editViewModel, "EditViewModel should not be null");
    }
}
