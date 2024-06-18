package com.imperial.academia.data_access.GroupMember;

import java.util.List;

import com.imperial.academia.entity.GroupMember;

public interface GroupMemberDataAccessInterface {
    void save(GroupMember groupMember);
    List<GroupMember> findByGroupId(int groupId);
}
