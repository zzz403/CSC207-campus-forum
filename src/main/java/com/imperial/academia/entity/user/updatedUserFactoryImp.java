package com.imperial.academia.entity.user;

import com.imperial.academia.app.UsecaseFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class updatedUserFactoryImp implements UpdateUserFactory {
    @Override
    public User create(int id, String username, String password, String email,String role, String avatarURL, Timestamp registrationDate, LocalDateTime lastModifiedDate) {
        return new User(id, username, password, email, role, avatarURL, registrationDate, Timestamp.valueOf(lastModifiedDate));
    }
}
