package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.chat_message.ChatMessage;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the ChatMessageCache interface using Guava Cache.
 */
public class ChatMessageCacheImpl implements ChatMessageCache {
    private Cache<String, ChatMessage> chatMessageCache;
    private Cache<String, List<ChatMessage>> chatMessagesCache;

    /**
     * Constructs a new ChatMessageCacheImpl with specific cache configurations.
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChatMessage(String key, ChatMessage chatMessage) {
        chatMessageCache.put(key, chatMessage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatMessage getChatMessage(String key) {
        return chatMessageCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteChatMessage(String key) {
        chatMessageCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsChatMessage(String key) {
        return chatMessageCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChatMessages(String key, List<ChatMessage> chatMessages) {
        chatMessagesCache.put(key, chatMessages);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChatMessage> getChatMessages(String key) {
        return chatMessagesCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteChatMessages(String key) {
        chatMessagesCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsChatMessages(String key) {
        return chatMessagesCache.getIfPresent(key) != null;
    }
}
