package com.imperial.academia.entity.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UpdatedUserFactoryImplTest {
    private UpdatedUserFactoryImpl updatedUserFactory;

    @BeforeEach
    public void init(){
        updatedUserFactory = new UpdatedUserFactoryImpl();
    }

    @Test
    void create() {
        User user = updatedUserFactory.create(
                1,
                "name",
                "password",
                "email",
                "role",
                "url",
                new Timestamp(1),
                LocalDateTime.now()
        );
        assertEquals("name", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email",user.getEmail());
        assertEquals("role", user.getRole());
        assertEquals("url", user.getAvatarUrl());
    }
}