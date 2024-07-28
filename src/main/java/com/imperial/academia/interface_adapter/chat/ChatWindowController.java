package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;

import java.io.File;
import java.sql.SQLException;

/**
 * Controller for handling actions related to the chat window.
 */
public class ChatWindowController {
    private final ChatWindowInputBoundary chatWindowInteractor;

    /**
     * Constructor for ChatWindowController.
     *
     */
    public ChatWindowController() {
        this.chatWindowInteractor = UsecaseFactory.getChatWindowInteractor();
    }

    /**
     * Loads chat messages for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    public void loadChatMessages(int chatGroupId) {
        chatWindowInteractor.execute(chatGroupId);
    }

    /**
     * Sends a message to a specific chat group.
     *
     * @param content the content of the message
     * @param contentType the type of the content (e.g., text, image, audio)
     * @param groupId the ID of the chat group
     */
    public void sendMessage(String content, String contentType, int groupId) {
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(groupId, content, contentType);
        try {
            chatWindowInteractor.sendMessage(chatWindowInputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts recording audio for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    public void startRecording(int chatGroupId) {
        chatWindowInteractor.startRecording(chatGroupId);
    }

    /**
     * Stops recording audio for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    public void stopRecording(int chatGroupId) {
        chatWindowInteractor.stopRecording(chatGroupId);
    }

    /**
     * Loads an audio file for playback.
     *
     * @param audioPath the path to the audio file
     */
    public void loadAudio(String audioPath) {
        chatWindowInteractor.loadAudio(audioPath);
    }


    public void sendLocation(int groupId){
        chatWindowInteractor.sendLocation(groupId);
    }

    public void sendFile(int groupId, File selectedFile){
        chatWindowInteractor.sendFile(groupId, selectedFile);
        System.out.println("File size: " + (double) selectedFile.length() / (1024 * 1024) + " mb");
    }

    public void summarizeChatHistory(int groupId) throws SQLException {
        chatWindowInteractor.summarizeChatHistory(groupId);
    }

    public void speechToText(String audioPath) throws Exception {
        chatWindowInteractor.speechToText(audioPath);
    }

    public void translate(String text, String targetLanguage) {
        chatWindowInteractor.translate(text, targetLanguage);
    }
}
