package com.imperial.academia.entity.user;

import java.time.LocalDateTime;

public interface UserFactory {
    User create(String username, String password, String email, String role, LocalDateTime registrationDate);
}