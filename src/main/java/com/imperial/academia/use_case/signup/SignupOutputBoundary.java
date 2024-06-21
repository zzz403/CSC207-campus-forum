package com.imperial.academia.use_case.signup;

/**
 * Interface representing the output boundary for the signup use case.
 */
public interface SignupOutputBoundary {
    /**
     * Prepares the success view for the signup use case.
     *
     * @param signupOutputData the output data for signup
     */
    void prepareSuccessView(SignupOutputData signupOutputData);

    /**
     * Prepares the fail view for the signup use case.
     *
     * @param error the error message
     */
    void prepareFailView(String error);

    /**
     * Navigates to the login screen.
     */
    void navigateToLogin();
}
