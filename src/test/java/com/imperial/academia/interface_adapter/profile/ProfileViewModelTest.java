package com.imperial.academia.interface_adapter.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

