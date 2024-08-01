package com.imperial.academia.use_case.login;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.signup.SignupInteractor;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.data_access.RememberMeDAI;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;

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

    @AfterEach
    void tearDown(){
        SessionManager.clearSession();
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

    @Test
    void loadCredentials() throws IOException {
        loginInteractor.loadCredentials();
        verify(rememberMeDAO).loadCredentials();
    }
    @Test
    void constructorTest() {
        // Setup static mocks for ServiceFactory and UsecaseFactory
        try (MockedStatic<ServiceFactory> mockedServiceFactory = Mockito.mockStatic(ServiceFactory.class);
             MockedStatic<UsecaseFactory> mockedUsecaseFactory = Mockito.mockStatic(UsecaseFactory.class)) {

            // Prepare static mocks to return specific objects when methods are called
            mockedServiceFactory.when(ServiceFactory::getUserService).thenReturn(userService);
            mockedUsecaseFactory.when(UsecaseFactory::getChangeViewInteractor).thenReturn(changeViewInteractor);
            mockedUsecaseFactory.when(UsecaseFactory::getChatSideBarInteractor).thenReturn(chatSideBarInteractor);

            // Execute: instantiate the SignupInteractor
            LoginInteractor interactor = new LoginInteractor(loginPresenter, rememberMeDAO);

            // Verify: Check if the static method was called as expected
            mockedUsecaseFactory.verify(() -> UsecaseFactory.getChangeViewInteractor(), Mockito.times(1));
        }
    }
    @Test
    void testExecute_LoginSuccessWithRememberMe_IOException() throws SQLException, IOException {
        String username = "user";
        String password = "correctpassword";
        LoginInputData inputData = new LoginInputData(username, password, true);
        User user = new User(1, username, password, "email@example.com", "user", "avatarUrl", null, null);

        when(userService.getByUsername(username)).thenReturn(user);
        doThrow(new IOException("Failed to save credentials")).when(rememberMeDAO).saveCredentials(username, password);

        loginInteractor.execute(inputData);

        verify(rememberMeDAO).saveCredentials(username, password);
        verify(loginPresenter).prepareSuccessView(any(LoginOutputData.class));
        verify(chatSideBarInteractor).execute();
        verify(changeViewInteractor).changeView("post board");
    }

    @Test
    void testExecute_LoginSuccessWithoutRememberMe_IOException() throws SQLException, IOException {
        String username = "user";
        String password = "correctpassword";
        LoginInputData inputData = new LoginInputData(username, password, false);
        User user = new User(1, username, password, "email@example.com", "user", "avatarUrl", null, null);

        when(userService.getByUsername(username)).thenReturn(user);
        doThrow(new IOException("Failed to clear credentials")).when(rememberMeDAO).clearCredentials();

        loginInteractor.execute(inputData);

        verify(rememberMeDAO).clearCredentials();
        verify(loginPresenter).prepareSuccessView(any(LoginOutputData.class));
        verify(chatSideBarInteractor).execute();
        verify(changeViewInteractor).changeView("post board");
    }


}



