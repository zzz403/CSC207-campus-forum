package com.imperial.academia.interface_adapter.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ViewManagerModelTest {

    private ViewManagerModel viewManagerModel;
    private TestPropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel();
        listener = new TestPropertyChangeListener();
        viewManagerModel.addPropertyChangeListener(listener);
    }

    @Test
    void testGetAndSetActiveView() {
        viewManagerModel.setActiveView("HomeView");
        assertEquals("HomeView", viewManagerModel.getActiveView());

        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("changeView", event.getPropertyName());
        assertNull(event.getOldValue());
        assertEquals("HomeView", event.getNewValue());

        viewManagerModel.setActiveView("ProfileView");
        assertEquals("ProfileView", viewManagerModel.getActiveView());

        event = listener.getLastEvent();
        assertEquals("changeView", event.getPropertyName());
        assertEquals("HomeView", event.getOldValue());
        assertEquals("ProfileView", event.getNewValue());
    }

    @Test
    void testGoBack() {
        viewManagerModel.setActiveView("HomeView");
        viewManagerModel.setActiveView("ProfileView");

        viewManagerModel.goBack();
        assertEquals("HomeView", viewManagerModel.getActiveView());

        viewManagerModel.goBack();
        assertEquals("HomeView", viewManagerModel.getActiveView()); // No change since stack is empty

        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("changeView", event.getPropertyName());
        assertEquals("HomeView", event.getOldValue());
        assertEquals("ProfileView", event.getNewValue());
    }

    @Test
    void testFirePropertyChanged() {
        viewManagerModel.setActiveView("HomeView");
        viewManagerModel.firePropertyChanged();

        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("view", event.getPropertyName());
        assertNull(event.getOldValue());
        assertEquals("HomeView", event.getNewValue());
    }

    // Helper class to listen for property change events
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private PropertyChangeEvent lastEvent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            lastEvent = evt;
        }

        public PropertyChangeEvent getLastEvent() {
            return lastEvent;
        }
    }
}
