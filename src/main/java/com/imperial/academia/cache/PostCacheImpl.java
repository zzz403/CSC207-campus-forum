package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.post.Post;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the PostCache interface using Guava Cache.
 */
public class PostCacheImpl implements PostCache {
    private Cache<String, Post> postCache;
    private Cache<String, List<Post>> postsCache;

    /**
     * Constructs a new PostCacheImpl with specific cache configurations.
     */
    public PostCacheImpl() {
        postCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        postsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    /**
     * Constructs a new PostCacheImpl with the specified caches.
     *
     * @param postCache The cache for Post objects.
     * @param postsCache The cache for lists of Post objects.
     */
    public PostCacheImpl(Cache<String, Post> postCache, Cache<String, List<Post>> postsCache) {
        this.postCache = postCache;
        this.postsCache = postsCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPost(String key, Post post) {
        postCache.put(key, post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPost(String key) {
        return postCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePost(String key) {
        postCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsPost(String key) {
        return postCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosts(String key, List<Post> posts) {
        postsCache.put(key, posts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPosts(String key) {
        return postsCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePosts(String key) {
        postsCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsPosts(String key) {
        return postsCache.getIfPresent(key) != null;
    }
}
