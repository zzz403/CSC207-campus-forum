package com.imperial.academia.use_case.login;

/**
 * The LoginOutputData class represents the output data of the login process.
 */
public class LoginOutputData {
    private final String username;
    private final String message;

    /**
     * Constructs a new LoginOutputData with the specified details.
     * 
     * @param username The username of the user.
     * @param message The message to be displayed.
     */
    public LoginOutputData(String username, String message) {
        this.username = username;
        this.message = message;
    }

    /**
     * Gets the username.
     * 
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the message to be displayed.
     * 
     * @return The message to be displayed.
     */
    public String getMessage() {
        return message;
    }
}
