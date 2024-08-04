package com.imperial.academia.use_case.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;

class PostInfoDataTest {

    @Test
    void testBuilderWithAllFields() {
        String title = "Test Title";
        String content = "Test Content";
        String username = "testuser";
        String avatarUrl = "http://example.com/avatar.jpg";
        Timestamp date = Timestamp.from(Instant.now());
        int likes = 100;
        int postID = 1;
        boolean isLiked = true;

        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(title)
                .setContent(content)
                .setUsername(username)
                .setAvatarUrl(avatarUrl)
                .setDate(date)
                .setLikes(likes)
                .setPostId(postID)
                .setIsLiked(isLiked)
                .build();

        assertEquals(title, postInfoData.getTitle());
        assertEquals(content, postInfoData.getContent());
        assertEquals(username, postInfoData.getUsername());
        assertEquals(avatarUrl, postInfoData.getAvatarUrl());
        assertEquals(date, postInfoData.getDate());
        assertEquals(likes, postInfoData.getLikes());
        assertEquals(postID, postInfoData.getPostID());
        assertTrue(postInfoData.isLiked());
    }

    @Test
    void testBuilderWithNullFields() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(null)
                .setContent(null)
                .setUsername(null)
                .setAvatarUrl(null)
                .setDate(null)
                .build();

        assertNull(postInfoData.getTitle());
        assertNull(postInfoData.getContent());
        assertNull(postInfoData.getUsername());
        assertNull(postInfoData.getAvatarUrl());
        assertNull(postInfoData.getDate());
    }

    @Test
    void testBuilderWithEmptyFields() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("")
                .setContent("")
                .setUsername("")
                .setAvatarUrl("")
                .setDate(null)
                .build();

        assertEquals("", postInfoData.getTitle());
        assertEquals("", postInfoData.getContent());
        assertEquals("", postInfoData.getUsername());
        assertEquals("", postInfoData.getAvatarUrl());
        assertNull(postInfoData.getDate());
    }

    @Test
    void testBuilderWithPartialFields() {
        String title = "Test Title";
        String username = "testuser";
        Timestamp date = Timestamp.from(Instant.now());

        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(title)
                .setUsername(username)
                .setDate(date)
                .build();

        assertEquals(title, postInfoData.getTitle());
        assertNull(postInfoData.getContent());
        assertEquals(username, postInfoData.getUsername());
        assertNull(postInfoData.getAvatarUrl());
        assertEquals(date, postInfoData.getDate());
    }

    @Test
    void testGettersWithSetFields() {
        String title = "Test Title";
        String content = "Test Content";
        String username = "testuser";
        String avatarUrl = "http://example.com/avatar.jpg";
        Timestamp date = Timestamp.from(Instant.now());
        int likes = 100;
        int postID = 1;
        boolean isLiked = true;

        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(title)
                .setContent(content)
                .setUsername(username)
                .setAvatarUrl(avatarUrl)
                .setDate(date)
                .setLikes(likes)
                .setPostId(postID)
                .setIsLiked(isLiked)
                .build();

        assertEquals(title, postInfoData.getTitle());
        assertEquals(content, postInfoData.getContent());
        assertEquals(username, postInfoData.getUsername());
        assertEquals(avatarUrl, postInfoData.getAvatarUrl());
        assertEquals(date, postInfoData.getDate());
        assertEquals(likes, postInfoData.getLikes());
        assertEquals(postID, postInfoData.getPostID());
        assertTrue(postInfoData.isLiked());
    }

    @Test
    void testSettersAndGetters() {
        PostInfoData postInfoData = new PostInfoData();
        String title = "Test Title";
        String content = "Test Content";
        String username = "testuser";
        String avatarUrl = "http://example.com/avatar.jpg";
        Timestamp date = Timestamp.from(Instant.now());
        int likes = 100;
        int postID = 1;
        boolean isLiked = true;

        postInfoData.setTitle(title);
        postInfoData.setContent(content);
        postInfoData.setUsername(username);
        postInfoData.setAvatarUrl(avatarUrl);
        postInfoData.setDate(date);
        postInfoData.setLikes(likes);
        postInfoData.setPostID(postID);
        postInfoData.setLiked(isLiked);

        assertEquals(title, postInfoData.getTitle());
        assertEquals(content, postInfoData.getContent());
        assertEquals(username, postInfoData.getUsername());
        assertEquals(avatarUrl, postInfoData.getAvatarUrl());
        assertEquals(date, postInfoData.getDate());
        assertEquals(likes, postInfoData.getLikes());
        assertEquals(postID, postInfoData.getPostID());
        assertTrue(postInfoData.isLiked());
    }

    @Test
    void testBuilderCreatesNewInstances() {
        PostInfoData postInfoData1 = PostInfoData.builder()
                .setTitle("Title 1")
                .setContent("Content 1")
                .setUsername("user1")
                .setAvatarUrl("http://example.com/avatar1.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .setLikes(10)
                .setPostId(1)
                .setIsLiked(true)
                .build();

        PostInfoData postInfoData2 = PostInfoData.builder()
                .setTitle("Title 2")
                .setContent("Content 2")
                .setUsername("user2")
                .setAvatarUrl("http://example.com/avatar2.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .setLikes(20)
                .setPostId(2)
                .setIsLiked(false)
                .build();

        assertEquals("Title 1", postInfoData1.getTitle());
        assertEquals("Content 1", postInfoData1.getContent());
        assertEquals("user1", postInfoData1.getUsername());
        assertEquals("http://example.com/avatar1.jpg", postInfoData1.getAvatarUrl());
        assertEquals(10, postInfoData1.getLikes());
        assertEquals(1, postInfoData1.getPostID());
        assertTrue(postInfoData1.isLiked());

        assertEquals("Title 2", postInfoData2.getTitle());
        assertEquals("Content 2", postInfoData2.getContent());
        assertEquals("user2", postInfoData2.getUsername());
        assertEquals("http://example.com/avatar2.jpg", postInfoData2.getAvatarUrl());
        assertEquals(20, postInfoData2.getLikes());
        assertEquals(2, postInfoData2.getPostID());
        assertFalse(postInfoData2.isLiked());
    }
}
