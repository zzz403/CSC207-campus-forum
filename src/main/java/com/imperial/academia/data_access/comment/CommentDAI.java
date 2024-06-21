package com.imperial.academia.data_access.comment;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface CommentDAI {
    void insert(Comment comment) throws SQLException;
    Comment get(int id) throws SQLException;
    List<Comment> getAll() throws SQLException;
    List<Comment> getAllSince(Timestamp timestamp) throws SQLException;
    void update(Comment comment) throws SQLException;
    void delete(int id) throws SQLException;

    // Methods for Comment Likes
    void likeComment(int commentId, int userId) throws SQLException;
    void unlikeComment(int commentId, int userId) throws SQLException;
    List<CommentLike> getCommentLikes(int commentId) throws SQLException;
}
