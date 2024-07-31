package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.comment.Comment;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the CommentCache interface using Guava Cache.
 */
public class CommentCacheImpl implements CommentCache {
    private Cache<String, Comment> commentCache;
    private Cache<String, List<Comment>> commentsCache;

    /**
     * Constructs a new CommentCacheImpl with specific cache configurations.
     */
    public CommentCacheImpl() {
        commentCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        commentsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    /**
     * Constructs a new CommentCacheImpl with the specified caches.
     *
     * @param commentCache The cache for Comment objects.
     * @param commentsCache The cache for lists of Comment objects.
     */
    public CommentCacheImpl(Cache<String, Comment> commentCache, Cache<String, List<Comment>> commentsCache) {
        this.commentCache = commentCache;
        this.commentsCache = commentsCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setComment(String key, Comment comment) {
        commentCache.put(key, comment);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comment getComment(String key) {
        return commentCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteComment(String key) {
        commentCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsComment(String key) {
        return commentCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setComments(String key, List<Comment> comments) {
        commentsCache.put(key, comments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> getComments(String key) {
        return commentsCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteComments(String key) {
        commentsCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsComments(String key) {
        return commentsCache.getIfPresent(key) != null;
    }
}
