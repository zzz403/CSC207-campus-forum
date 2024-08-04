package com.imperial.academia.data_access;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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
import org.mockito.Mockito;

import com.imperial.academia.entity.board.Board;

class BoardDAOTest {

    private BoardDAO boardDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private Statement mockStatement;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        mockStatement = Mockito.mock(Statement.class);

        boardDAO = new BoardDAO(mockConnection);
    }

    @Test
    void insert_shouldInsertBoardAndSetId() throws SQLException {
        Board board = new Board(0, "Test Board", "Description", 1, null, null);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        boardDAO.insert(board);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockResultSet, times(1)).next();
        assertEquals(1, board.getId());
    }

    @Test
    void get_shouldReturnBoard() throws SQLException {
        int boardId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("board_id")).thenReturn(boardId);
        when(mockResultSet.getString("name")).thenReturn("Test Board");
        when(mockResultSet.getString("description")).thenReturn("Description");
        when(mockResultSet.getInt("creator_id")).thenReturn(1);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        Board board = boardDAO.get(boardId);

        assertNotNull(board);
        assertEquals(boardId, board.getId());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void get_shouldReturnNullIfBoardNotFound() throws SQLException {
        int boardId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        Board board = boardDAO.get(boardId);

        assertNull(board);
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getAll_shouldReturnAllBoards() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("board_id")).thenReturn(1, 2);
        when(mockResultSet.getString("name")).thenReturn("Test Board 1", "Test Board 2");
        when(mockResultSet.getString("description")).thenReturn("Description 1", "Description 2");
        when(mockResultSet.getInt("creator_id")).thenReturn(1, 2);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Board> boards = boardDAO.getAll();

        assertNotNull(boards);
        assertEquals(2, boards.size());
        verify(mockStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(3)).next();
    }

    @Test
    void getAllSince_shouldReturnBoardsModifiedSince() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 10000);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("board_id")).thenReturn(1, 2);
        when(mockResultSet.getString("name")).thenReturn("Test Board 1", "Test Board 2");
        when(mockResultSet.getString("description")).thenReturn("Description 1", "Description 2");
        when(mockResultSet.getInt("creator_id")).thenReturn(1, 2);
        when(mockResultSet.getTimestamp("created_at")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<Board> boards = boardDAO.getAllSince(timestamp);

        assertNotNull(boards);
        assertEquals(2, boards.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(3)).next();
    }

    @Test
    void update_shouldUpdateBoard() throws SQLException {
        Board board = new Board(1, "Updated Board", "Updated Description", 1, null, null);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        boardDAO.update(board);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setString(1, "Updated Board");
        verify(mockPreparedStatement, times(1)).setString(2, "Updated Description");
        verify(mockPreparedStatement, times(1)).setInt(3, 1);
        verify(mockPreparedStatement, times(1)).setInt(4, 1);
    }

    @Test
    void delete_shouldDeleteBoard() throws SQLException {
        int boardId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        boardDAO.delete(boardId);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setInt(1, boardId);
    }
}
