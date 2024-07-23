package com.imperial.academia.use_case.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChatWindowOutputDataTest {

    @Test
    void testChatWindowOutputDataCreation() {
        int chatGroupId = 1;
        List<ChatMessageDTO> chatMessages = Arrays.asList(
                new ChatMessageDTO(1, 2, "John Doe", "avatarUrl", chatGroupId, "text", "Hello", true, new Timestamp(System.currentTimeMillis())),
                new ChatMessageDTO(2, 3, "Jane Doe", "avatarUrl", chatGroupId, "text", "Hi", true, new Timestamp(System.currentTimeMillis()))
        );

        ChatWindowOutputData outputData = new ChatWindowOutputData(chatMessages, chatGroupId);

        assertNotNull(outputData);
        assertEquals(chatGroupId, outputData.getChatGroupId());
        assertEquals(chatMessages, outputData.getChatMessages());
    }

    @Test
    void testGetChatMessages() {
        int chatGroupId = 1;
        List<ChatMessageDTO> chatMessages = Arrays.asList(
                new ChatMessageDTO(1, 2, "John Doe", "avatarUrl", chatGroupId, "text", "Hello", true, new Timestamp(System.currentTimeMillis())),
                new ChatMessageDTO(2, 3, "Jane Doe", "avatarUrl", chatGroupId, "text", "Hi", true, new Timestamp(System.currentTimeMillis()))
        );

        ChatWindowOutputData outputData = new ChatWindowOutputData(chatMessages, chatGroupId);

        List<ChatMessageDTO> retrievedMessages = outputData.getChatMessages();
        assertEquals(chatMessages.size(), retrievedMessages.size());
    }

    @Test
    void testGetChatGroupId() {
        int chatGroupId = 1;
        List<ChatMessageDTO> chatMessages = Arrays.asList(
                new ChatMessageDTO(1, 2, "John Doe", "avatarUrl", chatGroupId, "text", "Hello", true, new Timestamp(System.currentTimeMillis()))
        );

        ChatWindowOutputData outputData = new ChatWindowOutputData(chatMessages, chatGroupId);

        assertEquals(chatGroupId, outputData.getChatGroupId());
    }
}
