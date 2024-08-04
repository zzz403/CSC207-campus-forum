package com.imperial.academia.use_case.session;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SessionOutputDataTest {

    private static final int USER_ID = 1;
    private static final String USERNAME = "testuser";
    private static final String AVATAR_URL = "http://example.com/avatar.jpg";

    private SessionOutputData sessionOutputData;

    @BeforeEach
    void setUp() {
        sessionOutputData = new SessionOutputData(USER_ID, USERNAME, AVATAR_URL);
    }

    @Test
    void testConstructorInitializesFieldsCorrectly() {
        assertNotNull(sessionOutputData);
        assertEquals(USER_ID, sessionOutputData.getUserId());
        assertEquals(USERNAME, sessionOutputData.getUsername());
        assertEquals(AVATAR_URL, sessionOutputData.getAvatarUrl());
    }

    @Test
    void testGetUserId() {
        assertEquals(USER_ID, sessionOutputData.getUserId());
    }

    @Test
    void testGetUsername() {
        assertEquals(USERNAME, sessionOutputData.getUsername());
    }

    @Test
    void testGetAvatarUrl() {
        assertEquals(AVATAR_URL, sessionOutputData.getAvatarUrl());
    }

    @Test
    void testEquals() {
        SessionOutputData sameData = new SessionOutputData(USER_ID, USERNAME, AVATAR_URL);
        SessionOutputData differentData = new SessionOutputData(2, "differentUser", "http://example.com/different.jpg");

        assertEquals(sessionOutputData, sameData);
        assertNotEquals(sessionOutputData, differentData);
    }

    @Test
    void testHashCode() {
        SessionOutputData sameData = new SessionOutputData(USER_ID, USERNAME, AVATAR_URL);
        SessionOutputData differentData = new SessionOutputData(2, "differentUser", "http://example.com/different.jpg");

        assertEquals(sessionOutputData.hashCode(), sameData.hashCode());
        assertNotEquals(sessionOutputData.hashCode(), differentData.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "SessionOutputData{userId=" + USER_ID + ", username='" + USERNAME + "', avatarUrl='" + AVATAR_URL + "'}";
        assertEquals(expectedString, sessionOutputData.toString());
    }
}
