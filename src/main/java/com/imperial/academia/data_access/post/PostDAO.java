package com.imperial.academia.data_access.post;

import com.imperial.academia.entity.Post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO implements PostDAI {
    private Connection conn;

    public PostDAO(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Post post) throws SQLException {
        String sql = "INSERT INTO posts (title, content, author_id, board_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setInt(3, post.getAuthorId());
            pstmt.setInt(4, post.getBoardId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Post get(int id) throws SQLException {
        String sql = "SELECT * FROM posts WHERE post_id = ?";
        Post post = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                post = new Post(
                    rs.getInt("post_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("author_id"),
                    rs.getInt("board_id"),
                    rs.getTimestamp("creation_date"),
                    rs.getTimestamp("last_modified_date")
                );
            }
        }
        return post;
    }

    @Override
    public List<Post> getAll() throws SQLException {
        String sql = "SELECT * FROM posts";
        List<Post> posts = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                posts.add(new Post(
                    rs.getInt("post_id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getInt("author_id"),
                    rs.getInt("board_id"),
                    rs.getTimestamp("creation_date"),
                    rs.getTimestamp("last_modified_date")
                ));
            }
        }
        return posts;
    }

    @Override
    public void update(Post post) throws SQLException {
        String sql = "UPDATE posts SET title = ?, content = ?, author_id = ?, board_id = ? WHERE post_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setInt(3, post.getAuthorId());
            pstmt.setInt(4, post.getBoardId());
            pstmt.setInt(5, post.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM posts WHERE post_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}