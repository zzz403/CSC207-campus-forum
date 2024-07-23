package com.imperial.academia.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.cache.GroupMemberCache;
import com.imperial.academia.data_access.GroupMemberDAI;
import com.imperial.academia.entity.group_member.GroupMember;
import org.junit.*;
import org.mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class GroupMemberServiceImplTest {
    @Mock
    private GroupMemberCache groupMemberCache;
    @Mock
    private GroupMemberDAI groupMemberDAO;

    @InjectMocks
    private GroupMemberServiceImpl groupMemberService;

    private AutoCloseable closeable;

    private Timestamp now;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        now = new Timestamp(System.currentTimeMillis());
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testInsert() throws SQLException {
        GroupMember groupMember = new GroupMember(1, 1, "member", now, now);
        groupMemberService.insert(groupMember);
        verify(groupMemberDAO, times(1)).insert(groupMember);
        verify(groupMemberCache, times(1)).setGroupMember("groupmember:1:1", groupMember);
    }

    @Test
    public void testGet_Cached() throws SQLException {
        GroupMember groupMember = new GroupMember(1, 1, "member", now, now);
        when(groupMemberCache.getGroupMember("groupmember:1:1")).thenReturn(groupMember);

        GroupMember result = groupMemberService.get(1, 1);
        assertNotNull(result);
        assertEquals(1, result.getGroupId());
        assertEquals(1, result.getUserId());
        verify(groupMemberCache, times(1)).getGroupMember("groupmember:1:1");
        verifyNoMoreInteractions(groupMemberDAO);
    }

    @Test
    public void testGet_NotCached() throws SQLException {
        when(groupMemberCache.getGroupMember("groupmember:1:1")).thenReturn(null);
        GroupMember groupMember = new GroupMember(1, 1, "member", now, now);
        when(groupMemberDAO.get(1, 1)).thenReturn(groupMember);

        GroupMember result = groupMemberService.get(1, 1);
        assertNotNull(result);
        assertEquals(1, result.getGroupId());
        assertEquals(1, result.getUserId());
        verify(groupMemberCache, times(1)).getGroupMember("groupmember:1:1");
        verify(groupMemberDAO, times(1)).get(1, 1);
        verify(groupMemberCache, times(1)).setGroupMember("groupmember:1:1", groupMember);
    }

    @Test
    public void testGetAll_Cached() throws SQLException {
        GroupMember groupMember1 = new GroupMember(1, 1, "member", now, now);
        GroupMember groupMember2 = new GroupMember(1, 2, "member", now, now);
        List<GroupMember> groupMembers = Arrays.asList(groupMember1, groupMember2);
        when(groupMemberCache.getGroupMembers("groupmembers:all")).thenReturn(groupMembers);

        List<GroupMember> result = groupMemberService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(groupMemberCache, times(1)).getGroupMembers("groupmembers:all");
        verifyNoMoreInteractions(groupMemberDAO);
    }

    @Test
    public void testGetAll_NotCached() throws SQLException {
        when(groupMemberCache.getGroupMembers("groupmembers:all")).thenReturn(null);
        GroupMember groupMember1 = new GroupMember(1, 1, "member", now, now);
        GroupMember groupMember2 = new GroupMember(1, 2, "member", now, now);
        List<GroupMember> groupMembers = Arrays.asList(groupMember1, groupMember2);
        when(groupMemberDAO.getAll()).thenReturn(groupMembers);

        List<GroupMember> result = groupMemberService.getAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(groupMemberCache, times(1)).getGroupMembers("groupmembers:all");
        verify(groupMemberDAO, times(1)).getAll();
        verify(groupMemberCache, times(1)).setGroupMembers("groupmembers:all", groupMembers);
    }

    @Test
    public void testUpdate() throws SQLException {
        GroupMember groupMember = new GroupMember(1, 1, "member", now, now);
        groupMemberService.update(groupMember);
        verify(groupMemberDAO, times(1)).update(groupMember);
        verify(groupMemberCache, times(1)).setGroupMember("groupmember:1:1", groupMember);
    }

    @Test
    public void testDelete() throws SQLException {
        groupMemberService.delete(1, 1);
        verify(groupMemberDAO, times(1)).delete(1, 1);
        verify(groupMemberCache, times(1)).deleteGroupMember("groupmember:1:1");
    }
}
