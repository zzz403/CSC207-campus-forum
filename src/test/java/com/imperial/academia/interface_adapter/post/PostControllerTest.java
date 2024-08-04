package com.imperial.academia.interface_adapter.post;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.post.PostInputBoundary;

class PostControllerTest {

    @Mock
    private PostInputBoundary postInteractor;

    @InjectMocks
    private PostController postController;

    private MockedStatic<SessionManager> sessionManagerMockedStatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sessionManagerMockedStatic = mockStatic(SessionManager.class);
    }

    @AfterEach
    void tearDown() {
        sessionManagerMockedStatic.close();
    }

    @Test
    void testConstructorInitializesInteractor() {
        assertNotNull(postController);
    }

    @Test
    void testLikeClickWithUserNotLoggedIn() {
        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);

        postController.likeClick(1);

        verify(postInteractor, never()).checkLiked(anyInt(), anyInt());
        verify(postInteractor, never()).addLike(anyInt(), anyInt());
        verify(postInteractor, never()).removeLike(anyInt(), anyInt());
    }

    @Test
    void testLikeClickWithUserLoggedInAndNotLikedPost() {
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);
        when(postInteractor.checkLiked(1, 1)).thenReturn(false);

        postController.likeClick(1);

        verify(postInteractor).checkLiked(1, 1);
        verify(postInteractor).addLike(1, 1);
        verify(postInteractor, never()).removeLike(anyInt(), anyInt());
    }

    @Test
    void testLikeClickWithUserLoggedInAndLikedPost() {
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);
        when(postInteractor.checkLiked(1, 1)).thenReturn(true);

        postController.likeClick(1);

        verify(postInteractor).checkLiked(1, 1);
        verify(postInteractor).removeLike(1, 1);
        verify(postInteractor, never()).addLike(anyInt(), anyInt());
    }

    @Test
    void testPostCommentWithUserNotLoggedIn() {
        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);

        postController.postComment(1, "Test Comment");

        verify(postInteractor, never()).postComment(anyInt(), anyInt(), anyString());
    }

    @Test
    void testPostCommentWithUserLoggedIn() {
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);

        postController.postComment(1, "Test Comment");

        verify(postInteractor).postComment(1, 1, "Test Comment");
    }
}
