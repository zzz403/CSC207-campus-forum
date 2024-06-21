package com.imperial.academia.interface_adapter.forum;

import com.imperial.academia.interface_adapter.common.ViewManagerModel;

public class ForumPresenter implements ForumViewSwitcher {
    private final ViewManagerModel viewManagerModel;

    public ForumPresenter(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
    
    @Override
    public void changeView(String viewName){
        viewManagerModel.setActiveView(viewName);
        viewManagerModel.firePropertyChanged();
    }
}
