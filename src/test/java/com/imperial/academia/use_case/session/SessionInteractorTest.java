package com.imperial.academia.use_case.session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class SessionInteractorTest {

    private SessionInteractor sessionInteractor;
    private MockedStatic<SessionManager> sessionManagerMockedStatic;

    @BeforeEach
    void setUp() {
        sessionInteractor = new SessionInteractor();
        sessionManagerMockedStatic = mockStatic(SessionManager.class);
    }

    @AfterEach
    void tearDown() {
        sessionManagerMockedStatic.close();
    }

    @Test
    void testGetSessionInfoWithUser() {
        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        when(mockUser.getUsername()).thenReturn("testuser");
        when(mockUser.getAvatarUrl()).thenReturn("http://example.com/avatar.jpg");

        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);

        SessionOutputData sessionInfo = sessionInteractor.getSessionInfo();

        assertNotNull(sessionInfo);
        assertEquals(1, sessionInfo.getUserId());
        assertEquals("testuser", sessionInfo.getUsername());
        assertEquals("http://example.com/avatar.jpg", sessionInfo.getAvatarUrl());
    }

    @Test
    void testGetSessionInfoWithoutUser() {
        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);

        SessionOutputData sessionInfo = sessionInteractor.getSessionInfo();

        assertNull(sessionInfo);
    }

    @Test
    void testAddSessionChangeListener() {
        PropertyChangeListener listener = evt -> {};
        sessionInteractor.addSessionChangeListener(listener);
        
        sessionInteractor.notifySessionChange();
    }

    @Test
    void testRemoveSessionChangeListener() {
        PropertyChangeListener listener = evt -> {};
        sessionInteractor.addSessionChangeListener(listener);
        sessionInteractor.removeSessionChangeListener(listener);

        sessionInteractor.notifySessionChange();
    }

    @Test
    void testNotifySessionChange() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        sessionInteractor.addSessionChangeListener(listener);

        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(1);
        when(mockUser.getUsername()).thenReturn("testuser");
        when(mockUser.getAvatarUrl()).thenReturn("http://example.com/avatar.jpg");

        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);

        sessionInteractor.notifySessionChange();

        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testNotifySessionChangeWithoutUser() {
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        sessionInteractor.addSessionChangeListener(listener);

        sessionManagerMockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);

        sessionInteractor.notifySessionChange();

        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}
