package com.imperial.academia.interface_adapter.login;

/**
 * The LoginState class represents the state of the login view.
 */
public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    /**
     * Constructs a LoginState by copying another LoginState.
     * 
     * @param copy The LoginState to copy.
     */
    public LoginState(LoginState copy) {
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
    }

    /**
     * Constructs a new LoginState.
     */
    public LoginState() {}

    // Getters and Setters

    /**
     * Gets the username.
     * 
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the username error message.
     * 
     * @return The username error message.
     */
    public String getUsernameError() {
        return usernameError;
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
     * Gets the password error message.
     * 
     * @return The password error message.
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Sets the username.
     * 
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username error message.
     * 
     * @param usernameError The username error message to set.
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Sets the password.
     * 
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the password error message.
     * 
     * @param passwordError The password error message to set.
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Clears all error messages.
     */
    public void clearErrors() {
        this.usernameError = null;
        this.passwordError = null;
    }
}
