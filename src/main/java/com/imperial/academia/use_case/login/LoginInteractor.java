package com.imperial.academia.use_case.login;

import com.imperial.academia.data_access.user.UserDAI;
import com.imperial.academia.data_access.user.UserDAO;
import com.imperial.academia.entity.user.User;

import java.sql.SQLException;

public class LoginInteractor implements LoginInputBoundary {
    private final UserDAI userDAO;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserDAI userDAO, LoginOutputBoundary loginPresenter) {
        this.userDAO = userDAO;
        this.loginPresenter = loginPresenter;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        try {
            User user = userDAO.getByUsername(loginInputData.getUsername());
            if (user == null) {
                loginPresenter.prepareFailView("User not found.");
            } else if (!user.getPassword().equals(loginInputData.getPassword())) {
                loginPresenter.prepareFailView("Invalid password.");
            } else {
                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), "Login successful.");
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        } catch (SQLException e) {
            loginPresenter.prepareFailView("An error occurred during login: " + e.getMessage());
        }
    }
}
