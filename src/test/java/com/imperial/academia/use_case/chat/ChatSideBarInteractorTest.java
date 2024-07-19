package com.imperial.academia.use_case.chat;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.use_case.chat.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class ChatSideBarInteractorTest {
    @Mock
    private ChatGroupService chatGroupService; // Mock对象
    @Mock
    private ChatSideBarOutputBoundary chatSideBarPresenter;

    @InjectMocks
    private ChatSideBarInteractor chatSideBarInteractor;

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();  // Protect against NPE
        }
    }

    @Test
    public void testExecuteWithSearchQuery() throws SQLException {
        String searchQuery = "testGroup";
        ChatSideBarInputData inputData = new ChatSideBarInputData(searchQuery);
        List<ChatGroupDTO> expectedGroups = List.of(new ChatGroupDTO(1, "testGroup1", true, new Timestamp(System.currentTimeMillis()), "Last message 1", new Timestamp(System.currentTimeMillis()), "avatarUrl1"));

        when(chatGroupService.getChatGroupsByGroupName(searchQuery)).thenReturn(expectedGroups);

        chatSideBarInteractor.execute(inputData);
    }

    @Test
    public void testExecuteWithNoSearchQuery() throws Exception {
        ChatSideBarInputData inputData = new ChatSideBarInputData("");
        List<ChatGroupDTO> chatGroups = Arrays.asList(
                new ChatGroupDTO(1, "testGroup1", true, new Timestamp(System.currentTimeMillis()), "Last message 1", new Timestamp(System.currentTimeMillis()), "avatarUrl1"),
                new ChatGroupDTO(2, "testGroup2", true, new Timestamp(System.currentTimeMillis()), "Last message 2", new Timestamp(System.currentTimeMillis()), "avatarUrl2")
        );

        when(chatGroupService.getChatGroupsByGroupName("")).thenReturn(chatGroups);

        chatSideBarInteractor.execute(inputData);

        verify(chatGroupService, times(1)).getChatGroupsByGroupName("");
        ArgumentCaptor<ChatSideBarOutputData> captor = ArgumentCaptor.forClass(ChatSideBarOutputData.class);
        verify(chatSideBarPresenter, times(1)).presentChatGroups(captor.capture());

        ChatSideBarOutputData outputData = captor.getValue();
        assertEquals(2, outputData.getChatGroups().size());
        assertEquals("testGroup1", outputData.getChatGroups().get(0).getGroupName());
        assertEquals("Last message 1", outputData.getChatGroups().get(0).getLastMessage());
        assertEquals("avatarUrl1", outputData.getChatGroups().get(0).getAvatarUrl());
    }

    @Test
    public void testExecuteWithNoChatGroupsFound() throws Exception {
        String searchQuery = "nonExistingGroup";
        ChatSideBarInputData inputData = new ChatSideBarInputData(searchQuery);

        when(chatGroupService.getChatGroupsByGroupName(searchQuery)).thenReturn(Arrays.asList());

        chatSideBarInteractor.execute(inputData);

        verify(chatGroupService, times(1)).getChatGroupsByGroupName(searchQuery);
        verify(chatSideBarPresenter, times(1)).presentError("No chat groups found.");
    }

    @Test
    public void testExecuteWithException() throws Exception {
        String searchQuery = "errorGroup";
        ChatSideBarInputData inputData = new ChatSideBarInputData(searchQuery);

        when(chatGroupService.getChatGroupsByGroupName(searchQuery)).thenThrow(new RuntimeException("Database error"));

        chatSideBarInteractor.execute(inputData);

        verify(chatGroupService, times(1)).getChatGroupsByGroupName(searchQuery);
        verify(chatSideBarPresenter, times(1)).presentError("An error occurred while searching for chat groups.");
    }
}





