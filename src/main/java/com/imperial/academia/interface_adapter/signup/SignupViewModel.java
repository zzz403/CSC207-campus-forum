package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.interface_adapter.common.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The SignupViewModel class represents the view model for the signup view.
 */
public class SignupViewModel extends ViewModel {
    public final String TITLE_LABEL = "Sign Up";
    public final String USERNAME_LABEL = "Choose username";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";
    public final String EMAIL_LABEL = "Enter email";
    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();
    private String errorMessage;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new SignupViewModel.
     */
    public SignupViewModel() {
        super("sign up");
    }

    // Getters and Setters

    /**
     * Gets the current state of the signup view.
     * 
     * @return The current state of the signup view.
     */
    public SignupState getState() {
        return new SignupState(state);
    }

    /**
     * Sets the current state of the signup view.
     * 
     * @param state The state to set.
     */
    public void setState(SignupState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    /**
     * Gets the error message.
     * 
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * 
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        String oldMessage = this.errorMessage;
        this.errorMessage = errorMessage;
        support.firePropertyChange("errorMessage", oldMessage, errorMessage);
    }

    /**
     * Fires a property change event to notify listeners of state changes.
     */
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
