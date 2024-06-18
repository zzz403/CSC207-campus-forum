package com.imperial.academia.data_access.post;

import java.util.List;

import com.imperial.academia.entity.Post;

public interface PostDataAccessInterface {
    void save(Post post);
    Post findById(int id);
    List<Post> findByBoardId(int boardId);
}