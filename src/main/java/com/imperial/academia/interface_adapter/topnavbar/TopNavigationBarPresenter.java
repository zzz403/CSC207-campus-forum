package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;

public class TopNavigationBarPresenter implements ChangeViewOutputBoundary{
    
    private final ViewManagerModel viewManagerModel;

    public TopNavigationBarPresenter(ViewManagerModel viewManagerModel){
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void changeView(String viewName){
        viewManagerModel.setActiveView(viewName);
        viewManagerModel.firePropertyChanged();
    }
}
