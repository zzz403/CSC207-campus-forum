package com.imperial.academia.data_access.post;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface PostDAI {
    void insert(Post post) throws SQLException;
    Post get(int id) throws SQLException;
    List<Post> getAll() throws SQLException;
    List<Post> getAllSince(Timestamp timestamp) throws SQLException;
    void update(Post post) throws SQLException;
    void delete(int id) throws SQLException;

    // Methods for Post Likes
    void likePost(int postId, int userId) throws SQLException;
    void unlikePost(int postId, int userId) throws SQLException;
    List<PostLike> getPostLikes(int postId) throws SQLException;
}
