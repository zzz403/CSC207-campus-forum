package com.imperial.academia.use_case.post;

import java.sql.Timestamp;

/**
 * The PostInfoData class encapsulates the data for a post.
 * It uses the Builder pattern to create instances.
 */
public class PostInfoData {
    private String title;
    private String content;
    private String username;
    private String avatarUrl;
    private Timestamp date;
    private int likes;

    /**
     * Private constructor for PostInfoData.
     * Instances should be created using the PostInfoDataBuilder.
     * 
     * @param builder the builder instance used to create the PostInfoData object.
     */
    private PostInfoData(PostInfoDataBuilder builder) {
        this.title = builder.title;
        this.content = builder.content;
        this.username = builder.username;
        this.avatarUrl = builder.avatarUrl;
        this.date = builder.date;
        this.likes = builder.likes;
    }

    /**
     * Constructs a new PostInfoData with the specified parameters.
     * 
     * @param title     the title of the post.
     * @param content   the content of the post.
     * @param username  the username of the post author.
     * @param avatarUrl the avatar URL of the post author.
     * @param date      the date of the post.
     * @param likes     the number of likes the post has.
     */
    public PostInfoData(String title, String content, String username, String avatarUrl, Timestamp date, int likes) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.date = date;
        this.likes = likes;
    }

    /**
     * Default constructor for PostInfoData.
     */
    public PostInfoData() {
    }

    /**
     * Sets the title of the post.
     * 
     * @param title the title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the content of the post.
     * 
     * @param content the content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the username of the post author.
     * 
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the avatar URL of the post author.
     * 
     * @param avatarUrl the avatar URL to set.
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Sets the date of the post.
     * 
     * @param date the date to set.
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Gets the number of likes the post has.
     * 
     * @return the number of likes the post has.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Sets the number of likes the post has.
     * 
     * @param likes the number of likes to set.
     */
    public void setLikes(int likes) {
        this.likes = likes;
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
     * Gets the content of the post.
     * 
     * @return the content of the post.
     */
    public String getContent() {
        return content;
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
     * Gets the avatar URL of the post author.
     * 
     * @return the avatar URL of the post author.
     */
    public String getAvatarUrl() {
        return avatarUrl;
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
     * Creates a new builder for PostInfoData.
     * 
     * @return a new PostInfoDataBuilder instance.
     */
    public static PostInfoDataBuilder builder() {
        return new PostInfoDataBuilder();
    }

    /**
     * The PostInfoDataBuilder class helps in building an instance of PostInfoData.
     */
    public static class PostInfoDataBuilder {
        private String title;
        private String content;
        private String username;
        private String avatarUrl;
        private Timestamp date;
        private int likes;

        public PostInfoDataBuilder setLikes(int likes) {
            this.likes = likes;
            return this;
        }

        /**
         * Sets the title of the post.
         * 
         * @param title the title to set.
         * @return the builder instance.
         */
        public PostInfoDataBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        /**
         * Sets the content of the post.
         * 
         * @param content the content to set.
         * @return the builder instance.
         */
        public PostInfoDataBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        /**
         * Sets the username of the post author.
         * 
         * @param username the username to set.
         * @return the builder instance.
         */
        public PostInfoDataBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Sets the avatar URL of the post author.
         * 
         * @param avatarUrl the avatar URL to set.
         * @return the builder instance.
         */
        public PostInfoDataBuilder setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        /**
         * Sets the date of the post.
         * 
         * @param date the date to set.
         * @return the builder instance.
         */
        public PostInfoDataBuilder setDate(Timestamp date) {
            this.date = date;
            return this;
        }

        /**
         * Builds and returns an instance of PostInfoData.
         * 
         * @return the constructed PostInfoData object.
         */
        public PostInfoData build() {
            return new PostInfoData(this);
        }
    }
}
