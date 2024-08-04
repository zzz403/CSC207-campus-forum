package com.imperial.academia.interface_adapter.postboard;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;
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

    private final PostInputBoundary postInteractor;

    /**
     * Constructs the PostBoardController
     */
    public PostBoardController() {
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
        this.postBoardInteractor = UsecaseFactory.getPostBoardInteractor();
        this.postInteractor = UsecaseFactory.getPostInteractor();
    }

    /**
     * Constructs the PostBoardController with the specified ChangeViewInputBoundary
     * and PostBoardInputBoundary.
     * 
     * @param changeViewInteractor the interactor for changing the view.
     * @param postBoardInteractor  the interactor for postBoard
     */
    public PostBoardController(ChangeViewInputBoundary changeViewInteractor, PostBoardInputBoundary postBoardInteractor,
            PostInputBoundary postInteractor) {
        this.changeViewInteractor = changeViewInteractor;
        this.postBoardInteractor = postBoardInteractor;
        this.postInteractor = postInteractor;
    }

    /**
     * Changes the view to the specified view name.
     * 
     * @param viewName the name of the view to change to
     */
    public void changeView(String viewName) {
        changeViewInteractor.changeView(viewName);
    }

    /**
     * Fetches all posts from the database and updates the post board view with the
     * post information.
     * 
     * @return true if the posts were fetched successfully, false otherwise.
     */
    public Boolean fetchAllPost() {
        return postBoardInteractor.fetchAllPost();
    }

    /**
     * Initializes the post page with the post information for the post with the
     * given post ID.
     * 
     * @param postID the ID of the post to initialize the post page with.
     * @return true if the post was fetched successfully, false otherwise.
     */
    public Boolean initPostById(int postID) {
        if (!postInteractor.initPostById(postID)) {
            return false;
        }
        changeViewInteractor.changeView("post");
        return true;
    }
}
