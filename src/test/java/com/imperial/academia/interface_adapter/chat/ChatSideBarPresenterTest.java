package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarOutputData;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatSideBarPresenterTest {

    private ChatSideBarPresenter chatSideBarPresenter;
    private ChatSideBarViewModel chatSideBarViewModel;
    private ChatSideBarState chatSideBarState;

    @BeforeEach
    void setUp() {
        chatSideBarState = new ChatSideBarState();
        chatSideBarViewModel = Mockito.mock(ChatSideBarViewModel.class);

        when(chatSideBarViewModel.getState()).thenReturn(chatSideBarState);

        chatSideBarPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
    }

    @Test
    void testPresentChatGroups() {
        List<ChatGroupDTO> chatGroups = new ArrayList<>();
        chatGroups.add(new ChatGroupDTO(1, "Group 1", true, null, "Hello", null, null));
        chatGroups.add(new ChatGroupDTO(2, "Group 2", false, null, "Hi", null, null));

        ChatSideBarOutputData outputData = new ChatSideBarOutputData(chatGroups);

        chatSideBarPresenter.presentChatGroups(outputData);

        assertEquals(chatGroups, chatSideBarState.getChatGroups());
        verify(chatSideBarViewModel, times(1)).setState(chatSideBarState);
        verify(chatSideBarViewModel, times(1)).firePropertyChanged();
    }

    @Test
    void testPresentError() {
        String error = "An error occurred";

        chatSideBarPresenter.presentError(error);

        assertEquals(error, chatSideBarState.getError());
        assertTrue(chatSideBarState.getChatGroups().isEmpty());
        verify(chatSideBarViewModel, times(1)).setState(chatSideBarState);
        verify(chatSideBarViewModel, times(1)).firePropertyChanged();
    }
}
