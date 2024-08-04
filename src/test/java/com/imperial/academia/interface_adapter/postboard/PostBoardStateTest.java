package com.imperial.academia.interface_adapter.postboard;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.imperial.academia.use_case.post.PostOverviewInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostBoardStateTest {

    private PostBoardState postBoardState;

    @BeforeEach
    void setUp() {
        postBoardState = new PostBoardState();
    }

    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(postBoardState);
        assertTrue(postBoardState.getPostList().isEmpty());
    }

    @Test
    void testGetPostList() {
        List<PostOverviewInfo> postList = postBoardState.getPostList();
        assertNotNull(postList);
        assertTrue(postList.isEmpty());
    }

    @Test
    void testAddPost() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();
        postBoardState.addPost(postOverviewInfo);

        List<PostOverviewInfo> postList = postBoardState.getPostList();
        assertEquals(1, postList.size());
        assertEquals(postOverviewInfo, postList.get(0));
    }

    @Test
    void testSetPostList() {
        List<PostOverviewInfo> postList = Arrays.asList(
                PostOverviewInfo.builder().setPostID(1).setPostTitle("Title 1").setSummary("Summary 1")
                        .setUserName("Username 1")
                        .setAvatarURL("http://example.com/avatar1.jpg").setLikes(10).build(),
                PostOverviewInfo.builder().setPostID(2).setPostTitle("Title 2").setSummary("Summary 2")
                        .setUserName("Username 2")
                        .setAvatarURL("http://example.com/avatar2.jpg").setLikes(20).build());
        postBoardState.setPostList(postList);

        List<PostOverviewInfo> result = postBoardState.getPostList();
        assertEquals(2, result.size());
        assertEquals(postList, result);
    }

    @Test
    void testIncrementLikesByPostId() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();
        postBoardState.addPost(postOverviewInfo);

        postBoardState.incrementLikesByPostId(1);

        List<PostOverviewInfo> postList = postBoardState.getPostList();
        assertEquals(11, postList.get(0).getLikes());
    }

    @Test
    void testIncrementLikesByPostIdNonExistingPost() {
        postBoardState.incrementLikesByPostId(1);

        // Since no post exists, nothing should happen and there should be no
        // exceptions.
        List<PostOverviewInfo> postList = postBoardState.getPostList();
        assertTrue(postList.isEmpty());
    }

    @Test
    void testDecrementLikesByPostId() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();
        postBoardState.addPost(postOverviewInfo);

        postBoardState.decrementLikesByPostId(1);

        List<PostOverviewInfo> postList = postBoardState.getPostList();
        assertEquals(9, postList.get(0).getLikes());
    }

    @Test
    void testDecrementLikesByPostIdNonExistingPost() {
        postBoardState.decrementLikesByPostId(1);

        // Since no post exists, nothing should happen and there should be no
        // exceptions.
        List<PostOverviewInfo> postList = postBoardState.getPostList();
        assertTrue(postList.isEmpty());
    }

    @Test
    void testGetLikesByPostId() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();

        postBoardState.addPost(postOverviewInfo);

        int likes = postBoardState.getLikesByPostId(1);

        assertEquals(10, likes);
    }

    @Test
    void testGetLikesByPostIdNonExistingPost() {
        int likes = postBoardState.getLikesByPostId(1);

        assertEquals(0, likes);
    }
}
