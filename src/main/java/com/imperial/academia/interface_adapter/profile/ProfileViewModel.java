package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.interface_adapter.common.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ProfileViewModel class extends the ViewModel class and manages the state of the profile view.
 * It supports property change listeners to notify changes in the profile state.
 */
public class ProfileViewModel extends ViewModel {

    /**
     * The current state of the profile.
     */
    private ProfileState profileState = new ProfileState();

    /**
     * The support for property change listeners.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a new ProfileViewModel with the default view name "profile".
     */
    public ProfileViewModel() {
        super("profile");
    }

    /**
     * Returns a copy of the current profile state.
     *
     * @return a copy of the profile state
     */
    public ProfileState getProfileState() {
        return new ProfileState(profileState);
    }

    /**
     * Sets the profile state and notifies listeners of the change.
     *
     * @param profileState the new profile state
     */
    public void setProfileState(ProfileState profileState) {
        this.profileState = profileState;
    }

    /**
     * Fires a property change event to notify listeners of a change in the profile state.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.profileState);
    }

    /**
     * Adds a property change listener to be notified of changes in the profile state.
     *
     * @param listener the property change listener to add
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
