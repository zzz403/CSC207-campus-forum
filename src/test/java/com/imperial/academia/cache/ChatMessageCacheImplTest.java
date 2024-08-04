package com.imperial.academia.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.cache.Cache;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.FileData;
import com.imperial.academia.entity.chat_message.MapData;
import com.imperial.academia.entity.chat_message.WaveformData;

class ChatMessageCacheImplTest {

    private ChatMessageCacheImpl chatMessageCacheImpl;
    private Cache<String, ChatMessage> mockChatMessageCache;
    private Cache<String, List<ChatMessage>> mockChatMessagesCache;
    private Cache<String, WaveformData> mockWaveformDataCache;
    private Cache<String, MapData> mockMapDataCache;
    private Cache<String, FileData> mockFileDataCache;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        mockChatMessageCache = Mockito.mock(Cache.class);
        mockChatMessagesCache = Mockito.mock(Cache.class);
        mockWaveformDataCache = Mockito.mock(Cache.class);
        mockMapDataCache = Mockito.mock(Cache.class);
        mockFileDataCache = Mockito.mock(Cache.class);

        chatMessageCacheImpl = new ChatMessageCacheImpl(
                mockChatMessageCache,
                mockChatMessagesCache,
                mockWaveformDataCache,
                mockMapDataCache,
                mockFileDataCache
        );
    }

    @Test
    void testDefaultConstructor() {
        chatMessageCacheImpl = new ChatMessageCacheImpl();
    }

    @Test
    void setChatMessage_shouldPutChatMessageInCache() {
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        chatMessageCacheImpl.setChatMessage("testKey", chatMessage);

        verify(mockChatMessageCache, times(1)).put("testKey", chatMessage);
    }

    @Test
    void getChatMessage_shouldReturnChatMessageFromCache() {
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        when(mockChatMessageCache.getIfPresent("testKey")).thenReturn(chatMessage);

        ChatMessage result = chatMessageCacheImpl.getChatMessage("testKey");

        assertEquals(chatMessage, result);
        verify(mockChatMessageCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteChatMessage_shouldInvalidateChatMessageInCache() {
        chatMessageCacheImpl.deleteChatMessage("testKey");

        verify(mockChatMessageCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsChatMessage_shouldReturnTrueIfChatMessageExistsInCache() {
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        when(mockChatMessageCache.getIfPresent("testKey")).thenReturn(chatMessage);

        boolean result = chatMessageCacheImpl.existsChatMessage("testKey");

        assertTrue(result);
        verify(mockChatMessageCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsChatMessage_shouldReturnFalseIfChatMessageDoesNotExistInCache() {
        when(mockChatMessageCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatMessageCacheImpl.existsChatMessage("testKey");

        assertFalse(result);
        verify(mockChatMessageCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setChatMessages_shouldPutChatMessagesInCache() {
        List<ChatMessage> chatMessages = new ArrayList<>();
        chatMessageCacheImpl.setChatMessages("testKey", chatMessages);

        verify(mockChatMessagesCache, times(1)).put("testKey", chatMessages);
    }

    @Test
    void getChatMessages_shouldReturnChatMessagesFromCache() {
        List<ChatMessage> chatMessages = new ArrayList<>();
        when(mockChatMessagesCache.getIfPresent("testKey")).thenReturn(chatMessages);

        List<ChatMessage> result = chatMessageCacheImpl.getChatMessages("testKey");

        assertEquals(chatMessages, result);
        verify(mockChatMessagesCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteChatMessages_shouldInvalidateChatMessagesInCache() {
        chatMessageCacheImpl.deleteChatMessages("testKey");

        verify(mockChatMessagesCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsChatMessages_shouldReturnTrueIfChatMessagesExistInCache() {
        List<ChatMessage> chatMessages = new ArrayList<>();
        when(mockChatMessagesCache.getIfPresent("testKey")).thenReturn(chatMessages);

        boolean result = chatMessageCacheImpl.existsChatMessages("testKey");

        assertTrue(result);
        verify(mockChatMessagesCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsChatMessages_shouldReturnFalseIfChatMessagesDoNotExistInCache() {
        when(mockChatMessagesCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatMessageCacheImpl.existsChatMessages("testKey");

        assertFalse(result);
        verify(mockChatMessagesCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setWaveformData_shouldPutWaveformDataInCache() {
        WaveformData waveformData = new WaveformData(List.of(1, 2, 3), List.of(4, 5, 6), 7.5f);
        chatMessageCacheImpl.setWaveformData("testKey", waveformData);

        verify(mockWaveformDataCache, times(1)).put("testKey", waveformData);
    }

    @Test
    void getWaveformData_shouldReturnWaveformDataFromCache() {
        WaveformData waveformData = new WaveformData(List.of(1, 2, 3), List.of(4, 5, 6), 7.5f);
        when(mockWaveformDataCache.getIfPresent("testKey")).thenReturn(waveformData);

        WaveformData result = chatMessageCacheImpl.getWaveformData("testKey");

        assertEquals(waveformData, result);
        verify(mockWaveformDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteWaveformData_shouldInvalidateWaveformDataInCache() {
        chatMessageCacheImpl.deleteWaveformData("testKey");

        verify(mockWaveformDataCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsWaveformData_shouldReturnTrueIfWaveformDataExistsInCache() {
        WaveformData waveformData = new WaveformData(List.of(1, 2, 3), List.of(4, 5, 6), 7.5f);
        when(mockWaveformDataCache.getIfPresent("testKey")).thenReturn(waveformData);

        boolean result = chatMessageCacheImpl.existsWaveformData("testKey");

        assertTrue(result);
        verify(mockWaveformDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsWaveformData_shouldReturnFalseIfWaveformDataDoesNotExistInCache() {
        when(mockWaveformDataCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatMessageCacheImpl.existsWaveformData("testKey");

        assertFalse(result);
        verify(mockWaveformDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setMapData_shouldPutMapDataInCache() {
        MapData mapData = new MapData(40.7128, -74.0060, "New York");
        chatMessageCacheImpl.setMapData("testKey", mapData);

        verify(mockMapDataCache, times(1)).put("testKey", mapData);
    }

    @Test
    void getMapData_shouldReturnMapDataFromCache() {
        MapData mapData = new MapData(40.7128, -74.0060, "New York");
        when(mockMapDataCache.getIfPresent("testKey")).thenReturn(mapData);

        MapData result = chatMessageCacheImpl.getMapData("testKey");

        assertEquals(mapData, result);
        verify(mockMapDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteMapData_shouldInvalidateMapDataInCache() {
        chatMessageCacheImpl.deleteMapData("testKey");

        verify(mockMapDataCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsMapData_shouldReturnTrueIfMapDataExistsInCache() {
        MapData mapData = new MapData(40.7128, -74.0060, "New York");
        when(mockMapDataCache.getIfPresent("testKey")).thenReturn(mapData);

        boolean result = chatMessageCacheImpl.existsMapData("testKey");

        assertTrue(result);
        verify(mockMapDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsMapData_shouldReturnFalseIfMapDataDoesNotExistInCache() {
        when(mockMapDataCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatMessageCacheImpl.existsMapData("testKey");

        assertFalse(result);
        verify(mockMapDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setFileData_shouldPutFileDataInCache() {
        FileData fileData = new FileData("example.txt", "1MB", "text/plain");
        chatMessageCacheImpl.setFileData("testKey", fileData);

        verify(mockFileDataCache, times(1)).put("testKey", fileData);
    }

    @Test
    void getFileData_shouldReturnFileDataFromCache() {
        FileData fileData = new FileData("example.txt", "1MB", "text/plain");
        when(mockFileDataCache.getIfPresent("testKey")).thenReturn(fileData);

        FileData result = chatMessageCacheImpl.getFileData("testKey");

        assertEquals(fileData, result);
        verify(mockFileDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteFileData_shouldInvalidateFileDataInCache() {
        chatMessageCacheImpl.deleteFileData("testKey");

        verify(mockFileDataCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsFileData_shouldReturnTrueIfFileDataExistsInCache() {
        FileData fileData = new FileData("example.txt", "1MB", "text/plain");
        when(mockFileDataCache.getIfPresent("testKey")).thenReturn(fileData);

        boolean result = chatMessageCacheImpl.existsFileData("testKey");

        assertTrue(result);
        verify(mockFileDataCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsFileData_shouldReturnFalseIfFileDataDoesNotExistInCache() {
        when(mockFileDataCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatMessageCacheImpl.existsFileData("testKey");

        assertFalse(result);
        verify(mockFileDataCache, times(1)).getIfPresent("testKey");
    }
}
