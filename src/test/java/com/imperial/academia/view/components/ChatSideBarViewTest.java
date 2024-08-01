package com.imperial.academia.view.components;

import com.imperial.academia.interface_adapter.chat.ChatSideBarController;
import com.imperial.academia.interface_adapter.chat.ChatSideBarState;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class ChatSideBarViewTest {

    private ChatSideBarViewModel mockViewModel;
    private ChatSideBarController mockController;
    private ChatSideBarView chatSideBarView;

    @BeforeEach
    void setUp() {
        mockViewModel = mock(ChatSideBarViewModel.class);
        mockController = mock(ChatSideBarController.class);
        chatSideBarView = new ChatSideBarView(mockViewModel);
        chatSideBarView.setChatSideBarController(mockController);
    }

    @Test
    void testInitialization() {
        // Test initialization
        chatSideBarView.setChatSideBarController(mockController);
    }

    @Test
    void testDisplayChatGroups() {
        // Test displayChatGroups method
        List<ChatGroupDTO> chatGroups = new ArrayList<>();
        chatGroups.add(new ChatGroupDTO(1, "Group1", true, new Timestamp(System.currentTimeMillis()), "Last message", new Timestamp(System.currentTimeMillis()), "path/to/avatar"));
        chatSideBarView.displayChatGroups(chatGroups);
    }

    @Test
    void testPropertyChange() {
        // Test property change listener
        ChatSideBarState state = new ChatSideBarState();
        when(mockViewModel.getState()).thenReturn(state);
        mockViewModel.firePropertyChanged();
    }
}
