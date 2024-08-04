package com.imperial.academia.use_case.topnav;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedStatic;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.service.PostService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;

class TopNavInteractorTest {

    @Mock
    private PostService postService;

    @Mock
    private PostInputBoundary postInteractor;

    @Mock
    private ChangeViewInputBoundary changeViewInteractor;

    @InjectMocks
    private TopNavInteractor topNavInteractor;

    private MockedStatic<ServiceFactory> serviceFactoryMockedStatic;
    private MockedStatic<UsecaseFactory> usecaseFactoryMockedStatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the static ServiceFactory and UsecaseFactory methods
        serviceFactoryMockedStatic = mockStatic(ServiceFactory.class);
        usecaseFactoryMockedStatic = mockStatic(UsecaseFactory.class);

        serviceFactoryMockedStatic.when(ServiceFactory::getPostService).thenReturn(postService);
        usecaseFactoryMockedStatic.when(UsecaseFactory::getPostInteractor).thenReturn(postInteractor);
        usecaseFactoryMockedStatic.when(UsecaseFactory::getChangeViewInteractor).thenReturn(changeViewInteractor);
    }

    @AfterEach
    void tearDown() {
        serviceFactoryMockedStatic.close();
        usecaseFactoryMockedStatic.close();
    }

    @Test
    void testDefaultConstructor() {
        TopNavInteractor interactor = new TopNavInteractor();
        assertNotNull(interactor);
    }

    @Test
    void testParameterizedConstructor() {
        TopNavInteractor interactor = new TopNavInteractor(postService, postInteractor, changeViewInteractor);
        assertNotNull(interactor);
    }

    @Test
    void testSearchPostByTitleSuccess() throws SQLException {
        String title = "Test Title";
        Post post = mock(Post.class);
        when(postService.getReleventPostByTitle(title)).thenReturn(post);
        when(post.getId()).thenReturn(1);

        topNavInteractor.searchPostByTitle(title);

        verify(postService).getReleventPostByTitle(title);
        verify(postInteractor).initPostById(1);
        verify(changeViewInteractor).changeView("post");
    }

    @Test
    void testSearchPostByTitleSQLException() throws SQLException {
        String title = "Test Title";
        when(postService.getReleventPostByTitle(title)).thenThrow(SQLException.class);

        topNavInteractor.searchPostByTitle(title);

        verify(postService).getReleventPostByTitle(title);
        verify(postInteractor, never()).initPostById(anyInt());
        verify(changeViewInteractor, never()).changeView(anyString());
    }

    @Test
    void testSearchPostByTitlePostNotFound() throws SQLException {
        String title = "Test Title";
        when(postService.getReleventPostByTitle(title)).thenReturn(null);

        topNavInteractor.searchPostByTitle(title);

        verify(postService).getReleventPostByTitle(title);
        verify(postInteractor, never()).initPostById(anyInt());
        verify(changeViewInteractor, never()).changeView(anyString());
    }

    @Test
    void testSearchPostByTitleEmptyTitle() throws SQLException {
        String title = "";
        when(postService.getReleventPostByTitle(title)).thenReturn(null);

        topNavInteractor.searchPostByTitle(title);

        verify(postService).getReleventPostByTitle(title);
        verify(postInteractor, never()).initPostById(anyInt());
        verify(changeViewInteractor, never()).changeView(anyString());
    }

    @Test
    void testSearchPostByTitleWithWhitespace() throws SQLException {
        String title = "   ";
        when(postService.getReleventPostByTitle(title)).thenReturn(null);

        topNavInteractor.searchPostByTitle(title);

        verify(postService).getReleventPostByTitle(title);
        verify(postInteractor, never()).initPostById(anyInt());
        verify(changeViewInteractor, never()).changeView(anyString());
    }
}
