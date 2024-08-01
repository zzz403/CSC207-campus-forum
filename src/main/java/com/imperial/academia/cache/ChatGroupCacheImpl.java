package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.user.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the ChatGroupCache interface using Guava Cache.
 */
public class ChatGroupCacheImpl implements ChatGroupCache {
    private final Cache<String, ChatGroup> chatGroupCache;
    private final Cache<String, List<ChatGroup>> chatGroupsCache;
    private final Cache<String, ChatMessage> lastMessageCache;
    private final Cache<String, User> avatarUrlCache;

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

        lastMessageCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();

        avatarUrlCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    /**
     * Constructs a new ChatGroupCacheImpl with the specified caches.
     * @param chatGroupCache
     * @param chatGroupsCache
     * @param lastMessageCache
     * @param avatarUrlCache
     */
    public ChatGroupCacheImpl(Cache<String, ChatGroup> chatGroupCache, Cache<String, List<ChatGroup>> chatGroupsCache, Cache<String, ChatMessage> lastMessageCache, Cache<String, User> avatarUrlCache) {
        this.chatGroupCache = chatGroupCache;
        this.chatGroupsCache = chatGroupsCache;
        this.lastMessageCache = lastMessageCache;
        this.avatarUrlCache = avatarUrlCache;
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

    @Override
    public void setLastMessage(String key, ChatMessage lastMessage) {
        lastMessageCache.put(key, lastMessage);
    }

    @Override
    public ChatMessage getLastMessage(String key) {
        return lastMessageCache.getIfPresent(key);
    }

    @Override
    public void deleteLastMessage(String key) {
        lastMessageCache.invalidate(key);
    }

    @Override
    public boolean existsLastMessage(String key) {
        return lastMessageCache.getIfPresent(key) != null;
    }

    @Override
    public void setUser(String key, User user) {
        avatarUrlCache.put(key, user);
    }

    @Override
    public User getUser(String key) {
        return avatarUrlCache.getIfPresent(key);
    }

    @Override
    public void deleteUser(String key) {
        avatarUrlCache.invalidate(key);
    }

    @Override
    public boolean existsUser(String key) {
        return avatarUrlCache.getIfPresent(key) != null;
    }

}
