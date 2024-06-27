package com.imperial.academia.interface_adapter.topnavbar;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.imperial.academia.interface_adapter.common.ViewModel;

public class TopNavigationBarViewModel extends ViewModel{
    private TopNavigationBarState state = new TopNavigationBarState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public TopNavigationBarViewModel(){
        super("topnavbar");
    }

    public TopNavigationBarState getState() {
        return new TopNavigationBarState(state);
    }

    public void setState(TopNavigationBarState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    public void firePropertyChanged(){
        support.firePropertyChange("state", null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        support.addPropertyChangeListener(listener);
    }
}
