package com.imperial.academia.service;

import com.imperial.academia.cache.ChatMessageCache;
import com.imperial.academia.data_access.chat_message.ChatMessageDAI;
import com.imperial.academia.entity.chat_message.ChatMessage;

import java.sql.SQLException;
import java.util.List;

public class ChatMessageServiceImpl implements ChatMessageService {
    private ChatMessageCache chatMessageCache;
    private ChatMessageDAI chatMessageDAO;

    public ChatMessageServiceImpl(ChatMessageCache chatMessageCache, ChatMessageDAI chatMessageDAO) {
        this.chatMessageCache = chatMessageCache;
        this.chatMessageDAO = chatMessageDAO;
    }

    @Override
    public void insert(ChatMessage chatMessage) throws SQLException {
        chatMessageDAO.insert(chatMessage);
        chatMessageCache.setChatMessage("chatmessage:" + chatMessage.getId(), chatMessage);
    }

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

    @Override
    public List<ChatMessage> getAll() throws SQLException {
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages("chatmessages:all");
        if (chatMessages == null) {
            chatMessages = chatMessageDAO.getAll();
            chatMessageCache.setChatMessages("chatmessages:all", chatMessages);
        }
        return chatMessages;
    }

    @Override
    public void update(ChatMessage chatMessage) throws SQLException {
        chatMessageDAO.update(chatMessage);
        chatMessageCache.setChatMessage("chatmessage:" + chatMessage.getId(), chatMessage);
    }

    @Override
    public void delete(int id) throws SQLException {
        chatMessageDAO.delete(id);
        chatMessageCache.deleteChatMessage("chatmessage:" + id);
    }
}
