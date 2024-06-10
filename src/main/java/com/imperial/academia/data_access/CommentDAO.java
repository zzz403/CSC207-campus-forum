package com.imperial.academia.data_access;

import com.imperial.academia.entity.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements BaseDAO<Comment> {
    private Connection conn;

    public CommentDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Comment comment) throws SQLException {
        String sql = "INSERT INTO comments (content, author_id, post_id, parent_comment_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, comment.getContent());
            pstmt.setInt(2, comment.getAuthorId());
            pstmt.setInt(3, comment.getPostId());
            if (comment.getParentCommentId() != null) {
                pstmt.setInt(4, comment.getParentCommentId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }
            pstmt.executeUpdate();
        }
    }

    @Override
    public Comment get(int id) throws SQLException {
        String sql = "SELECT * FROM comments WHERE comment_id = ?";
        Comment comment = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
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
}
