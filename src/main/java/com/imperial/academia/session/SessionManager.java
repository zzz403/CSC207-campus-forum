package com.imperial.academia.session;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.imperial.academia.entity.user.User;

/**
 * SessionManager is a utility class responsible for managing the current user session.
 * It provides methods to set, get, and clear the current user session.
 */
public class SessionManager {
    private static User currentUser;
    private static final PropertyChangeSupport support = new PropertyChangeSupport(SessionManager.class);
    /**
     * Gets the current user of the session.
     *
     * @return the current user, or null if no user is logged in
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the current user of the session.
     *
     * @param user the user to set as the current user
     */
    public static void setCurrentUser(User user) {
        User oldUser = currentUser;
        currentUser = user;
        support.firePropertyChange("currentUser", oldUser, currentUser);
    }

    /**
     * Clears the current user session.
     * This method sets the current user to null, effectively logging the user out.
     */
    public static void clearSession() {
        User oldUser = currentUser;
        currentUser = null;
        support.firePropertyChange("currentUser", oldUser, currentUser);
    }

     public static void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public static void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
