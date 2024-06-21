package com.imperial.academia.interface_adapter.poster;

import java.beans.PropertyChangeListener;

import com.imperial.academia.interface_adapter.common.ViewModel;

public class PosterViewModel extends ViewModel {

    // private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    
    public PosterViewModel(){
        super("Poster board");
    }

    @Override
    public void firePropertyChanged() {
        // throw new UnsupportedOperationException("Unimplemented method 'firePropertyChanged'");
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        // throw new UnsupportedOperationException("Unimplemented method 'addPropertyChangeListener'");
    }
}
