package com.imperial.academia.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.data_access.post.PostDAI;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;
import com.imperial.academia.service.PostService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
// import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {
    private final PostDAI postDAO;
    private Cache<Integer, Post> postCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    public PostServiceImpl(PostDAI postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    public void createPost(Post post) throws SQLException {
        postDAO.insert(post);
        postCache.put(post.getId(), post);
    }

    @Override
    public Post getPostById(int id) throws SQLException {
        Post post = postCache.getIfPresent(id);
        if (post == null) {
            post = postDAO.get(id);
            if (post != null) {
                postCache.put(id, post);
            }
        }
        return post;
    }

    @Override
    public List<Post> getAllPosts() throws SQLException {
        return postDAO.getAll();
    }

    @Override
    public void updatePost(Post post) throws SQLException {
        postDAO.update(post);
        postCache.put(post.getId(), post);
    }

    @Override
    public void deletePost(int id) throws SQLException {
        postDAO.delete(id);
        postCache.invalidate(id);
    }

    @Override
    public void likePost(int postId, int userId) throws SQLException {
        postDAO.likePost(postId, userId);
    }

    @Override
    public void unlikePost(int postId, int userId) throws SQLException {
        postDAO.unlikePost(postId, userId);
    }

    @Override
    public List<PostLike> getPostLikes(int postId) throws SQLException {
        return postDAO.getPostLikes(postId);
    }
}
