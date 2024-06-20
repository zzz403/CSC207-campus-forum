package com.imperial.academia.data_access.post;

import com.imperial.academia.entity.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAI {
    void insert(Post post) throws SQLException;

    Post get(int id) throws SQLException;

    List<Post> getAll() throws SQLException;

    void update(Post post) throws SQLException;

    void delete(int id) throws SQLException;
}
