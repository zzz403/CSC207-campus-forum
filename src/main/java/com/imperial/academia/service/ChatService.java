package com.imperial.academia.service;

import com.imperial.academia.cache.GuavaCacheManager;

import java.util.ArrayList;
import java.util.List;

public class ChatService {
    private GuavaCacheManager cacheManager;

    public ChatService(GuavaCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    // 模拟从数据库中获取聊天记录
    private List<String> getChatMessagesFromDatabase(String userId) {
        List<String> messages = new ArrayList<>();
        messages.add("Message 1 from user " + userId);
        messages.add("Message 2 from user " + userId);
        // 其他消息
        return messages;
    }

    public List<String> getChatMessages(String userId) {
        String cacheKey = "chat_" + userId;

        // 先尝试从缓存中获取聊天记录
        List<String> cachedMessages = cacheManager.get(cacheKey);
        if (cachedMessages != null) {
            System.out.println("从缓存中获取聊天记录");
            return cachedMessages;
        }

        // 如果缓存中没有，模拟从数据库中获取，并缓存起来
        System.out.println("从数据库中获取聊天记录");
        List<String> dbMessages = getChatMessagesFromDatabase(userId);
        cacheManager.set(cacheKey, dbMessages);
        return dbMessages;
    }

    public void addChatMessage(String userId, String message) {
        String cacheKey = "chat_" + userId;

        // 获取当前的聊天记录
        List<String> messages = cacheManager.get(cacheKey);
        if (messages == null) {
            messages = new ArrayList<>();
        }

        // 添加新消息到聊天记录
        messages.add(message);

        // 更新缓存中的聊天记录
        cacheManager.set(cacheKey, messages);
    }
}
