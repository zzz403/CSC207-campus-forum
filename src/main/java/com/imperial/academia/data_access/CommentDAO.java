package com.imperial.academia.data_access;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the CommentDAI interface using JDBC for data access.
 */
public class CommentDAO implements CommentDAI {
    private Connection conn;

    /**
     * Constructs a new CommentDAO with the specified database connection.
     *
     * @param conn the database connection
     */
    public CommentDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (content, author_id, post_id, parent_comment_id, last_modified) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getAuthorId());
            pstmt.setInt(3, comment.getPostId());
            if (comment.getParentCommentId() != null) {
                pstmt.setInt(4, comment.getParentCommentId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comment.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Comment get(int id) throws SQLException {
        String sql = "SELECT * FROM comments WHERE comment_id = ?";
        Comment comment = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    comment = new Comment(
                        rs.getInt("comment_id"),
                        rs.getString("content"),
                        rs.getInt("author_id"),
                        rs.getInt("post_id"),
                        rs.getInt("parent_comment_id"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_modified")
                    );
                }
            }
        }
        return comment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> getAll() throws SQLException {
        String sql = "SELECT * FROM comments";
        List<Comment> comments = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                comments.add(new Comment(
                    rs.getInt("comment_id"),
                    rs.getString("content"),
                    rs.getInt("author_id"),
                    rs.getInt("post_id"),
                    rs.getInt("parent_comment_id"),
                    rs.getTimestamp("creation_date"),
                    rs.getTimestamp("last_modified")
                ));
            }
        }
        return comments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> getAllSince(Timestamp timestamp) throws SQLException {
        String sql = "SELECT * FROM comments WHERE last_modified > ?";
        List<Comment> comments = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, timestamp);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    comments.add(new Comment(
                        rs.getInt("comment_id"),
                        rs.getString("content"),
                        rs.getInt("author_id"),
                        rs.getInt("post_id"),
                        rs.getInt("parent_comment_id"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_modified")
                    ));
                }
            }
        }
        return comments;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Comment comment) throws SQLException {
        String sql = "UPDATE comments SET content = ?, author_id = ?, post_id = ?, parent_comment_id = ?, last_modified = CURRENT_TIMESTAMP WHERE comment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getAuthorId());
            pstmt.setInt(3, comment.getPostId());
            if (comment.getParentCommentId() != null) {
                pstmt.setInt(4, comment.getParentCommentId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.setInt(5, comment.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Methods for Comment Likes

    /**
     * {@inheritDoc}
     */
    @Override
    public void likeComment(int commentId, int userId) throws SQLException {
        String sql = "INSERT INTO comment_likes (user_id, comment_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, commentId);
            pstmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlikeComment(int commentId, int userId) throws SQLException {
        String sql = "DELETE FROM comment_likes WHERE user_id = ? AND comment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, commentId);
            pstmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CommentLike> getCommentLikes(int commentId) throws SQLException {
        String sql = "SELECT * FROM comment_likes WHERE comment_id = ?";
        List<CommentLike> likes = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, commentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                likes.add(new CommentLike(
                    rs.getInt("user_id"),
                    rs.getInt("comment_id"),
                    rs.getTimestamp("liked_at")
                ));
            }
        }
        return likes;
    }
}
