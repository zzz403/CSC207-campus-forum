package com.imperial.academia.data_access;

import com.imperial.academia.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements BaseDAO<User> {
    private Connection conn;

    public UserDAO() throws ClassNotFoundException, SQLException {
        this.conn = getConnection();
    }

    @Override
    public void insert(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.executeUpdate();
        }
    }

    @Override
    public User get(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        User user = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getTimestamp("registration_date")
                );
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("role"),
                    rs.getTimestamp("registration_date")
                ));
            }
        }
        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setInt(5, user.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
