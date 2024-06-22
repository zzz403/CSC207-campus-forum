package com.imperial.academia.interface_adapter.postboard;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.use_case.changeview.ChangeViewOutputBoundary;

public class PostBoardPresenter implements ChangeViewOutputBoundary{
    
    private final ViewManagerModel viewManagerModel;

    public PostBoardPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void changeView(String viewName){
        viewManagerModel.setActiveView(viewName);
        viewManagerModel.firePropertyChanged();
    }
}
