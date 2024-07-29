package com.imperial.academia.interface_adapter.postboard;

import java.util.List;

import com.imperial.academia.use_case.post.PostOverviewInfo;
import com.imperial.academia.use_case.postBoard.PostBoardOutputBoundary;

/**
 * The PostBoardPresenter class is the presenter for the post board view.
 */
public class PostBoardPresenter implements PostBoardOutputBoundary {

    /**
     * The PostBoardViewModel object for the presenter.
     */
    private final PostBoardViewModel postBoardViewModel;

    /**
     * Creates a new PostBoardPresenter object.
     * 
     * @param postBoardViewModel the view model for the post board view.
     */
    public PostBoardPresenter(PostBoardViewModel postBoardViewModel) {
        this.postBoardViewModel = postBoardViewModel;
    }

    /**
     * Updates the post list info with the given list of post overview information
     * to ViewModel.
     * 
     * @param postsInfo the list of post overview information to update the post
     *                  list with.
     */
    @Override
    public void updatePostList(List<PostOverviewInfo> postsInfo) {
        postBoardViewModel.setStatePostList(postsInfo);
    }
}
