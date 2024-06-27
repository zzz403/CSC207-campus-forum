package com.imperial.academia.data_access;

import com.imperial.academia.entity.chat_message.ChatMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageDAO implements ChatMessageDAI {
    private Connection conn;

    public ChatMessageDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(ChatMessage chatMessage) throws SQLException {
        String sql = "INSERT INTO chat_messages (sender_id, recipient_id, group_id, content, timestamp) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, chatMessage.getSenderId());
            pstmt.setInt(2, chatMessage.getRecipientId());
            if (chatMessage.getGroupId() != null) {
                pstmt.setInt(3, chatMessage.getGroupId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setString(4, chatMessage.getContent());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chatMessage.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public ChatMessage get(int id) throws SQLException {
        String sql = "SELECT * FROM chat_messages WHERE message_id = ?";
        ChatMessage chatMessage = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    chatMessage = new ChatMessage(
                        rs.getInt("message_id"),
                        rs.getInt("sender_id"),
                        rs.getInt("recipient_id"),
                        rs.getInt("group_id"),
                        rs.getString("content_type"),
                        rs.getString("content"),
                        rs.getTimestamp("timestamp")
                    );
                }
            }
        }
        return chatMessage;
    }

    @Override
    public List<ChatMessage> getAll() throws SQLException {
        String sql = "SELECT * FROM chat_messages";
        List<ChatMessage> chatMessages = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                chatMessages.add(new ChatMessage(
                    rs.getInt("message_id"),
                    rs.getInt("sender_id"),
                    rs.getInt("recipient_id"),
                    rs.getInt("group_id"),
                    rs.getString("content_type"),
                    rs.getString("content"),
                    rs.getTimestamp("timestamp")
                ));
            }
        }
        return chatMessages;
    }

    @Override
    public List<ChatMessage> getAllByGroupId(int groupId) throws SQLException {
        String sql = "SELECT * FROM chat_messages WHERE group_id = ?";
        List<ChatMessage> chatMessages = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    chatMessages.add(new ChatMessage(
                        rs.getInt("message_id"),
                        rs.getInt("sender_id"),
                        rs.getInt("recipient_id"),
                        rs.getInt("group_id"),
                        rs.getString("content_type"),
                        rs.getString("content"),
                        rs.getTimestamp("timestamp")
                    ));
                }
            }
        }
        return chatMessages;
    }

    @Override
    public void update(ChatMessage chatMessage) throws SQLException {
        String sql = "UPDATE chat_messages SET sender_id = ?, recipient_id = ?, group_id = ?, content = ?, timestamp = CURRENT_TIMESTAMP WHERE message_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chatMessage.getSenderId());
            pstmt.setInt(2, chatMessage.getRecipientId());
            if (chatMessage.getGroupId() != null) {
                pstmt.setInt(3, chatMessage.getGroupId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setString(4, chatMessage.getContent());
            pstmt.setInt(5, chatMessage.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chat_messages WHERE message_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
