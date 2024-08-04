package com.imperial.academia.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.cache.Cache;
import com.imperial.academia.entity.group_member.GroupMember;

class GroupMemberCacheImplTest {

    private GroupMemberCacheImpl groupMemberCacheImpl;
    private Cache<String, GroupMember> mockGroupMemberCache;
    private Cache<String, List<GroupMember>> mockGroupMembersCache;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        mockGroupMemberCache = Mockito.mock(Cache.class);
        mockGroupMembersCache = Mockito.mock(Cache.class);

        groupMemberCacheImpl = new GroupMemberCacheImpl(mockGroupMemberCache, mockGroupMembersCache);
    }

    @Test
    void testDefaultConstructor() {
        groupMemberCacheImpl = new GroupMemberCacheImpl();
        assertNotNull(groupMemberCacheImpl);
    }

    @Test
    void setGroupMember_shouldPutGroupMemberInCache() {
        GroupMember groupMember = new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        groupMemberCacheImpl.setGroupMember("testKey", groupMember);

        verify(mockGroupMemberCache, times(1)).put("testKey", groupMember);
    }

    @Test
    void getGroupMember_shouldReturnGroupMemberFromCache() {
        GroupMember groupMember = new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockGroupMemberCache.getIfPresent("testKey")).thenReturn(groupMember);

        GroupMember result = groupMemberCacheImpl.getGroupMember("testKey");

        assertEquals(groupMember, result);
        verify(mockGroupMemberCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteGroupMember_shouldInvalidateGroupMemberInCache() {
        groupMemberCacheImpl.deleteGroupMember("testKey");

        verify(mockGroupMemberCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsGroupMember_shouldReturnTrueIfGroupMemberExistsInCache() {
        GroupMember groupMember = new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockGroupMemberCache.getIfPresent("testKey")).thenReturn(groupMember);

        boolean result = groupMemberCacheImpl.existsGroupMember("testKey");

        assertTrue(result);
        verify(mockGroupMemberCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsGroupMember_shouldReturnFalseIfGroupMemberDoesNotExistInCache() {
        when(mockGroupMemberCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = groupMemberCacheImpl.existsGroupMember("testKey");

        assertFalse(result);
        verify(mockGroupMemberCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setGroupMembers_shouldPutGroupMembersInCache() {
        List<GroupMember> groupMembers = new ArrayList<>();
        groupMemberCacheImpl.setGroupMembers("testKey", groupMembers);

        verify(mockGroupMembersCache, times(1)).put("testKey", groupMembers);
    }

    @Test
    void getGroupMembers_shouldReturnGroupMembersFromCache() {
        List<GroupMember> groupMembers = new ArrayList<>();
        when(mockGroupMembersCache.getIfPresent("testKey")).thenReturn(groupMembers);

        List<GroupMember> result = groupMemberCacheImpl.getGroupMembers("testKey");

        assertEquals(groupMembers, result);
        verify(mockGroupMembersCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteGroupMembers_shouldInvalidateGroupMembersInCache() {
        groupMemberCacheImpl.deleteGroupMembers("testKey");

        verify(mockGroupMembersCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsGroupMembers_shouldReturnTrueIfGroupMembersExistInCache() {
        List<GroupMember> groupMembers = new ArrayList<>();
        when(mockGroupMembersCache.getIfPresent("testKey")).thenReturn(groupMembers);

        boolean result = groupMemberCacheImpl.existsGroupMembers("testKey");

        assertTrue(result);
        verify(mockGroupMembersCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsGroupMembers_shouldReturnFalseIfGroupMembersDoNotExistInCache() {
        when(mockGroupMembersCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = groupMemberCacheImpl.existsGroupMembers("testKey");

        assertFalse(result);
        verify(mockGroupMembersCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void getChatGroupId_shouldReturnGroupIdWhenBothUsersAreFound() {
        List<GroupMember> groupMembers = new ArrayList<>();
        groupMembers.add(new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        groupMembers.add(new GroupMember(1, 2, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));

        @SuppressWarnings("unchecked")
        ConcurrentMap<String, List<GroupMember>> mockMap = Mockito.mock(ConcurrentMap.class);
        when(mockMap.values()).thenReturn(List.of(groupMembers));
        when(mockGroupMembersCache.asMap()).thenReturn(mockMap);

        int result = groupMemberCacheImpl.getChatGroupId(1, 2);

        assertEquals(1, result);
    }

    @Test
    void getChatGroupId_shouldReturnMinusOneWhenBothUsersAreNotFound() {
        List<GroupMember> groupMembers = new ArrayList<>();
        groupMembers.add(new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));

        @SuppressWarnings("unchecked")
        ConcurrentMap<String, List<GroupMember>> mockMap = Mockito.mock(ConcurrentMap.class);
        when(mockMap.values()).thenReturn(List.of(groupMembers));
        when(mockGroupMembersCache.asMap()).thenReturn(mockMap);

        int result = groupMemberCacheImpl.getChatGroupId(1, 2);

        assertEquals(-1, result);
    }

    @Test
    void getChatGroupId_shouldReturnMinusOneWhenGroupSizeIsNotTwo() {
        List<GroupMember> groupMembers = new ArrayList<>();
        groupMembers.add(new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        groupMembers.add(new GroupMember(1, 2, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        groupMembers.add(new GroupMember(1, 3, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));

        @SuppressWarnings("unchecked")
        ConcurrentMap<String, List<GroupMember>> mockMap = Mockito.mock(ConcurrentMap.class);
        when(mockMap.values()).thenReturn(List.of(groupMembers));
        when(mockGroupMembersCache.asMap()).thenReturn(mockMap);

        int result = groupMemberCacheImpl.getChatGroupId(1, 2);

        assertEquals(-1, result);
    }
}
