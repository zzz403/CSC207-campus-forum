package com.imperial.academia.entity.group_member;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class GroupMemberTest {
    private GroupMember groupMember;

    @Test
    void groupMember(){
        groupMember = new GroupMember(1,1,"",
                new Timestamp(1),new Timestamp(1));
        groupMember.setGroupId(2);
        groupMember.setUserId(3);
        groupMember.setRole("hh");
        groupMember.setJoinedDate(new Timestamp(111));
        groupMember.setLastModified(new Timestamp(222));

        assertEquals(2, groupMember.getGroupId());
        assertEquals(3, groupMember.getUserId());
        assertEquals("hh", groupMember.getRole());
        assertEquals(new Timestamp(111), groupMember.getJoinedDate());
        assertEquals(new Timestamp(222), groupMember.getLastModified());
    }
}