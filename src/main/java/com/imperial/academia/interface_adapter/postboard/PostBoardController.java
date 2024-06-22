package com.imperial.academia.interface_adapter.postboard;

import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

public class PostBoardController {
    private final ChangeViewInputBoundary changeViewInteractor;

    public PostBoardController(ChangeViewInputBoundary changeViewInteractor){
        this.changeViewInteractor = changeViewInteractor;
    }

    public void changeView(String viewName){
        changeViewInteractor.changeView(viewName);
    }
}
