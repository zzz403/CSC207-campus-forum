package com.imperial.academia.cache;

import com.imperial.academia.entity.post.Post;
import java.util.List;

/**
 * Interface for caching post entities.
 */
public interface PostCache {
    /**
     * Caches a single post.
     *
     * @param key the key for the post
     * @param post the post to cache
     */
    void setPost(String key, Post post);

    /**
     * Retrieves a single cached post.
     *
     * @param key the key for the post
     * @return the cached post, or null if not found
     */
    Post getPost(String key);

    /**
     * Deletes a single cached post.
     *
     * @param key the key for the post to delete
     */
    void deletePost(String key);

    /**
     * Checks if a single post is cached.
     *
     * @param key the key for the post
     * @return true if the post is cached, false otherwise
     */
    boolean existsPost(String key);

    /**
     * Caches a list of posts.
     *
     * @param key the key for the list of posts
     * @param posts the list of posts to cache
     */
    void setPosts(String key, List<Post> posts);

    /**
     * Retrieves a list of cached posts.
     *
     * @param key the key for the list of posts
     * @return the cached list of posts, or null if not found
     */
    List<Post> getPosts(String key);

    /**
     * Deletes a list of cached posts.
     *
     * @param key the key for the list of posts to delete
     */
    void deletePosts(String key);

    /**
     * Checks if a list of posts is cached.
     *
     * @param key the key for the list of posts
     * @return true if the list of posts is cached, false otherwise
     */
    boolean existsPosts(String key);
}
