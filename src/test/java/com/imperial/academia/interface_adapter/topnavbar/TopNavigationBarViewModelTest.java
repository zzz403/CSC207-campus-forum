package com.imperial.academia.interface_adapter.topnavbar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopNavigationBarViewModelTest {

    private TopNavigationBarViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new TopNavigationBarViewModel();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testDefaultConstructor() {
        assertNotNull(viewModel);
        assertEquals("topnavbar", viewModel.getViewName());
        TopNavigationBarState state = viewModel.getState();
        assertEquals("", state.getAvatarUrl());
        assertEquals(0, state.getUserId());
        assertEquals("", state.getCurrentViewName());
    }

    @Test
    void testSetAndGetState() {
        TopNavigationBarState newState = new TopNavigationBarState();
        newState.setAvatarUrl("newAvatarUrl");
        newState.setUserId(123);
        newState.setCurrentViewName("home");

        viewModel.setState(newState);

        TopNavigationBarState state = viewModel.getState();
        assertEquals("newAvatarUrl", state.getAvatarUrl());
        assertEquals(123, state.getUserId());
        assertEquals("home", state.getCurrentViewName());

        verify(listener).propertyChange(any());
    }

    @Test
    void testFirePropertyChanged() {
        viewModel.firePropertyChanged();
        verify(listener).propertyChange(any());
    }

    @Test
    void testAddPropertyChangeListener() {
        @SuppressWarnings("unused")
        PropertyChangeSupport support = mock(PropertyChangeSupport.class);
        viewModel.addPropertyChangeListener(listener);
        viewModel.firePropertyChanged();
        verify(listener, times(2)).propertyChange(any());
    }
}
