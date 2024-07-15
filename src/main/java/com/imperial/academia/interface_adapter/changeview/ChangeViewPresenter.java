package com.imperial.academia.interface_adapter.changeview;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;

public class ChangeViewPresenter implements ChangeViewOutputBoundary {
    
    private final ViewManagerModel viewManagerModel;

    public ChangeViewPresenter(ViewManagerModel viewManagerModel){
        this.viewManagerModel = viewManagerModel;
    }
    
    @Override
    public void changeView(String viewName){
        viewManagerModel.setActiveView(viewName);;
    }
    
}
