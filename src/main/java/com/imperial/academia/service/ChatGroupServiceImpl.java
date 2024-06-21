package com.imperial.academia.service;

import com.imperial.academia.cache.ChatGroupCache;
import com.imperial.academia.data_access.chat_group.ChatGroupDAI;
import com.imperial.academia.entity.chat_group.ChatGroup;

import java.sql.SQLException;
import java.util.List;

public class ChatGroupServiceImpl implements ChatGroupService {
    private ChatGroupCache chatGroupCache;
    private ChatGroupDAI chatGroupDAO;

    public ChatGroupServiceImpl(ChatGroupCache chatGroupCache, ChatGroupDAI chatGroupDAO) {
        this.chatGroupCache = chatGroupCache;
        this.chatGroupDAO = chatGroupDAO;
    }

    @Override
    public void insert(ChatGroup chatGroup) throws SQLException {
        chatGroupDAO.insert(chatGroup);
        chatGroupCache.setChatGroup("chatgroup:" + chatGroup.getId(), chatGroup);
    }

    @Override
    public ChatGroup get(int id) throws SQLException {
        ChatGroup chatGroup = chatGroupCache.getChatGroup("chatgroup:" + id);
        if (chatGroup == null) {
            chatGroup = chatGroupDAO.get(id);
            if (chatGroup != null) {
                chatGroupCache.setChatGroup("chatgroup:" + id, chatGroup);
            }
        }
        return chatGroup;
    }

    @Override
    public List<ChatGroup> getAll() throws SQLException {
        List<ChatGroup> chatGroups = chatGroupCache.getChatGroups("chatgroups:all");
        if (chatGroups == null) {
            chatGroups = chatGroupDAO.getAll();
            chatGroupCache.setChatGroups("chatgroups:all", chatGroups);
        }
        return chatGroups;
    }

    @Override
    public void update(ChatGroup chatGroup) throws SQLException {
        chatGroupDAO.update(chatGroup);
        chatGroupCache.setChatGroup("chatgroup:" + chatGroup.getId(), chatGroup);
    }

    @Override
    public void delete(int id) throws SQLException {
        chatGroupDAO.delete(id);
        chatGroupCache.deleteChatGroup("chatgroup:" + id);
    }
}
