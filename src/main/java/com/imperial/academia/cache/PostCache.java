package com.imperial.academia.cache;

import com.imperial.academia.entity.post.Post;
import java.util.List;

public interface PostCache {
    void setPost(String key, Post post);
    Post getPost(String key);
    void deletePost(String key);
    boolean existsPost(String key);

    void setPosts(String key, List<Post> posts);
    List<Post> getPosts(String key);
    void deletePosts(String key);
    boolean existsPosts(String key);
}
