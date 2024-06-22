package com.imperial.academia.interface_adapter.topnavbar;

import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

public class TopNavigationBarController {

    private final ChangeViewInputBoundary changeViewInteractor;
    
    public TopNavigationBarController(ChangeViewInputBoundary changeViewInteractor){
        this.changeViewInteractor = changeViewInteractor;
    }

    public void changeView(String viewName){
        changeViewInteractor.changeView(viewName);
    }
}
