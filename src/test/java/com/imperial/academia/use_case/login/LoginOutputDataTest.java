package com.imperial.academia.use_case.login;

import junit.framework.TestCase;

public class LoginOutputDataTest extends TestCase {
    public void testGetUsername() {
        LoginOutputData loginOutputData = new LoginOutputData("username", "message", "avatarUrl", 1);
        assertEquals("username", loginOutputData.getUsername());
    }

    public void testGetMessage() {
        LoginOutputData loginOutputData = new LoginOutputData("username", "message", "avatarUrl", 1);
        assertEquals("message", loginOutputData.getMessage());
    }

    public void testGetAvatarUrl() {
        LoginOutputData loginOutputData = new LoginOutputData("username", "message", "avatarUrl", 1);
        assertEquals("avatarUrl", loginOutputData.getAvatarUrl());
    }

    public void testGetUserId() {
        LoginOutputData loginOutputData = new LoginOutputData("username", "message", "avatarUrl", 1);
        assertEquals(1, loginOutputData.getUserId());
    }
}