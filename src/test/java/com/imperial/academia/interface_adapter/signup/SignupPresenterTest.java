package com.imperial.academia.interface_adapter.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SignupStateTest {

    private SignupState signupState;

    @BeforeEach
    void setUp() {
        signupState = new SignupState();
    }

    @Test
    void testGettersAndSetters() {
        signupState.setUsername("testUser");
        assertEquals("testUser", signupState.getUsername());

        signupState.setUsernameError("Username error");
        assertEquals("Username error", signupState.getUsernameError());

        signupState.setPassword("testPassword");
        assertEquals("testPassword", signupState.getPassword());

        signupState.setPasswordError("Password error");
        assertEquals("Password error", signupState.getPasswordError());

        signupState.setRepeatPassword("testRepeatPassword");
        assertEquals("testRepeatPassword", signupState.getRepeatPassword());

        signupState.setRepeatPasswordError("Repeat password error");
        assertEquals("Repeat password error", signupState.getRepeatPasswordError());

        signupState.setEmail("test@example.com");
        assertEquals("test@example.com", signupState.getEmail());

        signupState.setEmailError("Email error");
        assertEquals("Email error", signupState.getEmailError());
    }

    @Test
    void testClear() {
        signupState.setUsername("testUser");
        signupState.setUsernameError("Username error");
        signupState.setPassword("testPassword");
        signupState.setPasswordError("Password error");
        signupState.setRepeatPassword("testRepeatPassword");
        signupState.setRepeatPasswordError("Repeat password error");
        signupState.setEmail("test@example.com");
        signupState.setEmailError("Email error");

        signupState.clear();

        assertEquals("", signupState.getUsername());
        assertNull(signupState.getUsernameError());
        assertEquals("", signupState.getPassword());
        assertNull(signupState.getPasswordError());
        assertEquals("", signupState.getRepeatPassword());
        assertNull(signupState.getRepeatPasswordError());
        assertEquals("", signupState.getEmail());
        assertNull(signupState.getEmailError());
    }

    @Test
    void testClearErrors() {
        signupState.setUsernameError("Username error");
        signupState.setPasswordError("Password error");
        signupState.setRepeatPasswordError("Repeat password error");
        signupState.setEmailError("Email error");

        signupState.clearErrors();

        assertNull(signupState.getUsernameError());
        assertNull(signupState.getPasswordError());
        assertNull(signupState.getRepeatPasswordError());
        assertNull(signupState.getEmailError());
    }

    @Test
    void testCopyConstructor() {
        signupState.setUsername("testUser");
        signupState.setUsernameError("Username error");
        signupState.setPassword("testPassword");
        signupState.setPasswordError("Password error");
        signupState.setRepeatPassword("testRepeatPassword");
        signupState.setRepeatPasswordError("Repeat password error");
        signupState.setEmail("test@example.com");
        signupState.setEmailError("Email error");

        SignupState copiedState = new SignupState(signupState);

        assertEquals("testUser", copiedState.getUsername());
        assertEquals("Username error", copiedState.getUsernameError());
        assertEquals("testPassword", copiedState.getPassword());
        assertEquals("Password error", copiedState.getPasswordError());
        assertEquals("testRepeatPassword", copiedState.getRepeatPassword());
        assertEquals("Repeat password error", copiedState.getRepeatPasswordError());
        assertEquals("test@example.com", copiedState.getEmail());
        assertEquals("Email error", copiedState.getEmailError());
    }
}
