package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.use_case.login.LoginInputBoundary;
import com.imperial.academia.use_case.login.LoginInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginInputBoundary loginInputBoundary;
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        loginInputBoundary = mock(LoginInputBoundary.class);
        loginController = new LoginController(loginInputBoundary);
    }

    @Test
    void testExecute() {
        String username = "testUser";
        String password = "testPass";
        boolean rememberMe = true;

        loginController.execute(username, password, rememberMe);

        ArgumentCaptor<LoginInputData> captor = ArgumentCaptor.forClass(LoginInputData.class);
        verify(loginInputBoundary).execute(captor.capture());

        LoginInputData capturedArgument = captor.getValue();
        assertEquals(username, capturedArgument.getUsername());
        assertEquals(password, capturedArgument.getPassword());
        assertEquals(rememberMe, capturedArgument.isRememberMe());
    }

    @Test
    void testLoadCredentials() throws IOException {
        String[] expectedCredentials = {"user", "pass"};
        when(loginInputBoundary.loadCredentials()).thenReturn(expectedCredentials);

        String[] actualCredentials = loginController.loadCredentials();
        assertArrayEquals(expectedCredentials, actualCredentials);
    }

    @Test
    void testNavigateToSignup() {
        loginController.navigateToSignup();
        verify(loginInputBoundary).navigateToSignup();
    }
}
