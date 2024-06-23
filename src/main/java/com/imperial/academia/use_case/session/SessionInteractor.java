package com.imperial.academia.use_case.session;

import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SessionInteractor implements SessionInputBoundary {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    @Override
    public SessionOutputData getSessionInfo() {
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser != null) {
            return new SessionOutputData(
                currentUser.getId(),
                currentUser.getUsername(),
                currentUser.getAvatarUrl()
            );
        }
        return null;
    }

    @Override
    public void addSessionChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void removeSessionChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    public void notifySessionChange() {
        pcs.firePropertyChange("currentUser", null, getSessionInfo());
    }
}
