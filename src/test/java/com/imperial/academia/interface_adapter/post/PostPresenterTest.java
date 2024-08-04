package com.imperial.academia.interface_adapter.post;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.interface_adapter.postboard.PostBoardViewModel;
import com.imperial.academia.use_case.post.CommentData;
import com.imperial.academia.use_case.post.PostInfoData;

class PostPresenterTest {

    private PostViewModel postViewModel;
    private PostBoardViewModel postBoardViewModel;
    private PostPresenter postPresenter;

    @BeforeEach
    void setUp() {
        postViewModel = mock(PostViewModel.class);
        postBoardViewModel = mock(PostBoardViewModel.class);
        postPresenter = new PostPresenter(postViewModel, postBoardViewModel);
    }

    @Test
    void testConstructorInitializesViewModel() {
        assertNotNull(postPresenter);
    }

    @Test
    void testInitPostPage() {
        PostInfoData postInfoData = mock(PostInfoData.class);
        when(postInfoData.getTitle()).thenReturn("Title");
        when(postInfoData.getContent()).thenReturn("Content");
        when(postInfoData.getUsername()).thenReturn("Username");
        when(postInfoData.getAvatarUrl()).thenReturn("http://example.com/avatar.jpg");
        when(postInfoData.getDate()).thenReturn(new Timestamp(1000));
        when(postInfoData.getLikes()).thenReturn(10);
        when(postInfoData.getPostID()).thenReturn(1);
        when(postInfoData.isLiked()).thenReturn(true);

        postPresenter.initPostPage(postInfoData);

        verify(postViewModel).setStateTitle("Title");
        verify(postViewModel).setStateContent("Content");
        verify(postViewModel).setStateUsername("Username");
        verify(postViewModel).setStateAvatarUrl("http://example.com/avatar.jpg");
        verify(postViewModel).setStateDate(new Timestamp(1000));
        verify(postViewModel).setStateLikes(10);
        verify(postViewModel).setStatePostID(1);
        verify(postViewModel).setStateIsLiked(true);
    }

    @Test
    void testAddLike() {
        int postId = 1;

        postPresenter.addLike(postId);

        verify(postViewModel).incrementStateLikes();
        verify(postViewModel).setStateIsLiked(true);
        verify(postBoardViewModel).incrementStateLikesByPostId(postId);
    }

    @Test
    void testRemoveLike() {
        int postId = 1;

        postPresenter.removeLike(postId);

        verify(postViewModel).decrementStateLikes();
        verify(postViewModel).setStateIsLiked(false);
        verify(postBoardViewModel).decrementStateLikesByPostId(postId);
    }

    @Test
    void testInitComments() {
        List<CommentData> comments = Arrays.asList(
                CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(1).build(),
                CommentData.builder().setId(2).setContent("Comment 2").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(2).build()
        );

        postPresenter.initComments(comments);

        verify(postViewModel).setStateComments(comments);
    }

    @Test
    void testAddPost() {
        CommentData commentData = CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
        .setLastModified(new Timestamp(1000)).setAuthorId(1).build();
        
        postPresenter.addPost(commentData);

        verify(postViewModel).addStateComment(commentData);
    }
}
