package com.imperial.academia.entity.comment;

import java.sql.Timestamp;

/**
 * The CommentLike class represents a like on a comment by a user.
 */
public class CommentLike {
    private int userId;
    private int commentId;
    private Timestamp likedAt;

    /**
     * Constructs a new CommentLike with the specified details.
     * 
     * @param userId The ID of the user who liked the comment.
     * @param commentId The ID of the comment that was liked.
     * @param likedAt The timestamp when the comment was liked.
     */
    public CommentLike(int userId, int commentId, Timestamp likedAt) {
        this.userId = userId;
        this.commentId = commentId;
        this.likedAt = likedAt;
    }

    // Getters and Setters

    /**
     * Gets the ID of the user who liked the comment.
     * 
     * @return The ID of the user who liked the comment.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who liked the comment.
     * 
     * @param userId The ID of the user who liked the comment.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the comment that was liked.
     * 
     * @return The ID of the comment that was liked.
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * Sets the ID of the comment that was liked.
     * 
     * @param commentId The ID of the comment that was liked.
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    /**
     * Gets the timestamp when the comment was liked.
     * 
     * @return The timestamp when the comment was liked.
     */
    public Timestamp getLikedAt() {
        return likedAt;
    }

    /**
     * Sets the timestamp when the comment was liked.
     * 
     * @param likedAt The timestamp when the comment was liked.
     */
    public void setLikedAt(Timestamp likedAt) {
        this.likedAt = likedAt;
    }
}
