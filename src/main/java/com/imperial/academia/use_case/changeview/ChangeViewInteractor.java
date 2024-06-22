package com.imperial.academia.use_case.changeview;

public class ChangeViewInteractor implements ChangeViewInputBoundary{
    
    private final ChangeViewOutputBoundary changeViewPresenter; 
    
    public ChangeViewInteractor(ChangeViewOutputBoundary changeViewPresenter){
        this.changeViewPresenter = changeViewPresenter;
    }

    @Override
    public void changeView(String viewName){
        changeViewPresenter.changeView(viewName);
    }
}
