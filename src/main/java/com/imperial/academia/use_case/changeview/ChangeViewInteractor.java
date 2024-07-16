package com.imperial.academia.use_case.changeview;

/**
 * The interactor that handles the logic for changing the view.
 * It communicates with the output boundary (presenter) to change the view.
 */
public class ChangeViewInteractor implements ChangeViewInputBoundary {
    
    /** The presenter that handles the view change logic. */
    private final ChangeViewOutputBoundary changeViewPresenter;
    
    /**
     * Constructs a new ChangeViewInteractor with the specified presenter.
     * 
     * @param changeViewPresenter the presenter that handles the view change logic
     */
    public ChangeViewInteractor(ChangeViewOutputBoundary changeViewPresenter){
        this.changeViewPresenter = changeViewPresenter;
    }

    /**
     * Requests a change to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    @Override
    public void changeView(String viewName){
        changeViewPresenter.changeView(viewName);
    }
}
