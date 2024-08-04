package com.imperial.academia.interface_adapter.createpost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreatePostPresenterTest {

    private CreatePostViewModel createPostViewModel;
    private CreatePostPresenter createPostPresenter;

    @BeforeEach
    void setUp() {
        createPostViewModel = mock(CreatePostViewModel.class);
        createPostPresenter = new CreatePostPresenter(createPostViewModel);
    }

    @Test
    void testConstructorInitializesViewModel() {
        assertNotNull(createPostPresenter);
    }

    @Test
    void testUpdateBoardsName() {
        List<String> boardsName = Arrays.asList("Board1", "Board2");
        CreatePostState state = new CreatePostState();
        when(createPostViewModel.getState()).thenReturn(state);

        createPostPresenter.updateBoardsName(boardsName);

        assertEquals(boardsName, state.getBoardsName());
        verify(createPostViewModel).setState(state);
    }

    @Test
    void testUpdateBoardsNameEmptyList() {
        List<String> boardsName = Collections.emptyList();
        CreatePostState state = new CreatePostState();
        when(createPostViewModel.getState()).thenReturn(state);

        createPostPresenter.updateBoardsName(boardsName);

        assertEquals(boardsName, state.getBoardsName());
        verify(createPostViewModel).setState(state);
    }

    @Test
    void testSubmitSeccuss() {
        createPostPresenter.submitSeccuss();

        verify(createPostViewModel).resetState();
    }

    @Test
    void testUpdateContent() {
        String content = "Updated content";

        createPostPresenter.updateContent(content);

        verify(createPostViewModel).setStateContent(content);
    }

    @Test
    void testUpdateContentEmpty() {
        String content = "";

        createPostPresenter.updateContent(content);

        verify(createPostViewModel).setStateContent(content);
    }

    @Test
    void testUpdateContentNull() {
        createPostPresenter.updateContent(null);

        verify(createPostViewModel).setStateContent(null);
    }
}
