package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.WaveformData;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the ChatMessageCache interface using Guava Cache.
 */
public class ChatMessageCacheImpl implements ChatMessageCache {
    private final Cache<String, ChatMessage> chatMessageCache;
    private final Cache<String, List<ChatMessage>> chatMessagesCache;
    private final Cache<String, WaveformData> waveformDataCache;

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

        waveformDataCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(500)
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWaveformData(String key, WaveformData waveformData) {
        waveformDataCache.put(key, waveformData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WaveformData getWaveformData(String key) {
        return waveformDataCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteWaveformData(String key) {
        waveformDataCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsWaveformData(String key) {
        return waveformDataCache.getIfPresent(key) != null;
    }
}
