package com.imperial.academia.interface_adapter.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.imperial.academia.use_case.post.CommentData;

class PostViewModelTest {

    private PostViewModel postViewModel;

    @BeforeEach
    void setUp() {
        postViewModel = new PostViewModel();
    }

    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(postViewModel);
        assertEquals("post", postViewModel.getViewName());
    }

    @Test
    void testFirePropertyChanged() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.firePropertyChanged();

        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateTitle() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStateTitle("New Title");

        assertEquals("New Title", postViewModel.getStateTitle());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateContent() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStateContent("New Content");

        assertEquals("New Content", postViewModel.getStateContent());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateUsername() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStateUsername("New User");

        assertEquals("New User", postViewModel.getStateUsername());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateAvatarUrl() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStateAvatarUrl("http://example.com/new_avatar.jpg");

        assertEquals("http://example.com/new_avatar.jpg", postViewModel.getStateAvatarUrl());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateDate() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        Timestamp newDate = new Timestamp(System.currentTimeMillis());
        postViewModel.setStateDate(newDate);

        assertEquals(newDate, postViewModel.getStateDate());
    }

    @Test
    void testSetStateLikes() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStateLikes(10);

        assertEquals(10, postViewModel.getStateLikes());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStatePostID() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStatePostID(123);

        assertEquals(123, postViewModel.getStatePostId());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testIncrementStateLikes() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.incrementStateLikes();

        assertEquals(1, postViewModel.getStateLikes());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testDecrementStateLikes() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.incrementStateLikes(); // Ensure likes > 0
        postViewModel.decrementStateLikes();

        assertEquals(0, postViewModel.getStateLikes());
    }

    @Test
    void testDecrementStateLikesNotNegative() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.decrementStateLikes(); // Ensure likes is not negative

        assertEquals(0, postViewModel.getStateLikes());
        Mockito.verify(listener, Mockito.never()).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateIsLiked() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        postViewModel.setStateIsLiked(true);

        assertTrue(postViewModel.getStateIsLiked());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateComments() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        List<CommentData> comments = Arrays.asList(
                CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(1).build(),
                CommentData.builder().setId(2).setContent("Comment 2").setUsername("User1")
                        .setLastModified(new Timestamp(1000)).setAuthorId(2).build()
        );
        postViewModel.setStateComments(comments);

        assertEquals(comments, postViewModel.getStateComments());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testAddStateComment() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        postViewModel.addPropertyChangeListener(listener);

        CommentData commentData = CommentData.builder().setId(1).setContent("Comment 1").setUsername("User1")
        .setLastModified(new Timestamp(1000)).setAuthorId(1).build();
        postViewModel.addStateComment(commentData);

        List<CommentData> comments = postViewModel.getStateComments();
        assertEquals(1, comments.size());
        assertEquals(commentData, comments.get(0));
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testGetFormattedStateDate() {
        Timestamp timestamp = Timestamp.valueOf("2023-01-01 00:00:00");
        postViewModel.setStateDate(timestamp);

        assertEquals("2023-01-01", postViewModel.getFormattedStateDate());
    }
}
