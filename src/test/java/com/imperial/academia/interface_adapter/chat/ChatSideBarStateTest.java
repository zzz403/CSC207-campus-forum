package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatSideBarStateTest {

    private ChatSideBarState chatSideBarState;

    @BeforeEach
    void setUp() {
        chatSideBarState = new ChatSideBarState();
    }

    @Test
    void testGetAndSetChatGroups() {
        List<ChatGroupDTO> chatGroups = new ArrayList<>();
        chatGroups.add(new ChatGroupDTO(1, "Group 1", true, null, "Hello", null, null));
        chatGroups.add(new ChatGroupDTO(2, "Group 2", false, null, "Hi", null, null));

        chatSideBarState.setChatGroups(chatGroups);
        assertEquals(chatGroups, chatSideBarState.getChatGroups());
    }

    @Test
    void testGetAndSetError() {
        String error = "An error occurred";

        chatSideBarState.setError(error);
        assertEquals(error, chatSideBarState.getError());
    }

    @Test
    void testInitialState() {
        assertTrue(chatSideBarState.getChatGroups().isEmpty());
        assertEquals("", chatSideBarState.getError());
    }
}
