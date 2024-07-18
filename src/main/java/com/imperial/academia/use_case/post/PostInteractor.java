package com.imperial.academia.use_case.post;

public class PostInteractor implements PostInputBoundary{
    private final PostOutputBoundary postPresenter;

    public PostInteractor(PostOutputBoundary postPresenter){
        this.postPresenter = postPresenter;
    }

    @Override
    public void initPostPage(PostInfoData postInfoData) {
        postPresenter.initPostPage(postInfoData);
    }

}
