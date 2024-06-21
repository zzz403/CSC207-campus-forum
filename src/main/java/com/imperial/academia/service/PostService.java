package com.imperial.academia.service;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.util.List;

public interface PostService {
    void createPost(Post post) throws SQLException;
    Post getPostById(int id) throws SQLException;
    List<Post> getAllPosts() throws SQLException;
    void updatePost(Post post) throws SQLException;
    void deletePost(int id) throws SQLException;
    void likePost(int postId, int userId) throws SQLException;
    void unlikePost(int postId, int userId) throws SQLException;
    List<PostLike> getPostLikes(int postId) throws SQLException;
}
