package com.imperial.academia.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.data_access.ChatMessageDAI;
import com.imperial.academia.data_access.UserDAI;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.cache.ChatMessageCache;
import com.imperial.academia.cache.UserCache;
import com.imperial.academia.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ChatMessageServiceImplTest {
    @Mock
    private ChatMessageDAI chatMessageDAO;
    @Mock
    private UserDAI userDAO;
    @Mock
    private ChatMessageCache chatMessageCache;
    @Mock
    private UserCache userCache;

    @InjectMocks
    private ChatMessageServiceImpl chatMessageService;

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        SessionManager.clearSession();
        closeable.close();
    }

    @Test
    public void testInsertChatMessage() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", timestamp);
        chatMessageService.insert(chatMessage);

        verify(chatMessageDAO, times(1)).insert(chatMessage);
        verify(chatMessageCache, times(1)).setChatMessage("chatMessage:1", chatMessage);

        String groupCacheKey = "chatMessages:group:" + chatMessage.getGroupId();
        verify(chatMessageCache, times(1)).getChatMessages(groupCacheKey);
    }

    @Test
    public void testGetChatMessage() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", timestamp);
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);

        when(chatMessageCache.getChatMessage("chatMessage:1")).thenReturn(null);
        when(chatMessageDAO.get(1)).thenReturn(chatMessage);
        when(userCache.getUser("user:1")).thenReturn(null); // Ensure cache miss
        when(userDAO.get(1)).thenReturn(user); // Ensure DAO hit

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(user); // Mock current user

            ChatMessageDTO chatMessageDTO = chatMessageService.get(1);

            assertNotNull(chatMessageDTO);
            assertEquals(1, chatMessageDTO.getId());
            assertEquals("user1", chatMessageDTO.getSenderName());
            assertEquals("Hello", chatMessageDTO.getContent());
            verify(chatMessageCache, times(1)).getChatMessage("chatMessage:1");
            verify(chatMessageDAO, times(1)).get(1);
            verify(chatMessageCache, times(1)).setChatMessage("chatMessage:1", chatMessage);
            verify(userCache, times(1)).getUser("user:1");
            verify(userDAO, times(1)).get(1);
            verify(userCache, times(1)).setUser("user:1", user);
        }
    }

    @Test
    public void testGetAllChatMessages() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<ChatMessage> chatMessages = Arrays.asList(
                new ChatMessage(1, 1, 1, "text", "Hello", timestamp),
                new ChatMessage(2, 2, 1, "text", "Hi", timestamp)
        );
        User user1 = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);
        User user2 = new User(2, "user2", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);

        when(chatMessageCache.getChatMessages("chatMessages:all")).thenReturn(chatMessages);
        when(userCache.getUser("user:1")).thenReturn(null); // Ensure cache miss
        when(userDAO.get(1)).thenReturn(user1); // Ensure DAO hit
        when(userCache.getUser("user:2")).thenReturn(null); // Ensure cache miss
        when(userDAO.get(2)).thenReturn(user2); // Ensure DAO hit

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(user1); // Mock current user

            List<ChatMessageDTO> chatMessageDTOs = chatMessageService.getAll();

            assertNotNull(chatMessageDTOs);
            assertEquals(2, chatMessageDTOs.size());
        }
    }

    @Test
    public void testDeleteChatMessage() throws SQLException {
        chatMessageService.delete(1);

        verify(chatMessageDAO, times(1)).delete(1);
        verify(chatMessageCache, times(1)).deleteChatMessage("chatMessage:1");
    }

    @Test
    public void testGetChatMessageFromCache() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", timestamp);
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);

        when(chatMessageCache.getChatMessage("chatMessage:1")).thenReturn(chatMessage);
        when(userCache.getUser("user:1")).thenReturn(user);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(user); // Mock current user

            ChatMessageDTO chatMessageDTO = chatMessageService.get(1);

            assertNotNull(chatMessageDTO);
            assertEquals(1, chatMessageDTO.getId());
            assertEquals("user1", chatMessageDTO.getSenderName());
            assertEquals("Hello", chatMessageDTO.getContent());
            verify(chatMessageCache, times(1)).getChatMessage("chatMessage:1");
            verify(userCache, times(1)).getUser("user:1");
            verify(chatMessageDAO, never()).get(1);
            verify(userDAO, never()).get(1);
        }
    }

}


