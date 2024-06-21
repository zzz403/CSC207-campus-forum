package com.imperial.academia.use_case.signup;

/**
 * Interface representing the input boundary for the signup use case.
 */
public interface SignupInputBoundary {
    /**
     * Executes the signup use case.
     *
     * @param signupInputData the input data for signup
     */
    void execute(SignupInputData signupInputData);

    /**
     * Navigates to the login screen.
     */
    void navigateToLogin();
}
