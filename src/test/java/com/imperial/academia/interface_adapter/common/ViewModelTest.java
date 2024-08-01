package com.imperial.academia.interface_adapter.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewModelTest {

    private TestViewModel viewModel;
    private TestPropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new TestViewModel("testView");
        listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testGetViewName() {
        assertEquals("testView", viewModel.getViewName());
    }

    @Test
    void testFirePropertyChanged() {
        viewModel.firePropertyChanged();
        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("propertyChange", event.getPropertyName());
        assertEquals(null, event.getOldValue());
        assertEquals("propertyChanged", event.getNewValue());
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

    // Concrete implementation of the abstract ViewModel class for testing
    private static class TestViewModel extends ViewModel {
        private final PropertyChangeSupport support = new PropertyChangeSupport(this);

        public TestViewModel(String viewName) {
            super(viewName);
        }

        @Override
        public void firePropertyChanged() {
            support.firePropertyChange("propertyChange", null, "propertyChanged");
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            support.addPropertyChangeListener(listener);
        }
    }
}
