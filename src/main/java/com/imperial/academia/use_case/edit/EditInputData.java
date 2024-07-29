package com.imperial.academia.use_case.edit;

/**
 * Encapsulates the data required for editing user profile details.
 * This class is used as a data transfer object to pass user data collected from UI to the business logic layer.
 */
public class EditInputData {
    private final String username;
    private final String password;
    private final String repeatPassword;
    private final String avatarURL;
    private final String email;

    /**
     * Constructs a new EditInputData instance.
     *
     * @param username       the user's new or existing username.
     * @param password       the user's new password.
     * @param repeatPassword the user's new password repeated for confirmation.
     * @param avatarURL      the URL to the user's new or existing avatar image.
     * @param email          the user's new or existing email address.
     */
    public EditInputData(String username, String password, String repeatPassword, String avatarURL, String email) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.avatarURL = avatarURL;
        this.email = email;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the repeat password.
     *
     * @return the repeat password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Returns the URL of the avatar.
     *
     * @return the avatar URL
     */
    public String getAvatarURL() {
        return avatarURL;
    }

    /**
     * Returns the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}
