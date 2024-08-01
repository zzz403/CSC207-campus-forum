package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarState;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.use_case.login.LoginOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginPresenterTest {

    private LoginViewModel loginViewModel;
    private TopNavigationBarViewModel topNavigationBarViewModel;
    private LoginPresenter loginPresenter;

    @BeforeEach
    void setUp() {
        loginViewModel = mock(LoginViewModel.class);
        topNavigationBarViewModel = mock(TopNavigationBarViewModel.class);
        loginPresenter = new LoginPresenter(loginViewModel, topNavigationBarViewModel);

        // Mocking the SessionManager to return a mock user
        User mockUser = mock(User.class);
        when(mockUser.getAvatarUrl()).thenReturn("mockAvatarUrl");
        SessionManager.setCurrentUser(mockUser);
    }

    @Test
    void testPrepareSuccessView() {
        LoginOutputData loginOutputData = new LoginOutputData("testUser", "Welcome", "testAvatarUrl", 123);

        TopNavigationBarState topNavigationBarState = new TopNavigationBarState();
        when(topNavigationBarViewModel.getState()).thenReturn(topNavigationBarState);

        loginPresenter.prepareSuccessView(loginOutputData);

        ArgumentCaptor<TopNavigationBarState> captor = ArgumentCaptor.forClass(TopNavigationBarState.class);
        verify(topNavigationBarViewModel).setState(captor.capture());
        verify(topNavigationBarViewModel).firePropertyChanged();

        TopNavigationBarState capturedState = captor.getValue();
        assertEquals("mockAvatarUrl", capturedState.getAvatarUrl());
        assertEquals(123, capturedState.getUserId());
        assertEquals("post board", capturedState.getCurrentViewName());
    }

    @Test
    void testPrepareFailViewWithUserError() {
        String error = "User not found";

        LoginState loginState = new LoginState();
        when(loginViewModel.getState()).thenReturn(loginState);

        loginPresenter.prepareFailView(error);

        ArgumentCaptor<LoginState> captor = ArgumentCaptor.forClass(LoginState.class);
        verify(loginViewModel).setState(captor.capture());
        verify(loginViewModel).firePropertyChanged();

        LoginState capturedState = captor.getValue();
        assertEquals(error, capturedState.getUsernameError());
        assertNull(capturedState.getPasswordError());
    }

    @Test
    void testPrepareFailViewWithPasswordError() {
        String error = "Invalid password";

        LoginState loginState = new LoginState();
        when(loginViewModel.getState()).thenReturn(loginState);

        loginPresenter.prepareFailView(error);

        ArgumentCaptor<LoginState> captor = ArgumentCaptor.forClass(LoginState.class);
        verify(loginViewModel).setState(captor.capture());
        verify(loginViewModel).firePropertyChanged();

        LoginState capturedState = captor.getValue();
        assertEquals(error, capturedState.getPasswordError());
        assertNull(capturedState.getUsernameError());
    }

    @Test
    void testPrepareFailViewWithNullError() {
        String error = null;

        LoginState loginState = new LoginState();
        when(loginViewModel.getState()).thenReturn(loginState);

        loginPresenter.prepareFailView(error);

        ArgumentCaptor<LoginState> captor = ArgumentCaptor.forClass(LoginState.class);
        verify(loginViewModel).setState(captor.capture());
        verify(loginViewModel).firePropertyChanged();

        LoginState capturedState = captor.getValue();
        assertNull(capturedState.getUsernameError());
        assertNull(capturedState.getPasswordError());
    }
}
