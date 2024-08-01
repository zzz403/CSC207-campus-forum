package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatWindowControllerTest {

    private ChatWindowController chatWindowController;
    private ChatWindowInputBoundary mockChatWindowInteractor;

    @BeforeEach
    void setUp() {
        mockChatWindowInteractor = mock(ChatWindowInputBoundary.class);
        chatWindowController = new ChatWindowController(mockChatWindowInteractor);
    }

    @Test
    void testLoadChatMessages() {
        int chatGroupId = 1;
        chatWindowController.loadChatMessages(chatGroupId);
        verify(mockChatWindowInteractor).execute(chatGroupId);
    }

    @Test
    void testSendMessage() throws UnsupportedAudioFileException, SQLException, IOException {
        int groupId = 1;
        String content = "Hello";
        String contentType = "text";

        chatWindowController.sendMessage(content, contentType, groupId);

        ArgumentCaptor<ChatWindowInputData> captor = ArgumentCaptor.forClass(ChatWindowInputData.class);
        verify(mockChatWindowInteractor).sendMessage(captor.capture());
        ChatWindowInputData capturedData = captor.getValue();

        assertEquals(groupId, capturedData.getChatGroupId());
        assertEquals(content, capturedData.getContent());
        assertEquals(contentType, capturedData.getContentType());
    }

    @Test
    void testStartRecording() {
        int chatGroupId = 1;
        chatWindowController.startRecording(chatGroupId);
        verify(mockChatWindowInteractor).startRecording(chatGroupId);
    }

    @Test
    void testStopRecording() {
        int chatGroupId = 1;
        chatWindowController.stopRecording(chatGroupId);
        verify(mockChatWindowInteractor).stopRecording(chatGroupId);
    }

    @Test
    void testLoadAudio() {
        String audioPath = "path/to/audio";
        chatWindowController.loadAudio(audioPath);
        verify(mockChatWindowInteractor).loadAudio(audioPath);
    }

    @Test
    void testSendLocation() {
        int groupId = 1;
        chatWindowController.sendLocation(groupId);
        verify(mockChatWindowInteractor).sendLocation(groupId);
    }

    @Test
    void testSendFile() {
        int groupId = 1;
        File file = new File("path/to/file");

        chatWindowController.sendFile(groupId, file);

        verify(mockChatWindowInteractor).sendFile(groupId, file);
        assertEquals("File size: " + (double) file.length() / (1024 * 1024) + " mb", "File size: 0.0 mb");
    }

    @Test
    void testSummarizeChatHistory() throws SQLException {
        int groupId = 1;
        chatWindowController.summarizeChatHistory(groupId);
        verify(mockChatWindowInteractor).summarizeChatHistory(groupId);
    }

    @Test
    void testSpeechToText() throws Exception {
        String audioPath = "path/to/audio";
        chatWindowController.speechToText(audioPath);
        verify(mockChatWindowInteractor).speechToText(audioPath);
    }

    @Test
    void testTranslate() {
        String text = "Hello";
        String targetLanguage = "es";

        chatWindowController.translate(text, targetLanguage);
        verify(mockChatWindowInteractor).translate(text, targetLanguage);
    }
}
