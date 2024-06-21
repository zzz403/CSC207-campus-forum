package com.imperial.academia.use_case.login;

import com.imperial.academia.session.SessionManager;
import com.imperial.academia.data_access.remember_me.RememberMeDAI;
import com.imperial.academia.service.UserService;
import com.imperial.academia.entity.user.User;

import java.io.IOException;
import java.sql.SQLException;

public class LoginInteractor implements LoginInputBoundary {
    private final UserService userService;
    private final LoginOutputBoundary loginPresenter;
    private final RememberMeDAI rememberMeDAO;

    public LoginInteractor(UserService userService, LoginOutputBoundary loginPresenter, RememberMeDAI rememberMeDAO) {
        this.userService = userService;
        this.loginPresenter = loginPresenter;
        this.rememberMeDAO = rememberMeDAO;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        try {
            loginPresenter.prepareFailView(null);

            User user = userService.getByUsername(loginInputData.getUsername());
            if (user == null) {
                handleLoginFailure("User not found.");
            } else if (!user.getPassword().equals(loginInputData.getPassword())) {
                handleLoginFailure("Invalid password.");
            } else {
                handleLoginSuccess(user, loginInputData.isRememberMe(), loginInputData.getUsername(), loginInputData.getPassword());
            }
        } catch (SQLException e) {
            handleLoginFailure("An error occurred during login: " + e.getMessage());
        }
    }

    private void handleLoginFailure(String errorMessage) {
        loginPresenter.prepareFailView(errorMessage);
    }

    private void handleLoginSuccess(User user, boolean rememberMe, String username, String password) {
        if (rememberMe) {
            try {
                saveCredentials(username, password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                clearCredentials();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        SessionManager.setCurrentUser(user);
        LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), "Login successful.");
        loginPresenter.prepareSuccessView(loginOutputData);
    }

    public void saveCredentials(String username, String password) throws IOException {
        rememberMeDAO.saveCredentials(username, password);
    }

    public String[] loadCredentials() throws IOException {
        return rememberMeDAO.loadCredentials();
    }

    public void clearCredentials() throws IOException {
        rememberMeDAO.clearCredentials();
    }

    @Override
    public void navigateToSignup() {
        loginPresenter.navigateToSignup();
    }
}
