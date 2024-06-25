package com.imperial.academia.use_case.login;

/**
 * The LoginOutputData class represents the output data of the login process.
 */
public class LoginOutputData {
    private final String username;
    private final String message;
    private final String avatarUrl;
    private final int userId;

    /**
     * Constructs a new LoginOutputData with the specified details.
     * 
     * @param username The username of the user.
     * @param message The message to be displayed.
     */
    public LoginOutputData(String username, String message, String avatarUrl, int userId) {
        this.username = username;
        this.message = message;
        this.avatarUrl = avatarUrl;
        this.userId = userId;
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

    /**
     * Gets the avatar URL.
     * 
     * @return The avatar URL.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Gets the user ID.
     * 
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }
}
