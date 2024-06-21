package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.post.Post;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostCacheImpl implements PostCache {
    private Cache<String, Post> postCache;
    private Cache<String, List<Post>> postsCache;

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

    @Override
    public void setPost(String key, Post post) {
        postCache.put(key, post);
    }

    @Override
    public Post getPost(String key) {
        return postCache.getIfPresent(key);
    }

    @Override
    public void deletePost(String key) {
        postCache.invalidate(key);
    }

    @Override
    public boolean existsPost(String key) {
        return postCache.getIfPresent(key) != null;
    }

    @Override
    public void setPosts(String key, List<Post> posts) {
        postsCache.put(key, posts);
    }

    @Override
    public List<Post> getPosts(String key) {
        return postsCache.getIfPresent(key);
    }

    @Override
    public void deletePosts(String key) {
        postsCache.invalidate(key);
    }

    @Override
    public boolean existsPosts(String key) {
        return postsCache.getIfPresent(key) != null;
    }
}
