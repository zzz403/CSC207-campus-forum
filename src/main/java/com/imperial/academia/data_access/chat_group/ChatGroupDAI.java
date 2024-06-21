package com.imperial.academia.data_access.chat_group;

import com.imperial.academia.entity.chat_group.ChatGroup;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface ChatGroupDAI {
    void insert(ChatGroup chatGroup) throws SQLException;
    ChatGroup get(int id) throws SQLException;
    List<ChatGroup> getAll() throws SQLException;
    List<ChatGroup> getAllSince(Timestamp timestamp) throws SQLException;
    void update(ChatGroup chatGroup) throws SQLException;
    void delete(int id) throws SQLException;
}
