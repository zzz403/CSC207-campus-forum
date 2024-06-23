package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;

public class TopNavigationBarPresenter implements ChangeViewOutputBoundary{
    
    private final ViewManagerModel viewManagerModel;
    private final TopNavigationBarViewModel topNavigationBarViewModel;

    public TopNavigationBarPresenter(ViewManagerModel viewManagerModel, TopNavigationBarViewModel topNavigationBarViewModel){
        this.viewManagerModel = viewManagerModel;
        this.topNavigationBarViewModel = topNavigationBarViewModel;
    }

    @Override
    public void changeView(String viewName){
        TopNavigationBarState state = topNavigationBarViewModel.getState();
        state.setCurrentViewName(viewName);
        topNavigationBarViewModel.setState(state);

        viewManagerModel.setActiveView(viewName);

        topNavigationBarViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
