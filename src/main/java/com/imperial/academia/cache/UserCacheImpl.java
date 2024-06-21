package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.user.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserCacheImpl implements UserCache {
    private Cache<String, User> userCache;
    private Cache<String, List<User>> usersCache;

    public UserCacheImpl() {
        userCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        usersCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    @Override
    public void setUser(String key, User user) {
        userCache.put(key, user);
    }

    @Override
    public User getUser(String key) {
        return userCache.getIfPresent(key);
    }

    @Override
    public void deleteUser(String key) {
        userCache.invalidate(key);
    }

    @Override
    public boolean exists(String key) {
        return userCache.getIfPresent(key) != null;
    }

    @Override
    public void setUsers(String key, List<User> users) {
        usersCache.put(key, users);
    }

    @Override
    public List<User> getUsers(String key) {
        return usersCache.getIfPresent(key);
    }

    @Override
    public void deleteUsers(String key) {
        usersCache.invalidate(key);
    }

    @Override
    public boolean existsUsers(String key) {
        return usersCache.getIfPresent(key) != null;
    }
}
