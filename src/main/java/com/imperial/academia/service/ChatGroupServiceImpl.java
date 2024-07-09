package com.imperial.academia.service;

import com.imperial.academia.cache.ChatGroupCache;
import com.imperial.academia.data_access.ChatGroupDAI;
import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;

import java.util.*;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Implementation of the ChatGroupService interface.
 * Uses caching to reduce database access.
 */
public class ChatGroupServiceImpl implements ChatGroupService {
    private final ChatGroupCache chatGroupCache;
    private final ChatGroupDAI chatGroupDAO;

    /**
     * Constructs a new ChatGroupServiceImpl with the specified cache and DAO.
     *
     * @param chatGroupCache the cache to use
     * @param chatGroupDAO   the DAO to use
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
    public List<ChatGroupDTO> getChatGroupsByGroupName(String serchGroupName) throws SQLException {
        List<ChatGroup> allChatGroups = chatGroupCache.getChatGroups("chatgroups:all");
        if (allChatGroups == null) {
            allChatGroups = getAll();
        }

        List<ChatGroupDTO> matchingChatGroups = new ArrayList<>();
        for (ChatGroup chatGroup : allChatGroups) {
            User user;
            String groupName;
            if (chatGroup.isGroup()) {
                user = null;
                groupName = chatGroup.getGroupName();
            } else {
                user = getUser(chatGroup.getId());
                groupName = chatGroup.isGroup() ? chatGroup.getGroupName() : user.getUsername();
            }

            if (groupName.contains(serchGroupName)) {
                ChatMessage lastMessage = getLastMessage(chatGroup.getId());
                Timestamp lastMessageTime = lastMessage != null ? lastMessage.getTimestamp() : null;
                String avatarUrl;
                if (chatGroup.isGroup()) {
                    avatarUrl = "group_chat_avatar.png";
                } else {
                    assert user != null;
                    avatarUrl = user.getAvatarUrl();
                }
                String lastMessageContent;
                if (lastMessage != null) {
                    if (Objects.equals(lastMessage.getContentType(), "image")) {
                        lastMessageContent = "[Image]";
                    } else if (Objects.equals(lastMessage.getContentType(), "file")) {
                        lastMessageContent = "[File]";
                    } else if (Objects.equals(lastMessage.getContentType(), "audio")) {
                        lastMessageContent = "[Audio]";
                    } else if (Objects.equals(lastMessage.getContentType(), "map")) {
                        lastMessageContent = "[Location]";
                    } else {
                        lastMessageContent = lastMessage.getContent();
                    }
                } else {
                    lastMessageContent = "";
                }
                matchingChatGroups.add(new ChatGroupDTO(
                        chatGroup.getId(),
                        groupName,
                        chatGroup.isGroup(),
                        chatGroup.getLastModified(),
                        lastMessageContent,
                        lastMessageTime,
                        avatarUrl
                ));
            }
        }

        return sortChatGroupsByTime(matchingChatGroups);
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

    @Override
    public ChatMessage getLastMessage(int groupId) throws SQLException {
        String key = "lastMessage:" + groupId;
        ChatMessage lastMessage = chatGroupCache.getLastMessage(key);
        if (lastMessage == null) {
            lastMessage = chatGroupDAO.getLastMessage(groupId);
            if (lastMessage != null) {
                chatGroupCache.setLastMessage(key, lastMessage);
            }
        }
        return lastMessage;
    }

    private User getUser(int groupId) throws SQLException {
        String key = "user:" + groupId;
        User user = chatGroupCache.getUser(key);
        if (user == null) {
            user = chatGroupDAO.getMember(groupId, SessionManager.getCurrentUser().getId());
            if (user != null) {
                chatGroupCache.setUser(key, user);
            }
        }
        return user;
    }

    private List<ChatGroupDTO> sortChatGroupsByTime(List<ChatGroupDTO> chatGroups) {
        chatGroups.sort(new Comparator<ChatGroupDTO>() {
            @Override
            public int compare(ChatGroupDTO cg1, ChatGroupDTO cg2) {
                if (cg1.getLastMessageTime() == null && cg2.getLastMessageTime() == null) {
                    return 0;
                }
                if (cg1.getLastMessageTime() == null) {
                    return 1;
                }
                if (cg2.getLastMessageTime() == null) {
                    return -1;
                }
                return cg2.getLastMessageTime().compareTo(cg1.getLastMessageTime());
            }
        });
        return chatGroups;
    }
}
