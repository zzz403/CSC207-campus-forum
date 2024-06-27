package com.imperial.academia.service;

import javax.sound.sampled.*;


import com.imperial.academia.session.SessionManager;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class AudioServiceImpl implements AudioService{
    private AudioRecorder audioRecorder;
    private AudioPlayer audioPlayer;
    private boolean recording;
    private String outputFilePath;

    public AudioServiceImpl() {
        this.audioRecorder = new AudioRecorder();
        this.audioPlayer = new AudioPlayer();
        this.recording = false;
    }
    
    @Override
    public void startRecording(int groupId) throws LineUnavailableException {
        // Create the directory if it doesn't exist
        File groupDir = new File("resources/audio/" + groupId);
        if (!groupDir.exists()) {
            groupDir.mkdirs();
        }

        String senderName = SessionManager.getCurrentUser().getUsername();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestampStr = dateFormat.format(timestamp);
        outputFilePath = "resources/audio/" + groupId + "/" + senderName + "_" + timestampStr + ".wav";
        audioRecorder.startRecording(outputFilePath);
        recording = true;
    }

    @Override
    public void stopRecording() {
        if (recording) {
            audioRecorder.stopRecording();
            recording = false;
        }
    }

    @Override
    public void loadAudio(String audioFilePath) {
        audioPlayer.load(audioFilePath);
        audioPlayer.play();
    }

    @Override
    public String getOutputFilePath() {
        return outputFilePath;
    }

    class AudioRecorder {
        private AudioFormat audioFormat;
        private TargetDataLine targetDataLine;
        private String outputFilePath;

        public AudioRecorder() {
            audioFormat = new AudioFormat(44100, 16, 2, true, true);
        }

        public void startRecording(String outputFilePath) {
            this.outputFilePath = outputFilePath;
            try {
                DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
                targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
                targetDataLine.open(audioFormat);
                targetDataLine.start();

                Thread recordingThread = new Thread(() -> {
                    AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);
                    File audioFile = new File(outputFilePath);
                    try {
                        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                recordingThread.start();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void stopRecording() {
            targetDataLine.stop();
            targetDataLine.close();
        }

        public String getOutputFilePath() {
            return outputFilePath;
        }
    }

    class AudioPlayer {
        private Clip audioClip;

        public void load(String audioFilePath) {
            try {
                File audioFile = new File(audioFilePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                audioClip = AudioSystem.getClip();
                audioClip.open(audioStream);
                audioClip.start(); // Automatically start playing the audio
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void play() {
            if (audioClip != null) {
                audioClip.start();
            }
        }

        public void stop() {
            if (audioClip != null) {
                audioClip.stop();
            }
        }
    }
}

