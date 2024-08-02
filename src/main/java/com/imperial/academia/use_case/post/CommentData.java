package com.imperial.academia.use_case.post;

import java.sql.Timestamp;

public class CommentData {
    private int id;
    private String content;
    private int authorId;
    private String username;
    private int postId;
    private Integer parentCommentId;
    private Timestamp creationDate;
    private Timestamp lastModified;

    /**
     * Constructs a new CommentData with the specified details.
     * 
     * @param id              The unique identifier of the comment.
     * @param content         The content of the comment.
     * @param authorId        The ID of the user who authored the comment.
     * @param postId          The ID of the post the comment is associated with.
     * @param parentCommentId The ID of the parent comment, if any.
     * @param creationDate    The timestamp when the comment was created.
     * @param lastModified    The timestamp when the comment was last modified.
     */
    public CommentData(int id, String content, int authorId, int postId, Integer parentCommentId,
            Timestamp creationDate, Timestamp lastModified, String username) {
        this.id = id;
        this.content = content;
        this.authorId = authorId;
        this.postId = postId;
        this.parentCommentId = parentCommentId;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
        this.username = username;
    }

    /**
     * Constructs a new CommentData with the specified details.
     * 
     * @param id              The unique identifier of the comment.
     * @param content         The content of the comment.
     * @param authorId        The ID of the user who authored the comment.
     * @param postId          The ID of the post the comment is associated with.
     * @param parentCommentId The ID of the parent comment, if any.
     * @param creationDate    The timestamp when the comment was created.
     * @param lastModified    The timestamp when the comment was last modified.
     */
    public CommentData(CommentDataBuilder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.authorId = builder.authorId;
        this.postId = builder.postId;
        this.parentCommentId = builder.parentCommentId;
        this.creationDate = builder.creationDate;
        this.lastModified = builder.lastModified;
        this.username = builder.username;
    }

    /**
     * Gets the unique identifier of the comment.
     * 
     * @return The unique identifier of the comment.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the comment.
     * 
     * @param id The unique identifier of the comment.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the content of the comment.
     * 
     * @return The content of the comment.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the comment.
     * 
     * @param content The content of the comment.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the ID of the user who authored the comment.
     * 
     * @return The ID of the user who authored the comment.
     */
    public int getAuthorId() {
        return authorId;
    }

    /**
     * Sets the ID of the user who authored the comment.
     * 
     * @param authorId The ID of the user who authored the comment.
     */
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    /**
     * Gets the ID of the post the comment is associated with.
     * 
     * @return The ID of the post the comment is associated with.
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Sets the ID of the post the comment is associated with.
     * 
     * @param postId The ID of the post the comment is associated with.
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * Gets the ID of the parent comment, if any.
     * 
     * @return The ID of the parent comment, if any.
     */
    public Integer getParentCommentId() {
        return parentCommentId;
    }

    /**
     * Sets the ID of the parent comment, if any.
     * 
     * @param parentCommentId The ID of the parent comment, if any.
     */
    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    /**
     * Gets the timestamp when the comment was created.
     * 
     * @return The timestamp when the comment was created.
     */
    public Timestamp getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the timestamp when the comment was created.
     * 
     * @param creationDate The timestamp when the comment was created.
     */
    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets the timestamp when the comment was last modified.
     * 
     * @return The timestamp when the comment was last modified.
     */
    public Timestamp getLastModified() {
        return lastModified;
    }

    /**
     * Sets the timestamp when the comment was last modified.
     * 
     * @param lastModified The timestamp when the comment was last modified.
     */
    public void setLastModified(Timestamp lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Constructs a new CommentData with the specified details.
     */
    public CommentData() {
    }

    /**
     * Creates a new CommentDataBuilder.
     * 
     * @return A new CommentDataBuilder.
     */
    public static CommentDataBuilder builder() {
        return new CommentDataBuilder();
    }

    /**
     * The CommentDataBuilder class is used to construct a new CommentData.
     */
    public static class CommentDataBuilder {
        private int id;
        private String content;
        private int authorId;
        private int postId;
        private Integer parentCommentId;
        private Timestamp creationDate;
        private Timestamp lastModified;
        private String username;

        /**
         * Set the username of the author of the comment.
         * 
         * @param username The username of the author of the comment.
         * @return The CommentDataBuilder.
         */
        public CommentDataBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        /**
         * Constructs a new CommentDataBuilder.
         */
        public CommentDataBuilder setId(int id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the content of the comment.
         * 
         * @param content The content of the comment.
         */
        public CommentDataBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        /**
         * Sets the ID of the user who authored the comment.
         * 
         * @param authorId The ID of the user who authored the comment.
         */
        public CommentDataBuilder setAuthorId(int authorId) {
            this.authorId = authorId;
            return this;
        }

        /**
         * Sets the ID of the post the comment is associated with.
         * 
         * @param postId The ID of the post the comment is associated with.
         */
        public CommentDataBuilder setPostId(int postId) {
            this.postId = postId;
            return this;
        }

        /**
         * Sets the ID of the parent comment, if any.
         * 
         * @param parentCommentId The ID of the parent comment, if any.
         */
        public CommentDataBuilder setParentCommentId(Integer parentCommentId) {
            this.parentCommentId = parentCommentId;
            return this;
        }

        /**
         * Sets the timestamp when the comment was created.
         * 
         * @param creationDate The timestamp when the comment was created.
         */
        public CommentDataBuilder setCreationDate(Timestamp creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Sets the timestamp when the comment was last modified.
         * 
         * @param lastModified The timestamp when the comment was last modified.
         */
        public CommentDataBuilder setLastModified(Timestamp lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        /**
         * Constructs a new CommentData with the specified details.
         * 
         * @return A new CommentData.
         */
        public CommentData build() {
            return new CommentData(id, content, authorId, postId, parentCommentId, creationDate, lastModified, username);
        }
    }

    /**
     * Gets the username of the author of the comment.
     * 
     * @return The username of the author of the comment.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the author of the comment.
     * 
     * @param username The username of the author of the comment.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
