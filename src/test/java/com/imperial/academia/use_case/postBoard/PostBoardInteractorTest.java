package com.imperial.academia.use_case.postBoard;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.use_case.LLM.LLMInputBoundary;

class PostBoardInteractorTest {

    @Mock
    private PostBoardOutputBoundary postBoardPresenter;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private LLMInputBoundary llmInteractor;

    @InjectMocks
    private PostBoardInteractor postBoardInteractor;

    private MockedStatic<ServiceFactory> serviceFactoryMockedStatic;
    private MockedStatic<UsecaseFactory> usecaseFactoryMockedStatic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the static ServiceFactory and UsecaseFactory methods
        serviceFactoryMockedStatic = mockStatic(ServiceFactory.class);
        usecaseFactoryMockedStatic = mockStatic(UsecaseFactory.class);

        serviceFactoryMockedStatic.when(ServiceFactory::getPostService).thenReturn(postService);
        serviceFactoryMockedStatic.when(ServiceFactory::getUserService).thenReturn(userService);
        usecaseFactoryMockedStatic.when(UsecaseFactory::getLLMInteractor).thenReturn(llmInteractor);
    }

    @AfterEach
    void tearDown() {
        serviceFactoryMockedStatic.close();
        usecaseFactoryMockedStatic.close();
    }

    @Test
    void testFetchAllPostSuccess() throws SQLException {
        Post post1 = mock(Post.class);
        Post post2 = mock(Post.class);
        User user1 = mock(User.class);
        User user2 = mock(User.class);

        when(postService.getAll()).thenReturn(Arrays.asList(post1, post2));
        when(post1.getId()).thenReturn(1);
        when(post1.getAuthorId()).thenReturn(101);
        when(post1.getTitle()).thenReturn("Post 1 Title");
        when(post1.getContent()).thenReturn("Content 1");
        when(post2.getId()).thenReturn(2);
        when(post2.getAuthorId()).thenReturn(102);
        when(post2.getTitle()).thenReturn("Post 2 Title");
        when(post2.getContent()).thenReturn("Content 2");
        when(userService.get(101)).thenReturn(user1);
        when(userService.get(102)).thenReturn(user2);
        when(user1.getUsername()).thenReturn("User1");
        when(user1.getAvatarUrl()).thenReturn("http://example.com/avatar1.png");
        when(user2.getUsername()).thenReturn("User2");
        when(user2.getAvatarUrl()).thenReturn("http://example.com/avatar2.png");
        when(postService.getTotalLikesNumberByPostId(1)).thenReturn(10);
        when(postService.getTotalLikesNumberByPostId(2)).thenReturn(20);
        when(llmInteractor.summarizeChatHistory("Content 1")).thenReturn("Summary 1");
        when(llmInteractor.summarizeChatHistory("Content 2")).thenReturn("Summary 2");

        boolean result = postBoardInteractor.fetchAllPost();

        assertTrue(result);
        verify(postBoardPresenter).updatePostList(anyList());
    }

    @Test
    void testFetchAllPostSQLExceptionOnGetAll() throws SQLException {
        when(postService.getAll()).thenThrow(SQLException.class);

        boolean result = postBoardInteractor.fetchAllPost();

        assertFalse(result);
        verify(postBoardPresenter, never()).updatePostList(anyList());
    }

    @Test
    void testForConstractor(){
        PostBoardInteractor postBoardInteractor = new PostBoardInteractor(postBoardPresenter);
        assertNotNull(postBoardInteractor);
    }

    @Test
    void testFetchAllPostWithUserNotFound() throws SQLException {
        Post post1 = mock(Post.class);

        when(postService.getAll()).thenReturn(Collections.singletonList(post1));
        when(post1.getId()).thenReturn(1);
        when(post1.getAuthorId()).thenReturn(101);
        when(post1.getTitle()).thenReturn("Post 1 Title");
        when(post1.getContent()).thenReturn("Content 1");
        when(userService.get(101)).thenThrow(SQLException.class);
        when(postService.getTotalLikesNumberByPostId(1)).thenReturn(10);
        when(llmInteractor.summarizeChatHistory("Content 1")).thenReturn("Summary 1");

        boolean result = postBoardInteractor.fetchAllPost();

        assertTrue(result);
        verify(postBoardPresenter).updatePostList(anyList());
    }

    @Test
    void testFetchAllPostWithEmptyPosts() throws SQLException {
        when(postService.getAll()).thenReturn(Collections.emptyList());

        boolean result = postBoardInteractor.fetchAllPost();

        assertTrue(result);
        verify(postBoardPresenter).updatePostList(Collections.emptyList());
    }

    @Test
    void testFetchAllPostWithLLMException() throws SQLException {
        Post post1 = mock(Post.class);
        User user1 = mock(User.class);

        when(postService.getAll()).thenReturn(Collections.singletonList(post1));
        when(post1.getId()).thenReturn(1);
        when(post1.getAuthorId()).thenReturn(101);
        when(post1.getTitle()).thenReturn("Post 1 Title");
        when(post1.getContent()).thenReturn("Content 1");
        when(userService.get(101)).thenReturn(user1);
        when(user1.getUsername()).thenReturn("User1");
        when(user1.getAvatarUrl()).thenReturn("http://example.com/avatar1.png");
        when(postService.getTotalLikesNumberByPostId(1)).thenReturn(10);

        boolean result = postBoardInteractor.fetchAllPost();

        assertTrue(result);
        verify(postBoardPresenter).updatePostList(anyList());
    }
}
