package com.imperial.academia.cache;

import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.WaveformData;
import java.util.List;

/**
 * Interface for caching chat message entities.
 */
public interface ChatMessageCache {
    /**
     * Caches a single chat message.
     *
     * @param key the key for the chat message
     * @param chatMessage the chat message to cache
     */
    void setChatMessage(String key, ChatMessage chatMessage);

    /**
     * Retrieves a single cached chat message.
     *
     * @param key the key for the chat message
     * @return the cached chat message, or null if not found
     */
    ChatMessage getChatMessage(String key);

    /**
     * Deletes a single cached chat message.
     *
     * @param key the key for the chat message to delete
     */
    void deleteChatMessage(String key);

    /**
     * Checks if a single chat message is cached.
     *
     * @param key the key for the chat message
     * @return true if the chat message is cached, false otherwise
     */
    boolean existsChatMessage(String key);

    /**
     * Caches a list of chat messages.
     *
     * @param key the key for the list of chat messages
     * @param chatMessages the list of chat messages to cache
     */
    void setChatMessages(String key, List<ChatMessage> chatMessages);

    /**
     * Retrieves a list of cached chat messages.
     *
     * @param key the key for the list of chat messages
     * @return the cached list of chat messages, or null if not found
     */
    List<ChatMessage> getChatMessages(String key);

    /**
     * Deletes a list of cached chat messages.
     *
     * @param key the key for the list of chat messages to delete
     */
    void deleteChatMessages(String key);

    /**
     * Checks if a list of chat messages is cached.
     *
     * @param key the key for the list of chat messages
     * @return true if the list of chat messages is cached, false otherwise
     */
    boolean existsChatMessages(String key);

    /**
     * Caches waveform data.
     *
     * @param key the key for the waveform data
     * @param waveformData the waveform data to cache
     */
    void setWaveformData(String key, WaveformData waveformData);

    /**
     * Retrieves cached waveform data.
     *
     * @param key the key for the waveform data
     * @return the cached waveform data, or null if not found
     */
    WaveformData getWaveformData(String key);

    /**
     * Deletes cached waveform data.
     *
     * @param key the key for the waveform data to delete
     */
    void deleteWaveformData(String key);

    /**
     * Checks if waveform data is cached.
     *
     * @param key the key for the waveform data
     * @return true if the waveform data is cached, false otherwise
     */
    boolean existsWaveformData(String key);
}
