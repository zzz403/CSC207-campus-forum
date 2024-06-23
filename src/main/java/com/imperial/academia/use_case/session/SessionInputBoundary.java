package com.imperial.academia.use_case.session;

import java.beans.PropertyChangeListener;

public interface SessionInputBoundary {
    SessionOutputData getSessionInfo();
    void addSessionChangeListener(PropertyChangeListener listener);
    void removeSessionChangeListener(PropertyChangeListener listener);
}
