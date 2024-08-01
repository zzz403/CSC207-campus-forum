package com.imperial.academia.entity.comment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class CommentLikeTest {
    private CommentLike commentLike;

    @BeforeEach
    public void init(){
        commentLike = new CommentLike(1,1,new Timestamp(1));
    }

    @Test
    void setUserId() {
        commentLike.setUserId(12);
        assertEquals(12, commentLike.getUserId());
    }

    @Test
    void setCommentId() {
        commentLike.setCommentId(123);
        assertEquals(123,commentLike.getCommentId());
    }

    @Test
    void setLikedAt() {
        commentLike.setLikedAt(new Timestamp(123));
        assertEquals(new Timestamp(123),commentLike.getLikedAt());
    }
}