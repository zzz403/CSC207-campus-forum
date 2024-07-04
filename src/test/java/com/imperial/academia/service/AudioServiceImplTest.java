package com.imperial.academia.service;

import com.imperial.academia.entity.chat_message.WaveformData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import java.io.File;

import static org.junit.Assert.*;

public class AudioServiceImplTest {

    private AudioServiceImpl audioService;

    @Before
    public void setUp() {
        audioService = new AudioServiceImpl();
    }

    @After
    public void tearDown() {
        File recordedFile = new File(audioService.getOutputFilePath());
        if (recordedFile.exists()) {
            recordedFile.delete();
        }
    }

    @Test
    public void testGetOutputFilePath() throws LineUnavailableException {
        int groupId = 1;
        audioService.startRecording(groupId);
        String outputPath = audioService.getOutputFilePath();
        assertNotNull(outputPath);
        assertTrue(outputPath.endsWith(".wav"));
        audioService.stopRecording();
    }

    @Test
    public void testLoadAudio() {
        String testAudioPath = "resources/test_audio.wav";
        File testAudioFile = new File(testAudioPath);
        assertTrue("Test audio file does not exist", testAudioFile.exists());
        audioService.loadAudio(testAudioPath);
    }

    @Test
    public void testProcessAudio() {
        String testAudioPath = "resources/test_audio.wav";
        File testAudioFile = new File(testAudioPath);
        assertTrue("Test audio file does not exist", testAudioFile.exists());
        WaveformData waveformData = audioService.processAudio(testAudioPath);
        assertNotNull(waveformData);
        assertTrue(waveformData.getMinValues().size() > 0);
        assertTrue(waveformData.getMaxValues().size() > 0);
        assertTrue(waveformData.getDuration() > 0);
    }

    @Test
    public void testStartRecording() throws LineUnavailableException {
        int groupId = 1;
        audioService.startRecording(groupId);
        String outputPath = audioService.getOutputFilePath();
        assertNotNull(outputPath);
        assertTrue(outputPath.endsWith(".wav"));
        audioService.stopRecording();
    }

    @Test
    public void testStopRecording() throws LineUnavailableException {
        int groupId = 1;
        audioService.startRecording(groupId);
        audioService.stopRecording();
        File recordedFile = new File(audioService.getOutputFilePath());
        assertTrue(recordedFile.exists());
    }
}
