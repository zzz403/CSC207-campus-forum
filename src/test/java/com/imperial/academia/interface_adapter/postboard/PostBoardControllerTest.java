package com.imperial.academia.interface_adapter.postboard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;
import com.imperial.academia.use_case.postBoard.PostBoardInputBoundary;

class PostBoardControllerTest {

    private ChangeViewInputBoundary changeViewInteractor;
    private PostBoardInputBoundary postBoardInteractor;
    private PostInputBoundary postInteractor;
    private PostBoardController postBoardController;

    @BeforeEach
    void setUp() {
        changeViewInteractor = mock(ChangeViewInputBoundary.class);
        postBoardInteractor = mock(PostBoardInputBoundary.class);
        postInteractor = mock(PostInputBoundary.class);
        postBoardController = new PostBoardController(changeViewInteractor, postBoardInteractor, postInteractor);
    }

    @Test
    void testConstructorInitializesInteractors() {
        assertNotNull(postBoardController);
    }

    @Test
    void testChangeView() {
        String viewName = "someView";
        postBoardController.changeView(viewName);
        verify(changeViewInteractor).changeView(viewName);
    }

    @Test
    void testFetchAllPostSuccess() {
        when(postBoardInteractor.fetchAllPost()).thenReturn(true);
        assertTrue(postBoardController.fetchAllPost());
        verify(postBoardInteractor).fetchAllPost();
    }

    @Test
    void testFetchAllPostFailure() {
        when(postBoardInteractor.fetchAllPost()).thenReturn(false);
        assertFalse(postBoardController.fetchAllPost());
        verify(postBoardInteractor).fetchAllPost();
    }

    @Test
    void testInitPostByIdSuccess() {
        int postId = 1;
        when(postInteractor.initPostById(postId)).thenReturn(true);

        assertTrue(postBoardController.initPostById(postId));
        verify(postInteractor).initPostById(postId);
        verify(changeViewInteractor).changeView("post");
    }

    @Test
    void testInitPostByIdFailure() {
        int postId = 1;
        when(postInteractor.initPostById(postId)).thenReturn(false);

        assertFalse(postBoardController.initPostById(postId));
        verify(postInteractor).initPostById(postId);
        verify(changeViewInteractor, never()).changeView("post");
    }
}
