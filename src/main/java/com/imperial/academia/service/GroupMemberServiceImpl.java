package com.imperial.academia.service;

import com.imperial.academia.cache.GroupMemberCache;
import com.imperial.academia.data_access.group_member.GroupMemberDAI;
import com.imperial.academia.entity.group_member.GroupMember;

import java.sql.SQLException;
import java.util.List;

public class GroupMemberServiceImpl implements GroupMemberService {
    private GroupMemberCache groupMemberCache;
    private GroupMemberDAI groupMemberDAO;

    public GroupMemberServiceImpl(GroupMemberCache groupMemberCache, GroupMemberDAI groupMemberDAO) {
        this.groupMemberCache = groupMemberCache;
        this.groupMemberDAO = groupMemberDAO;
    }

    @Override
    public void insert(GroupMember groupMember) throws SQLException {
        groupMemberDAO.insert(groupMember);
        groupMemberCache.setGroupMember("groupmember:" + groupMember.getGroupId() + ":" + groupMember.getUserId(), groupMember);
    }

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

    @Override
    public List<GroupMember> getAll() throws SQLException {
        List<GroupMember> groupMembers = groupMemberCache.getGroupMembers("groupmembers:all");
        if (groupMembers == null) {
            groupMembers = groupMemberDAO.getAll();
            groupMemberCache.setGroupMembers("groupmembers:all", groupMembers);
        }
        return groupMembers;
    }

    @Override
    public void update(GroupMember groupMember) throws SQLException {
        groupMemberDAO.update(groupMember);
        groupMemberCache.setGroupMember("groupmember:" + groupMember.getGroupId() + ":" + groupMember.getUserId(), groupMember);
    }

    @Override
    public void delete(int groupId, int userId) throws SQLException {
        groupMemberDAO.delete(groupId, userId);
        groupMemberCache.deleteGroupMember("groupmember:" + groupId + ":" + userId);
    }
}
