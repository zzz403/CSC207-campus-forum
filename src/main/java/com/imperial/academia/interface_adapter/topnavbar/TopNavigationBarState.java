package com.imperial.academia.interface_adapter.topnavbar;

/**
 * Represents the state of the top navigation bar.
 * This class holds the data required for the top navigation bar, 
 * including the avatar URL, user ID, and current view name.
 */
public class TopNavigationBarState {
    
    /** The URL of the user's avatar. */
    private String avatarUrl = "";
    
    /** The ID of the user. */
    private int userId = 0;
    
    /** The name of the current view. */
    private String currentViewName = "";

    /**
     * Constructs a new TopNavigationBarState with default values.
     */
    public TopNavigationBarState() {}

    /**
     * Constructs a new TopNavigationBarState by copying the state from another instance.
     * 
     * @param copy the instance to copy the state from
     */
    public TopNavigationBarState(TopNavigationBarState copy) {
        this.avatarUrl = copy.avatarUrl;
        this.userId = copy.userId;
        this.currentViewName = copy.currentViewName;
    }

    /**
     * Gets the URL of the user's avatar.
     * 
     * @return the URL of the user's avatar
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the URL of the user's avatar.
     * 
     * @param avatarUrl the URL of the user's avatar
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Gets the ID of the user.
     * 
     * @return the ID of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user.
     * 
     * @param userId the ID of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the name of the current view.
     * 
     * @return the name of the current view
     */
    public String getCurrentViewName() {
        return currentViewName;
    }

    /**
     * Sets the name of the current view.
     * 
     * @param currentViewName the name of the current view
     */
    public void setCurrentViewName(String currentViewName) {
        this.currentViewName = currentViewName;
    }
}
