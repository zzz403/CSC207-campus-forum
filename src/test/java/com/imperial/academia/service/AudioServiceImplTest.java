package com.imperial.academia.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;

public class AudioServiceImplTest {

    private AudioServiceImpl audioService;
    private AudioServiceImpl.AudioRecorder mockAudioRecorder;
    private AudioServiceImpl.AudioPlayer mockAudioPlayer;

    @BeforeEach
    void setUp() throws Exception {
        mockAudioRecorder = Mockito.mock(AudioServiceImpl.AudioRecorder.class);
        mockAudioPlayer = Mockito.mock(AudioServiceImpl.AudioPlayer.class);

        // Use reflection to set the mocks
        audioService = new AudioServiceImpl();
        setPrivateField(audioService, "audioRecorder", mockAudioRecorder);
        setPrivateField(audioService, "audioPlayer", mockAudioPlayer);

    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void testStartRecording() throws Exception {
        int groupId = 1;

        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)) {
            // Create a mock User
            System.out.println("Creating mock user");
            User mockUser = mock(User.class);

            // Configure the mock User to return an ID
            when(mockUser.getId()).thenReturn(1);

            // Configure the mock SessionManager to return the mock User
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(mockUser);

            // Call the method under test
            audioService.startRecording(groupId);

            // Ensure the mocked method is called
            mockedStatic.verify(SessionManager::getCurrentUser, times(1));

            // Verify that the recording started
            verify(mockAudioRecorder).startRecording(any());
            assertTrue((Boolean) getPrivateField(audioService, "recording"));
        }
    }

    @Test
    void testStartRecordingNoUserLoggedIn() {
        int groupId = 1;

        try (MockedStatic<SessionManager> mockedStatic = Mockito.mockStatic(SessionManager.class)) {
            // Configure the mock SessionManager to return null for getCurrentUser
            mockedStatic.when(SessionManager::getCurrentUser).thenReturn(null);

            // Expect an IllegalStateException when no user is logged in
            Exception exception = assertThrows(IllegalStateException.class, () -> {
                audioService.startRecording(groupId);
            });

            assertEquals("No user is logged in", exception.getMessage());
        }
    }

    @Test
    void testLoadAudioFileNotFound() {
        String audioFilePath = "non_existent_file.wav";

        doThrow(new RuntimeException("File not found")).when(mockAudioPlayer).load(audioFilePath);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            audioService.loadAudio(audioFilePath);
        });

        assertEquals("File not found", exception.getMessage());
    }

    @Test
    void testProcessUnsupportedAudioFile() {
        String audioFilePath = "unsupported_audio_format.xyz";

        WaveformData waveformData = audioService.processAudio(audioFilePath);

        // Exception exception = assertThrows(RuntimeException.class, () -> {
        //     audioService.processAudio(audioFilePath);
        // });

        assertNull(waveformData);
    }


    @Test
    void testStopRecording() throws Exception {
        setPrivateField(audioService, "recording", true);

        audioService.stopRecording();

        verify(mockAudioRecorder).stopRecording();
        assertFalse((Boolean) getPrivateField(audioService, "recording"));
    }

    @Test
    void testLoadAudio() {
        String audioFilePath = "test.wav";
        audioService.loadAudio(audioFilePath);

        verify(mockAudioPlayer).load(audioFilePath);
        verify(mockAudioPlayer).play();
    }

    @Test
    void testProcessAudio() {
        String audioFilePath = "resources/audio/for_test/admin_20240626_180700.wav";
        WaveformData waveformData = audioService.processAudio(audioFilePath);

        assertNotNull(waveformData);
        assertFalse(waveformData.getMinValues().isEmpty());
        assertFalse(waveformData.getMaxValues().isEmpty());
    }

    @Test
    void testGetOutputFilePath() throws Exception {
        String expectedPath = "test_output.wav";
        setPrivateField(audioService, "outputFilePath", expectedPath);

        String actualPath = audioService.getOutputFilePath();
        assertEquals(expectedPath, actualPath);
    }

    private Object getPrivateField(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}
