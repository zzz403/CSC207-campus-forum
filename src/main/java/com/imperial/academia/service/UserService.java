package com.imperial.academia.service;

import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    void insert(User user) throws SQLException;

    boolean existsByUsername(String username) throws SQLException;

    boolean existsByEmail(String email) throws SQLException;

    Optional<User> getByUsername(String username) throws SQLException;

    Optional<User> get(int id) throws SQLException;

    List<User> getAll() throws SQLException;

    void update(User user) throws SQLException;

    void delete(int id) throws SQLException;
}
