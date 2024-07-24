package com.imperial.academia.use_case.profile;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ProfileOutputDataTest {
    private int id;

    private String username;


    private String email;


    private String role;

    private String avatarUrl;

    private Timestamp registrationDate;

    private boolean isMe;
    private ProfileOutputData outputData;


    @BeforeEach
    public void init(){
        id = 1;
        username = "name";
        email = "email";
        role = "role";
        avatarUrl = "url";
        registrationDate = new Timestamp(1);
        isMe = true;
        outputData = new ProfileOutputData(id,username,email,role,avatarUrl,registrationDate,isMe);

    }

    @Test
    void getId() {
        assertEquals(id, outputData.getId());
    }

    @Test
    void getRegistrationDate() {
        assertEquals(registrationDate, outputData.getRegistrationDate());
    }

    @Test
    void getAvatarUrl() {
        assertEquals(avatarUrl, outputData.getAvatarUrl());
    }

    @Test
    void getRole() {
        assertEquals(role, outputData.getRole());
    }

    @Test
    void getEmail() {
        assertEquals(email, outputData.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals(username, outputData.getUsername());
    }

    @Test
    void isMe() {
        assertEquals(isMe, outputData.isMe());
    }
}