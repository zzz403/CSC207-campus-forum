package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInputData;

/**
 * The SignupController class handles the signup process and manages
 * the interaction between the view and the signup use case.
 */
public class SignupController {
    final SignupInputBoundary signupInteractor;

    /**
     * Constructs a SignupController with the specified SignupInputBoundary.
     * 
     */
    public SignupController() {
        this.signupInteractor = UsecaseFactory.getSignupInteractor();
    }

    /**
     * Executes the signup process with the provided user details.
     * 
     * @param username The username of the user.
     * @param password1 The password of the user.
     * @param password2 The repeated password for confirmation.
     * @param email The email address of the user.
     */
    public void execute(String username, String password1, String password2, String email) {
        SignupInputData signupInputData = new SignupInputData(username, password1, password2, email);
        signupInteractor.execute(signupInputData);
    }

    /**
     * Navigates to the login view.
     */
    public void navigateToLogin() {
        signupInteractor.navigateToLogin();
    }
}
