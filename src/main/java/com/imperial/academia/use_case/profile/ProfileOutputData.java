package com.imperial.academia.use_case.profile;

import java.sql.Timestamp;

/**
 * The ProfileOutputData class encapsulates the data required to present a user's profile.
 * This class is immutable and provides methods to retrieve profile details such as user ID, username, email, role, avatar URL, registration date, and a flag indicating if the profile belongs to the current user.
 */
public class ProfileOutputData {

    /**
     * The ID of the user.
     */
    private final int id;

    /**
     * The username of the user.
     */
    private final String username;

    /**
     * The email of the user.
     */
    private final String email;

    /**
     * The role of the user.
     */
    private final String role;

    /**
     * The avatar URL of the user.
     */
    private final String avatarUrl;

    /**
     * The registration date of the user.
     */
    private final Timestamp registrationDate;

    /**
     * A flag indicating if the profile belongs to the current user.
     */
    private final boolean isMe;

    /**
     * Constructs a new ProfileOutputData instance with the specified profile details.
     *
     * @param id               the ID of the user
     * @param username         the username of the user
     * @param email            the email of the user
     * @param role             the role of the user
     * @param avatarUrl        the avatar URL of the user
     * @param registrationDate the registration date of the user
     * @param isMe             a flag indicating if the profile belongs to the current user
     */
    public ProfileOutputData(int id, String username, String email, String role, String avatarUrl, Timestamp registrationDate, boolean isMe) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.registrationDate = registrationDate;
        this.isMe = isMe;
    }

    /**
     * Returns the ID of the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the registration date of the user.
     *
     * @return the registration date
     */
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Returns the avatar URL of the user.
     *
     * @return the avatar URL
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Returns the role of the user.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Returns the email of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the username of the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a flag indicating if the profile belongs to the current user.
     *
     * @return true if the profile belongs to the current user, false otherwise
     */
    public boolean isMe() {
        return isMe;
    }
}
