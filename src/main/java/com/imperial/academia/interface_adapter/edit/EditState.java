package com.imperial.academia.interface_adapter.edit;

public class EditState {
    private int userId = 0;
    private String userName = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    private String email = "";
    private String emailError = null;
    private String avatarURL = "";
    private String avatarError = null;


    public String getAvatarError() {

        return this.avatarError;

    }

    public void setAvatarError(String avatarError) {
        this.avatarError = avatarError;
    }

    public EditState(EditState copy) {
        this.userId = copy.getUserId();
        this.userName = copy.getUserName();
        this.usernameError = copy.getUsernameError();
        this.password = copy.getPassword();
        this.passwordError = copy.getPasswordError();
        this.repeatPassword = copy.getRepeatPassword();
        this.repeatPasswordError = copy.getRepeatPasswordError();
        this.email = copy.getEmail();
        this.emailError = copy.getEmailError();
        this.avatarURL = copy.getAvatarURL();
        this.avatarError = copy.getAvatarError();
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public EditState() {}

    // Getters and Setters

    /**
     * Gets the username.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
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
     * @param userName The username to set.
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
        this.userName = "";
        this.usernameError = null;
        this.password = "";
        this.passwordError = null;
        this.repeatPassword = "";
        this.repeatPasswordError = null;
        this.email = "";
        this.emailError = null;
        this.avatarError = null;
    }

    /**
     * Clears all error messages.
     */
    public void clearErrors() {
        this.usernameError = null;
        this.passwordError = null;
        this.repeatPasswordError = null;
        this.emailError = null;
        this.avatarError = null;
    }
}
