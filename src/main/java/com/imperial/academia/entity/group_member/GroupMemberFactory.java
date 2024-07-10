package com.imperial.academia.entity.group_member;

import java.sql.Timestamp;
import java.util.Date;

public class GroupMemberFactory {
    public GroupMember createGroupMember(int groupId, int userId, String role) {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return new GroupMember(groupId, userId, role,timestamp , timestamp);
    }
}
