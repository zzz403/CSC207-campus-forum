package com.imperial.academia.service;

import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.entity.chat_message.ChatMessage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for Chat Group Service.
 */
public interface ChatGroupService {
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
     * Retrieves all chat groups that have the specified group name.
     *
     * @param groupName the group name to search for
     * @return a list of chat groups with the specified group name
     * @throws SQLException if a database access error occurs
     */
    List<ChatGroupDTO> getChatGroupsByGroupName(String groupName) throws SQLException;

    /**
     * Retrieves all chat groups from the database.
     *
     * @return a list of all chat groups
     * @throws SQLException if a database access error occurs
     */
    List<ChatGroup> getAll() throws SQLException;

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


    ChatMessage getLastMessage(int groupId) throws SQLException;
}
