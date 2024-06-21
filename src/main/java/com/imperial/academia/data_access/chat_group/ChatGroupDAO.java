package com.imperial.academia.data_access.chat_group;

import com.imperial.academia.entity.chat_group.ChatGroup;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGroupDAO implements ChatGroupDAI {
    private Connection conn;

    public ChatGroupDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(ChatGroup chatGroup) throws SQLException {
        String sql = "INSERT INTO chat_groups (group_name, last_modified) VALUES (?, CURRENT_TIMESTAMP)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, chatGroup.getGroupName());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chatGroup.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public ChatGroup get(int id) throws SQLException {
        String sql = "SELECT * FROM chat_groups WHERE group_id = ?";
        ChatGroup chatGroup = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    chatGroup = new ChatGroup(
                        rs.getInt("group_id"),
                        rs.getString("group_name"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_modified")
                    );
                }
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
                    rs.getTimestamp("creation_date"),
                    rs.getTimestamp("last_modified")
                ));
            }
        }
        return chatGroups;
    }

    @Override
    public List<ChatGroup> getAllSince(Timestamp timestamp) throws SQLException {
        String sql = "SELECT * FROM chat_groups WHERE last_modified > ?";
        List<ChatGroup> chatGroups = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setTimestamp(1, timestamp);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    chatGroups.add(new ChatGroup(
                        rs.getInt("group_id"),
                        rs.getString("group_name"),
                        rs.getTimestamp("creation_date"),
                        rs.getTimestamp("last_modified")
                    ));
                }
            }
        }
        return chatGroups;
    }

    @Override
    public void update(ChatGroup chatGroup) throws SQLException {
        String sql = "UPDATE chat_groups SET group_name = ?, last_modified = CURRENT_TIMESTAMP WHERE group_id = ?";
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
