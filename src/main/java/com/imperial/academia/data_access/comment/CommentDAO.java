package com.imperial.academia.data_access.comment;

import com.imperial.academia.entity.comment.Comment;
import com.imperial.academia.entity.comment.CommentLike;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements CommentDAI {
    private Connection conn;

    public CommentDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (content, author_id, post_id, parent_comment_id) VALUES (?, ?, ?, ?)";
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
                        rs.getTimestamp("creation_date")
                    );
                }
            }
        }
        return comment;
    }

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
                    rs.getTimestamp("creation_date")
                ));
            }
        }
        return comments;
    }

    @Override
    public void update(Comment comment) throws SQLException {
        String sql = "UPDATE comments SET content = ?, author_id = ?, post_id = ?, parent_comment_id = ? WHERE comment_id = ?";
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

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM comments WHERE comment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Methods for Comment Likes
    @Override
    public void likeComment(int commentId, int userId) throws SQLException {
        String sql = "INSERT INTO comment_likes (user_id, comment_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, commentId);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void unlikeComment(int commentId, int userId) throws SQLException {
        String sql = "DELETE FROM comment_likes WHERE user_id = ? AND comment_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, commentId);
            pstmt.executeUpdate();
        }
    }

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
