package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.user.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the UserCache interface using Guava Cache.
 */
public class UserCacheImpl implements UserCache {
    private Cache<String, User> userCache;
    private Cache<String, List<User>> usersCache;

    /**
     * Constructs a new UserCacheImpl with specific cache configurations.
     */
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

    /**
     * Constructs a new UserCacheImpl with the specified caches.
     *
     * @param userCache The cache for User objects.
     * @param usersCache The cache for lists of User objects.
     */
    public UserCacheImpl(Cache<String, User> userCache, Cache<String, List<User>> usersCache) {
        this.userCache = userCache;
        this.usersCache = usersCache;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUser(String key, User user) {


        userCache.put(key, user);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String key) {
        return userCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUser(String key) {
        userCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists(String key) {
        return userCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUsers(String key, List<User> users) {
        usersCache.put(key, users);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> getUsers(String key) {
        return usersCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteUsers(String key) {
        usersCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsUsers(String key) {
        return usersCache.getIfPresent(key) != null;
    }
}
