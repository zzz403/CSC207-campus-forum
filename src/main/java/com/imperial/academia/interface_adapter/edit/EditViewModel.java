package com.imperial.academia.interface_adapter.edit;

import com.imperial.academia.interface_adapter.common.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditViewModel extends ViewModel {
    public final String TITLE_LABEL = "edit";
    public final String USERNAME_LABEL = "Choose username";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public final String EMAIL_LABEL = "Enter email";
    public final String UPDATE_BUTTON_LABEL = "Update";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private  EditState state = new EditState();
    private String errorMessage;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public EditViewModel(){super("edit");};

    public EditState getState(){return new EditState(state);}

    public void setState(EditState state){
        this.state = state;
        support.firePropertyChange("state", null, state);
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        String oldMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldMessage, errorMessage);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Fires a property change event to notify listeners of specific property changes.
     *
     * @param propertyName The name of the property that changed.
     */
    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
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
