package com.imperial.academia.cache;

import com.imperial.academia.entity.comment.Comment;
import java.util.List;

public interface CommentCache {
    void setComment(String key, Comment comment);
    Comment getComment(String key);
    void deleteComment(String key);
    boolean existsComment(String key);

    void setComments(String key, List<Comment> comments);
    List<Comment> getComments(String key);
    void deleteComments(String key);
    boolean existsComments(String key);
}
