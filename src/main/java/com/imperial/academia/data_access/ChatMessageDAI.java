package com.imperial.academia.data_access;

import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.MapData;
import com.imperial.academia.entity.chat_message.WaveformData;

import java.sql.SQLException;
import java.util.List;

/**
 * The ChatMessageDAI interface defines the data access operations for chat messages
 * and their associated waveform data.
 */
public interface ChatMessageDAI {

    /**
     * Inserts a new chat message into the data store.
     *
     * @param chatMessage The ChatMessage object to be inserted.
     * @throws SQLException If an SQL error occurs during the insertion.
     */
    void insert(ChatMessage chatMessage) throws SQLException;

    /**
     * Inserts waveform data associated with a specific chat message.
     *
     * @param messageId The ID of the chat message.
     * @param waveformData The WaveformData object to be inserted.
     * @throws SQLException If an SQL error occurs during the insertion.
     */
    void insertWaveformData(int messageId, WaveformData waveformData) throws SQLException;

    /**
     * Inserts map data associated with a specific chat message.
     *
     * @param messageId The ID of the chat message.
     * @param mapData The MapData object to be inserted.
     * @throws SQLException If an SQL error occurs during the insertion.
     */
    void insertMapData(int messageId, MapData mapData) throws SQLException;

    /**
     * Retrieves a chat message by its ID.
     *
     * @param id The ID of the chat message to retrieve.
     * @return The ChatMessage object with the specified ID.
     * @throws SQLException If an SQL error occurs during the retrieval.
     */
    ChatMessage get(int id) throws SQLException;

    /**
     * Retrieves all chat messages from the data store.
     *
     * @return A list of all ChatMessage objects.
     * @throws SQLException If an SQL error occurs during the retrieval.
     */
    List<ChatMessage> getAll() throws SQLException;

    /**
     * Retrieves all chat messages associated with a specific group ID.
     *
     * @param groupId The ID of the group whose chat messages are to be retrieved.
     * @return A list of ChatMessage objects associated with the specified group ID.
     * @throws SQLException If an SQL error occurs during the retrieval.
     */
    List<ChatMessage> getAllByGroupId(int groupId) throws SQLException;

    /**
     * Updates an existing chat message in the data store.
     *
     * @param chatMessage The ChatMessage object with updated information.
     * @throws SQLException If an SQL error occurs during the update.
     */
    void update(ChatMessage chatMessage) throws SQLException;

    /**
     * Deletes a chat message by its ID.
     *
     * @param id The ID of the chat message to delete.
     * @throws SQLException If an SQL error occurs during the deletion.
     */
    void delete(int id) throws SQLException;

    /**
     * Retrieves waveform data associated with a specific chat message.
     *
     * @param messageId The ID of the chat message whose waveform data is to be retrieved.
     * @return The WaveformData object associated with the specified chat message ID.
     * @throws SQLException If an SQL error occurs during the retrieval.
     */
    WaveformData getWaveformData(int messageId) throws SQLException;

    /**
     * Retrieves map data associated with a specific chat message.
     *
     * @param messageId The ID of the chat message whose map data is to be retrieved.
     * @return The MapData object associated with the specified chat message ID.
     * @throws SQLException If an SQL error occurs during the retrieval.
     */
    MapData getMapData(int messageId) throws SQLException;
}
