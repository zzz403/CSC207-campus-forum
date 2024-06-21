package com.imperial.academia.data_access;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import com.imperial.academia.entity.chat_message.ChatMessage;

/**
 * Interface for Data Access Object for ChatMessage entities.
 */
public interface ChatMessageDAI {
    /**
     * Inserts a new chat message into the database.
     *
     * @param chatMessage the chat message to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(ChatMessage chatMessage) throws SQLException;

    /**
     * Retrieves a chat message by its ID.
     *
     * @param id the ID of the chat message to retrieve
     * @return the chat message with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    ChatMessage get(int id) throws SQLException;

    /**
     * Retrieves all chat messages from the database.
     *
     * @return a list of all chat messages
     * @throws SQLException if a database access error occurs
     */
    List<ChatMessage> getAll() throws SQLException;

    /**
     * Retrieves all chat messages that have been created since a given timestamp.
     *
     * @param timestamp the timestamp to compare against
     * @return a list of chat messages created since the specified timestamp
     * @throws SQLException if a database access error occurs
     */
    List<ChatMessage> getAllSince(Timestamp timestamp) throws SQLException;

    /**
     * Updates an existing chat message in the database.
     *
     * @param chatMessage the chat message to update
     * @throws SQLException if a database access error occurs
     */
    void update(ChatMessage chatMessage) throws SQLException;

    /**
     * Deletes a chat message by its ID.
     *
     * @param id the ID of the chat message to delete
     * @throws SQLException if a database access error occurs
     */
    void delete(int id) throws SQLException;
}
