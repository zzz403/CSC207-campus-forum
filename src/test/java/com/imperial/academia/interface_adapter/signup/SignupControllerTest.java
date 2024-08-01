package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.use_case.signup.SignupInputBoundary;
import com.imperial.academia.use_case.signup.SignupInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignupControllerTest {

    private SignupInputBoundary signupInputBoundary;
    private SignupController signupController;

    @BeforeEach
    void setUp() {
        signupInputBoundary = mock(SignupInputBoundary.class);
        signupController = new SignupController(signupInputBoundary);
    }

    @Test
    void testExecute() {
        String username = "testUser";
        String password1 = "password1";
        String password2 = "password2";
        String email = "test@example.com";

        signupController.execute(username, password1, password2, email);

        ArgumentCaptor<SignupInputData> argumentCaptor = ArgumentCaptor.forClass(SignupInputData.class);
        verify(signupInputBoundary).execute(argumentCaptor.capture());

        SignupInputData capturedInputData = argumentCaptor.getValue();
        assertEquals(username, capturedInputData.getUsername());
        assertEquals(password1, capturedInputData.getPassword());
        assertEquals(password2, capturedInputData.getRepeatPassword());
        assertEquals(email, capturedInputData.getEmail());
    }

    @Test
    void testNavigateToLogin() {
        signupController.navigateToLogin();
        verify(signupInputBoundary).navigateToLogin();
    }
}
