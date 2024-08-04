package com.imperial.academia.interface_adapter.createpost;

import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CreatePostViewModelTest {

    private CreatePostViewModel createPostViewModel;

    @BeforeEach
    void setUp() {
        createPostViewModel = new CreatePostViewModel();
    }

    @Test
    void testConstructorInitializesCorrectly() {
        assertNotNull(createPostViewModel);
        assertEquals("create post", createPostViewModel.getViewName());
    }

    @Test
    void testGetState() {
        CreatePostState state = createPostViewModel.getState();
        assertNotNull(state);
    }

    @Test
    void testSetState() {
        CreatePostState newState = new CreatePostState();
        newState.setTitle("Title");
        newState.setContent("Content");
        newState.setBoardsName(Arrays.asList("Board1", "Board2"));
        newState.setCurrentBoardName("Board1");

        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        createPostViewModel.addPropertyChangeListener(listener);

        createPostViewModel.setState(newState);

        assertNotEquals(newState, createPostViewModel.getState());
        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testFirePropertyChanged() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        createPostViewModel.addPropertyChangeListener(listener);

        createPostViewModel.firePropertyChanged();

        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }

    @Test
    void testSetStateTitle() {
        String newTitle = "New Title";
        createPostViewModel.setStateTitle(newTitle);
        assertEquals(newTitle, createPostViewModel.getStateTitle());
    }

    @Test
    void testSetStateContent() {
        String newContent = "New Content";
        createPostViewModel.setStateContent(newContent);
        assertEquals(newContent, createPostViewModel.getStateContent());
    }

    @Test
    void testSetStateCurrentBoardName() {
        String newBoardName = "New Board";
        createPostViewModel.setStateCurrentBoardName(newBoardName);
        assertEquals(newBoardName, createPostViewModel.getStateCurrentBoardName());
    }

    @Test
    void testGetStateCurrentBoardIndex() {
        List<String> boards = Arrays.asList("Board1", "Board2", "Board3");
        CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setBoardsName(boards);
        createPostViewModel.setState(createPostState);
        createPostViewModel.setStateCurrentBoardName("Board2");
        assertEquals(1, createPostViewModel.getStateCurrentBoardIndex());
    }

    @Test
    void testResetState() {
        createPostViewModel.setStateTitle("Title");
        createPostViewModel.setStateContent("Content");
        createPostViewModel.setStateCurrentBoardName("Board1");
        createPostViewModel.setStateIsSave(true);

        CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setBoardsName(Arrays.asList("Board1", "Board2"));
        createPostViewModel.setState(createPostState);

        createPostViewModel.resetState();

        assertEquals("", createPostViewModel.getStateTitle());
        assertEquals("", createPostViewModel.getStateContent());
        assertEquals("Board1", createPostViewModel.getStateCurrentBoardName());
        assertFalse(createPostViewModel.getStateIsSave());
    }

    @Test
    void testResetStateWithEmptyBoards() {
        createPostViewModel.setStateTitle("Title");
        createPostViewModel.setStateContent("Content");
        createPostViewModel.setStateCurrentBoardName("Board1");
        createPostViewModel.setStateIsSave(true);

        CreatePostState createPostState = createPostViewModel.getState();
        createPostState.setBoardsName(Collections.emptyList());
        createPostViewModel.setState(createPostState);

        createPostViewModel.resetState();

        assertEquals("", createPostViewModel.getStateTitle());
        assertEquals("", createPostViewModel.getStateContent());
        assertEquals("", createPostViewModel.getStateCurrentBoardName());
        assertFalse(createPostViewModel.getStateIsSave());
    }

    @Test
    void testSetStateIsSave() {
        createPostViewModel.setStateIsSave(true);
        assertTrue(createPostViewModel.getStateIsSave());

        createPostViewModel.setStateIsSave(false);
        assertFalse(createPostViewModel.getStateIsSave());
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener listener = Mockito.mock(PropertyChangeListener.class);
        createPostViewModel.addPropertyChangeListener(listener);

        createPostViewModel.firePropertyChanged();

        Mockito.verify(listener).propertyChange(Mockito.any(PropertyChangeEvent.class));
    }
}
