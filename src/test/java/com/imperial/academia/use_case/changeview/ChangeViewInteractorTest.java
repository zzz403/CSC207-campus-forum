package com.imperial.academia.use_case.changeview;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ChangeViewInteractorTest {

    @Mock
    private ChangeViewOutputBoundary changeViewPresenter;

    @InjectMocks
    private ChangeViewInteractor changeViewInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testChangeViewToHome() {
        String viewName = "Home";
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }

    @Test
    void testChangeViewToProfile() {
        String viewName = "Profile";
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }

    @Test
    void testChangeViewToSettings() {
        String viewName = "Settings";
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }

    @Test
    void testChangeViewToNull() {
        String viewName = null;
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }

    @Test
    void testChangeViewToEmptyString() {
        String viewName = "";
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }

    @Test
    void testChangeViewToSpecialCharacters() {
        String viewName = "@#$$%^&*()!";
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }

    @Test
    void testChangeViewMultipleTimes() {
        String viewName1 = "View1";
        String viewName2 = "View2";
        String viewName3 = "View3";

        changeViewInteractor.changeView(viewName1);
        changeViewInteractor.changeView(viewName2);
        changeViewInteractor.changeView(viewName3);

        verify(changeViewPresenter).changeView(viewName1);
        verify(changeViewPresenter).changeView(viewName2);
        verify(changeViewPresenter).changeView(viewName3);
    }

    @Test
    void testChangeViewToSameViewMultipleTimes() {
        String viewName = "Dashboard";
        changeViewInteractor.changeView(viewName);
        changeViewInteractor.changeView(viewName);
        changeViewInteractor.changeView(viewName);

        verify(changeViewPresenter, times(3)).changeView(viewName);
    }

    @Test
    void testChangeViewWithWhitespace() {
        String viewName = "   ";
        changeViewInteractor.changeView(viewName);
        verify(changeViewPresenter).changeView(viewName);
    }
}
