package com.imperial.academia.entity.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonUserFactoryTest {
    private  CommonUserFactory commonUserFactory;

    @BeforeEach
    public void init(){
        commonUserFactory = new CommonUserFactory();
    }

    @Test
    void creat(){
        User user = commonUserFactory.create(
                "name",
                "password",
                "email",
                 LocalDateTime.now()
        );
        assertEquals("name", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email",user.getEmail());
    }
}