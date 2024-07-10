package com.imperial.academia.data_access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.entity.chat_message.MapData;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The ChatMessageDAO class implements the ChatMessageDAI interface and provides
 * the data access operations for chat messages and their associated waveform
 * data.
 */
public class ChatMessageDAO implements ChatMessageDAI {
    private Connection conn;

    /**
     * Constructs a ChatMessageDAO with the specified database connection.
     *
     * @param conn The database connection to be used by this DAO.
     */
    public ChatMessageDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(ChatMessage chatMessage) throws SQLException {
        System.out.println("ChatMessageDAO: insert");
        String sql = "INSERT INTO chat_messages (sender_id, group_id, content_type, content, timestamp) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, chatMessage.getSenderId());
            if (chatMessage.getGroupId() != null) {
                pstmt.setInt(2, chatMessage.getGroupId());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setString(3, chatMessage.getContentType());
            pstmt.setString(4, chatMessage.getContent());
            System.out.println("ChatMessageDAO: insert: pstmt = " + pstmt);
            pstmt.executeUpdate();
            System.out.println("ChatMessageDAO: insert: pstmt.executeUpdate()");

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    chatMessage.setId(generatedKeys.getInt(1));
                    System.out.println("ChatMessageDAO: insert: chatMessage.getId() = " + chatMessage.getId());
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertWaveformData(int chatMessageId, WaveformData waveformData) throws SQLException {
        String sql = "INSERT INTO audio_waveforms (message_id, min_values, max_values, duration) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, chatMessageId);
            ps.setString(2, waveformData.getMinValues().toString());
            ps.setString(3, waveformData.getMaxValues().toString());
            ps.setFloat(4, waveformData.getDuration());
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertMapData(int chatMessageId, MapData mapData) throws SQLException {
        String sql = "INSERT INTO map_data (message_id, latitude, longitude, location_info) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, chatMessageId);
            ps.setDouble(2, mapData.getLatitude());
            ps.setDouble(3, mapData.getLongitude());
            ps.setString(4, mapData.getLocationInfo());
            ps.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
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
                            rs.getInt("group_id"),
                            rs.getString("content_type"),
                            rs.getString("content"),
                            rs.getTimestamp("timestamp"));
                }
            }
        }
        return chatMessage;
    }

    /**
     * {@inheritDoc}
     */
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
                        rs.getInt("group_id"),
                        rs.getString("content_type"),
                        rs.getString("content"),
                        rs.getTimestamp("timestamp")));
            }
        }
        return chatMessages;
    }

    /**
     * {@inheritDoc}
     */
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
                            rs.getInt("group_id"),
                            rs.getString("content_type"),
                            rs.getString("content"),
                            rs.getTimestamp("timestamp")));
                }
            }
        }
        return chatMessages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ChatMessage chatMessage) throws SQLException {
        String sql = "UPDATE chat_messages SET sender_id = ?, group_id = ?, content = ?, timestamp = CURRENT_TIMESTAMP WHERE message_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chatMessage.getSenderId());
            if (chatMessage.getGroupId() != null) {
                pstmt.setInt(2, chatMessage.getGroupId());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            pstmt.setString(3, chatMessage.getContent());
            pstmt.setInt(4, chatMessage.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM chat_messages WHERE message_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveformData getWaveformData(int messageId) throws SQLException {
        String sql = "SELECT min_values, max_values, duration FROM audio_waveforms WHERE message_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("ChatMessageDAO: getWaveformData: pstmt = " + pstmt);
            pstmt.setInt(1, messageId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ObjectMapper mapper = new ObjectMapper();
                    @SuppressWarnings("unchecked")
                    List<Integer> minValues = mapper.readValue(rs.getString("min_values"), List.class);
                    @SuppressWarnings("unchecked")
                    List<Integer> maxValues = mapper.readValue(rs.getString("max_values"), List.class);
                    float duration = rs.getFloat("duration");
                    return new WaveformData(minValues, maxValues, duration);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new SQLException("Error parsing waveform data", e);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapData getMapData(int messageId) throws SQLException {
        String sql = "SELECT latitude, longitude, location_info FROM map_data WHERE message_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, messageId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double latitude = rs.getDouble("latitude");
                    double longitude = rs.getDouble("longitude");
                    String locationInfo = rs.getString("location_info");
                    return new MapData(latitude, longitude, locationInfo);
                }
            }
        }
        return null;
    }
}
