package com.imperial.academia.data_access;

import java.sql.Timestamp;
import com.imperial.academia.entity.board.Board;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the BoardDAI interface using JDBC for data access.
 */
public class BoardDAO implements BoardDAI {
    private Connection conn;

    /**
     * Constructs a new BoardDAO with the specified database connection.
     *
     * @param conn the database connection
     */
    public BoardDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Board board) throws SQLException {
        String sql = "INSERT INTO boards (name, description, creator_id, last_modified) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, board.getName());
            pstmt.setString(2, board.getDescription());
            pstmt.setInt(3, board.getCreatorId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    board.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Board get(int id) throws SQLException {
        String sql = "SELECT * FROM boards WHERE board_id = ?";
        Board board = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    board = new Board(
                        rs.getInt("board_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("creator_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("last_modified")
                    );
                }
            }
        }
        return board;
    }

    /**
     * {@inheritDoc}
     */
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
                    rs.getString("description"),
                    rs.getInt("creator_id"),
                    rs.getTimestamp("created_at"),
                    rs.getTimestamp("last_modified")
                ));
            }
        }
        System.out.println(boards);
        return boards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Board> getAllSince(Timestamp timestamp) throws SQLException {
        String sql = "SELECT * FROM boards WHERE last_modified > ?";
        List<Board> boards = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, timestamp);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    boards.add(new Board(
                        rs.getInt("board_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("creator_id"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("last_modified")
                    ));
                }
            }
        }
        return boards;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Board board) throws SQLException {
        String sql = "UPDATE boards SET name = ?, description = ?, creator_id = ?, last_modified = CURRENT_TIMESTAMP WHERE board_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, board.getName());
            pstmt.setString(2, board.getDescription());
            pstmt.setInt(3, board.getCreatorId());
            pstmt.setInt(4, board.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM boards WHERE board_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
