package com.imperial.academia.data_access.chat_message;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import com.imperial.academia.entity.chat_message.ChatMessage;

public interface ChatMessageDAI {
    void insert(ChatMessage chatMessage) throws SQLException;
    ChatMessage get(int id) throws SQLException;
    List<ChatMessage> getAll() throws SQLException;
    List<ChatMessage> getAllSince(Timestamp timestamp) throws SQLException;
    void update(ChatMessage chatMessage) throws SQLException;
    void delete(int id) throws SQLException;
}
