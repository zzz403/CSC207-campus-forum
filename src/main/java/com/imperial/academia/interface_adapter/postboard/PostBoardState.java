package com.imperial.academia.interface_adapter.postboard;

import java.util.ArrayList;
import java.util.List;

import com.imperial.academia.entity.post.Post;

public class PostBoardState {
    private List<Post> postList;

    public PostBoardState(){
        this.postList = new ArrayList<>();
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> posts) {
        this.postList = new ArrayList<>(posts);
    }

    
}
