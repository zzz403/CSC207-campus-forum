package com.imperial.academia.cache;

import com.imperial.academia.entity.comment.Comment;
import java.util.List;

/**
 * Interface for caching comment entities.
 */
public interface CommentCache {
    /**
     * Caches a single comment.
     *
     * @param key the key for the comment
     * @param comment the comment to cache
     */
    void setComment(String key, Comment comment);

    /**
     * Retrieves a single cached comment.
     *
     * @param key the key for the comment
     * @return the cached comment, or null if not found
     */
    Comment getComment(String key);

    /**
     * Deletes a single cached comment.
     *
     * @param key the key for the comment to delete
     */
    void deleteComment(String key);

    /**
     * Checks if a single comment is cached.
     *
     * @param key the key for the comment
     * @return true if the comment is cached, false otherwise
     */
    boolean existsComment(String key);

    /**
     * Caches a list of comments.
     *
     * @param key the key for the list of comments
     * @param comments the list of comments to cache
     */
    void setComments(String key, List<Comment> comments);

    /**
     * Retrieves a list of cached comments.
     *
     * @param key the key for the list of comments
     * @return the cached list of comments, or null if not found
     */
    List<Comment> getComments(String key);

    /**
     * Deletes a list of cached comments.
     *
     * @param key the key for the list of comments to delete
     */
    void deleteComments(String key);

    /**
     * Checks if a list of comments is cached.
     *
     * @param key the key for the list of comments
     * @return true if the list of comments is cached, false otherwise
     */
    boolean existsComments(String key);
}
