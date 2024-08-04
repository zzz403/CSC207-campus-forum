package com.imperial.academia.use_case.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentDataTest {

    private CommentData commentData;
    private CommentData.CommentDataBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new CommentData.CommentDataBuilder();
        commentData = new CommentData();
    }

    @Test
    void testBuilderInitialization() {
        int id = 1;
        String content = "This is a comment";
        int authorId = 2;
        String username = "user123";
        int postId = 3;
        Integer parentCommentId = 4;
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());

        commentData = builder
                .setId(id)
                .setContent(content)
                .setAuthorId(authorId)
                .setPostId(postId)
                .setParentCommentId(parentCommentId)
                .setCreationDate(creationDate)
                .setLastModified(lastModified)
                .setUsername(username)
                .build();

        assertEquals(id, commentData.getId());
        assertEquals(content, commentData.getContent());
        assertEquals(authorId, commentData.getAuthorId());
        assertEquals(postId, commentData.getPostId());
        assertEquals(parentCommentId, commentData.getParentCommentId());
        assertEquals(creationDate, commentData.getCreationDate());
        assertEquals(lastModified, commentData.getLastModified());
        assertEquals(username, commentData.getUsername());
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, commentData.getId());
        assertNull(commentData.getContent());
        assertEquals(0, commentData.getAuthorId());
        assertEquals(0, commentData.getPostId());
        assertNull(commentData.getParentCommentId());
        assertNull(commentData.getCreationDate());
        assertNull(commentData.getLastModified());
        assertNull(commentData.getUsername());
    }

    @Test
    void testSettersAndGetters() {
        int id = 1;
        String content = "This is a comment";
        int authorId = 2;
        String username = "user123";
        int postId = 3;
        Integer parentCommentId = 4;
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        Timestamp lastModified = new Timestamp(System.currentTimeMillis());

        commentData.setId(id);
        commentData.setContent(content);
        commentData.setAuthorId(authorId);
        commentData.setPostId(postId);
        commentData.setParentCommentId(parentCommentId);
        commentData.setCreationDate(creationDate);
        commentData.setLastModified(lastModified);
        commentData.setUsername(username);

        assertEquals(id, commentData.getId());
        assertEquals(content, commentData.getContent());
        assertEquals(authorId, commentData.getAuthorId());
        assertEquals(postId, commentData.getPostId());
        assertEquals(parentCommentId, commentData.getParentCommentId());
        assertEquals(creationDate, commentData.getCreationDate());
        assertEquals(lastModified, commentData.getLastModified());
        assertEquals(username, commentData.getUsername());
    }

    @Test
    void testBuilderWithPartialData() {
        int id = 1;
        String content = "Partial content";
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());

        commentData = builder
                .setId(id)
                .setContent(content)
                .setCreationDate(creationDate)
                .build();

        assertEquals(id, commentData.getId());
        assertEquals(content, commentData.getContent());
        assertEquals(creationDate, commentData.getCreationDate());
        assertEquals(0, commentData.getAuthorId());
        assertEquals(0, commentData.getPostId());
        assertNull(commentData.getParentCommentId());
        assertNull(commentData.getLastModified());
        assertNull(commentData.getUsername());
    }

    @Test
    void testEmptyBuilder() {
        commentData = builder.build();

        assertEquals(0, commentData.getId());
        assertNull(commentData.getContent());
        assertEquals(0, commentData.getAuthorId());
        assertEquals(0, commentData.getPostId());
        assertNull(commentData.getParentCommentId());
        assertNull(commentData.getCreationDate());
        assertNull(commentData.getLastModified());
        assertNull(commentData.getUsername());
    }
}
