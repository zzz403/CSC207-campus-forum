package com.imperial.academia.interface_adapter.profile;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ProfileStateTest {
    ProfileState profileState;
    @BeforeEach
    public void init(){
        profileState = new ProfileState();
    }
    @Test
    void getId() {
        int id = profileState.getId();
        assertEquals(-1, id);

    }

    @Test
    void getUsername() {
        String username = profileState.getUsername();
        assertEquals("", username);
    }

    @Test
    void getEmail() {
        String email = profileState.getEmail();
        assertEquals("", email);
    }

    @Test
    void getRole() {
        String role = profileState.getRole();
        assertEquals("", role);
    }

    @Test
    void getAvatarUrl() {
        String url = profileState.getAvatarUrl();
        assertEquals("", url);
    }

    @Test
    void getRegistrationDate() {
        Timestamp registrationDate = profileState.getRegistrationDate();
        assertNotNull(registrationDate);
    }

    @Test
    void isMe() {
        boolean isMe = profileState.isMe();
        assertTrue(isMe);
    }

    @Test
    void setId() {
        profileState.setId(6);
        assertEquals(6, profileState.getId());
    }

    @Test
    void setUsername() {
        profileState.setUsername("aaa");
        assertEquals("aaa", profileState.getUsername());
    }

    @Test
    void setEmail() {
        profileState.setEmail("sss");
        assertEquals("sss", profileState.getEmail());
    }

    @Test
    void setRole() {
        profileState.setRole("sds");
        assertEquals("sds", profileState.getRole());
    }

    @Test
    void setAvatarUrl() {
        profileState.setAvatarUrl("qqq");
        assertEquals("qqq", profileState.getAvatarUrl());
    }

    @Test
    void setRegistrationDate() {
        Timestamp testTimestamp = new Timestamp(2);
        profileState.setRegistrationDate(testTimestamp);
        assertEquals(testTimestamp, profileState.getRegistrationDate());
    }

    @Test
    void setIsMe() {
        profileState.setIsMe(false);
        assertFalse(profileState.isMe());
    }
    @Test
    void constructorTest(){
        profileState.setIsMe(false);
        profileState.setAvatarUrl("ssss");
        ProfileState testProfileState = new ProfileState(profileState);
        assertEquals("ssss", testProfileState.getAvatarUrl());
        assertFalse(testProfileState.isMe());

    }
}