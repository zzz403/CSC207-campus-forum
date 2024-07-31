package com.imperial.academia.interface_adapter.edit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class EditViewModelTest {
    private EditViewModel editViewModel;
    private EditState editState;

    @BeforeEach
    public void init(){
        editViewModel = new EditViewModel();
        editState = new EditState();
    }

    @Test
    void getState() {
        editViewModel.setState(editState);
        assertEquals(editState.getEmail(),editViewModel.getState().getEmail());
    }

    @Test
    void setState() {
        editViewModel.setState(editState);
        assertNotNull(editViewModel.getState());
    }


    @Test
    void setErrorMessage() {
        editViewModel.setErrorMessage("error");
        assertEquals("error",editViewModel.getErrorMessage());
    }

    @Test
    void firePropertyChanged() {
        PropertyChangeListener mockListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                assertEquals("state",evt.getPropertyName());
                assertNull(evt.getOldValue());
                assertNotNull(evt.getNewValue());
            }
        };
        editViewModel.addPropertyChangeListener(mockListener);
        editViewModel.firePropertyChanged("state");
        editViewModel.firePropertyChanged();

    }


}