package com.imperial.academia.service;

import com.imperial.academia.data_access.user.UserDAI;
import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDAI userDAI;

    public UserServiceImpl(UserDAI userDAI) {
        this.userDAI = userDAI;
    }

    @Override
    public void insert(User user) throws SQLException {
        userDAI.insert(user);
    }

    @Override
    public boolean existsByUsername(String username) throws SQLException {
        return userDAI.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) throws SQLException {
        return userDAI.existsByEmail(email);
    }

    @Override
    public Optional<User> getByUsername(String username) throws SQLException {
        return Optional.ofNullable(userDAI.getByUsername(username));
    }

    @Override
    public Optional<User> get(int id) throws SQLException {
        return Optional.ofNullable(userDAI.get(id));
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userDAI.getAll();
    }

    @Override
    public void update(User user) throws SQLException {
        userDAI.update(user);
    }

    @Override
    public void delete(int id) throws SQLException {
        userDAI.delete(id);
    }
}
