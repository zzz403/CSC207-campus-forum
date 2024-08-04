package com.imperial.academia.interface_adapter.postboard;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

import com.imperial.academia.use_case.post.PostOverviewInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PostBoardViewModelTest {

    private PostBoardViewModel postBoardViewModel;

    @BeforeEach
    void setUp() {
        postBoardViewModel = new PostBoardViewModel();
    }

    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(postBoardViewModel);
        assertEquals("post board", postBoardViewModel.getViewName());
    }

    @Test
    void testFirePropertyChanged() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postBoardViewModel.addPropertyChangeListener(listener);

        postBoardViewModel.firePropertyChanged();

        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStatePostList() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postBoardViewModel.addPropertyChangeListener(listener);

        List<PostOverviewInfo> postList = Arrays.asList(
                PostOverviewInfo.builder().setPostID(1).setPostTitle("Title 1").setSummary("Summary 1")
                        .setUserName("Username 1")
                        .setAvatarURL("http://example.com/avatar1.jpg").setLikes(10).build(),
                PostOverviewInfo.builder().setPostID(2).setPostTitle("Title 2").setSummary("Summary 2")
                        .setUserName("Username 2")
                        .setAvatarURL("http://example.com/avatar2.jpg").setLikes(20).build());
        postBoardViewModel.setStatePostList(postList);

        assertEquals(postList, postBoardViewModel.getPostInfoList());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testAddOnePostInfoToState() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postBoardViewModel.addPropertyChangeListener(listener);

        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();
        postBoardViewModel.addOnePostInfoToState(postOverviewInfo);

        List<PostOverviewInfo> postList = postBoardViewModel.getPostInfoList();
        assertEquals(1, postList.size());
        assertEquals(postOverviewInfo, postList.get(0));
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testIncrementStateLikesByPostId() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postBoardViewModel.addPropertyChangeListener(listener);

        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();
        postBoardViewModel.addOnePostInfoToState(postOverviewInfo);

        postBoardViewModel.incrementStateLikesByPostId(1);

        List<PostOverviewInfo> postList = postBoardViewModel.getPostInfoList();
        assertEquals(11, postList.get(0).getLikes());
    }

    @Test
    void testDecrementStateLikesByPostId() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postBoardViewModel.addPropertyChangeListener(listener);

        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();
        postBoardViewModel.addOnePostInfoToState(postOverviewInfo);

        postBoardViewModel.decrementStateLikesByPostId(1);

        List<PostOverviewInfo> postList = postBoardViewModel.getPostInfoList();
        assertEquals(9, postList.get(0).getLikes());
    }

    @Test
    void testGetPostLikesByPostId() {
        PostOverviewInfo postOverviewInfo = PostOverviewInfo.builder().setPostID(1).setPostTitle("Title")
                .setSummary("Summary").setUserName("Username")
                .setAvatarURL("http://example.com/avatar.jpg").setLikes(10).build();

        postBoardViewModel.addOnePostInfoToState(postOverviewInfo);

        int likes = postBoardViewModel.getPostLikesByPostId(1);

        assertEquals(10, likes);
    }

    @Test
    void testGetPostLikesByPostIdNonExistingPost() {
        int likes = postBoardViewModel.getPostLikesByPostId(1);

        assertEquals(0, likes);
    }
}
