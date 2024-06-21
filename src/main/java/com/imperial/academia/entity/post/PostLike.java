package com.imperial.academia.entity.post;

import java.sql.Timestamp;

/**
 * The PostLike class represents a like on a post by a user.
 */
public class PostLike {
    private int userId;
    private int postId;
    private Timestamp likedAt;

    /**
     * Constructs a new PostLike with the specified details.
     * 
     * @param userId The ID of the user who liked the post.
     * @param postId The ID of the post that was liked.
     * @param likedAt The timestamp when the post was liked.
     */
    public PostLike(int userId, int postId, Timestamp likedAt) {
        this.userId = userId;
        this.postId = postId;
        this.likedAt = likedAt;
    }

    // Getters and Setters

    /**
     * Gets the ID of the user who liked the post.
     * 
     * @return The ID of the user who liked the post.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who liked the post.
     * 
     * @param userId The ID of the user who liked the post.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the post that was liked.
     * 
     * @return The ID of the post that was liked.
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Sets the ID of the post that was liked.
     * 
     * @param postId The ID of the post that was liked.
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     * Gets the timestamp when the post was liked.
     * 
     * @return The timestamp when the post was liked.
     */
    public Timestamp getLikedAt() {
        return likedAt;
    }

    /**
     * Sets the timestamp when the post was liked.
     * 
     * @param likedAt The timestamp when the post was liked.
     */
    public void setLikedAt(Timestamp likedAt) {
        this.likedAt = likedAt;
    }
}
