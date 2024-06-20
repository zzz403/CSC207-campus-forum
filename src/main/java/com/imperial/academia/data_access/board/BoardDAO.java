package com.imperial.academia.data_access.board;

import com.imperial.academia.entity.Board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO implements BoardDAI {
    private Connection conn;

    public BoardDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Board board) throws SQLException {
        String sql = "INSERT INTO boards (name, description) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, board.getName());
            pstmt.setString(2, board.getDescription());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    board.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Board getByName(String name) throws SQLException {
        String sql = "SELECT * FROM boards WHERE name = ?";
        Board board = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    board = new Board(
                        rs.getInt("board_id"),
                        rs.getString("name"),
                        rs.getString("description")
                    );
                }
            }
        }
        return board;
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

    @Override
    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
