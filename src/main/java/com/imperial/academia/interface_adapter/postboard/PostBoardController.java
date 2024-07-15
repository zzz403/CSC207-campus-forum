package com.imperial.academia.interface_adapter.postboard;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;

public class PostBoardController {

    /**
     * The interactor for changing the view.
     */
    private final ChangeViewInputBoundary changeViewInteractor;

    /**
     * Constructs the PostBoardController
     */
    public PostBoardController(){
        changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }

    /**
     * Changes the view to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    public void changeView(String viewName){
        changeViewInteractor.changeView(viewName);
    }
}
