package com.imperial.academia.interface_adapter.signup;

import com.imperial.academia.interface_adapter.common.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

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

    public SignupViewModel() {
        super("sign up");
    }

    public void setState(SignupState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    public SignupState getState() {
        return state;
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

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
