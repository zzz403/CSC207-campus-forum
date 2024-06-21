package com.imperial.academia.service;

import com.imperial.academia.entity.group_member.GroupMember;
import java.sql.SQLException;
import java.util.List;

public interface GroupMemberService {
    void insert(GroupMember groupMember) throws SQLException;

    GroupMember get(int groupId, int userId) throws SQLException;

    List<GroupMember> getAll() throws SQLException;

    void update(GroupMember groupMember) throws SQLException;

    void delete(int groupId, int userId) throws SQLException;
}
