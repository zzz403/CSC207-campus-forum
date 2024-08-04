package com.imperial.academia.use_case.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class PostOverviewInfoTest {

    @Test
    void testBuilderWithAllFields() {
        int postID = 1;
        String postTitle = "Test Title";
        String summary = "Test Summary";
        String userName = "testuser";
        String avatarURL = "http://example.com/avatar.jpg";
        int likes = 100;
        String postImgURL = "http://example.com/post.jpg";

        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder()
                .setPostID(postID)
                .setPostTitle(postTitle)
                .setSummary(summary)
                .setUserName(userName)
                .setAvatarURL(avatarURL)
                .setLikes(likes)
                .setPostImgURL(postImgURL)
                .build();

        assertEquals(postID, postOverviewInfo.getPostID());
        assertEquals(postTitle, postOverviewInfo.getPostTitle());
        assertEquals(summary, postOverviewInfo.getSummary());
        assertEquals(userName, postOverviewInfo.getUserName());
        assertEquals(avatarURL, postOverviewInfo.getAvatarURL());
        assertEquals(likes, postOverviewInfo.getLikes());
        assertEquals(postImgURL, postOverviewInfo.getPostImgURL());
    }

    @Test
    void testBuilderWithNullFields() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder()
                .setPostID(0)
                .setPostTitle(null)
                .setSummary(null)
                .setUserName(null)
                .setAvatarURL(null)
                .setLikes(0)
                .setPostImgURL(null)
                .build();

        assertEquals(0, postOverviewInfo.getPostID());
        assertNull(postOverviewInfo.getPostTitle());
        assertNull(postOverviewInfo.getSummary());
        assertNull(postOverviewInfo.getUserName());
        assertNull(postOverviewInfo.getAvatarURL());
        assertEquals(0, postOverviewInfo.getLikes());
        assertNull(postOverviewInfo.getPostImgURL());
    }

    @Test
    void testBuilderWithEmptyFields() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder()
                .setPostID(0)
                .setPostTitle("")
                .setSummary("")
                .setUserName("")
                .setAvatarURL("")
                .setLikes(0)
                .setPostImgURL("")
                .build();

        assertEquals(0, postOverviewInfo.getPostID());
        assertEquals("", postOverviewInfo.getPostTitle());
        assertEquals("", postOverviewInfo.getSummary());
        assertEquals("", postOverviewInfo.getUserName());
        assertEquals("", postOverviewInfo.getAvatarURL());
        assertEquals(0, postOverviewInfo.getLikes());
        assertEquals("", postOverviewInfo.getPostImgURL());
    }

    @Test
    void testBuilderWithPartialFields() {
        int postID = 1;
        String postTitle = "Test Title";
        int likes = 100;

        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder()
                .setPostID(postID)
                .setPostTitle(postTitle)
                .setLikes(likes)
                .build();

        assertEquals(postID, postOverviewInfo.getPostID());
        assertEquals(postTitle, postOverviewInfo.getPostTitle());
        assertNull(postOverviewInfo.getSummary());
        assertNull(postOverviewInfo.getUserName());
        assertEquals("resources\\avatar\\default_avatar.png", postOverviewInfo.getAvatarURL());
        assertEquals(likes, postOverviewInfo.getLikes());
        assertNull(postOverviewInfo.getPostImgURL());
    }

    @Test
    void testGettersWithSetFields() {
        int postID = 1;
        String postTitle = "Test Title";
        String summary = "Test Summary";
        String userName = "testuser";
        String avatarURL = "http://example.com/avatar.jpg";
        int likes = 100;
        String postImgURL = "http://example.com/post.jpg";

        PostOverviewInfo postOverviewInfo = new PostOverviewInfo();
        postOverviewInfo.setPostID(postID);
        postOverviewInfo.setPostTitle(postTitle);
        postOverviewInfo.setSummary(summary);
        postOverviewInfo.setUserName(userName);
        postOverviewInfo.setAvatarURL(avatarURL);
        postOverviewInfo.setLikes(likes);
        postOverviewInfo.setPostImgURL(postImgURL);

        assertEquals(postID, postOverviewInfo.getPostID());
        assertEquals(postTitle, postOverviewInfo.getPostTitle());
        assertEquals(summary, postOverviewInfo.getSummary());
        assertEquals(userName, postOverviewInfo.getUserName());
        assertEquals(avatarURL, postOverviewInfo.getAvatarURL());
        assertEquals(likes, postOverviewInfo.getLikes());
        assertEquals(postImgURL, postOverviewInfo.getPostImgURL());
    }

    @Test
    void testDefaultConstructor() {
        PostOverviewInfo postOverviewInfo = new PostOverviewInfo();

        assertEquals("resources\\avatar\\default_avatar.png", postOverviewInfo.getAvatarURL());
        assertNull(postOverviewInfo.getPostTitle());
        assertNull(postOverviewInfo.getSummary());
        assertNull(postOverviewInfo.getUserName());
        assertNull(postOverviewInfo.getPostImgURL());
        assertEquals(0, postOverviewInfo.getPostID());
        assertEquals(0, postOverviewInfo.getLikes());
    }

    @Test
    void testBuilderCreatesNewInstances() {
        PostOverviewInfo postOverviewInfo1 = PostOverviewInfo.builder()
                .setPostID(1)
                .setPostTitle("Title 1")
                .setSummary("Summary 1")
                .setUserName("user1")
                .setAvatarURL("http://example.com/avatar1.jpg")
                .setLikes(10)
                .setPostImgURL("http://example.com/post1.jpg")
                .build();

        PostOverviewInfo postOverviewInfo2 = PostOverviewInfo.builder()
                .setPostID(2)
                .setPostTitle("Title 2")
                .setSummary("Summary 2")
                .setUserName("user2")
                .setAvatarURL("http://example.com/avatar2.jpg")
                .setLikes(20)
                .setPostImgURL("http://example.com/post2.jpg")
                .build();

        assertEquals(1, postOverviewInfo1.getPostID());
        assertEquals("Title 1", postOverviewInfo1.getPostTitle());
        assertEquals("Summary 1", postOverviewInfo1.getSummary());
        assertEquals("user1", postOverviewInfo1.getUserName());
        assertEquals("http://example.com/avatar1.jpg", postOverviewInfo1.getAvatarURL());
        assertEquals(10, postOverviewInfo1.getLikes());
        assertEquals("http://example.com/post1.jpg", postOverviewInfo1.getPostImgURL());

        assertEquals(2, postOverviewInfo2.getPostID());
        assertEquals("Title 2", postOverviewInfo2.getPostTitle());
        assertEquals("Summary 2", postOverviewInfo2.getSummary());
        assertEquals("user2", postOverviewInfo2.getUserName());
        assertEquals("http://example.com/avatar2.jpg", postOverviewInfo2.getAvatarURL());
        assertEquals(20, postOverviewInfo2.getLikes());
        assertEquals("http://example.com/post2.jpg", postOverviewInfo2.getPostImgURL());
    }
}
