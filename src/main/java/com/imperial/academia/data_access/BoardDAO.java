package com.imperial.academia.data_access;

import com.imperial.academia.entity.Board;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO implements BaseDAO<Board> {
    private Connection conn;

    public BoardDAO() throws SQLException, ClassNotFoundException {
        this.conn = getConnection();
    }

    @Override
    public void insert(Board board) throws SQLException {
        String sql = "INSERT INTO boards (name, description) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, board.getName());
            pstmt.setString(2, board.getDescription());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Board get(int id) throws SQLException {
        String sql = "SELECT * FROM boards WHERE board_id = ?";
        Board board = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                board = new Board(
                    rs.getInt("board_id"),
                    rs.getString("name"),
                    rs.getString("description")
                );
            }
        }
        return board;
    }

    @Override
    public List<Board> getAll() throws SQLException {
        String sql = "SELECT * FROM boards";
        List<Board> boards = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                boards.add(new Board(
                    rs.getInt("board_id"),
                    rs.getString("name"),
                    rs.getString("description")
                ));
            }
        }
        return boards;
    }

    @Override
    public void update(Board board) throws SQLException {
        String sql = "UPDATE boards SET name = ?, description = ? WHERE board_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, board.getName());
            pstmt.setString(2, board.getDescription());
            pstmt.setInt(3, board.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM boards WHERE board_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

