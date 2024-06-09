package com.imperial.academia.data_access;

import com.imperial.academia.entity.ChatMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageDAO implements BaseDAO<ChatMessage> {
    private Connection conn;

    public ChatMessageDAO() throws SQLException, ClassNotFoundException {
        this.conn = getConnection();
    }

    @Override
    public void insert(ChatMessage chatMessage) throws SQLException {
        String sql = "INSERT INTO chat_messages (sender_id, recipient_id, group_id, content) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chatMessage.getSenderId());
            pstmt.setInt(2, chatMessage.getRecipientId());
            if (chatMessage.getGroupId() != null) {
                pstmt.setInt(3, chatMessage.getGroupId());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setString(4, chatMessage.getContent());
            pstmt.executeUpdate();
        }
    }

    @Override
    public ChatMessage get(int id) throws SQLException {
        String sql = "SELECT * FROM chat_messages WHERE message_id = ?";
        ChatMessage chatMessage = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                chatMessage = new ChatMessage(
                    rs.getInt("message_id"),
                    rs.getInt("sender_id"),
                    rs.getInt("recipient_id"),
                    rs.getInt("group_id"),
                    rs.getString("content"),
                    rs.getTimestamp("timestamp")
                );
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
                    rs.getString("content"),
                    rs.getTimestamp("timestamp")
                ));
            }
        }
        return chatMessages;
    }

    @Override
    public void update(ChatMessage chatMessage) throws SQLException {
        String sql = "UPDATE chat_messages SET sender_id = ?, recipient_id = ?, group_id = ?, content = ? WHERE message_id = ?";
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
