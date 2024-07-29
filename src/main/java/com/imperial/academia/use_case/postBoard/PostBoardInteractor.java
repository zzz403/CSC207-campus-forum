package com.imperial.academia.use_case.postBoard;

import java.sql.SQLException;
import java.util.List;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.service.PostService;

public class PostBoardInteractor implements PostBoardInputBoundary {

    private final PostBoardOutputBoundary postBoardPresenter;

    private final PostService postService;

    public PostBoardInteractor(PostBoardOutputBoundary postBoardPresenter) {
        this.postBoardPresenter = postBoardPresenter;
        this.postService = ServiceFactory.getPostService();
    }

    @Override
    public Boolean fetchAllPost() {
        List<Post> posts;
        try {
            posts = postService.getAll();
        } catch (SQLException e) {
            System.out.println("fetch all post unseccess");
            return false;
        }

        postBoardPresenter.updatePostList(posts);
        return true;
    }
    
}
