package com.imperial.academia.use_case.login;

import java.io.IOException;

/**
 * The LoginInputBoundary interface defines the input methods for the login use case.
 */
public interface LoginInputBoundary {
    /**
     * Executes the login process with the provided login input data.
     * 
     * @param loginInputData The input data for the login process.
     */
    void execute(LoginInputData loginInputData);

    /**
     * Loads the saved credentials, if available.
     * 
     * @return An array containing the username and password.
     * @throws IOException If an I/O error occurs.
     */
    String[] loadCredentials() throws IOException;

    /**
     * Navigates to the signup view.
     */
    void navigateToSignup();
}
