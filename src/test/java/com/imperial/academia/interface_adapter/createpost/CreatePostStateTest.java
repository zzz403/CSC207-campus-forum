package com.imperial.academia.interface_adapter.createpost;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreatePostStateTest {

    private CreatePostState createPostState;

    @BeforeEach
    void setUp() {
        createPostState = new CreatePostState();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals("", createPostState.getTitle());
        assertEquals("", createPostState.getContent());
        assertEquals("", createPostState.getCurrentBoardName());
        assertEquals(Collections.emptyList(), createPostState.getBoardsName());
        assertFalse(createPostState.getIsSave());
    }

    @Test
    void testCopyConstructor() {
        createPostState.setTitle("Title");
        createPostState.setContent("Content");
        createPostState.setCurrentBoardName("Board1");
        createPostState.setBoardsName(Arrays.asList("Board1", "Board2"));
        createPostState.setIsSave(true);

        CreatePostState copiedState = new CreatePostState(createPostState);

        assertEquals("Title", copiedState.getTitle());
        assertEquals("Content", copiedState.getContent());
        assertEquals("Board1", copiedState.getCurrentBoardName());
        assertEquals(Arrays.asList("Board1", "Board2"), copiedState.getBoardsName());
        assertTrue(copiedState.getIsSave());
    }

    @Test
    void testSetTitle() {
        createPostState.setTitle("New Title");
        assertEquals("New Title", createPostState.getTitle());
    }

    @Test
    void testSetContent() {
        createPostState.setContent("New Content");
        assertEquals("New Content", createPostState.getContent());
    }

    @Test
    void testSetCurrentBoardName() {
        createPostState.setCurrentBoardName("New Board");
        assertEquals("New Board", createPostState.getCurrentBoardName());
    }

    @Test
    void testSetBoardsName() {
        List<String> boards = Arrays.asList("Board1", "Board2");
        createPostState.setBoardsName(boards);
        assertEquals(boards, createPostState.getBoardsName());
    }

    @Test
    void testSetIsSave() {
        createPostState.setIsSave(true);
        assertTrue(createPostState.getIsSave());

        createPostState.setIsSave(false);
        assertFalse(createPostState.getIsSave());
    }

    @Test
    void testGetCurrentBoardIndex() {
        List<String> boards = Arrays.asList("Board1", "Board2", "Board3");
        createPostState.setBoardsName(boards);
        createPostState.setCurrentBoardName("Board2");

        assertEquals(1, createPostState.getCurrentBoardIndex());
    }

    @Test
    void testGetCurrentBoardIndexNotFound() {
        List<String> boards = Arrays.asList("Board1", "Board2", "Board3");
        createPostState.setBoardsName(boards);
        createPostState.setCurrentBoardName("Board4");

        assertEquals(-1, createPostState.getCurrentBoardIndex());
    }
}

