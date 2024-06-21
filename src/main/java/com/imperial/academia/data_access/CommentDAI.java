package com.imperial.academia.data_access;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for Data Access Object for Comment entities.
 */
public interface CommentDAI {
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
     * Retrieves all comments that have been modified since a given timestamp.
     *
     * @param timestamp the timestamp to compare against
     * @return a list of comments modified since the specified timestamp
     * @throws SQLException if a database access error occurs
     */
    List<Comment> getAllSince(Timestamp timestamp) throws SQLException;

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

    // Methods for Comment Likes

    /**
     * Likes a comment.
     *
     * @param commentId the ID of the comment to like
     * @param userId the ID of the user who likes the comment
     * @throws SQLException if a database access error occurs
     */
    void likeComment(int commentId, int userId) throws SQLException;

    /**
     * Unlikes a comment.
     *
     * @param commentId the ID of the comment to unlike
     * @param userId the ID of the user who unlikes the comment
     * @throws SQLException if a database access error occurs
     */
    void unlikeComment(int commentId, int userId) throws SQLException;

    /**
     * Retrieves all likes for a specific comment.
     *
     * @param commentId the ID of the comment to retrieve likes for
     * @return a list of likes for the specified comment
     * @throws SQLException if a database access error occurs
     */
    List<CommentLike> getCommentLikes(int commentId) throws SQLException;
}
