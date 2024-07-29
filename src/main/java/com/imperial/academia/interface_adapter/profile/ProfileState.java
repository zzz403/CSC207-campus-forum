package com.imperial.academia.interface_adapter.profile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * The ProfileState class represents the state of a user's profile.
 * It includes user information such as ID, username, email, role, avatar URL, registration date, and a flag indicating if the profile belongs to the current user.
 */
public class ProfileState {

    /**
     * The ID of the user.
     */
    private int id = -1;

    /**
     * The username of the user.
     */
    private String username = "";

    /**
     * The email of the user.
     */
    private String email = "";

    /**
     * The role of the user.
     */
    private String role = "";

    /**
     * The avatar URL of the user.
     */
    private String avatarUrl = "";

    /**
     * The registration date of the user.
     */
    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

    /**
     * A flag indicating if the profile belongs to the current user.
     */
    private boolean isMe = true;

    private List<Integer> postIDs = new ArrayList<>();

    private List<String> postTitles = new ArrayList<>();
    private List<String> postContents = new ArrayList<>();
    private List<Timestamp> postCreationDates = new ArrayList<>();
    private List<String> postImageUrls = new ArrayList<>();




    /**
     * Constructs a new ProfileState by copying another ProfileState instance.
     *
     * @param copy the ProfileState instance to copy
     */
    public ProfileState(ProfileState copy) {
        this.id = copy.getId();
        this.username = copy.getUsername();
        this.email = copy.getEmail();
        this.role = copy.getRole();
        this.avatarUrl = copy.getAvatarUrl();
        this.registrationDate = copy.getRegistrationDate();
        this.isMe = copy.isMe();
        this.postTitles = copy.postTitles;
        this.postContents = copy.postContents;
        this.postCreationDates = copy.postCreationDates;
        this.postImageUrls = copy.postImageUrls;
        this.postIDs = copy.postIDs;
    }

    /**
     * Constructs a new, empty ProfileState.
     */
    public ProfileState() {}

    /**
     * Returns the ID of the user.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
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
     * Returns the email of the user.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
     * Returns the avatar URL of the user.
     *
     * @return the avatar URL
     */
    public String getAvatarUrl() {
        return avatarUrl;
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
     * Returns a flag indicating if the profile belongs to the current user.
     *
     * @return true if the profile belongs to the current user, false otherwise
     */
    public boolean isMe() {
        return isMe;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the user ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Sets the avatar URL of the user.
     *
     * @param avatarUrl the avatar URL
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Sets the registration date of the user.
     *
     * @param registrationDate the registration date
     */
    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Sets the flag indicating if the profile belongs to the current user.
     *
     * @param isMe true if the profile belongs to the current user, false otherwise
     */
    public void setIsMe(boolean isMe) {
        this.isMe = isMe;
    }

    public List<String> getPostTitles() {
        return postTitles;
    }

    public List<String> getPostContents() {
        return postContents;
    }



    public List<Timestamp> getPostCreationDates() {
        return postCreationDates;
    }

    public List<String> getPostImageUrls() {
        return postImageUrls;
    }

    public void setPostTitles(List<String> postTitles) {
        this.postTitles = postTitles;
    }

    public void setPostContents(List<String> postContents) {
        this.postContents = postContents;
    }



    public void setPostCreationDates(List<Timestamp> postCreationDates) {
        this.postCreationDates = postCreationDates;
    }

    public void setPostImageUrls(List<String> postImageUrls) {
        this.postImageUrls = postImageUrls;
    }

    public void setPostIds(List<Integer> postIDs) {
        this.postIDs = postIDs;
    }

    public List<Integer> getPostIds() {
        return postIDs;
    }
}
