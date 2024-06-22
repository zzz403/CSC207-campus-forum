package com.imperial.academia.interface_adapter.postboard;

import java.beans.PropertyChangeListener;

import com.imperial.academia.interface_adapter.common.ViewModel;

public class PostBoardViewModel extends ViewModel {

    // private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    
    public PostBoardViewModel(){
        super("post board");
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
