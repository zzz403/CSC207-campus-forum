package com.imperial.academia.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.data_access.user.UserDAI;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserServiceImpl implements UserService {
    private final UserDAI userDAO;
    private Cache<Integer, User> userCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    public UserServiceImpl(UserDAI userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void createUser(User user) throws SQLException {
        userDAO.insert(user);
        userCache.put(user.getId(), user);
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = userCache.getIfPresent(id);
        if (user == null) {
            user = userDAO.get(id);
            if (user != null) {
                userCache.put(id, user);
            }
        }
        return user;
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        User user = userDAO.getByUsername(username);
        if (user != null) {
            userCache.put(user.getId(), user);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAll();
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userDAO.update(user);
        userCache.put(user.getId(), user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        userDAO.delete(id);
        userCache.invalidate(id);
    }
}
