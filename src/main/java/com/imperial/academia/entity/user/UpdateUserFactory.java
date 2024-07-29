package com.imperial.academia.entity.user;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public interface UpdateUserFactory {
    User create(int id, String username, String password, String email,String role, String avatarURL, Timestamp registrationDate, LocalDateTime lastModifiedDate);
}
