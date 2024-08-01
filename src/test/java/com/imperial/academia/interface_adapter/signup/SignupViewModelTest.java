package com.imperial.academia.interface_adapter.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignupViewModelTest {

    private SignupViewModel signupViewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        signupViewModel = new SignupViewModel();
        mockListener = mock(PropertyChangeListener.class);
        signupViewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testGetState() {
        SignupState state = signupViewModel.getState();
        assertNotEquals(new SignupState(), state);
    }

    @Test
    void testSetState() {
        SignupState newState = new SignupState();
        newState.setUsername("newUser");
        signupViewModel.setState(newState);

        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
        assertEquals("newUser", signupViewModel.getState().getUsername());
    }

    @Test
    void testGetErrorMessage() {
        assertNull(signupViewModel.getErrorMessage());
    }

    @Test
    void testSetErrorMessage() {
        signupViewModel.setErrorMessage("New error message");

        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
        assertEquals("New error message", signupViewModel.getErrorMessage());
    }

    @Test
    void testFirePropertyChanged() {
        signupViewModel.firePropertyChanged();

        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testFirePropertyChangedWithName() {
        signupViewModel.firePropertyChanged("errorMessage");

        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener listener = evt -> {};
        signupViewModel.addPropertyChangeListener(listener);

        signupViewModel.setState(new SignupState());
        signupViewModel.setErrorMessage("Test error");

        verify(mockListener, times(2)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testLabels() {
        assertEquals("Sign Up", signupViewModel.TITLE_LABEL);
        assertEquals("Choose username", signupViewModel.USERNAME_LABEL);
        assertEquals("Choose password", signupViewModel.PASSWORD_LABEL);
        assertEquals("Enter password again", signupViewModel.REPEAT_PASSWORD_LABEL);
        assertEquals("Enter email", signupViewModel.EMAIL_LABEL);
        assertEquals("Sign up", signupViewModel.SIGNUP_BUTTON_LABEL);
        assertEquals("Cancel", signupViewModel.CANCEL_BUTTON_LABEL);
    }
}
