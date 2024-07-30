package com.imperial.academia.use_case.chat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;

import com.imperial.academia.use_case.ASR.ASRInputBoundary;
import com.imperial.academia.use_case.LLM.LLMInputBoundary;
import com.imperial.academia.use_case.Translator.TranslatorInputBoundary;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;
import com.imperial.academia.entity.chat_message.FileData;
import com.imperial.academia.entity.chat_message.MapData;
import com.imperial.academia.entity.chat_message.WaveformData;
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
    @Mock private LLMInputBoundary llmInputBoundary;
    @Mock private ASRInputBoundary asrInputBoundary;
    @Mock private TranslatorInputBoundary translatorInputBoundary;
    @Mock private ChatSideBarInputBoundary chatSideBarInteractor;

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
                audioService,
                llmInputBoundary,
                asrInputBoundary,
                translatorInputBoundary,
                chatSideBarInteractor
        );

        doNothing().when(audioService).startRecording(anyInt());
        doNothing().when(audioService).stopRecording();
        when(audioService.getOutputFilePath()).thenReturn("path/to/audio.mp3");
    }

    @After
    public void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
        SessionManager.clearSession();
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

    @Test
    public void testSendMessage_SendsAudioMessage() throws Exception {
        int senderId = 1;
        int groupId = 1;
        String contentType = "audio";
        String content = "path/to/audio.mp3";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "audio", content, timestamp);
        WaveformData waveformData = new WaveformData(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), 1.5f);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            User mockUser = new User(1, "username", "password", "email@example.com", "user", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            when(chatMessageFactory.createChatMessage(senderId, 1, groupId, contentType, content)).thenReturn(chatMessage);
            when(audioService.processAudio(content)).thenReturn(waveformData);

            ChatWindowInputData inputData = new ChatWindowInputData(groupId, content, contentType);
            chatWindowInteractor.sendMessage(inputData);

            verify(chatMessageService).insert(chatMessage, waveformData);
        }
    }

    @Test
    public void testSendMessage_SendsMapMessage() throws Exception {
        int senderId = 1;
        int groupId = 1;
        String contentType = "map";
        String content = "path/to/map.png";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "map", content, timestamp);
        double[] location = {37.7749, -122.4194};
        String locationInfo = "San Francisco, California, United States";
        MapData mapData = new MapData(location[0], location[1], locationInfo);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            User mockUser = new User(1, "username", "password", "email@example.com", "user", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            when(chatMessageFactory.createChatMessage(senderId, 1, groupId, contentType, content)).thenReturn(chatMessage);
            when(mapService.getUserLocation()).thenReturn(location);
            when(mapService.getLocationInfo(location[0], location[1])).thenReturn(locationInfo);

            ChatWindowInputData inputData = new ChatWindowInputData(groupId, content, contentType);
            chatWindowInteractor.sendMessage(inputData);

            ArgumentCaptor<MapData> mapDataCaptor = ArgumentCaptor.forClass(MapData.class);
            verify(chatMessageService).insert(any(ChatMessage.class), mapDataCaptor.capture());

            MapData capturedMapData = mapDataCaptor.getValue();
            assertEquals(location[0], capturedMapData.getLatitude(), 0.0001);
            assertEquals(location[1], capturedMapData.getLongitude(), 0.0001);
            assertEquals(locationInfo, capturedMapData.getLocationInfo());
        }
    }

    @Test
    public void testSendMessage_SendsFileMessage() throws Exception {
        int senderId = 1;
        int groupId = 1;
        String contentType = "file";
        String content = "path/to/file.pdf";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ChatMessage chatMessage = new ChatMessage(1, 1, 1, "file", content, timestamp);
        FileData fileData = new FileData(content, "file.pdf", "png");

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            User mockUser = new User(1, "username", "password", "email@example.com", "user", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            when(chatMessageFactory.createChatMessage(senderId, 1, groupId, contentType, content)).thenReturn(chatMessage);
            when(fileService.getFileData(content)).thenReturn(fileData);

            ChatWindowInputData inputData = new ChatWindowInputData(groupId, content, contentType);
            chatWindowInteractor.sendMessage(inputData);

            verify(chatMessageService).insert(chatMessage, fileData);
        }
    }

    @Test
    public void testStopRecording_StopsRecordingAndSendsAudioMessage() throws Exception {
        int groupId = 1;
        String audioFilePath = "path/to/audio.mp3";
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(groupId, audioFilePath, "audio");

        when(audioService.getOutputFilePath()).thenReturn(audioFilePath);

        try (MockedStatic<SessionManager> mockedSessionManager = mockStatic(SessionManager.class)) {
            User mockUser = new User(1, "username", "password", "email@example.com", "user", "avatarUrl", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            mockedSessionManager.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            ChatMessage chatMessage = new ChatMessage(1, mockUser.getId(), groupId, "audio", audioFilePath, new Timestamp(System.currentTimeMillis()));
            WaveformData waveformData = new WaveformData(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6), 1.5f);

            when(chatMessageFactory.createChatMessage(mockUser.getId(), 1, groupId, "audio", audioFilePath)).thenReturn(chatMessage);
            when(audioService.processAudio(audioFilePath)).thenReturn(waveformData);

            chatWindowInteractor.stopRecording(groupId);

            verify(audioService).stopRecording();
            verify(chatMessageService).insert(chatMessage, waveformData);
        }
    }

    @Test
    public void testExecuteHandlesException() throws SQLException {
        int chatGroupId = 1;
        when(chatMessageService.getAllByGroupId(chatGroupId)).thenThrow(new SQLException("Database error"));

        chatWindowInteractor.execute(chatGroupId);

        verify(chatWindowPresenter).presentError("An error occurred while loading chat messages.");
    }

    @Test
    public void testStartRecordingHandlesException() throws LineUnavailableException {
        int chatGroupId = 1;
        doThrow(new LineUnavailableException("Audio error")).when(audioService).startRecording(chatGroupId);

        chatWindowInteractor.startRecording(chatGroupId);

        verify(chatWindowPresenter).presentError("An error occurred while starting recording.");
    }
}
