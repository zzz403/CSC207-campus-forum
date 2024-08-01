package com.imperial.academia.interface_adapter.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginStateTest {

    private LoginState loginState;

    @BeforeEach
    void setUp() {
        loginState = new LoginState();
    }

    @Test
    void testDefaultConstructor() {
        assertNull(loginState.getUsernameError());
        assertNull(loginState.getPasswordError());
        assertEquals("", loginState.getUsername());
        assertEquals("", loginState.getPassword());
    }

    @Test
    void testCopyConstructor() {
        loginState.setUsername("testUser");
        loginState.setPassword("testPass");
        loginState.setUsernameError("Username error");
        loginState.setPasswordError("Password error");

        LoginState copyState = new LoginState(loginState);

        assertEquals("testUser", copyState.getUsername());
        assertEquals("testPass", copyState.getPassword());
        assertEquals("Username error", copyState.getUsernameError());
        assertEquals("Password error", copyState.getPasswordError());
    }

    @Test
    void testSetUsername() {
        loginState.setUsername("testUser");
        assertEquals("testUser", loginState.getUsername());
    }

    @Test
    void testSetPassword() {
        loginState.setPassword("testPass");
        assertEquals("testPass", loginState.getPassword());
    }

    @Test
    void testSetUsernameError() {
        loginState.setUsernameError("Username error");
        assertEquals("Username error", loginState.getUsernameError());
    }

    @Test
    void testSetPasswordError() {
        loginState.setPasswordError("Password error");
        assertEquals("Password error", loginState.getPasswordError());
    }

    @Test
    void testClearErrors() {
        loginState.setUsernameError("Username error");
        loginState.setPasswordError("Password error");
        loginState.clearErrors();
        assertNull(loginState.getUsernameError());
        assertNull(loginState.getPasswordError());
    }
}
