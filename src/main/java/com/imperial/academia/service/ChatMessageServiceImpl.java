package com.imperial.academia.service;

import com.imperial.academia.data_access.ChatMessageDAI;
import com.imperial.academia.data_access.UserDAI;
import com.imperial.academia.entity.chat_message.*;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.cache.ChatMessageCache;
import com.imperial.academia.cache.UserCache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageDAI chatMessageDAO;
    private final UserDAI userDAO;
    private final ChatMessageCache chatMessageCache;
    private final UserCache userCache;

    public ChatMessageServiceImpl(ChatMessageDAI chatMessageDAO, UserDAI userDAO, ChatMessageCache chatMessageCache,
                                  UserCache userCache) {
        this.chatMessageDAO = chatMessageDAO;
        this.userDAO = userDAO;
        this.chatMessageCache = chatMessageCache;
        this.userCache = userCache;
    }

    @Override
    public void insert(ChatMessage chatMessage) throws SQLException {
        chatMessageDAO.insert(chatMessage);
        // 更新单条消息缓存
        chatMessageCache.setChatMessage("chatMessage:" + chatMessage.getId(), chatMessage);
        chatMessageCache.deleteChatMessages("chatMessages:all");

        // 更新群组消息列表缓存
        String groupCacheKey = "chatMessages:group:" + chatMessage.getGroupId();
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages(groupCacheKey);
        if (chatMessages != null) {
            chatMessages.add(chatMessage);
            chatMessageCache.setChatMessages(groupCacheKey, chatMessages);
        }
    }

    @Override
    public void insert(ChatMessage chatMessage, WaveformData waveformData) throws SQLException {
        // 插入 ChatMessage
        chatMessageDAO.insert(chatMessage);
        // 更新单条消息缓存
        chatMessageCache.setChatMessage("chatMessage:" + chatMessage.getId(), chatMessage);

        // 如果消息类型为音频，则插入并缓存 WaveformData
        if (waveformData != null) {
            chatMessageDAO.insertWaveformData(chatMessage.getId(), waveformData);
            chatMessageCache.setWaveformData("waveformData:" + chatMessage.getId(), waveformData);
        }

        // 更新群组消息列表缓存
        String groupCacheKey = "chatMessages:group:" + chatMessage.getGroupId();
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages(groupCacheKey);
        if (chatMessages != null) {
            chatMessages.add(chatMessage);
            chatMessageCache.setChatMessages(groupCacheKey, chatMessages);
        }
    }

    @Override
    public void insert(ChatMessage chatMessage, MapData mapData) throws SQLException {
        // 插入 ChatMessage
        chatMessageDAO.insert(chatMessage);
        // 更新单条消息缓存
        chatMessageCache.setChatMessage("chatMessage:" + chatMessage.getId(), chatMessage);

        // 如果消息类型为地图，则插入并缓存 MapData
        if (mapData != null) {
            chatMessageDAO.insertMapData(chatMessage.getId(), mapData);
            chatMessageCache.setMapData("mapData:" + chatMessage.getId(), mapData);
        }

        String groupCacheKey = "chatMessages:group:" + chatMessage.getGroupId();
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages(groupCacheKey);
        if (chatMessages != null) {
            chatMessages.add(chatMessage);
            chatMessageCache.setChatMessages(groupCacheKey, chatMessages);
        }
    }

    @Override
    public void insert(ChatMessage chatMessage, FileData fileData) throws SQLException {
        // 插入 ChatMessage
        chatMessageDAO.insert(chatMessage);
        // 更新单条消息缓存
        chatMessageCache.setChatMessage("chatMessage:" + chatMessage.getId(), chatMessage);

        // 如果消息类型为文件，则插入并缓存 FileData
        if (fileData != null) {
            chatMessageDAO.insertFileData(chatMessage.getId(), fileData);
            chatMessageCache.setFileData("fileData:" + chatMessage.getId(), fileData);
        }

        String groupCacheKey = "chatMessages:group:" + chatMessage.getGroupId();
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages(groupCacheKey);
        if (chatMessages != null) {
            chatMessages.add(chatMessage);
            chatMessageCache.setChatMessages(groupCacheKey, chatMessages);
        }
    }

    @Override
    public ChatMessageDTO get(int id) throws SQLException {
        ChatMessage chatMessage = chatMessageCache.getChatMessage("chatMessage:" + id);
        if (chatMessage == null) {
            chatMessage = chatMessageDAO.get(id);
            if (chatMessage != null) {
                chatMessageCache.setChatMessage("chatMessage:" + id, chatMessage);
            }
        }

        if (chatMessage != null) {
            User user = userCache.getUser("user:" + chatMessage.getSenderId());
            if (user == null) {
                user = userDAO.get(chatMessage.getSenderId());
                if (user != null) {
                    userCache.setUser("user:" + chatMessage.getSenderId(), user);
                }
            }
            assert user != null;
            @SuppressWarnings("null")
            ChatMessageDTO chatMessageDTO = new ChatMessageDTO(
                    chatMessage.getId(),
                    chatMessage.getSenderId(),
                    user.getUsername(),
                    user.getAvatarUrl(),
                    chatMessage.getGroupId(),
                    chatMessage.getContentType(),
                    chatMessage.getContent(),
                    chatMessage.getId() == SessionManager.getCurrentUser().getId(),
                    chatMessage.getTimestamp());

            if ("audio".equals(chatMessage.getContentType())) {
                WaveformData waveformData = chatMessageCache.getWaveformData("waveformData:" + chatMessage.getId());
                if (waveformData == null) {
                    waveformData = chatMessageDAO.getWaveformData(chatMessage.getId());
                    chatMessageCache.setWaveformData("waveformData:" + chatMessage.getId(), waveformData);
                }
                chatMessageDTO.setWaveformData(waveformData);
            }

            return chatMessageDTO;
        }
        return null;
    }

    @Override
    public List<ChatMessageDTO> getAll() throws SQLException {
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages("chatMessages:all");
        if (chatMessages == null) {
            chatMessages = chatMessageDAO.getAll();
            chatMessageCache.setChatMessages("chatMessages:all", chatMessages);
        }
        return convertToDTOs(chatMessages);
    }

    @Override
    public List<ChatMessageDTO> getAllByGroupId(int groupId) throws SQLException {
        List<ChatMessage> chatMessages = chatMessageCache.getChatMessages("chatMessages:group:" + groupId);
        if (chatMessages == null) {
            chatMessages = chatMessageDAO.getAllByGroupId(groupId);
            chatMessageCache.setChatMessages("chatMessages:group:" + groupId, chatMessages);
        }
        return convertToDTOs(chatMessages);
    }

    @Override
    public void update(ChatMessage chatMessage) throws SQLException {
        chatMessageDAO.update(chatMessage);
        chatMessageCache.setChatMessage("chatMessage:" + chatMessage.getId(), chatMessage);
    }

    @Override
    public void delete(int id) throws SQLException {
        chatMessageDAO.delete(id);
        chatMessageCache.deleteChatMessage("chatMessage:" + id);
    }

    private List<ChatMessageDTO> convertToDTOs(List<ChatMessage> chatMessages) throws SQLException {
        List<ChatMessageDTO> chatMessageDTOs = new ArrayList<>();
        for (ChatMessage chatMessage : chatMessages) {
            User user = userCache.getUser("user:" + chatMessage.getSenderId());
            if (user == null) {
                user = userDAO.get(chatMessage.getSenderId());
                if (user != null) {
                    userCache.setUser("user:" + chatMessage.getSenderId(), user);
                }
            }
            @SuppressWarnings("null")
            ChatMessageDTO chatMessageDTO = new ChatMessageDTO(
                    chatMessage.getId(),
                    chatMessage.getSenderId(),
                    user.getUsername(),
                    user.getAvatarUrl(),
                    chatMessage.getGroupId(),
                    chatMessage.getContentType(),
                    chatMessage.getContent(),
                    chatMessage.getSenderId() == SessionManager.getCurrentUser().getId(),
                    chatMessage.getTimestamp());

            if ("audio".equals(chatMessage.getContentType())) {
                WaveformData waveformData = chatMessageCache.getWaveformData("waveformData:" + chatMessage.getId());
                if (waveformData == null) {
                    waveformData = chatMessageDAO.getWaveformData(chatMessage.getId());
                    chatMessageCache.setWaveformData("waveformData:" + chatMessage.getId(), waveformData);
                }
                chatMessageDTO.setWaveformData(waveformData);
            }

            if ("map".equals(chatMessage.getContentType())) {
                MapData mapData = chatMessageCache.getMapData("mapData:" + chatMessage.getId());
                if (mapData == null) {
                    mapData = chatMessageDAO.getMapData(chatMessage.getId());
                    chatMessageCache.setMapData("mapData:" + chatMessage.getId(), mapData);
                }
                chatMessageDTO.setMapData(mapData);
            }

            if ("file".equals(chatMessage.getContentType())) {
                FileData fileData = chatMessageCache.getFileData("fileData:" + chatMessage.getId());
                if (fileData == null) {
                    fileData = chatMessageDAO.getFileData(chatMessage.getId());
                    chatMessageCache.setFileData("fileData:" + chatMessage.getId(), fileData);
                }
                chatMessageDTO.setFileData(fileData);
            }

            chatMessageDTOs.add(chatMessageDTO);
        }
        return chatMessageDTOs;
    }
}
