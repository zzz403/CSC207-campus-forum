package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.chat_group.ChatGroup;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the ChatGroupCache interface using Guava Cache.
 */
public class ChatGroupCacheImpl implements ChatGroupCache {
    private Cache<String, ChatGroup> chatGroupCache;
    private Cache<String, List<ChatGroup>> chatGroupsCache;

    /**
     * Constructs a new ChatGroupCacheImpl with specific cache configurations.
     */
    public ChatGroupCacheImpl() {
        chatGroupCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        chatGroupsCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChatGroup(String key, ChatGroup chatGroup) {
        chatGroupCache.put(key, chatGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ChatGroup getChatGroup(String key) {
        return chatGroupCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteChatGroup(String key) {
        chatGroupCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsChatGroup(String key) {
        return chatGroupCache.getIfPresent(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setChatGroups(String key, List<ChatGroup> chatGroups) {
        chatGroupsCache.put(key, chatGroups);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ChatGroup> getChatGroups(String key) {
        return chatGroupsCache.getIfPresent(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteChatGroups(String key) {
        chatGroupsCache.invalidate(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsChatGroups(String key) {
        return chatGroupsCache.getIfPresent(key) != null;
    }
}
