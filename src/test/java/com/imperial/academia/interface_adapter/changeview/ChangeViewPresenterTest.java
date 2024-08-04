package com.imperial.academia.interface_adapter.changeview;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ChangeViewPresenterTest {

    @Mock
    private ViewManagerModel viewManagerModel;

    @InjectMocks
    private ChangeViewPresenter changeViewPresenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConstructorInitializesViewManagerModel() {
        assertNotNull(changeViewPresenter);
    }

    @Test
    void testChangeViewSetsActiveView() {
        String viewName = "newView";

        changeViewPresenter.changeView(viewName);

        verify(viewManagerModel).setActiveView(viewName);
    }

    @Test
    void testChangeViewWithNullViewName() {
        changeViewPresenter.changeView(null);

        verify(viewManagerModel).setActiveView(null);
    }

    @Test
    void testChangeViewWithEmptyViewName() {
        String viewName = "";

        changeViewPresenter.changeView(viewName);

        verify(viewManagerModel).setActiveView(viewName);
    }

    @Test
    void testChangeViewWithWhitespaceViewName() {
        String viewName = "   ";

        changeViewPresenter.changeView(viewName);

        verify(viewManagerModel).setActiveView(viewName);
    }

    @Test
    void testChangeViewMultipleCalls() {
        String firstViewName = "firstView";
        String secondViewName = "secondView";

        changeViewPresenter.changeView(firstViewName);
        changeViewPresenter.changeView(secondViewName);

        verify(viewManagerModel).setActiveView(firstViewName);
        verify(viewManagerModel).setActiveView(secondViewName);
    }
}
