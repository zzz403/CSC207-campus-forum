package com.imperial.academia.interface_adapter.topnavbar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.imperial.academia.interface_adapter.common.ViewModel;

/**
 * The view model for the top navigation bar.
 * It holds the state of the top navigation bar and supports property change listeners.
 */
public class TopNavigationBarViewModel extends ViewModel {
    
    /** The current state of the top navigation bar. */
    private TopNavigationBarState state = new TopNavigationBarState();
    
    /** The support for managing property change listeners. */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new TopNavigationBarViewModel with the specified view name.
     */
    public TopNavigationBarViewModel() {
        super("topnavbar");
    }

    /**
     * Gets the current state of the top navigation bar.
     * 
     * @return a copy of the current state of the top navigation bar
     */
    public TopNavigationBarState getState() {
        return new TopNavigationBarState(state);
    }

    /**
     * Sets the current state of the top navigation bar and notifies listeners of the change.
     * 
     * @param state the new state of the top navigation bar
     */
    public void setState(TopNavigationBarState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    /**
     * Fires a property change event to notify listeners of a change in the state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, state);
    }

    /**
     * Adds a property change listener.
     * 
     * @param listener the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
