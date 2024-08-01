package com.imperial.academia.entity.user;

import com.mysql.cj.result.LocalDateTimeValueFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

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