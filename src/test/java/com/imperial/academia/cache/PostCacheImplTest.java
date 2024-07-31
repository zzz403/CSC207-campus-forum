package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostCacheImplTest {

    private PostCacheImpl postCacheImpl;
    private Cache<String, Post> mockPostCache;
    private Cache<String, List<Post>> mockPostsCache;

    @BeforeEach
    void setUp() {
        mockPostCache = Mockito.mock(Cache.class);
        mockPostsCache = Mockito.mock(Cache.class);

        postCacheImpl = new PostCacheImpl(mockPostCache, mockPostsCache);
    }

    @Test
    void testDefaultConstructor() {
        postCacheImpl = new PostCacheImpl();
        assertNotNull(postCacheImpl);
    }

    @Test
    void setPost_shouldPutPostInCache() {
        Post post = new Post(1, "Test Title", "Test Content", 1, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        postCacheImpl.setPost("testKey", post);

        verify(mockPostCache, times(1)).put("testKey", post);
    }

    @Test
    void getPost_shouldReturnPostFromCache() {
        Post post = new Post(1, "Test Title", "Test Content", 1, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockPostCache.getIfPresent("testKey")).thenReturn(post);

        Post result = postCacheImpl.getPost("testKey");

        assertEquals(post, result);
        verify(mockPostCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deletePost_shouldInvalidatePostInCache() {
        postCacheImpl.deletePost("testKey");

        verify(mockPostCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsPost_shouldReturnTrueIfPostExistsInCache() {
        Post post = new Post(1, "Test Title", "Test Content", 1, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockPostCache.getIfPresent("testKey")).thenReturn(post);

        boolean result = postCacheImpl.existsPost("testKey");

        assertTrue(result);
        verify(mockPostCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsPost_shouldReturnFalseIfPostDoesNotExistInCache() {
        when(mockPostCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = postCacheImpl.existsPost("testKey");

        assertFalse(result);
        verify(mockPostCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setPosts_shouldPutPostsInCache() {
        List<Post> posts = new ArrayList<>();
        postCacheImpl.setPosts("testKey", posts);

        verify(mockPostsCache, times(1)).put("testKey", posts);
    }

    @Test
    void getPosts_shouldReturnPostsFromCache() {
        List<Post> posts = new ArrayList<>();
        when(mockPostsCache.getIfPresent("testKey")).thenReturn(posts);

        List<Post> result = postCacheImpl.getPosts("testKey");

        assertEquals(posts, result);
        verify(mockPostsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deletePosts_shouldInvalidatePostsInCache() {
        postCacheImpl.deletePosts("testKey");

        verify(mockPostsCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsPosts_shouldReturnTrueIfPostsExistInCache() {
        List<Post> posts = new ArrayList<>();
        when(mockPostsCache.getIfPresent("testKey")).thenReturn(posts);

        boolean result = postCacheImpl.existsPosts("testKey");

        assertTrue(result);
        verify(mockPostsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsPosts_shouldReturnFalseIfPostsDoNotExistInCache() {
        when(mockPostsCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = postCacheImpl.existsPosts("testKey");

        assertFalse(result);
        verify(mockPostsCache, times(1)).getIfPresent("testKey");
    }
}
