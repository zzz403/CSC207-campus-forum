package com.imperial.academia.use_case.chat;

import static org.mockito.Mockito.*;

import com.imperial.academia.entity.chat_message.*;
import com.imperial.academia.service.*;
import com.imperial.academia.session.SessionManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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
        MockitoAnnotations.openMocks(this);
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
}