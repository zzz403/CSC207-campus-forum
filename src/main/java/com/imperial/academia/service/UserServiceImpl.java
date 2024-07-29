package com.imperial.academia.service;

import com.imperial.academia.cache.UserCache;
import com.imperial.academia.data_access.UserDAI;
import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the UserService interface.
 * Uses caching to reduce database access.
 */
public class UserServiceImpl implements UserService {
    private UserCache userCache;
    private UserDAI userDAO;

    /**
     * Constructs a new UserServiceImpl with the specified cache and DAO.
     *
     * @param userCache the cache to use
     * @param userDAO the DAO to use
     */
    public UserServiceImpl(UserCache userCache, UserDAI userDAO) {
        this.userCache = userCache;
        this.userDAO = userDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(User user) throws SQLException {
        userDAO.insert(user);
        userCache.setUser("user:" + user.getId(), user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByUsername(String username) throws SQLException {
        if (userCache.exists("username:" + username)) {
            return true;
        }
        boolean exists = userDAO.existsByUsername(username);
        if (exists) {
            userCache.setUser("username:" + username, userDAO.getByUsername(username));
        }
        return exists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByEmail(String email) throws SQLException {
        if (userCache.exists("email:" + email)) {
            return true;
        }
        boolean exists = userDAO.existsByEmail(email);
        if (exists) {
            User user = userDAO.getByEmail(email);
            userCache.setUser("email:" + email, user);
        }
        return exists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getByUsername(String username) throws SQLException {
        User user = userCache.getUser("username:" + username);
        if (user == null) {
            user = userDAO.getByUsername(username);
            if (user != null) {
                userCache.setUser("username:" + username, user);
            }
        }
        return user;
    }

    public User getByEmail(String email) throws SQLException {
        User user = userCache.getUser("email:" + email);
        if (user == null) {
            user = userDAO.getByEmail(email);
            if (user != null) {
                userCache.setUser("email:" + email, user);
            }
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User get(int id) throws SQLException {
        User user = userCache.getUser("user:" + id);
        if (user == null) {
            user = userDAO.get(id);
            if (user != null) {
                userCache.setUser("user:" + id, user);
            }
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getAll() throws SQLException {
        List<User> users = userCache.getUsers("users:all");
        if (users == null) {
            users = userDAO.getAll();
            userCache.setUsers("users:all", users);
        }
        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) throws SQLException {
        userDAO.update(user);
        userCache.setUser("user:" + user.getId(), user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        userDAO.delete(id);
        userCache.deleteUser("user:" + id);
    }
}
