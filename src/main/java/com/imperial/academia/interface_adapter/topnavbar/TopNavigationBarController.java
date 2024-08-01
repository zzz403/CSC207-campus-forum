package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.session.SessionInputBoundary;
import com.imperial.academia.use_case.session.SessionOutputData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The controller for the top navigation bar.
 * It listens for changes in the session and handles view changes.
 */
public class TopNavigationBarController implements PropertyChangeListener {

    /** The interactor that handles the logic for changing the view. */
    private final ChangeViewInputBoundary changeViewInteractor;
    
    /** The input boundary for session-related operations. */
    private final SessionInputBoundary sessionInputBoundary;
    
    /** The current session data. */
    private SessionOutputData currentSession;

    /**
     * Constructs a new TopNavigationBarController with the specified interactor and session input boundary.
     * 
     */
    public TopNavigationBarController() {
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        this.sessionInputBoundary = UsecaseFactory.getSessionInteractor();
        this.currentSession = sessionInputBoundary.getSessionInfo();
        sessionInputBoundary.addSessionChangeListener(this);
    }

    public TopNavigationBarController(ChangeViewInputBoundary changeViewInteractor, SessionInputBoundary sessionInputBoundary) {
        this.changeViewInteractor = changeViewInteractor;
        this.sessionInputBoundary = sessionInputBoundary;
        this.currentSession = sessionInputBoundary.getSessionInfo();
        sessionInputBoundary.addSessionChangeListener(this);
    }

    /**
     * Changes the view to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    public void changeView(String viewName) {
        changeViewInteractor.changeView(viewName);
    }

    /**
     * Gets the URL of the current user's avatar.
     * 
     * @return the URL of the current user's avatar, or null if there is no current session
     */
    public String getCurrentUserAvatarUrl() {
        return currentSession != null ? currentSession.getAvatarUrl() : null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("currentUser".equals(evt.getPropertyName())) {
            currentSession = sessionInputBoundary.getSessionInfo();
        }
    }
}
