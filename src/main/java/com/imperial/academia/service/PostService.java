package com.imperial.academia.service;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.util.List;

public interface PostService {
    void insert(Post post) throws SQLException;

    Post get(int id) throws SQLException;

    List<Post> getAll() throws SQLException;

    void update(Post post) throws SQLException;

    void delete(int id) throws SQLException;

    // Methods for Post Likes
    void likePost(int postId, int userId) throws SQLException;

    void unlikePost(int postId, int userId) throws SQLException;

    List<PostLike> getPostLikes(int postId) throws SQLException;
}
