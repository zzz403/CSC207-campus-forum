package com.imperial.academia.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatGroupCacheImplTest {

    private ChatGroupCacheImpl chatGroupCacheImpl;
    private Cache<String, ChatGroup> mockChatGroupCache;
    private Cache<String, List<ChatGroup>> mockChatGroupsCache;
    private Cache<String, ChatMessage> mockLastMessageCache;
    private Cache<String, User> mockAvatarUrlCache;

    @BeforeEach
    void setUp() {
        mockChatGroupCache = Mockito.mock(Cache.class);
        mockChatGroupsCache = Mockito.mock(Cache.class);
        mockLastMessageCache = Mockito.mock(Cache.class);
        mockAvatarUrlCache = Mockito.mock(Cache.class);

        chatGroupCacheImpl = new ChatGroupCacheImpl(
                mockChatGroupCache,
                mockChatGroupsCache,
                mockLastMessageCache,
                mockAvatarUrlCache
        );
    }

    @Test
    void testDefaultConstructor() {
        chatGroupCacheImpl = new ChatGroupCacheImpl();
        assertNotNull(chatGroupCacheImpl);
    }

    @Test
    void setChatGroup_shouldPutChatGroupInCache() {
        ChatGroup chatGroup = new ChatGroup(1, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        chatGroupCacheImpl.setChatGroup("testKey", chatGroup);

        verify(mockChatGroupCache, times(1)).put("testKey", chatGroup);
    }

    @Test
    void getChatGroup_shouldReturnChatGroupFromCache() {
        ChatGroup chatGroup = new ChatGroup(1, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockChatGroupCache.getIfPresent("testKey")).thenReturn(chatGroup);

        ChatGroup result = chatGroupCacheImpl.getChatGroup("testKey");

        assertEquals(chatGroup, result);
        verify(mockChatGroupCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteChatGroup_shouldInvalidateChatGroupInCache() {
        chatGroupCacheImpl.deleteChatGroup("testKey");

        verify(mockChatGroupCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsChatGroup_shouldReturnTrueIfChatGroupExistsInCache() {
        ChatGroup chatGroup = new ChatGroup(1, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        when(mockChatGroupCache.getIfPresent("testKey")).thenReturn(chatGroup);

        boolean result = chatGroupCacheImpl.existsChatGroup("testKey");

        assertTrue(result);
        verify(mockChatGroupCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsChatGroup_shouldReturnFalseIfChatGroupDoesNotExistInCache() {
        when(mockChatGroupCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatGroupCacheImpl.existsChatGroup("testKey");

        assertFalse(result);
        verify(mockChatGroupCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setChatGroups_shouldPutChatGroupsInCache() {
        List<ChatGroup> chatGroups = new ArrayList<>();
        chatGroupCacheImpl.setChatGroups("testKey", chatGroups);

        verify(mockChatGroupsCache, times(1)).put("testKey", chatGroups);
    }

    @Test
    void getChatGroups_shouldReturnChatGroupsFromCache() {
        List<ChatGroup> chatGroups = new ArrayList<>();
        when(mockChatGroupsCache.getIfPresent("testKey")).thenReturn(chatGroups);

        List<ChatGroup> result = chatGroupCacheImpl.getChatGroups("testKey");

        assertEquals(chatGroups, result);
        verify(mockChatGroupsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteChatGroups_shouldInvalidateChatGroupsInCache() {
        chatGroupCacheImpl.deleteChatGroups("testKey");

        verify(mockChatGroupsCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsChatGroups_shouldReturnTrueIfChatGroupsExistInCache() {
        List<ChatGroup> chatGroups = new ArrayList<>();
        when(mockChatGroupsCache.getIfPresent("testKey")).thenReturn(chatGroups);

        boolean result = chatGroupCacheImpl.existsChatGroups("testKey");

        assertTrue(result);
        verify(mockChatGroupsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsChatGroups_shouldReturnFalseIfChatGroupsDoNotExistInCache() {
        when(mockChatGroupsCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatGroupCacheImpl.existsChatGroups("testKey");

        assertFalse(result);
        verify(mockChatGroupsCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setLastMessage_shouldPutLastMessageInCache() {
        ChatMessage lastMessage = new ChatMessage(1, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        chatGroupCacheImpl.setLastMessage("testKey", lastMessage);

        verify(mockLastMessageCache, times(1)).put("testKey", lastMessage);
    }

    @Test
    void getLastMessage_shouldReturnLastMessageFromCache() {
        ChatMessage lastMessage = new ChatMessage(1, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        when(mockLastMessageCache.getIfPresent("testKey")).thenReturn(lastMessage);

        ChatMessage result = chatGroupCacheImpl.getLastMessage("testKey");

        assertEquals(lastMessage, result);
        verify(mockLastMessageCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteLastMessage_shouldInvalidateLastMessageInCache() {
        chatGroupCacheImpl.deleteLastMessage("testKey");

        verify(mockLastMessageCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsLastMessage_shouldReturnTrueIfLastMessageExistsInCache() {
        ChatMessage lastMessage = new ChatMessage(1, 1, 1, "text", "Hello", new Timestamp(System.currentTimeMillis()));
        when(mockLastMessageCache.getIfPresent("testKey")).thenReturn(lastMessage);

        boolean result = chatGroupCacheImpl.existsLastMessage("testKey");

        assertTrue(result);
        verify(mockLastMessageCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsLastMessage_shouldReturnFalseIfLastMessageDoesNotExistInCache() {
        when(mockLastMessageCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatGroupCacheImpl.existsLastMessage("testKey");

        assertFalse(result);
        verify(mockLastMessageCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void setUser_shouldPutUserInCache() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);
        chatGroupCacheImpl.setUser("testKey", user);

        verify(mockAvatarUrlCache, times(1)).put("testKey", user);
    }

    @Test
    void getUser_shouldReturnUserFromCache() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);
        when(mockAvatarUrlCache.getIfPresent("testKey")).thenReturn(user);

        User result = chatGroupCacheImpl.getUser("testKey");

        assertEquals(user, result);
        verify(mockAvatarUrlCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void deleteUser_shouldInvalidateUserInCache() {
        chatGroupCacheImpl.deleteUser("testKey");

        verify(mockAvatarUrlCache, times(1)).invalidate("testKey");
    }

    @Test
    void existsUser_shouldReturnTrueIfUserExistsInCache() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);
        when(mockAvatarUrlCache.getIfPresent("testKey")).thenReturn(user);

        boolean result = chatGroupCacheImpl.existsUser("testKey");

        assertTrue(result);
        verify(mockAvatarUrlCache, times(1)).getIfPresent("testKey");
    }

    @Test
    void existsUser_shouldReturnFalseIfUserDoesNotExistInCache() {
        when(mockAvatarUrlCache.getIfPresent("testKey")).thenReturn(null);

        boolean result = chatGroupCacheImpl.existsUser("testKey");

        assertFalse(result);
        verify(mockAvatarUrlCache, times(1)).getIfPresent("testKey");
    }
}
