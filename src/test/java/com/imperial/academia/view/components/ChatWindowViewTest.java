package com.imperial.academia.view.components;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.interface_adapter.chat.ChatWindowState;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;

class ChatWindowViewTest {

    private ChatWindowViewModel mockViewModel;
    private ChatWindowView chatWindowView;
    private JFrame mockFrame;

    @BeforeEach
    void setUp() {
        mockViewModel = Mockito.mock(ChatWindowViewModel.class);
        mockFrame = Mockito.mock(JFrame.class);
        chatWindowView = new ChatWindowView(mockViewModel, mockFrame);
    }

    @Test
    void testInitialization() {
        // Test initialization
        chatWindowView = new ChatWindowView(mockViewModel, mockFrame);
    }

    @Test
    void testDisplayChatMessages() {
        // Test displayChatMessages method
        List<ChatMessageDTO> chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessageDTO(1, 1, "User1", "avatar1.png", 1, "text", "Hello", false, new Timestamp(System.currentTimeMillis())));
        chatWindowView.displayChatMessages(chatMessages);
    }

    @Test
    void testPropertyChange() {
        // Test property change listener
        ChatWindowState state = new ChatWindowState();
        Mockito.when(mockViewModel.getState()).thenReturn(state);
        mockViewModel.firePropertyChanged();
    }
}
