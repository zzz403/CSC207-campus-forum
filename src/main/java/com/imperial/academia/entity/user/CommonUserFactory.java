package com.imperial.academia.entity.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    @Override
    public User create(String username, String password, String email, String role, LocalDateTime registrationDate) {
        return new User(0, username, password, email, role, Timestamp.valueOf(registrationDate));
    }
}
