package com.imperial.academia.use_case.login;

/**
 * The LoginInputData class represents the input data required for the login process.
 */
public class LoginInputData {
    private final String username;
    private final String password;
    private final boolean rememberMe;

    /**
     * Constructs a new LoginInputData with the specified details.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param rememberMe Whether to remember the user.
     */
    public LoginInputData(String username, String password, boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
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
     * Gets the password.
     * 
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the remember me flag.
     * 
     * @return true if remember me is enabled, false otherwise.
     */
    public boolean isRememberMe() {
        return rememberMe;
    }
}
