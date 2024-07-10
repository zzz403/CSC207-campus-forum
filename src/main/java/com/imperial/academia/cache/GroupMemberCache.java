package com.imperial.academia.cache;

import com.imperial.academia.entity.group_member.GroupMember;
import java.util.List;

/**
 * Interface for caching group member entities.
 */
public interface GroupMemberCache {
    /**
     * Caches a single group member.
     *
     * @param key the key for the group member
     * @param groupMember the group member to cache
     */
    void setGroupMember(String key, GroupMember groupMember);

    /**
     * Retrieves a single cached group member.
     *
     * @param key the key for the group member
     * @return the cached group member, or null if not found
     */
    GroupMember getGroupMember(String key);

    /**
     * Deletes a single cached group member.
     *
     * @param key the key for the group member to delete
     */
    void deleteGroupMember(String key);

    /**
     * Checks if a single group member is cached.
     *
     * @param key the key for the group member
     * @return true if the group member is cached, false otherwise
     */
    boolean existsGroupMember(String key);

    /**
     * Caches a list of group members.
     *
     * @param key the key for the list of group members
     * @param groupMembers the list of group members to cache
     */
    void setGroupMembers(String key, List<GroupMember> groupMembers);

    /**
     * Retrieves a list of cached group members.
     *
     * @param key the key for the list of group members
     * @return the cached list of group members, or null if not found
     */
    List<GroupMember> getGroupMembers(String key);

    /**
     * Deletes a list of cached group members.
     *
     * @param key the key for the list of group members to delete
     */
    void deleteGroupMembers(String key);

    /**
     * Checks if a list of group members is cached.
     *
     * @param key the key for the list of group members
     * @return true if the list of group members is cached, false otherwise
     */
    boolean existsGroupMembers(String key);

    int getChatGroupId(int userId1, int userId2);

}
