package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.session.SessionInputBoundary;
import com.imperial.academia.use_case.session.SessionOutputData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TopNavigationBarController implements PropertyChangeListener {

    private final ChangeViewInputBoundary changeViewInteractor;
    private final SessionInputBoundary sessionInputBoundary;
    private SessionOutputData currentSession;

    public TopNavigationBarController(ChangeViewInputBoundary changeViewInteractor, SessionInputBoundary sessionInputBoundary) {
        this.changeViewInteractor = changeViewInteractor;
        this.sessionInputBoundary = sessionInputBoundary;
        this.currentSession = sessionInputBoundary.getSessionInfo();
        sessionInputBoundary.addSessionChangeListener(this);
    }

    public void changeView(String viewName) {
        changeViewInteractor.changeView(viewName);
    }

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
