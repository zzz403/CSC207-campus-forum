package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.entity.chat_message.WaveformData;

import java.sql.SQLException;
import java.util.List;

public interface ChatMessageService {
    void insert(ChatMessage chatMessage) throws SQLException;
    void insert(ChatMessage chatMessage, WaveformData waveformData) throws SQLException;
    ChatMessageDTO get(int id) throws SQLException;
    List<ChatMessageDTO> getAll() throws SQLException;
    List<ChatMessageDTO> getAllByGroupId(int groupId) throws SQLException;
    void update(ChatMessage chatMessage) throws SQLException;
    void delete(int id) throws SQLException;
}
