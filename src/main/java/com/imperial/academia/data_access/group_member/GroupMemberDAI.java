package com.imperial.academia.data_access.group_member;

import com.imperial.academia.entity.GroupMember;

import java.sql.SQLException;
import java.util.List;

public interface GroupMemberDAI {
    void insert(GroupMember groupMember) throws SQLException;

    GroupMember get(int id) throws SQLException;

    List<GroupMember> getAll() throws SQLException;

    void update(GroupMember groupMember) throws SQLException;

    void delete(int id) throws SQLException;
}
