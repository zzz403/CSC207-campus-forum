package com.imperial.academia.service;

import com.imperial.academia.cache.ChatGroupCache;
import com.imperial.academia.data_access.ChatGroupDAI;
import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.session.SessionManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the ChatGroupService interface.
 * Uses caching to reduce database access.
 */
public class ChatGroupServiceImpl implements ChatGroupService {
    private ChatGroupCache chatGroupCache;
    private ChatGroupDAI chatGroupDAO;

    /**
     * Constructs a new ChatGroupServiceImpl with the specified cache and DAO.
     *
     * @param chatGroupCache the cache to use
     * @param chatGroupDAO the DAO to use
     */
    public ChatGroupServiceImpl(ChatGroupCache chatGroupCache, ChatGroupDAI chatGroupDAO) {
        this.chatGroupCache = chatGroupCache;
        this.chatGroupDAO = chatGroupDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(ChatGroup chatGroup) throws SQLException {
        chatGroupDAO.insert(chatGroup);
        chatGroupCache.setChatGroup("chatgroup:" + chatGroup.getId(), chatGroup);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChatGroupDTO> getChatGroupsByGroupName(String groupName) throws SQLException {
        List<ChatGroup> allChatGroups = chatGroupCache.getChatGroups("chatgroups:all");
        if (allChatGroups == null) {
            allChatGroups = getAll();
        }

        List<ChatGroup> matchingChatGroups = new ArrayList<>();
        for (ChatGroup chatGroup : allChatGroups) {
            if (chatGroup.getGroupName().contains(groupName)) {
                matchingChatGroups.add(chatGroup);
            }
        }

        return convertToDTO(matchingChatGroups);
    }

    private List<ChatGroupDTO> convertToDTO(List<ChatGroup> chatGroups) {
        List<ChatGroupDTO> chatGroupDTOs = new ArrayList<>();
        for (ChatGroup chatGroup : chatGroups) {
            chatGroupDTOs.add(new ChatGroupDTO(chatGroup.getId(),chatGroup.getGroupName(), chatGroup.getLastModified()));
        }
        return chatGroupDTOs;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChatGroup> getAll() throws SQLException {
        List<ChatGroup> chatGroups = chatGroupCache.getChatGroups("chatgroups:all");
        if (chatGroups == null) {
            int currentUserId = SessionManager.getCurrentUser().getId();
            chatGroups = chatGroupDAO.getAll(currentUserId);
            chatGroupCache.setChatGroups("chatgroups:all", chatGroups);
        }
        return chatGroups;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ChatGroup chatGroup) throws SQLException {
        chatGroupDAO.update(chatGroup);
        chatGroupCache.setChatGroup("chatgroup:" + chatGroup.getId(), chatGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        chatGroupDAO.delete(id);
        chatGroupCache.deleteChatGroup("chatgroup:" + id);
    }
}
