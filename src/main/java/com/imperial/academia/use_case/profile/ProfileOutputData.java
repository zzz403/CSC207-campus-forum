package com.imperial.academia.use_case.profile;

import java.sql.Timestamp;
import java.util.List;

/**
 * Encapsulates all relevant data for presenting a user's profile.
 * This class is immutable and provides accessors for user details such as user ID, username, email,
 * role, avatar URL, and registration date. It also includes post-related information, ensuring that all
 * necessary profile information is available in one consolidated object.
 */
public class ProfileOutputData {

    private final int id;
    private final String username;
    private final String email;
    private final String role;
    private final String avatarUrl;
    private final Timestamp registrationDate;
    private final boolean isMe;
    private final List<String> postTitles;
    private final List<String> postContents;
    private final List<Timestamp> postCreationDates;
    private final List<String> postImageUrls;
    private final List<Integer> postIds;

    /**
     * Constructs a ProfileOutputData object with detailed information about the user and their posts.
     *
     * @param id                The unique identifier for the user.
     * @param username          The user's username.
     * @param email             The user's email address.
     * @param role              The user's role within the system.
     * @param avatarUrl         The URL to the user's avatar image.
     * @param registrationDate  The date the user registered.
     * @param isMe              Flag indicating if this profile belongs to the current logged-in user.
     * @param postTitles        Titles of the user's posts.
     * @param postContents      Contents of the user's posts.
     * @param postCreationDates Creation dates of the user's posts.
     * @param postImageUrls     Image URLs for the user's posts.
     * @param postIds           Unique identifiers for the user's posts.
     */
    public ProfileOutputData(int id, String username, String email, String role, String avatarUrl,
                             Timestamp registrationDate, boolean isMe, List<String> postTitles, List<String> postContents,
                             List<Timestamp> postCreationDates, List<String> postImageUrls, List<Integer> postIds) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.avatarUrl = avatarUrl;
        this.registrationDate = registrationDate;
        this.isMe = isMe;
        this.postTitles = postTitles;
        this.postContents = postContents;
        this.postCreationDates = postCreationDates;
        this.postImageUrls = postImageUrls;
        this.postIds = postIds;
    }
    /**
     * Gets the unique identifier for the user.
     *
     * @return The user's unique identifier.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the username of the user.
     *
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email address of the user.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the role of the user within the system.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Gets the URL of the user's avatar image.
     *
     * @return The URL of the avatar image.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Gets the registration date of the user.
     *
     * @return The timestamp of when the user registered.
     */
    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Checks if the profile belongs to the currently logged-in user.
     *
     * @return True if the profile is of the current user, false otherwise.
     */
    public boolean isMe() {
        return isMe;
    }

    /**
     * Gets the titles of all posts made by the user.
     *
     * @return A list of post titles.
     */
    public List<String> getPostTitles() {
        return postTitles;
    }

    /**
     * Gets the contents of all posts made by the user.
     *
     * @return A list of post contents.
     */
    public List<String> getPostContents() {
        return postContents;
    }

    /**
     * Gets the creation dates of all posts made by the user.
     *
     * @return A list of timestamps representing the creation dates of the posts.
     */
    public List<Timestamp> getPostCreationDates() {
        return postCreationDates;
    }

    /**
     * Gets the image URLs for all posts made by the user.
     *
     * @return A list of URLs of images associated with the posts.
     */
    public List<String> getPostImageUrls() {
        return postImageUrls;
    }

    /**
     * Gets the unique identifiers for all posts made by the user.
     *
     * @return A list of post IDs.
     */
    public List<Integer> getPostIds() {
        return postIds;
    }


}
