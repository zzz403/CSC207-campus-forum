package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ChatWindowStateTest {

    private ChatWindowState chatWindowState;

    @BeforeEach
    void setUp() {
        chatWindowState = new ChatWindowState();
    }

    @Test
    void testGetAndSetChatMessages() {
        List<ChatMessageDTO> chatMessages = List.of(
                new ChatMessageDTO(1, 1, "User1", "url1", 1, "text", "Hello", true, null),
                new ChatMessageDTO(2, 2, "User2", "url2", 1, "text", "Hi", false, null)
        );
        chatWindowState.setChatMessages(chatMessages);
        assertEquals(chatMessages, chatWindowState.getChatMessages());
    }

    @Test
    void testGetAndSetError() {
        String error = "An error occurred";
        chatWindowState.setError(error);
        assertEquals(error, chatWindowState.getError());
    }

    @Test
    void testGetAndSetChatGroupId() {
        int chatGroupId = 123;
        chatWindowState.setChatGroupId(chatGroupId);
        assertEquals(chatGroupId, chatWindowState.getChatGroupId());
    }

    @Test
    void testGetAndSetSummary() {
        String summary = "This is a summary";
        chatWindowState.setSummary(summary);
        assertEquals(summary, chatWindowState.getSummary());
    }

    @Test
    void testGetAndSetTranscription() {
        String transcription = "This is a transcription";
        chatWindowState.setTranscription(transcription);
        assertEquals(transcription, chatWindowState.getTranscription());
    }

    @Test
    void testCopyConstructor() {
        List<ChatMessageDTO> chatMessages = List.of(
                new ChatMessageDTO(1, 1, "User1", "url1", 1, "text", "Hello", true, null),
                new ChatMessageDTO(2, 2, "User2", "url2", 1, "text", "Hi", false, null)
        );
        chatWindowState.setChatMessages(chatMessages);
        chatWindowState.setChatGroupId(123);
        chatWindowState.setError("An error occurred");
        chatWindowState.setSummary("This is a summary");
        chatWindowState.setTranscription("This is a transcription");

        ChatWindowState copiedState = new ChatWindowState(chatWindowState);

        assertEquals(chatMessages, copiedState.getChatMessages());
        assertEquals(123, copiedState.getChatGroupId());
        assertEquals("An error occurred", copiedState.getError());
        assertEquals("This is a summary", copiedState.getSummary());
        assertEquals("This is a transcription", copiedState.getTranscription());

        // Ensure it's a deep copy and not the same instance
        assertNotEquals(chatWindowState, copiedState);
    }
}
