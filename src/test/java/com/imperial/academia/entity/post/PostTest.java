package com.imperial.academia.entity.post;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {
    private Post post;

    @Test
    void post(){
        post = new Post(1,"","",1,
                1,new Timestamp(1), new Timestamp(1));
        Post newPost = Post.builder().setId(12).build();
        assertEquals(12,newPost.getId());
        post.setTitle("title");
        post.setContent("content");
        post.setAuthorId(123);
        post.setBoardId(1234);
        post.setCreationDate(new Timestamp(1111));
        post.setLastModifiedDate(new Timestamp(2222));
        assertEquals("title", post.getTitle());
        assertEquals("content", post.getContent());
        assertEquals(123, post.getAuthorId());
        assertEquals(1234, post.getBoardId());
        assertEquals(new Timestamp(1111), post.getCreationDate());
        assertEquals(new Timestamp(2222), post.getLastModifiedDate());
    }

}