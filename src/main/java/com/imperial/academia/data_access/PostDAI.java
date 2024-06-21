package com.imperial.academia.data_access;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for Data Access Object for Post entities.
 */
public interface PostDAI {
    /**
     * Inserts a new post into the database.
     *
     * @param post the post to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(Post post) throws SQLException;

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post to retrieve
     * @return the post with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    Post get(int id) throws SQLException;

    /**
     * Retrieves all posts from the database.
     *
     * @return a list of all posts
     * @throws SQLException if a database access error occurs
     */
    List<Post> getAll() throws SQLException;

    /**
     * Retrieves all posts that have been modified since a given timestamp.
     *
     * @param timestamp the timestamp to compare against
     * @return a list of posts modified since the specified timestamp
     * @throws SQLException if a database access error occurs
     */
    List<Post> getAllSince(Timestamp timestamp) throws SQLException;

    /**
     * Updates an existing post in the database.
     *
     * @param post the post to update
     * @throws SQLException if a database access error occurs
     */
    void update(Post post) throws SQLException;

    /**
     * Deletes a post by its ID.
     *
     * @param id the ID of the post to delete
     * @throws SQLException if a database access error occurs
     */
    void delete(int id) throws SQLException;

    // Methods for Post Likes

    /**
     * Likes a post.
     *
     * @param postId the ID of the post to like
     * @param userId the ID of the user who likes the post
     * @throws SQLException if a database access error occurs
     */
    void likePost(int postId, int userId) throws SQLException;

    /**
     * Unlikes a post.
     *
     * @param postId the ID of the post to unlike
     * @param userId the ID of the user who unlikes the post
     * @throws SQLException if a database access error occurs
     */
    void unlikePost(int postId, int userId) throws SQLException;

    /**
     * Retrieves all likes for a specific post.
     *
     * @param postId the ID of the post to retrieve likes for
     * @return a list of likes for the specified post
     * @throws SQLException if a database access error occurs
     */
    List<PostLike> getPostLikes(int postId) throws SQLException;
}
