package com.imperial.academia.data_access.user;

import com.imperial.academia.entity.user.User;

public interface UserSignupDataAccessInterface {
    void save(User user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}