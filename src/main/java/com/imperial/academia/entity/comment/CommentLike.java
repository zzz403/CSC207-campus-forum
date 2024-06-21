package com.imperial.academia.entity.comment;

import java.sql.Timestamp;

public class CommentLike {
    private int userId;
    private int commentId;
    private Timestamp likedAt;

    public CommentLike(int userId, int commentId, Timestamp likedAt) {
        this.userId = userId;
        this.commentId = commentId;
        this.likedAt = likedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Timestamp getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Timestamp likedAt) {
        this.likedAt = likedAt;
    }
}
