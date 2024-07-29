package com.imperial.academia.service;

import com.imperial.academia.cache.PostCache;
import com.imperial.academia.data_access.PostDAI;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the PostService interface.
 * Uses caching to reduce database access.
 */
public class PostServiceImpl implements PostService {
    private final PostCache postCache;
    private final PostDAI postDAO;

    /**
     * Constructs a new PostServiceImpl with the specified cache and DAO.
     *
     * @param postCache the cache to use
     * @param postDAO   the DAO to use
     */
    public PostServiceImpl(PostCache postCache, PostDAI postDAO) {
        this.postCache = postCache;
        this.postDAO = postDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Post post) throws SQLException {
        postDAO.insert(post);
        postCache.setPost("post:" + post.getId(), post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post get(int id) throws SQLException {
        Post post = postCache.getPost("post:" + id);
        if (post == null) {
            post = postDAO.get(id);
            if (post != null) {
                postCache.setPost("post:" + id, post);
            }
        }
        return post;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAllByUserId(int userId) throws SQLException {
        List<Post> posts = postCache.getPosts("posts:user:" + userId);
        if (posts == null) {
            posts = postDAO.getAllByUserId(userId);
            postCache.setPosts("posts:user:" + userId, posts);
        }
        return posts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> posts = postCache.getPosts("posts:all");
        if (posts == null) {
            posts = postDAO.getAll();
            postCache.setPosts("posts:all", posts);
        }
        return posts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Post post) throws SQLException {
        postDAO.update(post);
        postCache.setPost("post:" + post.getId(), post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        postDAO.delete(id);
        postCache.deletePost("post:" + id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void likePost(int postId, int userId) throws SQLException {
        postDAO.likePost(postId, userId);
        postCache.deletePost("post:" + postId); // Invalidate cache for the post
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlikePost(int postId, int userId) throws SQLException {
        postDAO.unlikePost(postId, userId);
        postCache.deletePost("post:" + postId); // Invalidate cache for the post
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PostLike> getPostLikes(int postId) throws SQLException {
        return postDAO.getPostLikes(postId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getTotalLikesNumberByPostId(int postId) throws SQLException {
        List<PostLike> postLikes = getPostLikes(postId);
        return postLikes.size();
    }
}
