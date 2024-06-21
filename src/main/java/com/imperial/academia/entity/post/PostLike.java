package com.imperial.academia.entity.post;

import java.sql.Timestamp;

public class PostLike {
    private int userId;
    private int postId;
    private Timestamp likedAt;

    public PostLike(int userId, int postId, Timestamp likedAt) {
        this.userId = userId;
        this.postId = postId;
        this.likedAt = likedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Timestamp getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Timestamp likedAt) {
        this.likedAt = likedAt;
    }
}
