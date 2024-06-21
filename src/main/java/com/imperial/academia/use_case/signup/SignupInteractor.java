package com.imperial.academia.use_case.signup;

import com.imperial.academia.data_access.user.UserDAI;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class SignupInteractor implements SignupInputBoundary {
    private final UserDAI userDAO;
    private final SignupOutputBoundary signupPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(UserDAI userDAO, SignupOutputBoundary signupPresenter, UserFactory userFactory) {
        this.userDAO = userDAO;
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        try {
            signupPresenter.prepareFailView(null);
            if (signupInputData.getUsername().length() <= 6) {
                signupPresenter.prepareFailView("Username must be longer than 6 characters.");
            } else if (userDAO.existsByUsername(signupInputData.getUsername())) {
                signupPresenter.prepareFailView("User already exists.");
            } else if (signupInputData.getPassword().length() <= 6) {
                signupPresenter.prepareFailView("Password must be longer than 6 characters.");
            } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
                signupPresenter.prepareFailView("Repeat Passwords don't match.");
            } else if (!isValidEmail(signupInputData.getEmail())) {
                signupPresenter.prepareFailView("Email is not valid.");
            } else if (userDAO.existsByEmail(signupInputData.getEmail())) {
                signupPresenter.prepareFailView("Email already used.");
            } else {
                LocalDateTime now = LocalDateTime.now();
                User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(),
                        signupInputData.getEmail(), "user", now);
                userDAO.insert(user);
                SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
                signupPresenter.prepareSuccessView(signupOutputData);
            }
        } catch (SQLException e) {
            signupPresenter.prepareFailView("An error occurred during signup: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    @Override
    public void navigateToLogin(){
        signupPresenter.navigateToLogin();
    }
}
