package com.imperial.academia.use_case.login;

/**
 * The LoginOutputBoundary interface defines the output methods for the login use case.
 */
public interface LoginOutputBoundary {
    /**
     * Prepares the success view when login is successful.
     * 
     * @param loginOutputData The output data of the login process.
     */
    void prepareSuccessView(LoginOutputData loginOutputData);

    /**
     * Prepares the fail view when login fails.
     * 
     * @param error The error message.
     */
    void prepareFailView(String error);
}
