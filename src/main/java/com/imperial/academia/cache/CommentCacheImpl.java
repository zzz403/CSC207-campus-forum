package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.comment.Comment;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommentCacheImpl implements CommentCache {
    private Cache<String, Comment> commentCache;
    private Cache<String, List<Comment>> commentsCache;

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

    @Override
    public void setComment(String key, Comment comment) {
        commentCache.put(key, comment);
    }

    @Override
    public Comment getComment(String key) {
        return commentCache.getIfPresent(key);
    }

    @Override
    public void deleteComment(String key) {
        commentCache.invalidate(key);
    }

    @Override
    public boolean existsComment(String key) {
        return commentCache.getIfPresent(key) != null;
    }

    @Override
    public void setComments(String key, List<Comment> comments) {
        commentsCache.put(key, comments);
    }

    @Override
    public List<Comment> getComments(String key) {
        return commentsCache.getIfPresent(key);
    }

    @Override
    public void deleteComments(String key) {
        commentsCache.invalidate(key);
    }

    @Override
    public boolean existsComments(String key) {
        return commentsCache.getIfPresent(key) != null;
    }
}
