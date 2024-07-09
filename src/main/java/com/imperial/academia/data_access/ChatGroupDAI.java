package com.imperial.academia.data_access;

import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.user.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Interface for Data Access Object for ChatGroup entities.
 */
public interface ChatGroupDAI {
    /**
     * Inserts a new chat group into the database.
     *
     * @param chatGroup the chat group to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(ChatGroup chatGroup) throws SQLException;

    /**
     * Retrieves a chat group by its ID.
     *
     * @param id the ID of the chat group to retrieve
     * @return the chat group with the specified ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    ChatGroup get(int id) throws SQLException;

    /**
     * Retrieves all chat groups from the database.
     *
     * @return a list of all chat groups
     * @throws SQLException if a database access error occurs
     */
    List<ChatGroup> getAll(int userId) throws SQLException;

    /**
     * Retrieves all chat groups that have been modified since a given timestamp.
     *
     * @param timestamp the timestamp to compare against
     * @return a list of chat groups modified since the specified timestamp
     * @throws SQLException if a database access error occurs
     */
    List<ChatGroup> getAllSince(Timestamp timestamp) throws SQLException;

    /**
     * Updates an existing chat group in the database.
     *
     * @param chatGroup the chat group to update
     * @throws SQLException if a database access error occurs
     */
    void update(ChatGroup chatGroup) throws SQLException;

    /**
     * Deletes a chat group by its ID.
     *
     * @param id the ID of the chat group to delete
     * @throws SQLException if a database access error occurs
     */
    void delete(int id) throws SQLException;

    /**
     * Retrieves the last message in a chat group.
     *
     * @param groupId the ID of the chat group
     * @return the last message in the chat group, or null if the group is empty
     * @throws SQLException if a database access error occurs
     */
    ChatMessage getLastMessage(int groupId) throws SQLException;

    /**
     * Retrieves the avatar URL of a member of a chat group.
     *
     * @param groupId      the ID of the chat group
     * @param excludeUserId the ID of the user to exclude
     * @return user of a member of the chat group, or null if no member is found
     * @throws SQLException if a database access error occurs
     */
    User getMember(int groupId, int excludeUserId) throws SQLException;
}
