package com.imperial.academia.use_case.chat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.service.AudioService;
import com.imperial.academia.service.ChatMessageService;
import com.imperial.academia.service.FileService;
import com.imperial.academia.service.MapService;
import com.imperial.academia.session.SessionManager;

public class ChatWindowInteractorTest {

    @Mock private ChatMessageService chatMessageService;
    @Mock private AudioService audioService;
    @Mock private MapService mapService;
    @Mock private FileService fileService;
    @Mock private ChatWindowOutputBoundary chatWindowPresenter;
    @Mock private ChatMessageFactory chatMessageFactory;

    private ChatWindowInteractor chatWindowInteractor;

    private AutoCloseable closeable;

    @Before
    public void setUp() throws LineUnavailableException {
        closeable = MockitoAnnotations.openMocks(this);
        chatWindowInteractor = new ChatWindowInteractor(
                chatWindowPresenter,
                chatMessageFactory,
                chatMessageService,
                mapService,
                fileService,
                audioService
        );

        doNothing().when(audioService).startRecording(anyInt());
        doNothing().when(audioService).stopRecording();
        when(audioService.getOutputFilePath()).thenReturn("path/to/audio.mp3");
    }

    @After
    public void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();  // Protect against NPE
        }
    }

    @Test
    public void testExecuteLoadsMessages() throws SQLException {
        int chatGroupId = 1;
        List<ChatMessageDTO> expectedMessages = Arrays.asList(
                new ChatMessageDTO(1, 2, "John Doe", "avatarUrl", chatGroupId, "text", "Hello", true, new java.sql.Timestamp(System.currentTimeMillis()))
        );
        when(chatMessageService.getAllByGroupId(chatGroupId)).thenReturn(expectedMessages);

        chatWindowInteractor.execute(chatGroupId);

        verify(chatMessageService).getAllByGroupId(chatGroupId);
        verify(chatWindowPresenter).presentChatMessages(any(ChatWindowOutputData.class));
    }

    @Test
    public void testStartRecording_StartsRecording() throws LineUnavailableException {
        int chatGroupId = 1;
        chatWindowInteractor.startRecording(chatGroupId);

        verify(audioService).startRecording(chatGroupId);
    }

    @Test
    public void testLoadAudio_LoadsAudio() {
        String audioPath = "test.wav";
        chatWindowInteractor.loadAudio(audioPath);

        verify(audioService).loadAudio(audioPath);
    }


    @Test
    public void testSendMessage_SendsTextMessage() throws Exception {
        int senderId = 1;
        int groupId = 1;
        String contentType = "text";
        String content = "Hello";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "text", "Hello", timestamp);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            User mockUser = new User(1, "username", "password", "email@example.com", "user", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            when(chatMessageFactory.createChatMessage(senderId, 1, groupId, contentType, content)).thenReturn(chatMessage);

            ChatWindowInputData inputData = new ChatWindowInputData(groupId, content, contentType);
            chatWindowInteractor.sendMessage(inputData);

            verify(chatMessageService).insert(chatMessage);
        }
    }
}
