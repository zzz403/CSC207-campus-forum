package com.imperial.academia.use_case.post;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.CommentService;
import com.imperial.academia.service.PostService;
import com.imperial.academia.service.UserService;
import com.imperial.academia.session.SessionManager;

class PostInteractorTest {

    @Mock
    private PostOutputBoundary postPresenter;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private CommentService commentService;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private PostInteractor postInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitPostPageWithValidData() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testForConstractor() {
        PostInteractor postInteractor = new PostInteractor(postPresenter);
        assertNotNull(postInteractor);
    }

    @Test
    void testInitPostPageWithEmptyTitle() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullTitle() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle(null)
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyContent() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullContent() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent(null)
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullUsername() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername(null)
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullAvatarUrl() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl(null)
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithNullDate() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(null)
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyUsername() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("")
                .setAvatarUrl("http://example.com/avatar.jpg")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostPageWithEmptyAvatarUrl() {
        PostInfoData postInfoData = PostInfoData.builder()
                .setTitle("Test Title")
                .setContent("Test Content")
                .setUsername("testuser")
                .setAvatarUrl("")
                .setDate(Timestamp.from(Instant.now()))
                .build();

        postInteractor.initPostPage(postInfoData);

        verify(postPresenter).initPostPage(postInfoData);
    }

    @Test
    void testInitPostByIdWhenSQLExceptionThrown() throws SQLException {
        int postID = 1;
        when(postService.get(postID)).thenThrow(SQLException.class);

        boolean result = postInteractor.initPostById(postID);

        assertFalse(result);
        verify(postPresenter, never()).initPostPage(any(PostInfoData.class));
    }

    @Test
    void testAddLikeWhenSQLExceptionThrown() throws SQLException {
        int postId = 1;
        int userId = 1;
        doThrow(SQLException.class).when(postService).likePost(postId, userId);

        boolean result = postInteractor.addLike(postId, userId);

        assertFalse(result);
        verify(postService).likePost(postId, userId);
        verify(postPresenter, never()).addLike(postId);
    }

    @Test
    void testInitCommentsWhenSQLExceptionThrown() throws SQLException {
        int postId = 1;
        User currentUser = mock(User.class);
        when(commentService.getAllByPostId(postId)).thenThrow(SQLException.class);

        postInteractor.initComments(postId, currentUser);

        verify(commentService).getAllByPostId(postId);
        verify(postPresenter, never()).initComments(anyList());
    }

    @Test
    void testInitPostByIdWithCurrentUser() throws SQLException {
        int postID = 1;
        Post post = mock(Post.class);
        User user = mock(User.class);
        User currentUser = mock(User.class);
        when(postService.get(postID)).thenReturn(post);
        when(userService.get(anyInt())).thenReturn(user);
        when(post.getAuthorId()).thenReturn(1);
        when(post.getTitle()).thenReturn("Test Title");
        when(post.getContent()).thenReturn("Test Content");
        when(user.getUsername()).thenReturn("testuser");
        when(user.getAvatarUrl()).thenReturn("http://example.com/avatar.jpg");
        when(post.getLastModifiedDate()).thenReturn(Timestamp.from(Instant.now()));
        when(postService.getTotalLikesNumberByPostId(postID)).thenReturn(10);
        when(postService.checkLiked(postID, currentUser.getId())).thenReturn(true);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(currentUser);

            boolean result = postInteractor.initPostById(postID);

            assertTrue(result);
            verify(postPresenter).initPostPage(any(PostInfoData.class));
        }
    }

    @Test
    void testInitPostByIdWithValidData() throws SQLException {
        int postID = 1;
        Post post = mock(Post.class);
        User user = mock(User.class);
        User currentUser = mock(User.class);

        when(postService.get(postID)).thenReturn(post);
        when(userService.get(anyInt())).thenReturn(user);
        when(post.getAuthorId()).thenReturn(1);
        when(post.getTitle()).thenReturn("Test Title");
        when(post.getContent()).thenReturn("Test Content");
        when(user.getUsername()).thenReturn("testuser");
        when(user.getAvatarUrl()).thenReturn("http://example.com/avatar.jpg");
        when(post.getLastModifiedDate()).thenReturn(Timestamp.from(Instant.now()));
        when(postService.getTotalLikesNumberByPostId(postID)).thenReturn(10);
        when(postService.checkLiked(postID, currentUser.getId())).thenReturn(true);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(currentUser);

            boolean result = postInteractor.initPostById(postID);

            assertTrue(result);
            verify(postPresenter).initPostPage(any(PostInfoData.class));
        }
    }

    @Test
    void testInitPostByIdWithInvalidData() throws SQLException {
        int postID = 1;
        when(postService.get(postID)).thenThrow(SQLException.class);

        boolean result = postInteractor.initPostById(postID);

        assertFalse(result);
        verify(postPresenter, never()).initPostPage(any(PostInfoData.class));
    }

    @Test
    void testAddLike() throws SQLException {
        int postId = 1;
        int userId = 1;

        boolean result = postInteractor.addLike(postId, userId);

        assertTrue(result);
        verify(postService).likePost(postId, userId);
        verify(postPresenter).addLike(postId);
    }

    @Test
    void testAddLikeWithException() throws SQLException {
        int postId = 1;
        int userId = 1;
        doThrow(SQLException.class).when(postService).likePost(postId, userId);

        boolean result = postInteractor.addLike(postId, userId);

        assertFalse(result);
        verify(postService).likePost(postId, userId);
        verify(postPresenter, never()).addLike(postId);
    }

    @Test
    void testRemoveLike() throws SQLException {
        int postId = 1;
        int userId = 1;

        boolean result = postInteractor.removeLike(postId, userId);

        assertTrue(result);
        verify(postService).unlikePost(postId, userId);
        verify(postPresenter).removeLike(postId);
    }

    @Test
    void testRemoveLikeWithException() throws SQLException {
        int postId = 1;
        int userId = 1;
        doThrow(SQLException.class).when(postService).unlikePost(postId, userId);

        boolean result = postInteractor.removeLike(postId, userId);

        assertFalse(result);
        verify(postService).unlikePost(postId, userId);
        verify(postPresenter, never()).removeLike(postId);
    }

    @Test
    void testCheckLiked() throws SQLException {
        int postId = 1;
        int userId = 1;

        when(postService.checkLiked(postId, userId)).thenReturn(true);

        boolean result = postInteractor.checkLiked(postId, userId);

        assertTrue(result);
        verify(postService).checkLiked(postId, userId);
    }

    @Test
    void testCheckLikedWithException() throws SQLException {
        int postId = 1;
        int userId = 1;
        when(postService.checkLiked(postId, userId)).thenThrow(SQLException.class);

        boolean result = postInteractor.checkLiked(postId, userId);

        assertFalse(result);
        verify(postService).checkLiked(postId, userId);
    }

    @Test
    void testPostComment() throws SQLException {
        int postId = 1;
        int userId = 1;
        String content = "Test Comment";

        postInteractor.postComment(postId, userId, content);

        verify(commentService).insert(any(Comment.class));
        verify(postPresenter).addPost(any(CommentData.class));
    }

    @Test
    void testPostCommentWithException() throws SQLException {
        int postId = 1;
        int userId = 1;
        String content = "Test Comment";
        doThrow(SQLException.class).when(commentService).insert(any(Comment.class));

        postInteractor.postComment(postId, userId, content);

        verify(commentService).insert(any(Comment.class));
        verify(postPresenter, never()).addPost(any(CommentData.class));
    }

    @Test
    void testInitCommentsWithValidData() throws SQLException {
        int postId = 1;
        User currentUser = mock(User.class);
        when(commentService.getAllByPostId(postId)).thenReturn(Collections.singletonList(mock(Comment.class)));
        when(userService.get(anyInt())).thenReturn(mock(User.class));

        postInteractor.initComments(postId, currentUser);

        verify(commentService).getAllByPostId(postId);
        verify(postPresenter).initComments(anyList());
    }

    @Test
    void testInitCommentsWithException() throws SQLException {
        int postId = 1;
        User currentUser = mock(User.class);
        when(commentService.getAllByPostId(postId)).thenThrow(SQLException.class);

        postInteractor.initComments(postId, currentUser);

        verify(commentService).getAllByPostId(postId);
        verify(postPresenter, never()).initComments(anyList());
    }
}
