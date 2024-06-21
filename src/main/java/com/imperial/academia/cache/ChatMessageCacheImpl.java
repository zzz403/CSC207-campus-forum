package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.chat_message.ChatMessage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatMessageCacheImpl implements ChatMessageCache {
    private Cache<String, ChatMessage> chatMessageCache;
    private Cache<String, List<ChatMessage>> chatMessagesCache;

    public ChatMessageCacheImpl() {
        chatMessageCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        chatMessagesCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    @Override
    public void setChatMessage(String key, ChatMessage chatMessage) {
        chatMessageCache.put(key, chatMessage);
    }

    @Override
    public ChatMessage getChatMessage(String key) {
        return chatMessageCache.getIfPresent(key);
    }

    @Override
    public void deleteChatMessage(String key) {
        chatMessageCache.invalidate(key);
    }

    @Override
    public boolean existsChatMessage(String key) {
        return chatMessageCache.getIfPresent(key) != null;
    }

    @Override
    public void setChatMessages(String key, List<ChatMessage> chatMessages) {
        chatMessagesCache.put(key, chatMessages);
    }

    @Override
    public List<ChatMessage> getChatMessages(String key) {
        return chatMessagesCache.getIfPresent(key);
    }

    @Override
    public void deleteChatMessages(String key) {
        chatMessagesCache.invalidate(key);
    }

    @Override
    public boolean existsChatMessages(String key) {
        return chatMessagesCache.getIfPresent(key) != null;
    }
}
