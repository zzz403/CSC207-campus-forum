package com.imperial.academia.service;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Post Service.
 */
public interface PostService {
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
     * Retrieves all posts from the database by user ID.
     *
     * @param userId the ID of the user to retrieve posts for
     * @return a list of all posts by the specified user
     * @throws SQLException if a database access error occurs
     */
    List<Post> getAllByUserId(int userId) throws SQLException;

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

    /**
     * Likes a post by its ID and user ID.
     *
     * @param postId the ID of the post to like
     * @param userId the ID of the user who likes the post
     * @throws SQLException if a database access error occurs
     */
    void likePost(int postId, int userId) throws SQLException;

    /**
     * Unlikes a post by its ID and user ID.
     *
     * @param postId the ID of the post to unlike
     * @param userId the ID of the user who unlikes the post
     * @throws SQLException if a database access error occurs
     */
    void unlikePost(int postId, int userId) throws SQLException;

    /**
     * Retrieves all likes for a specific post.
     *
     * @param postId the ID of the post to get likes for
     * @return a list of likes for the specified post
     * @throws SQLException if a database access error occurs
     */
    List<PostLike> getPostLikes(int postId) throws SQLException;

    /**
     * Return total like for a post
     *
     * @param postId the ID of the post to get likes for
     * @return the total likes for the specified post
     * @throws SQLException if a database access error occurs
     */
    int getTotalLikesNumberByPostId(int postId) throws SQLException;

    /**
     * Check if the post is liked by the user.
     *
     * @param postId the ID of the post to check if liked
     * @param userId the ID of the user to check if liked
     * @return true if the post is liked by the user, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean checkLiked(int postId, int userId) throws SQLException;

    /**
     * Retrieves all posts from the database by user ID that have been modified
     * since a given timestamp.
     *
     * @param userId    the ID of the user to retrieve posts for
     * @param timestamp the timestamp to compare against
     * @return a list of posts by the specified user modified since the specified
     *         timestamp. If no posts are found, an null object will return.
     * @throws SQLException if a database access error occurs
     */
    Post getReleventPostByTitle(String title) throws SQLException;
}
