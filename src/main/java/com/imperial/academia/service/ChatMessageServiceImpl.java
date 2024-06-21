package com.imperial.academia.service;

import com.imperial.academia.cache.ChatMessageCache;
import com.imperial.academia.data_access.ChatMessageDAI;
import com.imperial.academia.entity.chat_message.ChatMessage;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the ChatMessageService interface.
 * Uses caching to reduce database access.
 */
public class ChatMessageServiceImpl implements ChatMessageService {
    private ChatMessageCache chatMessageCache;
    private ChatMessageDAI chatMessageDAO;

    /**
     * Constructs a new ChatMessageServiceImpl with the specified cache and DAO.
     *
     * @param chatMessageCache the cache to use
     * @param chatMessageDAO the DAO to use
     */
    public ChatMessageServiceImpl(ChatMessageCache chatMessageCache, ChatMessageDAI chatMessageDAO) {
        this.chatMessageCache = chatMessageCache;
        this.chatMessageDAO = chatMessageDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(ChatMessage chatMessage) throws SQLException {
        chatMessageDAO.insert(chatMessage);
        chatMessageCache.setChatMessage("chatmessage:" + chatMessage.getId(), chatMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatMessage get(int id) throws SQLException {
        ChatMessage chatMessage = chatMessageCache.getChatMessage("chatmessage:" + id);
        if (chatMessage == null) {
            chatMessage = chatMessageDAO.get(id);
            if (chatMessage != null) {
                chatMessageCache.setChatMessage("chatmessage:" + id, chatMessage);
            }
        }
        return chatMessage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChatMessage> getAll() throws SQLException {
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages("chatmessages:all");
        if (chatMessages == null) {
            chatMessages = chatMessageDAO.getAll();
            chatMessageCache.setChatMessages("chatmessages:all", chatMessages);
        }
        return chatMessages;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(ChatMessage chatMessage) throws SQLException {
        chatMessageDAO.update(chatMessage);
        chatMessageCache.setChatMessage("chatmessage:" + chatMessage.getId(), chatMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int id) throws SQLException {
        chatMessageDAO.delete(id);
        chatMessageCache.deleteChatMessage("chatmessage:" + id);
    }
}
