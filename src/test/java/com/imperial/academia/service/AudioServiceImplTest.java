package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.entity.user.User;
import com.imperial.academia.session.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
//        PowerMockito.mockStatic(SessionManager.class);
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

//    @Test
//    void testStartRecording() throws Exception {
//        int groupId = 1;
//
//        // Create a mock User
//        User mockUser = mock(User.class);
//
//        // Configure the mock User to return an ID
//        when(mockUser.getId()).thenReturn(1);
//
//        // Configure the mock SessionManager to return the mock User
//        when(SessionManager.getCurrentUser()).thenReturn(mockUser);
//
//        // Call the method under test
//        audioService.startRecording(groupId);
//
//        // Verify that the recording started
//        verify(mockAudioRecorder).startRecording(anyString());
//        assertTrue((Boolean) getPrivateField(audioService, "recording"));
//    }

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
