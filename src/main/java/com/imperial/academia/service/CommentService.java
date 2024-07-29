package com.imperial.academia.service;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Comment Service.
 */
public interface CommentService {
    /**
     * Inserts a new comment into the database.
     *
     * @param comment the comment to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(Comment comment) throws SQLException;

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment to retrieve
     * @return the comment with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    Comment get(int id) throws SQLException;

    /**
     * Retrieves all comments from the database.
     *
     * @return a list of all comments
     * @throws SQLException if a database access error occurs
     */
    List<Comment> getAll() throws SQLException;

    /**
     * Retrieves all comments for a specific post.
     *
     * @param postId the ID of the post to get comments for
     * @return a list of comments for the specified post
     * @throws SQLException if a database access error occurs
     */
    List<Comment> getAllByPostId(int postId) throws SQLException;

    /**
     * Updates an existing comment in the database.
     *
     * @param comment the comment to update
     * @throws SQLException if a database access error occurs
     */
    void update(Comment comment) throws SQLException;

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete
     * @throws SQLException if a database access error occurs
     */
    void delete(int id) throws SQLException;

    /**
     * Likes a comment by its ID and user ID.
     *
     * @param commentId the ID of the comment to like
     * @param userId the ID of the user who likes the comment
     * @throws SQLException if a database access error occurs
     */
    void likeComment(int commentId, int userId) throws SQLException;

    /**
     * Unlikes a comment by its ID and user ID.
     *
     * @param commentId the ID of the comment to unlike
     * @param userId the ID of the user who unlikes the comment
     * @throws SQLException if a database access error occurs
     */
    void unlikeComment(int commentId, int userId) throws SQLException;

    /**
     * Retrieves all likes for a specific comment.
     *
     * @param commentId the ID of the comment to get likes for
     * @return a list of likes for the specified comment
     * @throws SQLException if a database access error occurs
     */
    List<CommentLike> getCommentLikes(int commentId) throws SQLException;
}
