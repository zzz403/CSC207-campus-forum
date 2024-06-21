package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.use_case.login.LoginOutputBoundary;
import com.imperial.academia.use_case.login.LoginOutputData;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;

public class LoginPresenter implements LoginOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        // 切换到下一个视图或显示成功消息
        viewManagerModel.setActiveView("forum"); // Example: navigate to forum view
        viewManagerModel.firePropertyChanged();
    }

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

    @Override
    public void navigateToSignup() {
        viewManagerModel.setActiveView("sign up");
        viewManagerModel.firePropertyChanged();
    }
}
