package com.imperial.academia.data_access;

import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.WaveformData;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageDAI {
    void insert(ChatMessage chatMessage) throws SQLException;
    ChatMessage get(int id) throws SQLException;
    List<ChatMessage> getAll() throws SQLException;
    List<ChatMessage> getAllByGroupId(int groupId) throws SQLException;
    void update(ChatMessage chatMessage) throws SQLException;
    void delete(int id) throws SQLException;
    WaveformData getWaveformData(int messageId) throws SQLException;
}
