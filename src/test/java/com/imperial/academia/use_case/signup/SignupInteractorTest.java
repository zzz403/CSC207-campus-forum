package com.imperial.academia.use_case.signup;

import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class SignupInteractorTest {

    @Mock private UserService userService;
    @Mock private SignupOutputBoundary signupPresenter;
    @Mock private UserFactory userFactory;
    @Mock private ChangeViewInputBoundary changeViewInteractor;

    private SignupInteractor signupInteractor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        signupInteractor = new SignupInteractor(signupPresenter, userFactory, userService, changeViewInteractor);
    }

    @Test
    public void testExecuteWithValidInput() throws SQLException {
        SignupInputData inputData = new SignupInputData("validUsername", "validPassword", "validPassword", "valid@example.com");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "validUsername", "validPassword", "valid@example.com", "user", "avatarUrl", timestamp, timestamp);

        when(userService.existsByUsername("validUsername")).thenReturn(false);
        when(userService.existsByEmail("valid@example.com")).thenReturn(false);
        when(userFactory.create(eq("validUsername"), eq("validPassword"), eq("valid@example.com"), any(LocalDateTime.class))).thenReturn(user);

        signupInteractor.execute(inputData);

        verify(userService).insert(user);
        verify(signupPresenter).prepareSuccessView(any(SignupOutputData.class));
        verify(changeViewInteractor).changeView("log in");
    }


    @Test
    public void testExecuteWithShortUsername() {
        SignupInputData inputData = new SignupInputData("short", "validPassword", "validPassword", "valid@example.com");

        signupInteractor.execute(inputData);

        verify(signupPresenter).prepareFailView("Username must be longer than 6 characters.");
    }

    @Test
    public void testExecuteWithExistingUsername() throws SQLException {
        SignupInputData inputData = new SignupInputData("existingUser", "validPassword", "validPassword", "valid@example.com");

        when(userService.existsByUsername("existingUser")).thenReturn(true);

        signupInteractor.execute(inputData);

        verify(signupPresenter).prepareFailView("User already exists.");
    }

    @Test
    public void testExecuteWithShortPassword() {
        SignupInputData inputData = new SignupInputData("validUsername", "short", "short", "valid@example.com");

        signupInteractor.execute(inputData);

        verify(signupPresenter).prepareFailView("Password must be longer than 6 characters.");
    }

    @Test
    public void testExecuteWithNonMatchingPasswords() {
        SignupInputData inputData = new SignupInputData("validUsername", "password1", "password2", "valid@example.com");

        signupInteractor.execute(inputData);

        verify(signupPresenter).prepareFailView("Repeat Passwords don't match.");
    }

    @Test
    public void testExecuteWithInvalidEmail() {
        SignupInputData inputData = new SignupInputData("validUsername", "validPassword", "validPassword", "invalid-email");

        signupInteractor.execute(inputData);

        verify(signupPresenter).prepareFailView("Email is not valid.");
    }

    @Test
    public void testExecuteWithExistingEmail() throws SQLException {
        SignupInputData inputData = new SignupInputData("validUsername", "validPassword", "validPassword", "existing@example.com");

        when(userService.existsByEmail("existing@example.com")).thenReturn(true);

        signupInteractor.execute(inputData);

        verify(signupPresenter).prepareFailView("Email already used.");
    }

    @Test
    public void testNavigateToLogin() {
        signupInteractor.navigateToLogin();

        verify(changeViewInteractor).changeView("log in");
    }
    @Test
    void executeSQLException(){
        SignupInputData inputData = new SignupInputData("validUsername", "validPassword", "validPassword", "existing@example.com");
        try {
            when(userService.existsByEmail("existing@example.com")).thenThrow(new SQLException());

            signupInteractor.execute(inputData);
        } catch (SQLException e){
            verify(signupPresenter).prepareFailView("An error occurred during signup: "+ e.getMessage());

        }

    }
    @Test
    void constructorTest(){
        try (MockedStatic<ServiceFactory> mockedStatic = Mockito.mockStatic(ServiceFactory.class)){
            mockedStatic.when(ServiceFactory::getUserService).thenReturn(userService);
            try(MockedStatic<UsecaseFactory> mockedStatic1 = Mockito.mockStatic(UsecaseFactory.class)){
                mockedStatic1.when(UsecaseFactory::getChangeViewInteractor).thenReturn(changeViewInteractor);
                signupInteractor = new SignupInteractor(signupPresenter, userFactory);
                verify(UsecaseFactory.getChangeViewInteractor());
            }
        }
    }
}
