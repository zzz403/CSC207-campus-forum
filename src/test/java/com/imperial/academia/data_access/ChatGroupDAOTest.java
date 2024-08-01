package com.imperial.academia.data_access;

import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatGroupDAOTest {

    private ChatGroupDAO chatGroupDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        chatGroupDAO = new ChatGroupDAO(mockConnection);
    }

    @Test
    void insert_shouldInsertChatGroupAndSetId() throws SQLException {
        ChatGroup chatGroup = new ChatGroup(0, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        chatGroupDAO.insert(chatGroup);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockResultSet, times(1)).next();
        assertEquals(1, chatGroup.getId());
    }

    @Test
    void get_shouldReturnChatGroup() throws SQLException {
        int groupId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("group_id")).thenReturn(groupId);
        when(mockResultSet.getString("group_name")).thenReturn("Test Group");
        when(mockResultSet.getBoolean("is_group")).thenReturn(true);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        ChatGroup chatGroup = chatGroupDAO.get(groupId);

        assertNotNull(chatGroup);
        assertEquals(groupId, chatGroup.getId());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void get_shouldReturnNullIfChatGroupNotFound() throws SQLException {
        int groupId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        ChatGroup chatGroup = chatGroupDAO.get(groupId);

        assertNull(chatGroup);
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getAll_shouldReturnAllChatGroupsForUser() throws SQLException {
        int userId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("group_id")).thenReturn(1, 2);
        when(mockResultSet.getString("group_name")).thenReturn("Test Group 1", "Test Group 2");
        when(mockResultSet.getBoolean("is_group")).thenReturn(true);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<ChatGroup> chatGroups = chatGroupDAO.getAll(userId);

        assertNotNull(chatGroups);
        assertEquals(2, chatGroups.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(3)).next();
    }

    @Test
    void getAllSince_shouldReturnChatGroupsModifiedSince() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 10000);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("group_id")).thenReturn(1, 2);
        when(mockResultSet.getString("group_name")).thenReturn("Test Group 1", "Test Group 2");
        when(mockResultSet.getBoolean("is_group")).thenReturn(true);
        when(mockResultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<ChatGroup> chatGroups = chatGroupDAO.getAllSince(timestamp);

        assertNotNull(chatGroups);
        assertEquals(2, chatGroups.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(3)).next();
    }

    @Test
    void update_shouldUpdateChatGroup() throws SQLException {
        ChatGroup chatGroup = new ChatGroup(1, "Updated Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatGroupDAO.update(chatGroup);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setString(1, "Updated Group");
        verify(mockPreparedStatement, times(1)).setInt(2, 1);
    }

    @Test
    void delete_shouldDeleteChatGroup() throws SQLException {
        int groupId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatGroupDAO.delete(groupId);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setInt(1, groupId);
    }

    @Test
    void getLastMessage_shouldReturnLastMessageForGroup() throws SQLException {
        int groupId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("message_id")).thenReturn(1);
        when(mockResultSet.getInt("sender_id")).thenReturn(1);
        when(mockResultSet.getInt("group_id")).thenReturn(groupId);
        when(mockResultSet.getString("content_type")).thenReturn("text");
        when(mockResultSet.getString("content")).thenReturn("Last message");
        when(mockResultSet.getTimestamp("timestamp")).thenReturn(new Timestamp(System.currentTimeMillis()));

        ChatMessage chatMessage = chatGroupDAO.getLastMessage(groupId);

        assertNotNull(chatMessage);
        assertEquals(groupId, chatMessage.getGroupId());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getLastMessage_shouldReturnNullIfNoMessages() throws SQLException {
        int groupId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        ChatMessage chatMessage = chatGroupDAO.getLastMessage(groupId);

        assertNull(chatMessage);
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getMember_shouldReturnUserIfExists() throws SQLException {
        int groupId = 1;
        int excludeUserId = 2;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("username")).thenReturn("JohnDoe");
        when(mockResultSet.getString("password")).thenReturn("password");
        when(mockResultSet.getString("email")).thenReturn("john.doe@example.com");
        when(mockResultSet.getString("role")).thenReturn("role");
        when(mockResultSet.getString("avatar_url")).thenReturn("avatarUrl");
        when(mockResultSet.getTimestamp("registration_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getTimestamp("last_modified")).thenReturn(new Timestamp(System.currentTimeMillis()));

        User user = chatGroupDAO.getMember(groupId, excludeUserId);

        assertNotNull(user);
        assertEquals(1, user.getId());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getMember_shouldReturnNullIfNoUserFound() throws SQLException {
        int groupId = 1;
        int excludeUserId = 2;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        User user = chatGroupDAO.getMember(groupId, excludeUserId);

        assertNull(user);
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }
}
