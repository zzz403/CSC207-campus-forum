package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.use_case.login.LoginOutputData;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;

/**
 * The LoginPresenter class presents the login results to the view.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    /**
     * Constructs a LoginPresenter with the specified ViewManagerModel and LoginViewModel.
     * 
     * @param viewManagerModel The view manager model.
     * @param loginViewModel The login view model.
     */
    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * Prepares the success view when login is successful.
     * 
     * @param loginOutputData The output data of the login process.
     */
    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        viewManagerModel.setActiveView("post board"); // Example: navigate to forum view
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view when login fails.
     * 
     * @param error The error message.
     */
    @Override
    public void prepareFailView(String error) {
        LoginState state = loginViewModel.getState();
        if (error == null) {
            state.setUsernameError(null);
            state.setPasswordError(null);
        } else if (error.contains("User")) {
            state.setUsernameError(error);
        } else if (error.contains("password")) {
            state.setPasswordError(error);
        }
        loginViewModel.setState(state);
        loginViewModel.firePropertyChanged();
    }

    /**
     * Navigates to the signup view.
     */
    @Override
    public void navigateToSignup() {
        viewManagerModel.setActiveView("sign up");
        viewManagerModel.firePropertyChanged();
    }
}
