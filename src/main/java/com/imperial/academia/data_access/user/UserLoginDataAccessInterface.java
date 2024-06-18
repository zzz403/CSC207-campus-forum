package com.imperial.academia.data_access.user;

import com.imperial.academia.entity.user.User;

public interface UserLoginDataAccessInterface {
    User findByUsername(String username);
}