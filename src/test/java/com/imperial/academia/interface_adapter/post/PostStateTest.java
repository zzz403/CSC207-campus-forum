package com.imperial.academia.interface_adapter.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.use_case.post.CommentData;

class PostStateTest {

    private PostState postState;

    @BeforeEach
    void setUp() {
        postState = new PostState();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals("", postState.getTitle());
        assertEquals("", postState.getContent());
        assertNotNull(postState.getDate());
        assertEquals("", postState.getUsername());
        assertEquals("", postState.getAvatarUrl());
        assertEquals(0, postState.getLikes());
        assertEquals(1, postState.getPostID());
        assertFalse(postState.isLiked());
        assertTrue(postState.getComments().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        postState.setTitle("Title");
        postState.setContent("Content");
        postState.setDate(new Timestamp(1000));
        postState.setUsername("User");
        postState.setAvatarUrl("http://example.com/avatar.jpg");
        postState.setLikes(10);
        postState.setPostID(2);
        postState.setLiked(true);
        List<CommentData> comments = Arrays.asList(
                CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(1).build(),
                CommentData.builder().setId(2).setContent("Comment 2").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(2).build());
        postState.setComments(comments);

        PostState copiedState = new PostState(postState);

        assertEquals("Title", copiedState.getTitle());
        assertEquals("Content", copiedState.getContent());
        assertEquals(new Timestamp(1000), copiedState.getDate());
        assertEquals("User", copiedState.getUsername());
        assertEquals("http://example.com/avatar.jpg", copiedState.getAvatarUrl());
        assertEquals(10, copiedState.getLikes());
        assertEquals(2, copiedState.getPostID());
        assertTrue(copiedState.isLiked());
        assertNotEquals(comments, copiedState.getComments());
    }

    @Test
    void testSetTitle() {
        postState.setTitle("New Title");
        assertEquals("New Title", postState.getTitle());
    }

    @Test
    void testSetContent() {
        postState.setContent("New Content");
        assertEquals("New Content", postState.getContent());
    }

    @Test
    void testSetDate() {
        Timestamp newDate = new Timestamp(1000);
        postState.setDate(newDate);
        assertEquals(newDate, postState.getDate());
    }

    @Test
    void testSetUsername() {
        postState.setUsername("New User");
        assertEquals("New User", postState.getUsername());
    }

    @Test
    void testSetAvatarUrl() {
        postState.setAvatarUrl("http://example.com/new_avatar.jpg");
        assertEquals("http://example.com/new_avatar.jpg", postState.getAvatarUrl());
    }

    @Test
    void testSetLikes() {
        postState.setLikes(20);
        assertEquals(20, postState.getLikes());
    }

    @Test
    void testSetPostID() {
        postState.setPostID(3);
        assertEquals(3, postState.getPostID());
    }

    @Test
    void testSetLiked() {
        postState.setLiked(true);
        assertTrue(postState.isLiked());

        postState.setLiked(false);
        assertFalse(postState.isLiked());
    }

    @Test
    void testSetComments() {
        List<CommentData> comments = Arrays.asList(
                CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(1).build(),
                CommentData.builder().setId(2).setContent("Comment 2").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(2).build());
        postState.setComments(comments);
        assertEquals(comments, postState.getComments());
    }

    @Test
    void testAddComment() {
        CommentData commentData = CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
                .setLastModified(new Timestamp(1000)).setAuthorId(1).build();
        postState.addComment(commentData);

        List<CommentData> comments = postState.getComments();
        assertEquals(1, comments.size());
        assertEquals(commentData, comments.get(0));
    }
}
