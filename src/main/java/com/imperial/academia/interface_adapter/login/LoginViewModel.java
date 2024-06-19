package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.interface_adapter.common.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel extends ViewModel {
    public final String TITLE_LABEL = "Log In";
    public final String USERNAME_LABEL = "Username or Email";
    public final String PASSWORD_LABEL = "Password";
    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String REMEMBER_BUTTON_LABEL = "Remember me";
    public final String SIGNUP_BUTTON_LABEL = "Don't have an account? Sign up";
    public final String FORGOT_BUTTON_LABEL = "Forgot password?";

    private LoginState state = new LoginState();
    private String errorMessage;
    private boolean rememberMe;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoginViewModel() {
        super("log in");
    }

    public void setState(LoginState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    public LoginState getState() {
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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        support.firePropertyChange("rememberMe", !rememberMe, rememberMe);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
