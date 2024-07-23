package com.imperial.academia.use_case.login;

import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.service.UserService;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.data_access.RememberMeDAI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class LoginInteractorTest {

    @Mock private UserService userService;
    @Mock private LoginOutputBoundary loginPresenter;
    @Mock private RememberMeDAI rememberMeDAO;
    @Mock private ChatSideBarInputBoundary chatSideBarInteractor;
    @Mock private ChangeViewInputBoundary changeViewInteractor;

    private LoginInteractor loginInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginInteractor = new LoginInteractor(loginPresenter, rememberMeDAO, userService, chatSideBarInteractor, changeViewInteractor);
        loginInteractor = spy(loginInteractor);
    }

    @Test
    void testExecute_UserNotFound() throws SQLException {
        String username = "nonexistent";
        String password = "password";
        LoginInputData inputData = new LoginInputData(username, password, false);

        when(userService.getByUsername(username)).thenReturn(null);

        loginInteractor.execute(inputData);

        verify(loginPresenter).prepareFailView("User not found.");
    }

    @Test
    void testExecute_InvalidPassword() throws SQLException {
        String username = "user";
        String password = "wrongpassword";
        LoginInputData inputData = new LoginInputData(username, password, false);
        User user = new User(1, username, "correctpassword", "email@example.com", "user", "avatarUrl", null, null);

        when(userService.getByUsername(username)).thenReturn(user);

        loginInteractor.execute(inputData);

        verify(loginPresenter).prepareFailView("Invalid password.");
    }

    @Test
    void testExecute_LoginSuccessWithoutRememberMe() throws SQLException, IOException {
        String username = "user";
        String password = "correctpassword";
        LoginInputData inputData = new LoginInputData(username, password, false);
        User user = new User(1, username, password, "email@example.com", "user", "avatarUrl", null, null);

        when(userService.getByUsername(username)).thenReturn(user);

        loginInteractor.execute(inputData);

        verify(rememberMeDAO).clearCredentials();
        verify(loginPresenter).prepareSuccessView(any(LoginOutputData.class));
        verify(chatSideBarInteractor).execute();
        verify(changeViewInteractor).changeView("post board");
        verify(loginPresenter, never()).prepareFailView(anyString());
    }

    @Test
    void testExecute_LoginSuccessWithRememberMe() throws SQLException, IOException {
        String username = "user";
        String password = "correctpassword";
        LoginInputData inputData = new LoginInputData(username, password, true);
        User user = new User(1, username, password, "email@example.com", "user", "avatarUrl", null, null);

        when(userService.getByUsername(username)).thenReturn(user);

        loginInteractor.execute(inputData);

        verify(rememberMeDAO).saveCredentials(username, password);
        verify(loginPresenter).prepareSuccessView(any(LoginOutputData.class));
        verify(chatSideBarInteractor).execute();
        verify(changeViewInteractor).changeView("post board");
        verify(loginPresenter, never()).prepareFailView(anyString());
    }

    @Test
    void testExecute_SQLError() throws SQLException {
        String username = "user";
        String password = "correctpassword";
        LoginInputData inputData = new LoginInputData(username, password, true);

        when(userService.getByUsername(username)).thenThrow(new SQLException("SQL error"));

        loginInteractor.execute(inputData);

        verify(loginPresenter).prepareFailView("An error occurred during login: SQL error");
    }

    @Test
    void testNavigateToSignup() {
        loginInteractor.navigateToSignup();

        verify(changeViewInteractor).changeView("sign up");
    }
}
