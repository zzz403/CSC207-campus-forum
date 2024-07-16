package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.interface_adapter.common.ViewModel;

import java.beans.PropertyChangeListener;

public class EditViewModel extends ViewModel {



    public EditViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }



}
