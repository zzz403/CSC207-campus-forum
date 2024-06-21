package com.imperial.academia.interface_adapter.login;

import java.io.IOException;

import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInputData;

public class LoginController {
    final LoginInputBoundary loginInteractor;

    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password, boolean rememberMe) {
        LoginInputData loginInputData = new LoginInputData(username, password, rememberMe);
        loginInteractor.execute(loginInputData);
    }

    public String[] loadCredentials() {
        try {
            return loginInteractor.loadCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[2];
    }

    public void navigateToSignup() {
        loginInteractor.navigateToSignup();
    }
}