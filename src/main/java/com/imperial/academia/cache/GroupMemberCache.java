package com.imperial.academia.cache;

import com.imperial.academia.entity.group_member.GroupMember;
import java.util.List;

public interface GroupMemberCache {
    void setGroupMember(String key, GroupMember groupMember);
    GroupMember getGroupMember(String key);
    void deleteGroupMember(String key);
    boolean existsGroupMember(String key);

    void setGroupMembers(String key, List<GroupMember> groupMembers);
    List<GroupMember> getGroupMembers(String key);
    void deleteGroupMembers(String key);
    boolean existsGroupMembers(String key);
}
