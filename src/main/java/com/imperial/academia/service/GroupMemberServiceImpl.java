package com.imperial.academia.service;

import com.imperial.academia.cache.GroupMemberCache;
import com.imperial.academia.data_access.GroupMemberDAI;
import com.imperial.academia.entity.group_member.GroupMember;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the GroupMemberService interface.
 * Uses caching to reduce database access.
 */
public class GroupMemberServiceImpl implements GroupMemberService {
    private GroupMemberCache groupMemberCache;
    private GroupMemberDAI groupMemberDAO;

    /**
     * Constructs a new GroupMemberServiceImpl with the specified cache and DAO.
     *
     * @param groupMemberCache the cache to use
     * @param groupMemberDAO the DAO to use
     */
    public GroupMemberServiceImpl(GroupMemberCache groupMemberCache, GroupMemberDAI groupMemberDAO) {
        this.groupMemberCache = groupMemberCache;
        this.groupMemberDAO = groupMemberDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insert(GroupMember groupMember) throws SQLException {
        groupMemberDAO.insert(groupMember);
        groupMemberCache.setGroupMember("groupmember:" + groupMember.getGroupId() + ":" + groupMember.getUserId(), groupMember);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GroupMember get(int groupId, int userId) throws SQLException {
        String key = "groupmember:" + groupId + ":" + userId;
        GroupMember groupMember = groupMemberCache.getGroupMember(key);
        if (groupMember == null) {
            groupMember = groupMemberDAO.get(groupId, userId);
            if (groupMember != null) {
                groupMemberCache.setGroupMember(key, groupMember);
            }
        }
        return groupMember;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GroupMember> getAll() throws SQLException {
        List<GroupMember> groupMembers = groupMemberCache.getGroupMembers("groupmembers:all");
        if (groupMembers == null) {
            groupMembers = groupMemberDAO.getAll();
            groupMemberCache.setGroupMembers("groupmembers:all", groupMembers);
        }
        return groupMembers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GroupMember groupMember) throws SQLException {
        groupMemberDAO.update(groupMember);
        groupMemberCache.setGroupMember("groupmember:" + groupMember.getGroupId() + ":" + groupMember.getUserId(), groupMember);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(int groupId, int userId) throws SQLException {
        groupMemberDAO.delete(groupId, userId);
        groupMemberCache.deleteGroupMember("groupmember:" + groupId + ":" + userId);
    }
}
