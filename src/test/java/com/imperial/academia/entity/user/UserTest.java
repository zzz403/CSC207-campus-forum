package com.imperial.academia.entity.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    public void init(){
        user = new User(
                1,
                "name",
                "password",
                "email",
                "role",
                "url",
                new Timestamp(1),
                new Timestamp(0)
        );
    }


    @Test
    void setPassword() {
        user.setPassword("qwe");
        assertEquals("qwe", user.getPassword());
    }

    @Test
    void setRole() {
        user.setRole("ooo");
        assertEquals("ooo", user.getRole());
    }

    @Test
    void setRegistrationDate() {
        user.setRegistrationDate(new Timestamp(988));
        assertEquals(new Timestamp(988), user.getRegistrationDate());
    }



    @Test
    void setLastModified() {
        user.setLastModified(new Timestamp(777));
        assertEquals(new Timestamp(777), user.getLastModified());
    }
}