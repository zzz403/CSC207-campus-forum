package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.session.SessionInputBoundary;
import com.imperial.academia.use_case.session.SessionOutputData;
import com.imperial.academia.use_case.topnav.TopNavInputBoundary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class TopNavigationBarControllerTest {

    private TopNavigationBarController controller;
    private ChangeViewInputBoundary mockChangeViewInteractor;
    private SessionInputBoundary mockSessionInputBoundary;
    private SessionOutputData mockSessionOutputData;
    private TopNavInputBoundary mockTopNavInteractor;

    @BeforeEach
    void setUp() {
        mockChangeViewInteractor = mock(ChangeViewInputBoundary.class);
        mockSessionInputBoundary = mock(SessionInputBoundary.class);
        mockSessionOutputData = mock(SessionOutputData.class);

        when(mockSessionInputBoundary.getSessionInfo()).thenReturn(mockSessionOutputData);

        controller = new TopNavigationBarController(mockChangeViewInteractor, mockSessionInputBoundary, mockTopNavInteractor);
        Mockito.reset(mockSessionInputBoundary); // Reset to ignore constructor call
    }

    @Test
    void testChangeView() {
        String viewName = "newView";
        controller.changeView(viewName);
        verify(mockChangeViewInteractor, times(1)).changeView(viewName);
    }

    @Test
    void testGetCurrentUserAvatarUrl() {
        String avatarUrl = "http://avatar.url";
        when(mockSessionOutputData.getAvatarUrl()).thenReturn(avatarUrl);

        String result = controller.getCurrentUserAvatarUrl();
        assertEquals(avatarUrl, result);
    }

    @Test
    void testGetCurrentUserAvatarUrl_NoSession() {
        when(mockSessionInputBoundary.getSessionInfo()).thenReturn(null);

        TopNavigationBarController noSessionController = new TopNavigationBarController(mockChangeViewInteractor, mockSessionInputBoundary, mockTopNavInteractor);
        String result = noSessionController.getCurrentUserAvatarUrl();
        assertNull(result);
    }

    @Test
    void testPropertyChange_CurrentUser() {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "currentUser", null, null);
        controller.propertyChange(evt);

        verify(mockSessionInputBoundary, times(1)).getSessionInfo();
    }

    @Test
    void testPropertyChange_OtherProperty() {
        PropertyChangeEvent evt = new PropertyChangeEvent(this, "otherProperty", null, null);
        controller.propertyChange(evt);

        verify(mockSessionInputBoundary, never()).getSessionInfo();
    }
}
