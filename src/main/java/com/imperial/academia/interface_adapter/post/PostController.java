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
     * post controller with postInteractor
     * 
     * @param postInteractor postInteractor 
     */
    public PostController(PostInputBoundary postInteractor) {
        this.postInteractor = postInteractor;
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

        if (postInteractor.checkLiked(postId, userId)) {
            postInteractor.removeLike(postId, userId);
            return;
        }

        postInteractor.addLike(postId, userId);
    }

    /**
     * Post a comment to the post with the specified post ID.
     * 
     * @param postId  the ID of the post to post a comment to.
     * @param content the content of the comment.
     */
    public void postComment(int postId, String content) {
        User user = SessionManager.getCurrentUser();
        if (user == null) {
            return;
        }
        int userId = user.getId();

        postInteractor.postComment(postId, userId, content);
    }
}
