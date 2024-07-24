package com.imperial.academia.use_case.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(title)
                .setContent(content)
                .setUsername(username)
                .setAvatarUrl(avatarUrl)
                .setDate(date)
                .build();

        assertEquals(title, postInfoData.getTitle());
        assertEquals(content, postInfoData.getContent());
        assertEquals(username, postInfoData.getUsername());
        assertEquals(avatarUrl, postInfoData.getAvatarUrl());
        assertEquals(date, postInfoData.getDate());
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

        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(title)
                .setContent(content)
                .setUsername(username)
                .setAvatarUrl(avatarUrl)
                .setDate(date)
                .build();

        assertEquals(title, postInfoData.getTitle());
        assertEquals(content, postInfoData.getContent());
        assertEquals(username, postInfoData.getUsername());
        assertEquals(avatarUrl, postInfoData.getAvatarUrl());
        assertEquals(date, postInfoData.getDate());
    }

    @Test
    void testBuilderCreatesNewInstances() {
        PostInfoData postInfoData1 = PostInfoData.builder()
                .setTitle("Title 1")
                .setContent("Content 1")
                .setUsername("user1")
                .setAvatarUrl("http://example.com/avatar1.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        PostInfoData postInfoData2 = PostInfoData.builder()
                .setTitle("Title 2")
                .setContent("Content 2")
                .setUsername("user2")
                .setAvatarUrl("http://example.com/avatar2.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        assertEquals("Title 1", postInfoData1.getTitle());
        assertEquals("Content 1", postInfoData1.getContent());
        assertEquals("user1", postInfoData1.getUsername());
        assertEquals("http://example.com/avatar1.jpg", postInfoData1.getAvatarUrl());

        assertEquals("Title 2", postInfoData2.getTitle());
        assertEquals("Content 2", postInfoData2.getContent());
        assertEquals("user2", postInfoData2.getUsername());
        assertEquals("http://example.com/avatar2.jpg", postInfoData2.getAvatarUrl());
    }
}
