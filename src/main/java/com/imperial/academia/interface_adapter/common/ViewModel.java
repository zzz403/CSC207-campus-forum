package com.imperial.academia.interface_adapter.common;

import java.beans.PropertyChangeListener;

/**
 * The ViewModel class is an abstract base class for different view models in an application.
 * It defines a common structure for managing view names and property change listeners.
 */
public abstract class ViewModel {

    private String viewName;

    /**
     * Constructs a new ViewModel with the specified view name.
     * 
     * @param viewName The name of the view.
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the name of the view.
     * 
     * @return The name of the view.
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * Abstract method to fire a property change event to notify listeners of changes.
     */
    public abstract void firePropertyChanged();

    /**
     * Abstract method to add a PropertyChangeListener to the listener list.
     * 
     * @param listener The PropertyChangeListener to be added.
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
