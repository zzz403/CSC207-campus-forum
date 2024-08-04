package com.imperial.academia.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.createpost.CreatePostViewModel;
import com.imperial.academia.interface_adapter.edit.EditViewModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.post.PostViewModel;
import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.interface_adapter.profile.ProfileViewModel;
import com.imperial.academia.interface_adapter.signup.SignupViewModel;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;

public class UsecaseFactoryTest {

    private ViewManagerModel viewManagerModelMock;
    private ViewModels viewModelsMock;

    @BeforeEach
    public void setUp() {
        viewManagerModelMock = mock(ViewManagerModel.class);
        viewModelsMock = mock(ViewModels.class);

        // Mocking ViewModels' getter methods to return mock ViewModel instances
        when(viewModelsMock.getLoginViewModel()).thenReturn(mock(LoginViewModel.class));
        when(viewModelsMock.getSignupViewModel()).thenReturn(mock(SignupViewModel.class));
        when(viewModelsMock.getCreatePostViewModel()).thenReturn(mock(CreatePostViewModel.class));
        when(viewModelsMock.getChatSideBarViewModel()).thenReturn(mock(ChatSideBarViewModel.class));
        when(viewModelsMock.getChatWindowViewModel()).thenReturn(mock(ChatWindowViewModel.class));
        when(viewModelsMock.getTopNavigationBarViewModel()).thenReturn(mock(TopNavigationBarViewModel.class));
        when(viewModelsMock.getProfileViewModel()).thenReturn(mock(ProfileViewModel.class));
        when(viewModelsMock.getPostViewModel()).thenReturn(mock(PostViewModel.class));
        when(viewModelsMock.getEditViewModel()).thenReturn(mock(EditViewModel.class));
        when(viewModelsMock.getPostBoardViewModel()).thenReturn(mock(PostBoardViewModel.class));

        UsecaseFactory.initialize(viewManagerModelMock, viewModelsMock);
    }

    @Test
    public void testGetCreatePostInteractor() {
        assertNotNull(UsecaseFactory.getCreatePostInteractor());
    }

    @Test
    public void testGetChatSideBarInteractor() {
        assertNotNull(UsecaseFactory.getChatSideBarInteractor());
    }

    @Test
    public void testGetLoginInteractor() {
        assertNotNull(UsecaseFactory.getLoginInteractor());
    }

    @Test
    public void testGetChangeViewInteractor() {
        assertNotNull(UsecaseFactory.getChangeViewInteractor());
    }

    @Test
    public void testGetSignupInteractor() {
        assertNotNull(UsecaseFactory.getSignupInteractor());
    }

    @Test
    public void testGetSessionInteractor() {
        assertNotNull(UsecaseFactory.getSessionInteractor());
    }

    @Test
    public void testGetProfileInteractor() {
        assertNotNull(UsecaseFactory.getProfileInteractor());
    }

    @Test
    public void testGetChatWindowInteractor() {
        assertNotNull(UsecaseFactory.getChatWindowInteractor());
    }

    @Test
    public void testGetChatCoordinatorInteractor() {
        assertNotNull(UsecaseFactory.getChatCoordinatorInteractor());
    }

    @Test
    public void testGetPostInteractor() {
        assertNotNull(UsecaseFactory.getPostInteractor());
    }

    @Test
    public void testGetLLMInteractor() {
        assertNotNull(UsecaseFactory.getLLMInteractor());
    }

    @Test
    public void testGetASRInteractor() {
        assertNotNull(UsecaseFactory.getASRInteractor());
    }

    @Test
    public void testGetTranslatorInteractor() {
        assertNotNull(UsecaseFactory.getTranslatorInteractor());
    }

    @Test
    public void testGetPostBoardInteractor() {
        assertNotNull(UsecaseFactory.getPostBoardInteractor());
    }

    @Test
    public void testGetEditInteractor() {
        assertNotNull(UsecaseFactory.getEditInteractor());
    }
}
