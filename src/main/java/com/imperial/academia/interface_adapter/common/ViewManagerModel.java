package com.imperial.academia.interface_adapter.common;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewManagerModel class manages the active view in an application and
 * supports property change listeners to notify when the active view changes.
 */
public class ViewManagerModel {
    private String activeViewName;

    // PropertyChangeSupport object to manage listeners and fire property changes
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Gets the name of the currently active view.
     * 
     * @return The name of the currently active view.
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Sets the name of the currently active view.
     * 
     * @param activeView The name of the view to set as active.
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Fires a property change event to notify listeners that the active view has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * 
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
