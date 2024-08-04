package com.imperial.academia.use_case.createpost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import com.imperial.academia.use_case.postBoard.PostBoardOutputBoundary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.BoardService;
import com.imperial.academia.service.PostService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.LLM.LLMInputBoundary;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.post.PostInfoData;
import com.imperial.academia.use_case.post.PostInputBoundary;

class CreatePostInteractorTest {

    @Mock
    private ChangeViewInputBoundary changeViewInteractor;

    @Mock
    private PostInputBoundary postInteractor;

    @Mock
    private LLMInputBoundary llmInteractor;

    @Mock
    private CreatePostOutputBoundary createPostPresenter;

    @Mock
    private BoardService boardService;

    @Mock
    private PostService postService;

    @Mock
    PostBoardOutputBoundary postBoardPresenter;

    @InjectMocks
    private CreatePostInteractor createPostInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createPostInteractor = new CreatePostInteractor(changeViewInteractor, postInteractor, llmInteractor,
                createPostPresenter, boardService, postService, postBoardPresenter);
    }

    @AfterEach
    void tearDown(){
        SessionManager.clearSession();
    }

    @Test
    void testUpdateBoardsName() throws SQLException {
        List<String> mockBoardNames = Arrays.asList("General", "Announcements");
        when(boardService.getAllBoardsName()).thenReturn(mockBoardNames);

        createPostInteractor.updateBoardsName();

        verify(createPostPresenter).updateBoardsName(mockBoardNames);
    }

    @Test
    void testUpdateBoardsNameSQLException() throws SQLException {
        when(boardService.getAllBoardsName()).thenThrow(new SQLException("Database error"));

        SQLException thrown = assertThrows(SQLException.class, () -> {
            createPostInteractor.updateBoardsName();
        });

        assertEquals("Database error", thrown.getMessage());
    }

    @Test
    void testSubmitPostSuccess() throws SQLException {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com", "user",
                "http://example.com/avatar.jpg", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        SessionManager.setCurrentUser(mockUser);
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "General";

        when(boardService.getBoardIdByName(boardName)).thenReturn(1);

        doNothing().when(postService).insert(any(Post.class));

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertTrue(result);
        verify(createPostPresenter).submitSeccuss();

        ArgumentCaptor<PostInfoData> captor = ArgumentCaptor.forClass(PostInfoData.class);
        verify(postInteractor).initPostPage(captor.capture());

        PostInfoData capturedPostInfoData = captor.getValue();

        assertEquals(title, capturedPostInfoData.getTitle());
        assertEquals(content, capturedPostInfoData.getContent());
        assertEquals(mockUser.getUsername(), capturedPostInfoData.getUsername());
        assertEquals(mockUser.getAvatarUrl(), capturedPostInfoData.getAvatarUrl());
        assertNotNull(capturedPostInfoData.getDate());

        verify(changeViewInteractor).changeView("post");
    }

    @Test
    void testSubmitPostBoardIdNotFound() throws SQLException {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com", "user",
                "http://example.com/avatar.jpg", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        SessionManager.setCurrentUser(mockUser);
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "General";

        when(boardService.getBoardIdByName(boardName)).thenThrow(new SQLException("Board not found"));

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostPresenter, never()).submitSeccuss();
    }

    @Test
    void testSubmitPostInsertSQLException() throws SQLException {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com", "user",
                "http://example.com/avatar.jpg", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        SessionManager.setCurrentUser(mockUser);
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "General";

        when(boardService.getBoardIdByName(boardName)).thenReturn(1);
        doThrow(new SQLException("Database insert error")).when(postService).insert(any(Post.class));

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostPresenter, never()).submitSeccuss();
    }

    @Test
    void testEnhanceContent() {
        String originalContent = "This is a test content.";
        String enhancedContent = "This is an enhanced test content.";

        when(llmInteractor.enhanceContent(originalContent)).thenReturn(enhancedContent);

        createPostInteractor.enhanceContent(originalContent);

        verify(createPostPresenter).updateContent(enhancedContent);
    }

    @Test
    void testSubmitPostWithNullTitle() throws SQLException {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com", "user",
                "http://example.com/avatar.jpg", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        SessionManager.setCurrentUser(mockUser);
        String title = null;
        String content = "Test Content";
        String boardName = "General";

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostPresenter, never()).submitSeccuss();
    }

    @Test
    void testSubmitPostWithEmptyContent() throws SQLException {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com", "user",
                "http://example.com/avatar.jpg", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        SessionManager.setCurrentUser(mockUser);
        String title = "Test Title";
        String content = "";
        String boardName = "General";

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostPresenter, never()).submitSeccuss();
    }

    @Test
    void testSubmitPostWithInvalidBoardName() throws SQLException {
        User mockUser = new User(1, "testuser", "password123", "testuser@example.com", "user",
                "http://example.com/avatar.jpg", Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        SessionManager.setCurrentUser(mockUser);
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "InvalidBoard";

        when(boardService.getBoardIdByName(boardName)).thenThrow(new SQLException("Board not found"));

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostPresenter, never()).submitSeccuss();
    }

    @Test
    void testSubmitPostWithoutUserSession() throws SQLException {
        SessionManager.setCurrentUser(null);
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "General";

        boolean result = createPostInteractor.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostPresenter, never()).submitSeccuss();
    }

    @Test
    void testEnhanceContentWithEmptyString() {
        String originalContent = "";
        String enhancedContent = "";

        when(llmInteractor.enhanceContent(originalContent)).thenReturn(enhancedContent);

        createPostInteractor.enhanceContent(originalContent);

        verify(createPostPresenter).updateContent(enhancedContent);
    }

    @Test
    void testEnhanceContentWithNullString() {
        String originalContent = null;
        String enhancedContent = null;

        when(llmInteractor.enhanceContent(originalContent)).thenReturn(enhancedContent);

        createPostInteractor.enhanceContent(originalContent);

        verify(createPostPresenter).updateContent(enhancedContent);
    }

}
