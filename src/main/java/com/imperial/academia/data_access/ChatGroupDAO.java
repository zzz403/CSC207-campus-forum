package com.imperial.academia.data_access;

import com.imperial.academia.entity.ChatGroup;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupDAO implements BaseDAO<ChatGroup> {
    private Connection conn;

    public ChatGroupDAO() throws SQLException, ClassNotFoundException {
        this.conn = getConnection();
    }

    @Override
    public void insert(ChatGroup chatGroup) throws SQLException {
        String sql = "INSERT INTO chat_groups (group_name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, chatGroup.getGroupName());
            pstmt.executeUpdate();
        }
    }

    @Override
    public ChatGroup get(int id) throws SQLException {
        String sql = "SELECT * FROM chat_groups WHERE group_id = ?";
        ChatGroup chatGroup = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                chatGroup = new ChatGroup(
                    rs.getInt("group_id"),
                    rs.getString("group_name"),
                    rs.getTimestamp("creation_date")
                );
            }
        }
        return chatGroup;
    }

    @Override
    public List<ChatGroup> getAll() throws SQLException {
        String sql = "SELECT * FROM chat_groups";
        List<ChatGroup> chatGroups = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                chatGroups.add(new ChatGroup(
                    rs.getInt("group_id"),
                    rs.getString("group_name"),
                    rs.getTimestamp("creation_date")
                ));
            }
        }
        return chatGroups;
    }

    @Override
    public void update(ChatGroup chatGroup) throws SQLException {
        String sql = "UPDATE chat_groups SET group_name = ? WHERE group_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, chatGroup.getGroupName());
            pstmt.setInt(2, chatGroup.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chat_groups WHERE group_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}

