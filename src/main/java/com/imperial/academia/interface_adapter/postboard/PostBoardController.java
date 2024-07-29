package com.imperial.academia.interface_adapter.postboard;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.postBoard.PostBoardInputBoundary;

public class PostBoardController {

    /**
     * The interactor for changing the view.
     */
    private final ChangeViewInputBoundary changeViewInteractor;

    /**
     * The interactor for postBoard
     */
    private final PostBoardInputBoundary postBoardInteractor;

    /**
     * Constructs the PostBoardController
     */
    public PostBoardController(){
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        this.postBoardInteractor = UsecaseFactory.getPostBoardInteractor();
    }

    /**
     * Changes the view to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    public void changeView(String viewName){
        changeViewInteractor.changeView(viewName);
    }


    public Boolean fetchAllPost(){
        return postBoardInteractor.fetchAllPost();
    }
}
