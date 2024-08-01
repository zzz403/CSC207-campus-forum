package com.imperial.academia.entity.post;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class PostLikeTest {
    private PostLike postLike;


    @Test
    void postLike(){
        postLike = new PostLike(1,1,new Timestamp(1));
        postLike.setLikedAt(new Timestamp(111));
        postLike.setPostId(123);
        postLike.setUserId(12333);
        assertEquals(123, postLike.getPostId());
        assertEquals(12333, postLike.getUserId());
        assertEquals(new Timestamp(111), postLike.getLikedAt());
    }

}