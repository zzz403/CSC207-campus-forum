package com.imperial.academia.interface_adapter.login;

import com.imperial.academia.interface_adapter.common.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The LoginViewModel class represents the view model for the login view.
 */
public class LoginViewModel extends ViewModel {
    public final String TITLE_LABEL = "Log In";
    public final String USERNAME_LABEL = "Username or Email";
    public final String PASSWORD_LABEL = "Password";
    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String REMEMBER_BUTTON_LABEL = "Remember me";
    public final String SIGNUP_BUTTON_LABEL = "Don't have an account? Sign up";
    public final String FORGOT_BUTTON_LABEL = "Forgot password?";

    private boolean rememberMe;
    private LoginState state = new LoginState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new LoginViewModel.
     */
    public LoginViewModel() {
        super("log in");
    }

    // Getters and Setters

    /**
     * Gets the current state of the login view.
     * 
     * @return The current state of the login view.
     */
    public LoginState getState() {
        return state;
    }

    /**
     * Sets the current state of the login view.
     * 
     * @param state The state to set.
     */
    public void setState(LoginState state) {
        this.state = state;
        support.firePropertyChange("state", null, state);
    }

    /**
     * Fires a property change event to notify listeners of state changes.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     * 
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the remember me flag.
     * 
     * @return true if remember me is enabled, false otherwise.
     */
    public boolean isRememberMe() {
        return rememberMe;
    }

    /**
     * Sets the remember me flag.
     * 
     * @param rememberMe The remember me flag to set.
     */
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
        support.firePropertyChange("rememberMe", !rememberMe, rememberMe);
    }

    /**
     * Sets the username in the state and fires a property change event.
     * 
     * @param username The username to set.
     */
    public void setStateUsername(String username) {
        state.setUsername(username);
        support.firePropertyChange("username", null, username);
    }

    /**
     * Sets the password in the state and fires a property change event.
     * 
     * @param password The password to set.
     */
    public void setStatePassword(String password) {
        state.setPassword(password);
        support.firePropertyChange("password", null, password);
    }
}
