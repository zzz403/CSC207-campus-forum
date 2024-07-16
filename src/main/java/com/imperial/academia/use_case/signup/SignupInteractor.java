package com.imperial.academia.use_case.signup;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.entity.user.UserFactory;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

/**
 * Interactor class for handling the signup use case.
 */
public class SignupInteractor implements SignupInputBoundary {
    private final SignupOutputBoundary signupPresenter;
    private final UserFactory userFactory;
    private final UserService userService;

    private final ChangeViewInputBoundary changeViewInteractor = UsecaseFactory.getChangeViewInteractor();

    /**
     * Constructs a SignupInteractor with the given dependencies.
     *
     * @param userService     the user service
     * @param signupPresenter the signup presenter
     * @param userFactory     the user factory
     */
    public SignupInteractor(SignupOutputBoundary signupPresenter, UserFactory userFactory) {
        this.userService = ServiceFactory.getUserService();
        this.signupPresenter = signupPresenter;
        this.userFactory = userFactory;
    }

    /**
     * Executes the signup use case.
     *
     * @param signupInputData the input data for signup
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        try {
            signupPresenter.prepareFailView(null);
            if (signupInputData.getUsername().length() <= 6) {
                signupPresenter.prepareFailView("Username must be longer than 6 characters.");
            } else if (userService.existsByUsername(signupInputData.getUsername())) {
                signupPresenter.prepareFailView("User already exists.");
            } else if (signupInputData.getPassword().length() <= 6) {
                signupPresenter.prepareFailView("Password must be longer than 6 characters.");
            } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
                signupPresenter.prepareFailView("Repeat Passwords don't match.");
            } else if (!isValidEmail(signupInputData.getEmail())) {
                signupPresenter.prepareFailView("Email is not valid.");
            } else if (userService.existsByEmail(signupInputData.getEmail())) {
                signupPresenter.prepareFailView("Email already used.");
            } else {
                LocalDateTime now = LocalDateTime.now();
                User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(),
                        signupInputData.getEmail(), now);
                userService.insert(user);
                SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), now.toString());
                signupPresenter.prepareSuccessView(signupOutputData);
                changeViewInteractor.changeView("log in");
            }
        } catch (SQLException e) {
            signupPresenter.prepareFailView("An error occurred during signup: " + e.getMessage());
        }
    }

    /**
     * Validates the given email address.
     *
     * @param email the email address to validate
     * @return true if the email is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    @Override
    public void navigateToLogin() {
        changeViewInteractor.changeView("log in");
    }
}
