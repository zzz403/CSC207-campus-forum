package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatSideBarViewModelTest {

    private ChatSideBarViewModel chatSideBarViewModel;

    @BeforeEach
    void setUp() {
        chatSideBarViewModel = new ChatSideBarViewModel();
    }

    @Test
    void testGetAndSetState() {
        ChatSideBarState newState = new ChatSideBarState();
        newState.setError("Error");

        chatSideBarViewModel.setState(newState);

        assertEquals(newState, chatSideBarViewModel.getState());
        assertEquals("Error", chatSideBarViewModel.getState().getError());
    }

    @Test
    void testAddChatGroup() {
        ChatGroupDTO chatGroup = new ChatGroupDTO(1, "Group 1", true, null, "Hello", null, null);

        chatSideBarViewModel.addChatGroup(chatGroup);

        List<ChatGroupDTO> chatGroups = chatSideBarViewModel.getState().getChatGroups();
        assertTrue(chatGroups.contains(chatGroup));
    }

    @Test
    void testRemoveChatGroup() {
        ChatGroupDTO chatGroup = new ChatGroupDTO(1, "Group 1", true, null, "Hello", null, null);

        chatSideBarViewModel.addChatGroup(chatGroup);
        chatSideBarViewModel.removeChatGroup(chatGroup);

        List<ChatGroupDTO> chatGroups = chatSideBarViewModel.getState().getChatGroups();
        assertFalse(chatGroups.contains(chatGroup));
    }

    @Test
    void testFirePropertyChanged() {
        ChatSideBarState state = new ChatSideBarState();
        chatSideBarViewModel.setState(state);

        TestPropertyChangeListener listener = new TestPropertyChangeListener();
        chatSideBarViewModel.addPropertyChangeListener(listener);

        chatSideBarViewModel.firePropertyChanged();

        assertTrue(listener.propertyChanged);
    }

    private static class TestPropertyChangeListener implements PropertyChangeListener {
        boolean propertyChanged = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            propertyChanged = true;
        }
    }
}
