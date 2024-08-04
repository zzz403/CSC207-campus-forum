package com.imperial.academia.interface_adapter.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;

class ChatWindowViewModelTest {

    private ChatWindowViewModel chatWindowViewModel;
    private TestPropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        chatWindowViewModel = new ChatWindowViewModel();
        listener = new TestPropertyChangeListener();
        chatWindowViewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testGetAndSetState() {
        ChatWindowState initialState = new ChatWindowState();
        initialState.setChatGroupId(1);
        chatWindowViewModel.setState(initialState);

        ChatWindowState state = chatWindowViewModel.getState();
        assertEquals(initialState.getChatGroupId(), state.getChatGroupId());
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAddChatMessage() {
        ChatMessageDTO chatMessage = new ChatMessageDTO(
                1, 1, "User1", "url1", 1, "text", "Hello", true, new Timestamp(System.currentTimeMillis()));
        chatWindowViewModel.addChatMessage(chatMessage);

        ChatWindowState state = chatWindowViewModel.getState();
        assertTrue(state.getChatMessages().contains(chatMessage));

        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("chatMessages", event.getPropertyName());
        assertTrue(((List<ChatMessageDTO>) event.getNewValue()).contains(chatMessage));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testRemoveChatMessage() {
        ChatMessageDTO chatMessage = new ChatMessageDTO(
                1, 1, "User1", "url1", 1, "text", "Hello", true, new Timestamp(System.currentTimeMillis()));
        chatWindowViewModel.addChatMessage(chatMessage);
        chatWindowViewModel.removeChatMessage(chatMessage);

        ChatWindowState state = chatWindowViewModel.getState();
        assertTrue(!state.getChatMessages().contains(chatMessage));

        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("chatMessages", event.getPropertyName());
        assertTrue(!((List<ChatMessageDTO>) event.getNewValue()).contains(chatMessage));
    }

    @Test
    void testFirePropertyChanged() {
        chatWindowViewModel.firePropertyChanged();
        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("chatWindowState", event.getPropertyName());
    }

    @Test
    void testFirePropertyChangedWithPropertyName() {
        chatWindowViewModel.firePropertyChanged("customProperty");
        PropertyChangeEvent event = listener.getLastEvent();
        assertEquals("customProperty", event.getPropertyName());
    }

    // Helper class to listen for property change events
    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private PropertyChangeEvent lastEvent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            lastEvent = evt;
        }

        public PropertyChangeEvent getLastEvent() {
            return lastEvent;
        }
    }
}
