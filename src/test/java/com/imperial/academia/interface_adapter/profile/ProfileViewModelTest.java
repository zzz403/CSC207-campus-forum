package com.imperial.academia.interface_adapter.profile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

class ProfileViewModelTest {
    private ProfileViewModel profileViewModel;
    private ProfileState profileState;
    @BeforeEach
    public void init(){
        profileViewModel = new ProfileViewModel();
        profileState = new ProfileState();
    }
    @Test
    void getProfileState() {
        profileViewModel.setProfileState(profileState);
        assertEquals(profileState.getId(),profileViewModel.getProfileState().getId());

    }

    @Test
    void setProfileState() {
        profileViewModel.setProfileState(profileState);
        assertNotNull(profileViewModel.getProfileState());

    }

    @Test
    void fireProperty() {
        PropertyChangeListener mockListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals("state", evt.getPropertyName());
                assertNull(evt.getOldValue());
                assertNotNull(evt.getNewValue());
            }
        };
        profileViewModel.addPropertyChangeListener(mockListener);
        profileViewModel.firePropertyChanged();
    }
}

