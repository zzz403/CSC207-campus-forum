package com.imperial.academia.interface_adapter.topnavbar;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TopNavigationBarStateTest {

    @Test
    void testDefaultConstructor() {
        TopNavigationBarState state = new TopNavigationBarState();
        assertEquals("", state.getAvatarUrl());
        assertEquals(0, state.getUserId());
        assertEquals("", state.getCurrentViewName());
    }

    @Test
    void testParameterizedConstructor() {
        TopNavigationBarState original = new TopNavigationBarState();
        original.setAvatarUrl("avatarUrl");
        original.setUserId(123);
        original.setCurrentViewName("home");

        TopNavigationBarState copy = new TopNavigationBarState(original);

        assertEquals("avatarUrl", copy.getAvatarUrl());
        assertEquals(123, copy.getUserId());
        assertEquals("home", copy.getCurrentViewName());
    }

    @Test
    void testSetAndGetAvatarUrl() {
        TopNavigationBarState state = new TopNavigationBarState();
        state.setAvatarUrl("newAvatarUrl");
        assertEquals("newAvatarUrl", state.getAvatarUrl());
    }

    @Test
    void testSetAndGetUserId() {
        TopNavigationBarState state = new TopNavigationBarState();
        state.setUserId(456);
        assertEquals(456, state.getUserId());
    }

    @Test
    void testSetAndGetCurrentViewName() {
        TopNavigationBarState state = new TopNavigationBarState();
        state.setCurrentViewName("dashboard");
        assertEquals("dashboard", state.getCurrentViewName());
    }
}
