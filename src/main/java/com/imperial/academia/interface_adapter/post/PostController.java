package com.imperial.academia.interface_adapter.post;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.use_case.post.PostInputBoundary;

/**
 * The PostController class handles the presentation logic for a post.
 */
public class PostController {

    /**
     * The interactor for post
     */
    private final PostInputBoundary postInteractor;

    /**
     * Constructs the PostController
     */
    public PostController() {
        this.postInteractor = UsecaseFactory.getPostInteractor();
    }

    
    /**
     * Add a like to the post with the specified post ID.
     * 
     * @param postId the ID of the post to add a like to.
     * @return true if the like was added successfully, false otherwise.
     */
    public void likeClick(int postId) {
        User user = SessionManager.getCurrentUser();
        if (user == null) {
            return;
        }
        int userId = user.getId();
        
        if(postInteractor.checkLiked(postId, userId)){
            postInteractor.removeLike(postId, userId);
            return;
        }
        
        postInteractor.addLike(postId, userId);
    }
    
}
