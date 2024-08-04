package com.imperial.academia.interface_adapter.createpost;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.imperial.academia.use_case.createpost.CreatePostInputBoundary;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;
import com.imperial.academia.app.UsecaseFactory;

class CreatePostControllerTest {

    @Mock
    private CreatePostInputBoundary createPostInteractor;

    @InjectMocks
    private CreatePostController createPostController;

    private MockedStatic<UsecaseFactory> usecaseFactoryMockedStatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        usecaseFactoryMockedStatic = mockStatic(UsecaseFactory.class);
        usecaseFactoryMockedStatic.when(UsecaseFactory::getCreatePostInteractor).thenReturn(createPostInteractor);
    }

    @AfterEach
    void tearDown() {
        usecaseFactoryMockedStatic.close();
    }

    @Test
    void testDefaultConstructor() {
        CreatePostController controller = new CreatePostController();
        assertNotNull(controller);
    }

    @Test
    void testParameterizedConstructor() {
        CreatePostController controller = new CreatePostController(createPostInteractor);
        assertNotNull(controller);
    }

    @Test
    void testUpdateBoardNameSuccess() throws SQLException {
        createPostController.updateBoardName();
        verify(createPostInteractor).updateBoardsName();
    }

    @Test
    void testUpdateBoardNameSQLException() throws SQLException {
        doThrow(SQLException.class).when(createPostInteractor).updateBoardsName();

        assertThrows(SQLException.class, () -> {
            createPostController.updateBoardName();
        });

        verify(createPostInteractor).updateBoardsName();
    }

    @Test
    void testSubmitPostSuccess() {
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "Test Board";
        when(createPostInteractor.submitPost(title, content, boardName)).thenReturn(true);

        boolean result = createPostController.submitPost(title, content, boardName);

        assertTrue(result);
        verify(createPostInteractor).submitPost(title, content, boardName);
    }

    @Test
    void testSubmitPostFailure() {
        String title = "Test Title";
        String content = "Test Content";
        String boardName = "Test Board";
        when(createPostInteractor.submitPost(title, content, boardName)).thenReturn(false);

        boolean result = createPostController.submitPost(title, content, boardName);

        assertFalse(result);
        verify(createPostInteractor).submitPost(title, content, boardName);
    }

    @Test
    void testEnhanceContentUsingChatGPT() {
        String content = "Test Content";

        createPostController.enhanceContentUsingChatGPT(content);

        verify(createPostInteractor).enhanceContent(content);
    }
}
