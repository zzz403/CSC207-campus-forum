package com.imperial.academia.data_access;

import com.imperial.academia.entity.group_member.GroupMember;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Interface for Data Access Object for GroupMember entities.
 */
public interface GroupMemberDAI {
    /**
     * Inserts a new group member into the database.
     *
     * @param groupMember the group member to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(GroupMember groupMember) throws SQLException;

    /**
     * Retrieves a group member by group ID and user ID.
     *
     * @param groupId the group ID
     * @param userId the user ID
     * @return the group member with the specified group ID and user ID, or null if not found
     * @throws SQLException if a database access error occurs
     */
    GroupMember get(int groupId, int userId) throws SQLException;

    /**
     * Retrieves all group members from the database.
     *
     * @return a list of all group members
     * @throws SQLException if a database access error occurs
     */
    List<GroupMember> getAll() throws SQLException;

    /**
     * Retrieves all group members that have been modified since a given timestamp.
     *
     * @param timestamp the timestamp to compare against
     * @return a list of group members modified since the specified timestamp
     * @throws SQLException if a database access error occurs
     */
    List<GroupMember> getAllSince(Timestamp timestamp) throws SQLException;

    /**
     * Updates an existing group member in the database.
     *
     * @param groupMember the group member to update
     * @throws SQLException if a database access error occurs
     */
    void update(GroupMember groupMember) throws SQLException;

    /**
     * Deletes a group member by group ID and user ID.
     *
     * @param groupId the group ID
     * @param userId the user ID
     * @throws SQLException if a database access error occurs
     */
    void delete(int groupId, int userId) throws SQLException;

    int getPrivateChatId(int userId1, int userId2) throws SQLException;
}
