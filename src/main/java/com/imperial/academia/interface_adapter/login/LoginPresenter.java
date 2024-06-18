package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.use_case.login.LoginOutputData;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView("forum");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        loginViewModel.setErrorMessage(error);
        loginViewModel.firePropertyChanged();
    }
}
