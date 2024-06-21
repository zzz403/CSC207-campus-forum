package com.imperial.academia.service;

import com.imperial.academia.cache.PostCache;
import com.imperial.academia.data_access.post.PostDAI;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.util.List;

public class PostServiceImpl implements PostService {
    private PostCache postCache;
    private PostDAI postDAO;

    public PostServiceImpl(PostCache postCache, PostDAI postDAO) {
        this.postCache = postCache;
        this.postDAO = postDAO;
    }

    @Override
    public void insert(Post post) throws SQLException {
        postDAO.insert(post);
        postCache.setPost("post:" + post.getId(), post);
    }

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

    @Override
    public List<Post> getAll() throws SQLException {
        List<Post> posts = postCache.getPosts("posts:all");
        if (posts == null) {
            posts = postDAO.getAll();
            postCache.setPosts("posts:all", posts);
        }
        return posts;
    }

    @Override
    public void update(Post post) throws SQLException {
        postDAO.update(post);
        postCache.setPost("post:" + post.getId(), post);
    }

    @Override
    public void delete(int id) throws SQLException {
        postDAO.delete(id);
        postCache.deletePost("post:" + id);
    }

    // Methods for Post Likes
    @Override
    public void likePost(int postId, int userId) throws SQLException {
        postDAO.likePost(postId, userId);
        postCache.deletePost("post:" + postId);  // Invalidate cache for the post
    }

    @Override
    public void unlikePost(int postId, int userId) throws SQLException {
        postDAO.unlikePost(postId, userId);
        postCache.deletePost("post:" + postId);  // Invalidate cache for the post
    }

    @Override
    public List<PostLike> getPostLikes(int postId) throws SQLException {
        return postDAO.getPostLikes(postId);
    }
}
