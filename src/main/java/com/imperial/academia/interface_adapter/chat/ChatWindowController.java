package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputData;

/**
 * Controller for handling actions related to the chat window.
 */
public class ChatWindowController {
    private final ChatWindowInputBoundary chatWindowInteractor;

    /**
     * Constructor for ChatWindowController.
     *
     * @param chatWindowInteractor the interactor for chat window operations
     */
    public ChatWindowController(ChatWindowInputBoundary chatWindowInteractor) {
        this.chatWindowInteractor = chatWindowInteractor;
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
}
