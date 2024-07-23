package com.imperial.academia.use_case.chat;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.service.ChatGroupService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ChatSideBarInteractorTest {

    @Mock
    private ChatGroupService chatGroupService;

    @Mock
    private ChatSideBarOutputBoundary chatSideBarPresenter;

    @InjectMocks
    private ChatSideBarInteractor chatSideBarInteractor;

    private AutoCloseable closeable;

    @Before
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    public void testExecute_WithSearchQuery_FindsChatGroups() throws Exception {
        String searchQuery = "test";
        ChatSideBarInputData inputData = new ChatSideBarInputData(searchQuery);

        List<ChatGroupDTO> chatGroups = new ArrayList<>();
        chatGroups.add(new ChatGroupDTO(1, "Test Group", true, null, "", null, ""));

        when(chatGroupService.getChatGroupsByGroupName(searchQuery)).thenReturn(chatGroups);

        chatSideBarInteractor.execute(inputData);

        ArgumentCaptor<ChatSideBarOutputData> captor = ArgumentCaptor.forClass(ChatSideBarOutputData.class);
        verify(chatSideBarPresenter).presentChatGroups(captor.capture());
        ChatSideBarOutputData outputData = captor.getValue();

        assertNotNull(outputData);
        assertEquals(1, outputData.getChatGroups().size());
        assertEquals("Test Group", outputData.getChatGroups().get(0).getGroupName());
    }

    @Test
    public void testExecute_WithSearchQuery_NoChatGroupsFound() throws Exception {
        String searchQuery = "test";
        ChatSideBarInputData inputData = new ChatSideBarInputData(searchQuery);

        when(chatGroupService.getChatGroupsByGroupName(searchQuery)).thenReturn(new ArrayList<>());

        chatSideBarInteractor.execute(inputData);

        verify(chatSideBarPresenter).presentError("No chat groups found.");
    }

    @Test
    public void testExecute_WithSearchQuery_ExceptionThrown() throws Exception {
        String searchQuery = "test";
        ChatSideBarInputData inputData = new ChatSideBarInputData(searchQuery);

        when(chatGroupService.getChatGroupsByGroupName(searchQuery)).thenThrow(new RuntimeException());

        chatSideBarInteractor.execute(inputData);

        verify(chatSideBarPresenter).presentError("An error occurred while searching for chat groups.");
    }

    @Test
    public void testExecute_WithoutSearchQuery_FindsChatGroups() throws Exception {
        List<ChatGroupDTO> chatGroups = new ArrayList<>();
        chatGroups.add(new ChatGroupDTO(1, "Test Group", true, null, "", null, ""));

        when(chatGroupService.getChatGroupsByGroupName("")).thenReturn(chatGroups);

        chatSideBarInteractor.execute();

        ArgumentCaptor<ChatSideBarOutputData> captor = ArgumentCaptor.forClass(ChatSideBarOutputData.class);
        verify(chatSideBarPresenter).presentChatGroups(captor.capture());
        ChatSideBarOutputData outputData = captor.getValue();

        assertNotNull(outputData);
        assertEquals(1, outputData.getChatGroups().size());
        assertEquals("Test Group", outputData.getChatGroups().get(0).getGroupName());
    }

    @Test
    public void testExecute_WithoutSearchQuery_NoChatGroupsFound() throws Exception {
        when(chatGroupService.getChatGroupsByGroupName("")).thenReturn(new ArrayList<>());

        chatSideBarInteractor.execute();

        verify(chatSideBarPresenter).presentError("No chat groups found.");
    }

    @Test
    public void testExecute_WithoutSearchQuery_ExceptionThrown() throws Exception {
        when(chatGroupService.getChatGroupsByGroupName("")).thenThrow(new RuntimeException());

        chatSideBarInteractor.execute();

        verify(chatSideBarPresenter).presentError("An error occurred while searching for chat groups.");
    }
}
