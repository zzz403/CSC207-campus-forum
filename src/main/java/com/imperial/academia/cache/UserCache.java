package com.imperial.academia.cache;

import com.imperial.academia.entity.user.User;
import java.util.List;

public interface UserCache {
    void setUser(String key, User user);
    User getUser(String key);
    void deleteUser(String key);
    boolean exists(String key);

    void setUsers(String key, List<User> users);
    List<User> getUsers(String key);
    void deleteUsers(String key);
    boolean existsUsers(String key);
}
