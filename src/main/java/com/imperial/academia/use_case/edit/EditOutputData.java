package com.imperial.academia.use_case.edit;

/**
 * Data class representing the output data from edit operations on a user profile.
 * This class is used to pass the updated user information from the business logic layer to the presentation layer,
 * ensuring that the UI can reflect changes made to the user's profile.
 */
public class EditOutputData {
    private final int userId;
    private final String userName;
    private final String password;
    private final String email;
    private final String avatarURL;

    /**
     * Constructs an instance of EditOutputData with detailed user profile information.
     *
     * @param userId The unique identifier of the user.
     * @param userName The updated username of the user.
     * @param password The updated password of the user. This should be handled securely and not exposed unnecessarily.
     * @param email The updated email address of the user.
     * @param avatarURL The URL to the updated avatar image of the user.
     */
    public EditOutputData(int userId, String userName, String password, String email, String avatarURL) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.avatarURL = avatarURL;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return The user's ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets the updated username of the user.
     *
     * @return The user's updated username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the updated password of the user.
     * Note: Care should be taken to ensure that password handling is secure.
     *
     * @return The user's updated password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the updated email address of the user.
     *
     * @return The user's updated email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the URL to the updated avatar image of the user.
     *
     * @return The URL of the user's updated avatar image.
     */
    public String getAvatarURL() {
        return avatarURL;
    }
}
