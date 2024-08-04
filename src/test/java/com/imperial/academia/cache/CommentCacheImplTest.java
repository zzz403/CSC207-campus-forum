package com.imperial.academia.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.cache.Cache;
import com.imperial.academia.entity.comment.Comment;

class CommentCacheImplTest {

    private CommentCacheImpl commentCacheImpl;
    private Cache<String, Comment> mockCommentCache;
    private Cache<String, List<Comment>> mockCommentsCache;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        mockCommentCache = Mockito.mock(Cache.class);
        mockCommentsCache = Mockito.mock(Cache.class);

        commentCacheImpl = new CommentCacheImpl(mockCommentCache, mockCommentsCache);
    }

    @Test
    void testDefaultConstructor() {
        commentCacheImpl = new CommentCacheImpl();
        assertNotNull(commentCacheImpl);
    }

    @Test
    void setComment_shouldPutCommentInCache() {
        Comment comment = new Comment(1, "Test comment", 1, 1, null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        commentCacheImpl.setComment("testKey", comment);

        verify(mockCommentCache, times(1)).put("testKey", comment);
    }

    @Test
    void getComment_shouldReturnCommentFromCache() {
        Comment comment = new Comment(1, "Test comment", 1, 1, null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockCommentCache.getIfPresent("testKey")).thenReturn(comment);

        Comment result = commentCacheImpl.getComment("testKey");

        assertEquals(comment, result);
        verify(mockCommentCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteComment_shouldInvalidateCommentInCache() {
        commentCacheImpl.deleteComment("testKey");

        verify(mockCommentCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsComment_shouldReturnTrueIfCommentExistsInCache() {
        Comment comment = new Comment(1, "Test comment", 1, 1, null, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockCommentCache.getIfPresent("testKey")).thenReturn(comment);

        boolean result = commentCacheImpl.existsComment("testKey");

        assertTrue(result);
        verify(mockCommentCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsComment_shouldReturnFalseIfCommentDoesNotExistInCache() {
        when(mockCommentCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = commentCacheImpl.existsComment("testKey");

        assertFalse(result);
        verify(mockCommentCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setComments_shouldPutCommentsInCache() {
        List<Comment> comments = new ArrayList<>();
        commentCacheImpl.setComments("testKey", comments);

        verify(mockCommentsCache, times(1)).put("testKey", comments);
    }

    @Test
    void getComments_shouldReturnCommentsFromCache() {
        List<Comment> comments = new ArrayList<>();
        when(mockCommentsCache.getIfPresent("testKey")).thenReturn(comments);

        List<Comment> result = commentCacheImpl.getComments("testKey");

        assertEquals(comments, result);
        verify(mockCommentsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteComments_shouldInvalidateCommentsInCache() {
        commentCacheImpl.deleteComments("testKey");

        verify(mockCommentsCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsComments_shouldReturnTrueIfCommentsExistInCache() {
        List<Comment> comments = new ArrayList<>();
        when(mockCommentsCache.getIfPresent("testKey")).thenReturn(comments);

        boolean result = commentCacheImpl.existsComments("testKey");

        assertTrue(result);
        verify(mockCommentsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsComments_shouldReturnFalseIfCommentsDoNotExistInCache() {
        when(mockCommentsCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = commentCacheImpl.existsComments("testKey");

        assertFalse(result);
        verify(mockCommentsCache, times(1)).getIfPresent("testKey");
    }
}
