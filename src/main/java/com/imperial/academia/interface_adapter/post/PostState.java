package com.imperial.academia.interface_adapter.post;

import java.sql.Timestamp;

/**
 * The PostState class represents the state of a post, including its title, content,
 * username, avatar URL, and date.
 */
public class PostState {
    private String title;
    private String content;
    private String username;
    private String avatarUrl;
    private Timestamp date;

    /**
     * Constructs a new PostState as a copy of the specified PostState.
     * 
     * @param other the PostState to copy.
     */
    public PostState(PostState other) {
        this.title = other.getTitle();
        this.content = other.getContent();
        this.date = other.getDate();
        this.username = other.getUsername();
        this.avatarUrl = other.getAvatarUrl();
    }

    /**
     * Constructs a new PostState with default values.
     */
    public PostState() {
        this.title = "";
        this.content = "";
        this.date = new Timestamp(System.currentTimeMillis());
        this.username = "";
        this.avatarUrl = "";
    }

    /**
     * Gets the title of the post.
     * 
     * @return the title of the post.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     * 
     * @param title the new title of the post.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content of the post.
     * 
     * @return the content of the post.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     * 
     * @param content the new content of the post.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the date of the post.
     * 
     * @return the date of the post.
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * Sets the date of the post.
     * 
     * @param date the new date of the post.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Gets the username of the post author.
     * 
     * @return the username of the post author.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the post author.
     * 
     * @param username the new username of the post author.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the avatar URL of the post author.
     * 
     * @return the avatar URL of the post author.
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Sets the avatar URL of the post author.
     * 
     * @param avatarUrl the new avatar URL of the post author.
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
