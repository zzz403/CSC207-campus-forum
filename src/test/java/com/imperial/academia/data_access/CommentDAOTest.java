package com.imperial.academia.data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

class CommentDAOTest {

    private CommentDAO commentDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        commentDAO = new CommentDAO(mockConnection);
    }

    @Test
    void insert_shouldInsertComment() throws SQLException {
        Comment comment = new Comment(0, "Test content", 1, 1, null, null, null);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        commentDAO.insert(comment);

        assertEquals(1, comment.getId());
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void get_shouldReturnComment() throws SQLException {
        int commentId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("comment_id")).thenReturn(commentId);
        when(mockResultSet.getString("content")).thenReturn("Test content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        Comment comment = commentDAO.get(commentId);

        assertNotNull(comment);
        assertEquals(commentId, comment.getId());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void getAll_shouldReturnAllComments() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mock(Statement.class));
        when(mockConnection.createStatement().executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("comment_id")).thenReturn(1);
        when(mockResultSet.getString("content")).thenReturn("Test content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = commentDAO.getAll();

        assertNotNull(comments);
        assertEquals(1, comments.size());
        verify(mockConnection.createStatement(), times(1)).executeQuery(anyString());
    }

    @Test
    void update_shouldUpdateComment() throws SQLException {
        Comment comment = new Comment(1, "Updated content", 1, 1, null, null, null);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        commentDAO.update(comment);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void delete_shouldDeleteComment() throws SQLException {
        int commentId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        commentDAO.delete(commentId);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void likeComment_shouldAddLike() throws SQLException {
        int commentId = 1;
        int userId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        commentDAO.likeComment(commentId, userId);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void unlikeComment_shouldRemoveLike() throws SQLException {
        int commentId = 1;
        int userId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        commentDAO.unlikeComment(commentId, userId);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void getCommentLikes_shouldReturnLikes() throws SQLException {
        int commentId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getInt("comment_id")).thenReturn(commentId);
        when(mockResultSet.getTimestamp("liked_at")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<CommentLike> likes = commentDAO.getCommentLikes(commentId);

        assertNotNull(likes);
        assertEquals(1, likes.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void getAllByPostId_shouldReturnComments() throws SQLException {
        int postId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("comment_id")).thenReturn(1);
        when(mockResultSet.getString("content")).thenReturn("Test content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("post_id")).thenReturn(postId);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = commentDAO.getAllByPostId(postId);

        assertNotNull(comments);
        assertEquals(1, comments.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void getAllSince_shouldReturnComments() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 1000);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("comment_id")).thenReturn(1);
        when(mockResultSet.getString("content")).thenReturn("Test content");
        when(mockResultSet.getInt("author_id")).thenReturn(1);
        when(mockResultSet.getInt("post_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Comment> comments = commentDAO.getAllSince(timestamp);

        assertNotNull(comments);
        assertEquals(1, comments.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    // Test for SQLException handling
    @Test
    void insert_shouldThrowSQLException() throws SQLException {
        Comment comment = new Comment(0, "Test content", 1, 1, null, null, null);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.insert(comment));
    }

    @Test
    void get_shouldThrowSQLException() throws SQLException {
        int commentId = 1;
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.get(commentId));
    }

    @Test
    void update_shouldThrowSQLException() throws SQLException {
        Comment comment = new Comment(1, "Updated content", 1, 1, null, null, null);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.update(comment));
    }

    @Test
    void delete_shouldThrowSQLException() throws SQLException {
        int commentId = 1;
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.delete(commentId));
    }

    @Test
    void likeComment_shouldThrowSQLException() throws SQLException {
        int commentId = 1;
        int userId = 1;
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.likeComment(commentId, userId));
    }

    @Test
    void unlikeComment_shouldThrowSQLException() throws SQLException {
        int commentId = 1;
        int userId = 1;
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.unlikeComment(commentId, userId));
    }

    @Test
    void getCommentLikes_shouldThrowSQLException() throws SQLException {
        int commentId = 1;
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.getCommentLikes(commentId));
    }

    @Test
    void getAllByPostId_shouldThrowSQLException() throws SQLException {
        int postId = 1;
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.getAllByPostId(postId));
    }

    @Test
    void getAllSince_shouldThrowSQLException() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 1000);
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Test SQL Exception"));

        assertThrows(SQLException.class, () -> commentDAO.getAllSince(timestamp));
    }
}
