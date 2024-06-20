package com.imperial.academia.data_access.user;

import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAI {
    void insert(User user) throws SQLException;

    boolean existsByUsername(String username) throws SQLException;

    boolean existsByEmail(String email) throws SQLException;

    User getByUsername(String username) throws SQLException;

    User get(int id) throws SQLException;

    List<User> getAll() throws SQLException;

    void update(User user) throws SQLException;

    void delete(int id) throws SQLException;
}
