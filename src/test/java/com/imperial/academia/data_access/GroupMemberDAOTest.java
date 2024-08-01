package com.imperial.academia.data_access;

import com.imperial.academia.entity.group_member.GroupMember;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupMemberDAOTest {
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Mock
    private Statement mockStatement;

    private GroupMemberDAO groupMemberDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        groupMemberDAO = new GroupMemberDAO(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    }

    @Test
    void insert_shouldInsertGroupMember() throws SQLException {
        GroupMember groupMember = new GroupMember(1, 1, "member", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        groupMemberDAO.insert(groupMember);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void get_shouldReturnGroupMember() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("group_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("role")).thenReturn("member");
        when(mockResultSet.getTimestamp("joined_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        GroupMember groupMember = groupMemberDAO.get(1, 1);

        assertNotNull(groupMember);
        assertEquals(1, groupMember.getGroupId());
        assertEquals(1, groupMember.getUserId());
        assertEquals("member", groupMember.getRole());
    }

    @Test
    void getAll_shouldReturnListOfGroupMembers() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("group_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("role")).thenReturn("member");
        when(mockResultSet.getTimestamp("joined_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<GroupMember> groupMembers = groupMemberDAO.getAll();

        assertEquals(2, groupMembers.size());
    }

    @Test
    void getAllSince_shouldReturnListOfGroupMembers() throws SQLException {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("group_id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("role")).thenReturn("member");
        when(mockResultSet.getTimestamp("joined_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<GroupMember> groupMembers = groupMemberDAO.getAllSince(new Timestamp(System.currentTimeMillis() - 1000));

        assertEquals(2, groupMembers.size());
    }

    @Test
    void update_shouldUpdateGroupMember() throws SQLException {
        GroupMember groupMember = new GroupMember(1, 1, "admin", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        groupMemberDAO.update(groupMember);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void delete_shouldDeleteGroupMember() throws SQLException {
        groupMemberDAO.delete(1, 1);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void getPrivateChatId_shouldReturnGroupId() throws SQLException {
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("group_id")).thenReturn(1);

        int groupId = groupMemberDAO.getPrivateChatId(1, 2);

        assertEquals(1, groupId);
    }

    @Test
    void getPrivateChatId_shouldReturnMinusOneWhenNotFound() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        int groupId = groupMemberDAO.getPrivateChatId(1, 2);

        assertEquals(-1, groupId);
    }
}
