package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class TopNavigationBarPresenterTest {

    private TopNavigationBarPresenter presenter;
    private ViewManagerModel mockViewManagerModel;
    private TopNavigationBarViewModel mockTopNavigationBarViewModel;
    private TopNavigationBarState mockState;

    @BeforeEach
    void setUp() {
        mockViewManagerModel = mock(ViewManagerModel.class);
        mockTopNavigationBarViewModel = mock(TopNavigationBarViewModel.class);
        mockState = mock(TopNavigationBarState.class);

        when(mockTopNavigationBarViewModel.getState()).thenReturn(mockState);

        presenter = new TopNavigationBarPresenter(mockViewManagerModel, mockTopNavigationBarViewModel);
    }

    @Test
    void testChangeView() {
        String viewName = "newView";

        presenter.changeView(viewName);

        verify(mockState).setCurrentViewName(viewName);
        verify(mockTopNavigationBarViewModel).setState(mockState);
        verify(mockViewManagerModel).setActiveView(viewName);
        verify(mockTopNavigationBarViewModel).firePropertyChanged();
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
