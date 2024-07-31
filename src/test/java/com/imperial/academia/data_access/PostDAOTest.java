package com.imperial.academia.data_access;

import com.imperial.academia.entity.post.Post;
import com.imperial.academia.entity.post.PostLike;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    private PostDAO postDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        postDAO = new PostDAO(mockConnection);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    }

    @Test
    void insert_shouldInsertPost() throws SQLException {
        Post post = new Post(1, "title", "content", 1, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        postDAO.insert(post);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        assertEquals(1, post.getId());
    }

    @Test
    void get_shouldReturnPost() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("title");
        when(mockResultSet.getString("content")).thenReturn("content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("board_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified_date")).thenReturn(new Timestamp(System.currentTimeMillis()));

        Post post = postDAO.get(1);

        assertNotNull(post);
        assertEquals(1, post.getId());
        assertEquals("title", post.getTitle());
        assertEquals("content", post.getContent());
    }

    @Test
    void getAllByUserId_shouldReturnListOfPosts() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("title");
        when(mockResultSet.getString("content")).thenReturn("content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("board_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified_date")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Post> posts = postDAO.getAllByUserId(1);

        assertEquals(2, posts.size());
    }

    @Test
    void getAll_shouldReturnListOfPosts() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("title");
        when(mockResultSet.getString("content")).thenReturn("content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("board_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified_date")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Post> posts = postDAO.getAll();

        assertEquals(2, posts.size());
    }

    @Test
    void getAllSince_shouldReturnListOfPosts() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getString("title")).thenReturn("title");
        when(mockResultSet.getString("content")).thenReturn("content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("board_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified_date")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Post> posts = postDAO.getAllSince(new Timestamp(System.currentTimeMillis() - 1000));

        assertEquals(2, posts.size());
    }

    @Test
    void update_shouldUpdatePost() throws SQLException {
        Post post = new Post(1, "title", "content", 1, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        postDAO.update(post);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void delete_shouldDeletePost() throws SQLException {
        postDAO.delete(1);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void likePost_shouldInsertLike() throws SQLException {
        postDAO.likePost(1, 1);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void unlikePost_shouldDeleteLike() throws SQLException {
        postDAO.unlikePost(1, 1);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void getPostLikes_shouldReturnListOfLikes() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("liked_at")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<PostLike> likes = postDAO.getPostLikes(1);

        assertEquals(2, likes.size());
    }
}
