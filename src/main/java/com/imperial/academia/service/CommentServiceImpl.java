package com.imperial.academia.service;

import com.imperial.academia.cache.CommentCache;
import com.imperial.academia.data_access.comment.CommentDAI;
import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentCache commentCache;
    private CommentDAI commentDAO;

    public CommentServiceImpl(CommentCache commentCache, CommentDAI commentDAO) {
        this.commentCache = commentCache;
        this.commentDAO = commentDAO;
    }

    @Override
    public void insert(Comment comment) throws SQLException {
        commentDAO.insert(comment);
        commentCache.setComment("comment:" + comment.getId(), comment);
    }

    @Override
    public Comment get(int id) throws SQLException {
        Comment comment = commentCache.getComment("comment:" + id);
        if (comment == null) {
            comment = commentDAO.get(id);
            if (comment != null) {
                commentCache.setComment("comment:" + id, comment);
            }
        }
        return comment;
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        List<Comment> comments = commentCache.getComments("comments:all");
        if (comments == null) {
            comments = commentDAO.getAll();
            commentCache.setComments("comments:all", comments);
        }
        return comments;
    }

    @Override
    public void update(Comment comment) throws SQLException {
        commentDAO.update(comment);
        commentCache.setComment("comment:" + comment.getId(), comment);
    }

    @Override
    public void delete(int id) throws SQLException {
        commentDAO.delete(id);
        commentCache.deleteComment("comment:" + id);
    }

    // Methods for Comment Likes
    @Override
    public void likeComment(int commentId, int userId) throws SQLException {
        commentDAO.likeComment(commentId, userId);
        commentCache.deleteComment("comment:" + commentId);  // Invalidate cache for the comment
    }

    @Override
    public void unlikeComment(int commentId, int userId) throws SQLException {
        commentDAO.unlikeComment(commentId, userId);
        commentCache.deleteComment("comment:" + commentId);  // Invalidate cache for the comment
    }

    @Override
    public List<CommentLike> getCommentLikes(int commentId) throws SQLException {
        return commentDAO.getCommentLikes(commentId);
    }
}
