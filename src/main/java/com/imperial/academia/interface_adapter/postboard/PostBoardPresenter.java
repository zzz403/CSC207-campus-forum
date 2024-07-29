package com.imperial.academia.interface_adapter.postboard;

import java.util.List;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.use_case.postBoard.PostBoardOutputBoundary;

public class PostBoardPresenter implements PostBoardOutputBoundary {

    private final PostBoardViewModel postBoardViewModel;
    
    public PostBoardPresenter(PostBoardViewModel postBoardViewModel){
        this.postBoardViewModel = postBoardViewModel;
    }

    @Override
    public void updatePostList(List<Post> posts) {
        postBoardViewModel.setStatePostList(posts);
    }
}
