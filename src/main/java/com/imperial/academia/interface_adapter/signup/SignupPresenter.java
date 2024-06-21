package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.use_case.signup.SignupOutputBoundary;
import com.imperial.academia.use_case.signup.SignupOutputData;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.interface_adapter.login.LoginViewModel;
import com.imperial.academia.interface_adapter.login.LoginState;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The SignupPresenter class presents the signup results to the view.
 */
public class SignupPresenter implements SignupOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a SignupPresenter with the specified ViewManagerModel, SignupViewModel, and LoginViewModel.
     * 
     * @param viewManagerModel The view manager model.
     * @param signupViewModel The signup view model.
     * @param loginViewModel The login view model.
     */
    public SignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the success view when signup is successful.
     * 
     * @param response The output data of the signup process.
     */
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        SignupState signupState = signupViewModel.getState();
        signupState.clear();

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginState.setPassword(null);
        loginState.clearErrors();

        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        signupViewModel.firePropertyChanged("clean");
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view when signup fails.
     * 
     * @param error The error message.
     */
    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        if (error == null) {
            signupState.setUsernameError(null);
            signupState.setPasswordError(null);
            signupState.setRepeatPasswordError(null);
            signupState.setEmailError(null);
        } else if (error.contains("Username")) {
            signupState.setUsernameError(error);
        } else if (error.contains("Password")) {
            signupState.setPasswordError(error);
        } else if (error.contains("Repeat Passwords")) {
            signupState.setRepeatPasswordError(error);
        } else if (error.contains("Email")) {
            signupState.setEmailError(error);
        }
        signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged("error");
    }

    /**
     * Navigates to the login view.
     */
    @Override
    public void navigateToLogin() {
        viewManagerModel.setActiveView("log in");
        viewManagerModel.firePropertyChanged();
    }
}
