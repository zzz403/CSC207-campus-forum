package com.imperial.academia.data_access.comment;

import java.util.List;

import com.imperial.academia.entity.Comment;

public interface CommentDataAccessInterface {
    void save(Comment comment);
    List<Comment> findByPostId(int postId);
}
