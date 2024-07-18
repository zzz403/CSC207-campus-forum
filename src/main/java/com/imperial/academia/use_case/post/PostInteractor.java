package com.imperial.academia.use_case.post;

/**
 * The PostInteractor class handles the interaction logic for posts.
 * It acts as a mediator between the input boundary and the output boundary.
 */
public class PostInteractor implements PostInputBoundary {
    private final PostOutputBoundary postPresenter;

    /**
     * Constructs a new PostInteractor with the specified PostOutputBoundary.
     * 
     * @param postPresenter the output boundary that will handle the presentation logic.
     */
    public PostInteractor(PostOutputBoundary postPresenter) {
        this.postPresenter = postPresenter;
    }

    /**
     * Initializes the post page desplay with the given post information data.
     * 
     * @param postInfoData the data required to initialize the post page.
     */
    @Override
    public void initPostPage(PostInfoData postInfoData) {
        postPresenter.initPostPage(postInfoData);
    }
}
