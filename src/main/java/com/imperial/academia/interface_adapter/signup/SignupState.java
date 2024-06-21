package com.imperial.academia.interface_adapter.signup;

/**
 * The SignupState class represents the state of the signup view.
 */
public class SignupState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String email = "";
    private String emailError = null;

    /**
     * Constructs a SignupState by copying another SignupState.
     * 
     * @param copy The SignupState to copy.
     */
    public SignupState(SignupState copy) {
        this.username = copy.username;
        this.usernameError = copy.usernameError;
        this.password = copy.password;
        this.passwordError = copy.passwordError;
        this.repeatPassword = copy.repeatPassword;
        this.repeatPasswordError = copy.repeatPasswordError;
        this.email = copy.email;
        this.emailError = copy.emailError;
    }

    /**
     * Constructs a new SignupState.
     */
    public SignupState() {}

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
     * Gets the repeat password.
     * 
     * @return The repeat password.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Gets the repeat password error message.
     * 
     * @return The repeat password error message.
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Gets the email address.
     * 
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the email error message.
     * 
     * @return The email error message.
     */
    public String getEmailError() {
        return emailError;
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
     * Sets the repeat password.
     * 
     * @param repeatPassword The repeat password to set.
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Sets the repeat password error message.
     * 
     * @param repeatPasswordError The repeat password error message to set.
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

    /**
     * Sets the email address.
     * 
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the email error message.
     * 
     * @param emailError The email error message to set.
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    /**
     * Clears all state fields.
     */
    public void clear() {
        this.username = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
        this.repeatPassword = "";
        this.repeatPasswordError = null;
        this.email = "";
        this.emailError = null;
    }

    /**
     * Clears all error messages.
     */
    public void clearErrors() {
        this.usernameError = null;
        this.passwordError = null;
        this.repeatPasswordError = null;
        this.emailError = null;
    }
}
