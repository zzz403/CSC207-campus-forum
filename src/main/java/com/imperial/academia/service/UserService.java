package com.imperial.academia.service;

import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    void createUser(User user) throws SQLException;
    User getUserById(int id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
}
