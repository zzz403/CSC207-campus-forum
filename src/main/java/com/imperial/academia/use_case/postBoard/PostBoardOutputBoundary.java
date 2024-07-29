package com.imperial.academia.use_case.postBoard;

import java.util.List;

import com.imperial.academia.entity.post.Post;

public interface PostBoardOutputBoundary {
    void updatePostList(List<Post> posts);
}
