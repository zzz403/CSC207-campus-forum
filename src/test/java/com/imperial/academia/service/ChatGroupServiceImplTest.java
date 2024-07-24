package com.imperial.academia.service;

import com.imperial.academia.cache.ChatGroupCache;
import com.imperial.academia.cache.GroupMemberCache;
import com.imperial.academia.data_access.ChatGroupDAI;
import com.imperial.academia.data_access.GroupMemberDAI;
import com.imperial.academia.entity.chat_group.ChatGroup;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.group_member.GroupMember;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChatGroupServiceImplTest {

    @Mock
    private ChatGroupCache chatGroupCache;

    @Mock
    private ChatGroupDAI chatGroupDAO;

    @Mock
    private GroupMemberCache groupMemberCache;

    @Mock
    private GroupMemberDAI groupMemberDAO;

    @InjectMocks
    private ChatGroupServiceImpl chatGroupService;

    @BeforeEach
    public void setUp() {
        chatGroupService = new ChatGroupServiceImpl(chatGroupCache, chatGroupDAO, groupMemberCache, groupMemberDAO);
    }

    @AfterEach
    public void tearDown(){
        SessionManager.clearSession();
    }

    @Test
    public void testInsert() throws SQLException {
        ChatGroup chatGroup = new ChatGroup(1, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        chatGroupService.insert(chatGroup);

        verify(chatGroupDAO, times(1)).insert(chatGroup);
        verify(chatGroupCache, times(1)).setChatGroup("chatgroup:1", chatGroup);
    }

    @Test
    public void testGet() throws SQLException {
        int id = 1;
        ChatGroup chatGroup = new ChatGroup(id, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        when(chatGroupCache.getChatGroup("chatgroup:" + id)).thenReturn(null);
        when(chatGroupDAO.get(id)).thenReturn(chatGroup);

        ChatGroup result = chatGroupService.get(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(chatGroupCache, times(1)).getChatGroup("chatgroup:" + id);
        verify(chatGroupDAO, times(1)).get(id);
        verify(chatGroupCache, times(1)).setChatGroup("chatgroup:" + id, chatGroup);
    }

    @Test
    public void testGetChatGroupsByGroupName() throws SQLException {
        String searchGroupName = "group";
        ChatGroup chatGroup = new ChatGroup(1, "testgroup", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        List<ChatGroup> allChatGroups = Arrays.asList(chatGroup);

        when(chatGroupCache.getChatGroups("chatgroups:all")).thenReturn(allChatGroups);

        List<ChatGroupDTO> result = chatGroupService.getChatGroupsByGroupName(searchGroupName);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(chatGroupCache, times(1)).getChatGroups("chatgroups:all");
    }

    @Test
    public void testUpdate() throws SQLException {
        ChatGroup chatGroup = new ChatGroup(1, "Test Group", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));

        chatGroupService.update(chatGroup);

        verify(chatGroupDAO, times(1)).update(chatGroup);
        verify(chatGroupCache, times(1)).setChatGroup("chatgroup:1", chatGroup);
    }

    @Test
    public void testDelete() throws SQLException {
        int id = 1;

        chatGroupService.delete(id);

        verify(chatGroupDAO, times(1)).delete(id);
        verify(chatGroupCache, times(1)).deleteChatGroup("chatgroup:" + id);
    }

    @Test
    public void testGetLastMessage() throws SQLException {
        int groupId = 1;
        ChatMessage chatMessage = new ChatMessage(1, 2, groupId, "text", "Hello", new Timestamp(System.currentTimeMillis()));

        when(chatGroupCache.getLastMessage("lastMessage:" + groupId)).thenReturn(null);
        when(chatGroupDAO.getLastMessage(groupId)).thenReturn(chatMessage);

        ChatMessage result = chatGroupService.getLastMessage(groupId);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(chatGroupCache, times(1)).getLastMessage("lastMessage:" + groupId);
        verify(chatGroupDAO, times(1)).getLastMessage(groupId);
        verify(chatGroupCache, times(1)).setLastMessage("lastMessage:" + groupId, chatMessage);
    }

    @Test
    public void testSortChatGroupsByTime() {
        ChatGroupDTO chatGroup1 = new ChatGroupDTO(1, "group1", true, new Timestamp(System.currentTimeMillis()), "message1", new Timestamp(System.currentTimeMillis()), "avatar1");
        ChatGroupDTO chatGroup2 = new ChatGroupDTO(2, "group2", true, new Timestamp(System.currentTimeMillis()), "message2", new Timestamp(System.currentTimeMillis() - 10000), "avatar2");

        List<ChatGroupDTO> chatGroups = Arrays.asList(chatGroup1, chatGroup2);

        List<ChatGroupDTO> sortedChatGroups = chatGroupService.sortChatGroupsByTime(chatGroups);

        assertNotNull(sortedChatGroups);
        assertEquals(2, sortedChatGroups.size());
        assertEquals(chatGroup1.getId(), sortedChatGroups.get(0).getId());
    }

    @Test
    public void testGetPrivateChatId() throws SQLException {
        int userId1 = 1;
        int userId2 = 2;
        int chatGroupId = 3;

        when(groupMemberCache.getChatGroupId(userId1, userId2)).thenReturn(-1);
        when(groupMemberDAO.getPrivateChatId(userId1, userId2)).thenReturn(chatGroupId);

        int result = chatGroupService.getPrivateChatId(userId1, userId2);

        assertEquals(chatGroupId, result);
        verify(groupMemberCache, times(1)).getChatGroupId(userId1, userId2);
        verify(groupMemberDAO, times(1)).getPrivateChatId(userId1, userId2);
    }

    @Test
    public void testGetChatGroupsByGroupNameNoMatch() throws SQLException {
        String searchGroupName = "nonexistentgroup";
        List<ChatGroup> allChatGroups = Arrays.asList(
                new ChatGroup(1, "testgroup1", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())),
                new ChatGroup(2, "testgroup2", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()))
        );

        when(chatGroupCache.getChatGroups("chatgroups:all")).thenReturn(allChatGroups);

        List<ChatGroupDTO> result = chatGroupService.getChatGroupsByGroupName(searchGroupName);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(chatGroupCache, times(1)).getChatGroups("chatgroups:all");
    }

    @Test
    public void testGetAll() throws SQLException {
        List<ChatGroup> chatGroups = Arrays.asList(
                new ChatGroup(1, "testgroup1", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())),
                new ChatGroup(2, "testgroup2", true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()))
        );

        when(chatGroupCache.getChatGroups("chatgroups:all")).thenReturn(null);
        when(chatGroupDAO.getAll(anyInt())).thenReturn(chatGroups);
        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)) {
            User mockUser = mock(User.class);
            when(mockUser.getId()).thenReturn(1);
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);
            List<ChatGroup> result = chatGroupService.getAll();
            assertNotNull(result);
            assertEquals(2, result.size());
            verify(chatGroupCache, times(1)).getChatGroups("chatgroups:all");
            verify(chatGroupDAO, times(1)).getAll(anyInt());
            verify(chatGroupCache, times(1)).setChatGroups("chatgroups:all", chatGroups);
        }
    }

    @Test
    public void testGetPrivateChatIdCreateNew() throws SQLException {
        int userId1 = 1;
        int userId2 = 2;
        int chatGroupId = 3;

        when(groupMemberCache.getChatGroupId(userId1, userId2)).thenReturn(-1);
        when(groupMemberDAO.getPrivateChatId(userId1, userId2)).thenReturn(-1);
        doAnswer(invocation -> {
            ChatGroup chatGroup = invocation.getArgument(0);
            chatGroup.setId(chatGroupId);
            return null;
        }).when(chatGroupDAO).insert(any(ChatGroup.class));

        int result = chatGroupService.getPrivateChatId(userId1, userId2);

        assertEquals(chatGroupId, result);
        verify(groupMemberCache, times(1)).getChatGroupId(userId1, userId2);
        verify(groupMemberDAO, times(1)).getPrivateChatId(userId1, userId2);
        verify(chatGroupDAO, times(1)).insert(any(ChatGroup.class));
        verify(groupMemberDAO, times(2)).insert(any(GroupMember.class));
    }

    @Test
    void testGetUser() throws Exception {
        int groupId = 1;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User mockUser = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);
        mockUser.setId(2);
        mockUser.setUsername("testuser");
        mockUser.setAvatarUrl("avatar.png");

        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)) {
            when(chatGroupCache.getUser("user:" + groupId)).thenReturn(null);
            // Configure the mock SessionManager to return the mock User
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);
            when(chatGroupDAO.getMember(groupId, SessionManager.getCurrentUser().getId())).thenReturn(mockUser);


            User result = chatGroupService.getUser(groupId);

            assertNotNull(result);
            assertEquals(mockUser.getId(), result.getId());
            assertEquals(mockUser.getUsername(), result.getUsername());
            assertEquals(mockUser.getAvatarUrl(), result.getAvatarUrl());
            verify(chatGroupCache, times(1)).getUser("user:" + groupId);
            verify(chatGroupDAO, times(1)).getMember(groupId, SessionManager.getCurrentUser().getId());
            verify(chatGroupCache, times(1)).setUser("user:" + groupId, mockUser);
        }
    }

    @Test
    void testGetUserFromCache() throws Exception {
        int groupId = 1;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        User mockUser = new User(1, "user1", "password", "email@example.com", "user", "avatarUrl", timestamp, timestamp);
        mockUser.setId(2);
        mockUser.setUsername("testuser");
        mockUser.setAvatarUrl("avatar.png");

        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)) {
            when(chatGroupCache.getUser("user:" + groupId)).thenReturn(mockUser);

            // Configure the mock SessionManager to return the mock User
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            User result = chatGroupService.getUser(groupId);

            assertNotNull(result);
            assertEquals(mockUser.getId(), result.getId());
            assertEquals(mockUser.getUsername(), result.getUsername());
            assertEquals(mockUser.getAvatarUrl(), result.getAvatarUrl());
            verify(chatGroupCache, times(1)).getUser("user:" + groupId);
            verify(chatGroupDAO, never()).getMember(groupId, SessionManager.getCurrentUser().getId());
        }
    }
}
