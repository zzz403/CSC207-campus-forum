package com.imperial.academia.cache;

import com.imperial.academia.entity.chat_group.ChatGroup;
import java.util.List;

/**
 * Interface for caching chat group entities.
 */
public interface ChatGroupCache {
    /**
     * Caches a single chat group.
     *
     * @param key the key for the chat group
     * @param chatGroup the chat group to cache
     */
    void setChatGroup(String key, ChatGroup chatGroup);

    /**
     * Retrieves a single cached chat group.
     *
     * @param key the key for the chat group
     * @return the cached chat group, or null if not found
     */
    ChatGroup getChatGroup(String key);

    /**
     * Deletes a single cached chat group.
     *
     * @param key the key for the chat group to delete
     */
    void deleteChatGroup(String key);

    /**
     * Checks if a single chat group is cached.
     *
     * @param key the key for the chat group
     * @return true if the chat group is cached, false otherwise
     */
    boolean existsChatGroup(String key);

    /**
     * Caches a list of chat groups.
     *
     * @param key the key for the list of chat groups
     * @param chatGroups the list of chat groups to cache
     */
    void setChatGroups(String key, List<ChatGroup> chatGroups);

    /**
     * Retrieves a list of cached chat groups.
     *
     * @param key the key for the list of chat groups
     * @return the cached list of chat groups, or null if not found
     */
    List<ChatGroup> getChatGroups(String key);

    /**
     * Deletes a list of cached chat groups.
     *
     * @param key the key for the list of chat groups to delete
     */
    void deleteChatGroups(String key);

    /**
     * Checks if a list of chat groups is cached.
     *
     * @param key the key for the list of chat groups
     * @return true if the list of chat groups is cached, false otherwise
     */
    boolean existsChatGroups(String key);
}
