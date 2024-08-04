package com.imperial.academia.interface_adapter.postboard;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.use_case.post.PostOverviewInfo;

class PostBoardPresenterTest {

    private PostBoardViewModel postBoardViewModel;
    private PostBoardPresenter postBoardPresenter;

    @BeforeEach
    void setUp() {
        postBoardViewModel = mock(PostBoardViewModel.class);
        postBoardPresenter = new PostBoardPresenter(postBoardViewModel);
    }

    @Test
    void testConstructorInitializesViewModel() {
        assertNotNull(postBoardPresenter);
    }

    @Test
    void testUpdatePostList() {
        List<PostOverviewInfo> postsInfo = Arrays.asList(
                PostOverviewInfo.builder().setPostID(1).setPostTitle("Title 1").setSummary("Summary 1")
                        .setUserName("Username 1")
                        .setAvatarURL("http://example.com/avatar1.jpg").setLikes(10).build(),
                PostOverviewInfo.builder().setPostID(2).setPostTitle("Title 2").setSummary("Summary 2")
                        .setUserName("Username 2")
                        .setAvatarURL("http://example.com/avatar2.jpg").setLikes(20).build());

        postBoardPresenter.updatePostList(postsInfo);

        verify(postBoardViewModel).setStatePostList(postsInfo);
    }

    @Test
    void testAddPost() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();

        postBoardPresenter.addPost(postOverviewInfo);

        verify(postBoardViewModel).addOnePostInfoToState(postOverviewInfo);
    }
}
