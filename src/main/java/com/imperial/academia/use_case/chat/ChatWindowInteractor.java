package com.imperial.academia.use_case.chat;

import com.imperial.academia.entity.chat_message.WaveformData;
import com.imperial.academia.service.AudioService;
import com.imperial.academia.service.ChatMessageService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.entity.chat_message.ChatMessage;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interactor class for handling chat window operations.
 * Implements the ChatWindowInputBoundary interface to process input data and perform actions.
 */
public class ChatWindowInteractor implements ChatWindowInputBoundary {
    private final ChatMessageService chatMessageService;
    private final AudioService audioService;
    private final ChatWindowOutputBoundary chatWindowPresenter;
    private final ChatMessageFactory chatMessageFactory;

    /**
     * Constructor for ChatWindowInteractor.
     *
     * @param chatMessageService the service for chat message operations
     * @param audioService the service for audio operations
     * @param chatWindowPresenter the presenter for chat window output
     * @param chatMessageFactory the factory for creating chat messages
     */
    public ChatWindowInteractor(ChatMessageService chatMessageService, AudioService audioService, ChatWindowOutputBoundary chatWindowPresenter, ChatMessageFactory chatMessageFactory) {
        this.chatMessageService = chatMessageService;
        this.audioService = audioService;
        this.chatWindowPresenter = chatWindowPresenter;
        this.chatMessageFactory = chatMessageFactory;
    }

    /**
     * Loads chat messages for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    @Override
    public void execute(int chatGroupId) {
        try {
            List<ChatMessageDTO> chatMessages = chatMessageService.getAllByGroupId(chatGroupId);
            ChatWindowOutputData chatWindowOutputData = new ChatWindowOutputData(chatMessages, chatGroupId);
            chatWindowPresenter.presentChatMessages(chatWindowOutputData);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while loading chat messages.");
        }
    }

    /**
     * Starts recording audio for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    @Override
    public void startRecording(int chatGroupId) {
        try {
            audioService.startRecording(chatGroupId);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while starting recording.");
        }
    }

    /**
     * Stops recording audio and sends the recorded message.
     *
     * @param chatGroupId the ID of the chat group
     */
    @Override
    public void stopRecording(int chatGroupId) {
        try {
            audioService.stopRecording();
            ChatWindowInputData chatWindowInputData = new ChatWindowInputData(chatGroupId, audioService.getOutputFilePath(), "audio");
            sendMessage(chatWindowInputData);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while stopping recording.");
        }
    }

    /**
     * Loads an audio file for playback.
     *
     * @param audioPath the path to the audio file
     */
    @Override
    public void loadAudio(String audioPath) {
        try {
            audioService.loadAudio(audioPath);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while loading audio.");
        }
    }

    /**
     * Sends a chat message.
     *
     * @param chatWindowInputData the input data for the chat message
     * @throws UnsupportedAudioFileException if the audio file format is not supported
     * @throws IOException if an I/O error occurs
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void sendMessage(ChatWindowInputData chatWindowInputData) throws UnsupportedAudioFileException, IOException, SQLException {
        int senderId = SessionManager.getCurrentUser().getId();
        int groupId = chatWindowInputData.getChatGroupId();
        String contentType = chatWindowInputData.getContentType();
        String content = chatWindowInputData.getContent();
        ChatMessage chatMessage = chatMessageFactory.createChatMessage(senderId, 1, groupId, contentType, content);
        try {
            if (contentType.equals("audio")) {
                WaveformData waveformData = audioService.processAudio(chatMessage.getContent());
                chatMessageService.insert(chatMessage, waveformData);
            } else {
                chatMessageService.insert(chatMessage);
            }
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while sending the message.");
        }

        // Refresh the chat messages after sending the message
        execute(groupId);
    }
}
