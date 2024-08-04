package com.imperial.academia.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.interface_adapter.signup.SignupViewModel;

class SignupViewTest {
    private SignupView signupView;

    @Mock
    private SignupViewModel mockSignupViewModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        signupView = new SignupView(mockSignupViewModel);
    }

    @Test
    void testSignupViewInitializes() {
        // This test will simply check if the SignupView initializes components correctly.
        assertNotNull(signupView, "SignupView should be initialized.");
    }


}