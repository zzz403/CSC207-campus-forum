package com.imperial.academia.interface_adapter.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.PropertyChangeListener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginViewModelTest {

    private LoginViewModel loginViewModel;
    private boolean propertyChangeFired;

    @BeforeEach
    void setUp() {
        loginViewModel = new LoginViewModel();
        propertyChangeFired = false;
    }

    @Test
    void testGetAndSetState() {
        LoginState state = new LoginState();
        state.setUsername("testUser");
        state.setPassword("testPass");

        loginViewModel.setState(state);
        assertEquals("testUser", loginViewModel.getState().getUsername());
        assertEquals("testPass", loginViewModel.getState().getPassword());
    }

    @Test
    void testSetRememberMe() {
        PropertyChangeListener listener = evt -> {
            if ("rememberMe".equals(evt.getPropertyName())) {
                propertyChangeFired = true;
            }
        };
        loginViewModel.addPropertyChangeListener(listener);

        loginViewModel.setRememberMe(true);
        assertTrue(loginViewModel.isRememberMe());
        assertTrue(propertyChangeFired);
    }

    @Test
    void testSetStateUsername() {
        PropertyChangeListener listener = evt -> {
            if ("username".equals(evt.getPropertyName())) {
                propertyChangeFired = true;
            }
        };
        loginViewModel.addPropertyChangeListener(listener);

        loginViewModel.setStateUsername("newUser");
        assertEquals("newUser", loginViewModel.getState().getUsername());
        assertTrue(propertyChangeFired);
    }

    @Test
    void testSetStatePassword() {
        PropertyChangeListener listener = evt -> {
            if ("password".equals(evt.getPropertyName())) {
                propertyChangeFired = true;
            }
        };
        loginViewModel.addPropertyChangeListener(listener);

        loginViewModel.setStatePassword("newPass");
        assertEquals("newPass", loginViewModel.getState().getPassword());
        assertTrue(propertyChangeFired);
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener listener = evt -> propertyChangeFired = true;
        loginViewModel.addPropertyChangeListener(listener);
        loginViewModel.firePropertyChanged();
        assertTrue(propertyChangeFired);
    }

    @Test
    void testFirePropertyChanged() {
        PropertyChangeListener listener = evt -> propertyChangeFired = true;
        loginViewModel.addPropertyChangeListener(listener);
        loginViewModel.firePropertyChanged();
        assertTrue(propertyChangeFired);
    }
}
