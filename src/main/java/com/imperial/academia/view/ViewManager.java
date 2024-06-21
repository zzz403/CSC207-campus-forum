package com.imperial.academia.view;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.*;

/**
 * ViewManager class manages the switching between different views in the application.
 */
public class ViewManager implements PropertyChangeListener {
    private final JPanel views;
    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs a ViewManager instance and sets up the property change listener.
     *
     * @param views the JPanel containing the views
     * @param cardLayout the CardLayout managing the views
     * @param viewManagerModel the view manager model
     */
    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        viewManagerModel.addPropertyChangeListener(this);
    }

    /**
     * Handles property change events to switch the active view.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        cardLayout.show(views, viewManagerModel.getActiveView());
    }
}
