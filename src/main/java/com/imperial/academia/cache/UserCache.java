package com.imperial.academia.cache;

import com.imperial.academia.entity.user.User;
import java.util.List;

/**
 * Interface for caching user entities.
 */
public interface UserCache {
    /**
     * Caches a single user.
     *
     * @param key the key for the user
     * @param user the user to cache
     */
    void setUser(String key, User user);

    /**
     * Retrieves a single cached user.
     *
     * @param key the key for the user
     * @return the cached user, or null if not found
     */
    User getUser(String key);

    /**
     * Deletes a single cached user.
     *
     * @param key the key for the user to delete
     */
    void deleteUser(String key);

    /**
     * Checks if a single user is cached.
     *
     * @param key the key for the user
     * @return true if the user is cached, false otherwise
     */
    boolean exists(String key);

    /**
     * Caches a list of users.
     *
     * @param key the key for the list of users
     * @param users the list of users to cache
     */
    void setUsers(String key, List<User> users);

    /**
     * Retrieves a list of cached users.
     *
     * @param key the key for the list of users
     * @return the cached list of users, or null if not found
     */
    List<User> getUsers(String key);

    /**
     * Deletes a list of cached users.
     *
     * @param key the key for the list of users to delete
     */
    void deleteUsers(String key);

    /**
     * Checks if a list of users is cached.
     *
     * @param key the key for the list of users
     * @return true if the list of users is cached, false otherwise
     */
    boolean existsUsers(String key);
}
