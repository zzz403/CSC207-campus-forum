package com.imperial.academia.interface_adapter.profile;

import com.imperial.academia.interface_adapter.common.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    private ProfileState profileState = new ProfileState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ProfileViewModel() {
        super("profile");
    }

    public ProfileState getProfileState() {
        return new ProfileState(profileState);
    }

    public void setProfileState(ProfileState profileState) {
        this.profileState = profileState;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.profileState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
