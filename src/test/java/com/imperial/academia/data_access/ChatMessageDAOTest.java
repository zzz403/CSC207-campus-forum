package com.imperial.academia.data_access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.FileData;
import com.imperial.academia.entity.chat_message.MapData;
import com.imperial.academia.entity.chat_message.WaveformData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatMessageDAOTest {

    private ChatMessageDAO chatMessageDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private ObjectMapper mockObjectMapper;

    @BeforeEach
    void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        mockObjectMapper = Mockito.mock(ObjectMapper.class);

        chatMessageDAO = new ChatMessageDAO(mockConnection);
    }

    @Test
    void insert_shouldInsertChatMessageAndSetId() throws SQLException {
        ChatMessage chatMessage = new ChatMessage(0, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(1);

        chatMessageDAO.insert(chatMessage);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockResultSet, times(1)).next();
        assertEquals(1, chatMessage.getId());
    }

    @Test
    void insertWaveformData_shouldInsertWaveformData() throws SQLException {
        WaveformData waveformData = new WaveformData(List.of(1, 2, 3), List.of(4, 5, 6), 3.5f);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatMessageDAO.insertWaveformData(1, waveformData);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void insertMapData_shouldInsertMapData() throws SQLException {
        MapData mapData = new MapData(12.34, 56.78, "Test Location");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatMessageDAO.insertMapData(1, mapData);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void insertFileData_shouldInsertFileData() throws SQLException {
        FileData fileData = new FileData("file.txt", "10MB", "text/plain");
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatMessageDAO.insertFileData(1, fileData);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void get_shouldReturnChatMessage() throws SQLException {
        int messageId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("message_id")).thenReturn(messageId);
        when(mockResultSet.getInt("sender_id")).thenReturn(1);
        when(mockResultSet.getInt("group_id")).thenReturn(1);
        when(mockResultSet.getString("content_type")).thenReturn("text");
        when(mockResultSet.getString("content")).thenReturn("Hello");
        when(mockResultSet.getTimestamp("timestamp")).thenReturn(new Timestamp(System.currentTimeMillis()));

        ChatMessage chatMessage = chatMessageDAO.get(messageId);

        assertNotNull(chatMessage);
        assertEquals(messageId, chatMessage.getId());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void get_shouldReturnNullIfChatMessageNotFound() throws SQLException {
        int messageId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        ChatMessage chatMessage = chatMessageDAO.get(messageId);

        assertNull(chatMessage);
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getAll_shouldReturnAllChatMessages() throws SQLException {
        when(mockConnection.createStatement()).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("message_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("sender_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("group_id")).thenReturn(1, 2);
        when(mockResultSet.getString("content_type")).thenReturn("text");
        when(mockResultSet.getString("content")).thenReturn("Hello", "Hi");
        when(mockResultSet.getTimestamp("timestamp")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<ChatMessage> chatMessages = chatMessageDAO.getAll();

        assertNotNull(chatMessages);
        assertEquals(2, chatMessages.size());
        verify(mockPreparedStatement, times(1)).executeQuery(anyString());
        verify(mockResultSet, times(3)).next();
    }

    @Test
    void getAllByGroupId_shouldReturnChatMessagesByGroupId() throws SQLException {
        int groupId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("message_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("sender_id")).thenReturn(1, 2);
        when(mockResultSet.getInt("group_id")).thenReturn(groupId);
        when(mockResultSet.getString("content_type")).thenReturn("text");
        when(mockResultSet.getString("content")).thenReturn("Hello", "Hi");
        when(mockResultSet.getTimestamp("timestamp")).thenReturn(new Timestamp(System.currentTimeMillis()));

        List<ChatMessage> chatMessages = chatMessageDAO.getAllByGroupId(groupId);

        assertNotNull(chatMessages);
        assertEquals(2, chatMessages.size());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(3)).next();
    }

    @Test
    void update_shouldUpdateChatMessage() throws SQLException {
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Updated content", new Timestamp(System.currentTimeMillis()));
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatMessageDAO.update(chatMessage);

        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void delete_shouldDeleteChatMessage() throws SQLException {
        int messageId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        chatMessageDAO.delete(messageId);

        verify(mockPreparedStatement, times(1)).executeUpdate();
        verify(mockPreparedStatement, times(1)).setInt(1, messageId);
    }

    @Test
    void getWaveformData_shouldReturnWaveformData() throws SQLException, IOException {
        int messageId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("min_values")).thenReturn("[1,2,3]");
        when(mockResultSet.getString("max_values")).thenReturn("[4,5,6]");
        when(mockResultSet.getFloat("duration")).thenReturn(3.5f);

        WaveformData waveformData = chatMessageDAO.getWaveformData(messageId);

        assertNotNull(waveformData);
        assertEquals(3.5f, waveformData.getDuration());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getWaveformData_shouldThrowSQLExceptionOnJsonMappingException() throws SQLException, IOException {
        int messageId = 1;

        // Mock the database connection and related objects
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("min_values")).thenReturn("[1,2,3]");
        when(mockResultSet.getString("max_values")).thenReturn("[4,5,6]");
        when(mockResultSet.getFloat("duration")).thenReturn(3.5f);

        // Mock the ObjectMapper to throw an IOException
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        chatMessageDAO = new ChatMessageDAO(mockConnection, mockObjectMapper);
        doAnswer(invocation -> {
            throw new IOException("Test IOException");
        }).when(mockObjectMapper).readValue(anyString(), any(Class.class));

        // Execute the method and assert the exception
        SQLException exception = assertThrows(SQLException.class, () -> {
            chatMessageDAO.getWaveformData(messageId);
        });

        assertEquals("Error parsing waveform data", exception.getMessage());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }


    @Test
    void getMapData_shouldReturnMapData() throws SQLException {
        int messageId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getDouble("latitude")).thenReturn(12.34);
        when(mockResultSet.getDouble("longitude")).thenReturn(56.78);
        when(mockResultSet.getString("location_info")).thenReturn("Test Location");

        MapData mapData = chatMessageDAO.getMapData(messageId);

        assertNotNull(mapData);
        assertEquals(12.34, mapData.getLatitude());
        assertEquals(56.78, mapData.getLongitude());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }

    @Test
    void getFileData_shouldReturnFileData() throws SQLException {
        int messageId = 1;
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("file_name")).thenReturn("file.txt");
        when(mockResultSet.getString("file_size")).thenReturn("10MB");
        when(mockResultSet.getString("file_type")).thenReturn("text/plain");

        FileData fileData = chatMessageDAO.getFileData(messageId);

        assertNotNull(fileData);
        assertEquals("file.txt", fileData.getFileName());
        assertEquals("10MB", fileData.getFileSize());
        assertEquals("text/plain", fileData.getFileType());
        verify(mockPreparedStatement, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
    }
}
