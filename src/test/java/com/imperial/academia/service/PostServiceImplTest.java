package com.imperial.academia.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.cache.PostCache;
import com.imperial.academia.data_access.PostDAI;
import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;
import org.junit.*;
import org.mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class PostServiceImplTest {
    @Mock
    private PostCache postCache;
    @Mock
    private PostDAI postDAO;

    @InjectMocks
    private PostServiceImpl postService;

    private AutoCloseable closeable;

    private Timestamp now;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        now = new Timestamp(System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testInsert() throws SQLException {
        Post post = new Post(1, "Title", "Content", 1, 1, now, now);
        postService.insert(post);
        verify(postDAO, times(1)).insert(post);
        verify(postCache, times(1)).setPost("post:1", post);
    }

    @Test
    public void testGet_Cached() throws SQLException {
        Post post = new Post(1, "Title", "Content", 1, 1, now, now);
        when(postCache.getPost("post:1")).thenReturn(post);

        Post result = postService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(postCache, times(1)).getPost("post:1");
        verifyNoMoreInteractions(postDAO);
    }

    @Test
    public void testGet_NotCached() throws SQLException {
        when(postCache.getPost("post:1")).thenReturn(null);
        Post post = new Post(1, "Title", "Content", 1, 1, now, now);
        when(postDAO.get(1)).thenReturn(post);

        Post result = postService.get(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(postCache, times(1)).getPost("post:1");
        verify(postDAO, times(1)).get(1);
        verify(postCache, times(1)).setPost("post:1", post);
    }

    @Test
    public void testGetAll_Cached() throws SQLException {
        Post post1 = new Post(1, "Title 1", "Content 1", 1, 1, now, now);
        Post post2 = new Post(2, "Title 2", "Content 2", 1, 1, now, now);
        List<Post> posts = Arrays.asList(post1, post2);
        when(postCache.getPosts("posts:all")).thenReturn(posts);

        List<Post> result = postService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(postCache, times(1)).getPosts("posts:all");
        verifyNoMoreInteractions(postDAO);
    }

    @Test
    public void testGetAll_NotCached() throws SQLException {
        when(postCache.getPosts("posts:all")).thenReturn(null);
        Post post1 = new Post(1, "Title 1", "Content 1", 1, 1, now, now);
        Post post2 = new Post(2, "Title 2", "Content 2", 1, 1, now, now);
        List<Post> posts = Arrays.asList(post1, post2);
        when(postDAO.getAll()).thenReturn(posts);

        List<Post> result = postService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(postCache, times(1)).getPosts("posts:all");
        verify(postDAO, times(1)).getAll();
        verify(postCache, times(1)).setPosts("posts:all", posts);
    }

    @Test
    public void testUpdate() throws SQLException {
        Post post = new Post(1, "Updated Title", "Updated Content", 1, 1, now, now);
        postService.update(post);
        verify(postDAO, times(1)).update(post);
        verify(postCache, times(1)).setPost("post:1", post);
    }

    @Test
    public void testDelete() throws SQLException {
        postService.delete(1);
        verify(postDAO, times(1)).delete(1);
        verify(postCache, times(1)).deletePost("post:1");
    }

    @Test
    public void testLikePost() throws SQLException {
        postService.likePost(1, 1);
        verify(postDAO, times(1)).likePost(1, 1);
        verify(postCache, times(1)).deletePost("post:1");
    }

    @Test
    public void testUnlikePost() throws SQLException {
        postService.unlikePost(1, 1);
        verify(postDAO, times(1)).unlikePost(1, 1);
        verify(postCache, times(1)).deletePost("post:1");
    }

    @Test
    public void testGetPostLikes() throws SQLException {
        PostLike like1 = new PostLike(1, 1, now);
        PostLike like2 = new PostLike(2, 1, now);
        List<PostLike> likes = Arrays.asList(like1, like2);
        when(postDAO.getPostLikes(1)).thenReturn(likes);

        List<PostLike> result = postService.getPostLikes(1);
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(postDAO, times(1)).getPostLikes(1);
    }
}
