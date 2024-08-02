package com.imperial.academia.use_case.topnav;

import java.sql.SQLException;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.service.PostService;
import com.imperial.academia.use_case.changeview.ChangeViewInputBoundary;
import com.imperial.academia.use_case.post.PostInputBoundary;

/**
 * The TopNavInteractor class handles the interaction logic for the top
 * navigation bar.
 * It acts as a mediator between the input boundary and the output boundary.
 */
public class TopNavInteractor implements TopNavInputBoundary {

    // The service that provides post-related operations.
    private final PostService postService;

    // The interactor that handles the logic for post-related operations.
    private final PostInputBoundary postInteractor;

    // The interactor that handles the logic for changing the view.
    private final ChangeViewInputBoundary changeViewInteractor;

    /**
     * Constructs a new TopNavInteractor with the default services.
     */
    public TopNavInteractor() {
        this.postService = ServiceFactory.getPostService();
        this.postInteractor = UsecaseFactory.getPostInteractor();
        this.changeViewInteractor = UsecaseFactory.getChangeViewInteractor();
    }

    /**
     * Constructs a new TopNavInteractor with the specified services.
     * 
     * @param postService          the service that provides post-related operations
     * @param postInteractor       the interactor that handles the logic for
     *                             post-related operations
     * @param changeViewInteractor the interactor that handles the logic for
     *                             changing the view
     */
    public TopNavInteractor(PostService postService, PostInputBoundary postInteractor,
            ChangeViewInputBoundary changeViewInteractor) {
        this.postService = postService;
        this.postInteractor = postInteractor;
        this.changeViewInteractor = changeViewInteractor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void searchPostByTitle(String title) {
        Post post;
        try {
            post = postService.getReleventPostByTitle(title);
        } catch (SQLException e) {
            System.out.println("serch post By title SQLException");
            return;
        }
        if (post == null) {
            return;
        }

        postInteractor.initPostById(post.getId());
        changeViewInteractor.changeView("post");
    }

}
