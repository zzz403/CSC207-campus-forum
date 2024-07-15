package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarState;
import com.imperial.academia.interface_adapter.topnavbar.TopNavigationBarViewModel;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.use_case.login.LoginOutputData;

/**
 * The LoginPresenter class presents the login results to the view.
 */
public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final TopNavigationBarViewModel topNavigationBarViewModel;

    /**
     * Constructs a LoginPresenter with the specified ViewManagerModel and LoginViewModel.
     * 
     * @param loginViewModel The login view model.
     */
    public LoginPresenter(LoginViewModel loginViewModel, TopNavigationBarViewModel topNavigationBarViewModel) {
        this.loginViewModel = loginViewModel;
        this.topNavigationBarViewModel = topNavigationBarViewModel;
    }

    /**
     * Prepares the success view when login is successful.
     * 
     * @param loginOutputData The output data of the login process.
     */
    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        TopNavigationBarState topNavigationBarState = topNavigationBarViewModel.getState();
        topNavigationBarState.setAvatarUrl(loginOutputData.getAvatarUrl());
        topNavigationBarState.setUserId(loginOutputData.getUserId());
        topNavigationBarState.setAvatarUrl(SessionManager.getCurrentUser().getAvatarUrl());
        topNavigationBarState.setCurrentViewName("post board");
        topNavigationBarViewModel.setState(topNavigationBarState);
        topNavigationBarViewModel.firePropertyChanged();
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

}
