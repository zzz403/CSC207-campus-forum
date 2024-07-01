package com.imperial.academia.service;

import javax.sound.sampled.*;


import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.session.SessionManager;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AudioServiceImpl implements AudioService{
    private final AudioRecorder audioRecorder;
    private final AudioPlayer audioPlayer;
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

    public WaveformData processAudio(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioInputStream.getFormat();
            byte[] audioBytes = audioInputStream.readAllBytes();
            int frameSize = format.getFrameSize();
            float frameRate = format.getFrameRate();
            float durationInSeconds = ((float) audioBytes.length / frameSize) / frameRate;

            // 计算目标存储数量
            int numSegments = durationInSeconds >= 5 ? 30 : Math.min(Math.max((int) (durationInSeconds / 5.0 * 30), 15), 30);
            int segmentSize = audioBytes.length / frameSize / numSegments;

            List<Integer> minValues = new ArrayList<>();
            List<Integer> maxValues = new ArrayList<>();

            for (int i = 0; i < numSegments; i++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < segmentSize; j++) {
                    int sampleIndex = (i * segmentSize + j) * frameSize;
                    if (sampleIndex < audioBytes.length - frameSize) {
                        int sample = getSample(audioBytes, sampleIndex, format);
                        if (sample < min) min = sample;
                        if (sample > max) max = sample;
                    }
                }
                minValues.add(min);
                maxValues.add(max);
            }

            return new WaveformData(minValues, maxValues, durationInSeconds);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int getSample(byte[] audioBytes, int sampleIndex, AudioFormat format) {
        int sampleSizeInBytes = format.getSampleSizeInBits() / 8;
        boolean isBigEndian = format.isBigEndian();
        int sample = 0;

        if (sampleSizeInBytes == 2) {
            if (isBigEndian) {
                sample = ByteBuffer.wrap(audioBytes, sampleIndex, sampleSizeInBytes).order(ByteOrder.BIG_ENDIAN).getShort();
            } else {
                sample = ByteBuffer.wrap(audioBytes, sampleIndex, sampleSizeInBytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
            }
        } else {
            // 处理其他样本大小（如 8 位）
            sample = audioBytes[sampleIndex];
        }

        return sample;
    }


    static class AudioRecorder {
        private final AudioFormat audioFormat;
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

    static class AudioPlayer {
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

