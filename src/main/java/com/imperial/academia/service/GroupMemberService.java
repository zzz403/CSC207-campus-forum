package com.imperial.academia.service;

import com.imperial.academia.entity.group_member.GroupMember;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface for Group Member Service.
 */
public interface GroupMemberService {
    /**
     * Inserts a new group member into the database.
     *
     * @param groupMember the group member to insert
     * @throws SQLException if a database access error occurs
     */
    void insert(GroupMember groupMember) throws SQLException;

    /**
     * Retrieves a group member by its group ID and user ID.
     *
     * @param groupId the ID of the group
     * @param userId the ID of the user
     * @return the group member with the specified IDs, or null if not found
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
     * Updates an existing group member in the database.
     *
     * @param groupMember the group member to update
     * @throws SQLException if a database access error occurs
     */
    void update(GroupMember groupMember) throws SQLException;

    /**
     * Deletes a group member by its group ID and user ID.
     *
     * @param groupId the ID of the group
     * @param userId the ID of the user
     * @throws SQLException if a database access error occurs
     */
    void delete(int groupId, int userId) throws SQLException;
}
