package com.imperial.academia.data_access.comment;

import com.imperial.academia.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDAI {
    void insert(Comment comment) throws SQLException;

    Comment get(int id) throws SQLException;

    List<Comment> getAll() throws SQLException;

    void update(Comment comment) throws SQLException;

    void delete(int id) throws SQLException;
}
