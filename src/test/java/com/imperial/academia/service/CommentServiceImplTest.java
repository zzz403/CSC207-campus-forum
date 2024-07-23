package com.imperial.academia.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.cache.CommentCache;
import com.imperial.academia.data_access.CommentDAI;
import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;
import org.junit.*;
import org.mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class CommentServiceImplTest {
    @Mock
    private CommentCache commentCache;
    @Mock
    private CommentDAI commentDAO;

    @InjectMocks
    private CommentServiceImpl commentService;

    private AutoCloseable closeable;

    private Timestamp now;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        now = new Timestamp(System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testInsert() throws SQLException {
        Comment comment = new Comment(1, "Comment content", 1, 1, null, now, now);
        commentService.insert(comment);
        verify(commentDAO, times(1)).insert(comment);
        verify(commentCache, times(1)).setComment("comment:1", comment);
    }

    @Test
    public void testGet_Cached() throws SQLException {
        Comment comment = new Comment(1, "Comment content", 1, 1, null, now, now);
        when(commentCache.getComment("comment:1")).thenReturn(comment);

        Comment result = commentService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(commentCache, times(1)).getComment("comment:1");
        verifyNoMoreInteractions(commentDAO);
    }

    @Test
    public void testGet_NotCached() throws SQLException {
        when(commentCache.getComment("comment:1")).thenReturn(null);
        Comment comment = new Comment(1, "Comment content", 1, 1, null, now, now);
        when(commentDAO.get(1)).thenReturn(comment);

        Comment result = commentService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(commentCache, times(1)).getComment("comment:1");
        verify(commentDAO, times(1)).get(1);
        verify(commentCache, times(1)).setComment("comment:1", comment);
    }

    @Test
    public void testGetAll_Cached() throws SQLException {
        Comment comment1 = new Comment(1, "Comment 1", 1, 1, null, now, now);
        Comment comment2 = new Comment(2, "Comment 2", 1, 1, null, now, now);
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentCache.getComments("comments:all")).thenReturn(comments);

        List<Comment> result = commentService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(commentCache, times(1)).getComments("comments:all");
        verifyNoMoreInteractions(commentDAO);
    }

    @Test
    public void testGetAll_NotCached() throws SQLException {
        when(commentCache.getComments("comments:all")).thenReturn(null);
        Comment comment1 = new Comment(1, "Comment 1", 1, 1, null, now, now);
        Comment comment2 = new Comment(2, "Comment 2", 1, 1, null, now, now);
        List<Comment> comments = Arrays.asList(comment1, comment2);
        when(commentDAO.getAll()).thenReturn(comments);

        List<Comment> result = commentService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(commentCache, times(1)).getComments("comments:all");
        verify(commentDAO, times(1)).getAll();
        verify(commentCache, times(1)).setComments("comments:all", comments);
    }

    @Test
    public void testUpdate() throws SQLException {
        Comment comment = new Comment(1, "Updated content", 1, 1, null, now, now);
        commentService.update(comment);
        verify(commentDAO, times(1)).update(comment);
        verify(commentCache, times(1)).setComment("comment:1", comment);
    }

    @Test
    public void testDelete() throws SQLException {
        commentService.delete(1);
        verify(commentDAO, times(1)).delete(1);
        verify(commentCache, times(1)).deleteComment("comment:1");
    }

    @Test
    public void testLikeComment() throws SQLException {
        commentService.likeComment(1, 1);
        verify(commentDAO, times(1)).likeComment(1, 1);
        verify(commentCache, times(1)).deleteComment("comment:1");
    }

    @Test
    public void testUnlikeComment() throws SQLException {
        commentService.unlikeComment(1, 1);
        verify(commentDAO, times(1)).unlikeComment(1, 1);
        verify(commentCache, times(1)).deleteComment("comment:1");
    }

    @Test
    public void testGetCommentLikes() throws SQLException {
        CommentLike like1 = new CommentLike(1, 1, now);
        CommentLike like2 = new CommentLike(2, 1, now);
        List<CommentLike> likes = Arrays.asList(like1, like2);
        when(commentDAO.getCommentLikes(1)).thenReturn(likes);

        List<CommentLike> result = commentService.getCommentLikes(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(commentDAO, times(1)).getCommentLikes(1);
    }
}
