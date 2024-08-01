package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.interface_adapter.login.LoginState;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.use_case.signup.SignupOutputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignupPresenterTest {

    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private SignupPresenter signupPresenter;

    @BeforeEach
    void setUp() {
        signupViewModel = mock(SignupViewModel.class);
        loginViewModel = mock(LoginViewModel.class);
        signupPresenter = new SignupPresenter(signupViewModel, loginViewModel);
    }

    @Test
    void testPrepareSuccessView() {
        String username = "testUser";
        String creationTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        SignupOutputData response = new SignupOutputData(username, creationTime);

        SignupState signupState = new SignupState();
        LoginState loginState = new LoginState();

        when(signupViewModel.getState()).thenReturn(signupState);
        when(loginViewModel.getState()).thenReturn(loginState);

        signupPresenter.prepareSuccessView(response);

        ArgumentCaptor<LoginState> loginStateCaptor = ArgumentCaptor.forClass(LoginState.class);
        verify(loginViewModel).setState(loginStateCaptor.capture());

        LoginState capturedLoginState = loginStateCaptor.getValue();
        assertEquals(username, capturedLoginState.getUsername());
        assertEquals(null, capturedLoginState.getPassword());

        verify(signupViewModel).firePropertyChanged("clean");
        verify(loginViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        String error = "Username already exists";
        SignupState signupState = new SignupState();
        when(signupViewModel.getState()).thenReturn(signupState);

        signupPresenter.prepareFailView(error);

        ArgumentCaptor<SignupState> signupStateCaptor = ArgumentCaptor.forClass(SignupState.class);
        verify(signupViewModel).setState(signupStateCaptor.capture());

        SignupState capturedSignupState = signupStateCaptor.getValue();
        assertEquals(error, capturedSignupState.getUsernameError());

        verify(signupViewModel).firePropertyChanged("error");
    }
}
