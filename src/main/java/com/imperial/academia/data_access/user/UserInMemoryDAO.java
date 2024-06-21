package com.imperial.academia.data_access.user;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class UserInMemoryDAO implements UserDAI {
    private Cache<Integer, User> userCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();
    private int currentId = 1;

    @Override
    public void insert(User user) throws SQLException {
        if (existsByUsername(user.getUsername())) {
            throw new SQLException("Username already exists");
        }
        user.setId(currentId++);
        userCache.put(user.getId(), user);
    }

    @Override
    public boolean existsByUsername(String username) throws SQLException {
        return userCache.asMap().values().stream().anyMatch(user -> user.getUsername().equals(username));
    }

    @Override
    public boolean existsByEmail(String email) throws SQLException {
        return userCache.asMap().values().stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public User getByUsername(String username) throws SQLException {
        return userCache.asMap().values().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public User get(int id) throws SQLException {
        return userCache.getIfPresent(id);
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userCache.asMap().values().stream().collect(Collectors.toList());
    }

    @Override
    public void update(User user) throws SQLException {
        if (userCache.getIfPresent(user.getId()) == null) {
            throw new SQLException("User not found");
        }
        userCache.put(user.getId(), user);
    }

    @Override
    public void delete(int id) throws SQLException {
        userCache.invalidate(id);
    }
}
