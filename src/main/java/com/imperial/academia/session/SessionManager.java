package com.imperial.academia.session;

import com.imperial.academia.entity.user.User;

/**
 * SessionManager is a utility class responsible for managing the current user session.
 * It provides methods to set, get, and clear the current user session.
 */
public class SessionManager {
    private static User currentUser;

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
        currentUser = user;
    }

    /**
     * Clears the current user session.
     * This method sets the current user to null, effectively logging the user out.
     */
    public static void clearSession() {
        currentUser = null;
    }
}
