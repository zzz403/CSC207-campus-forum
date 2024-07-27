package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * State class for the chat window, managing the list of chat messages,
 * the current chat group ID, and any errors.
 */
public class ChatWindowState {
    private List<ChatMessageDTO> chatMessages = new ArrayList<>();
    private int chatGroupId;
    private String error = "";
    private String summary = "";
    private String transcription = "";

    /**
     * Copy constructor for ChatWindowState.
     *
     * @param chatWindowState the existing chat window state to copy
     */
    public ChatWindowState(ChatWindowState chatWindowState) {
        this.chatMessages = chatWindowState.getChatMessages();
        this.chatGroupId = chatWindowState.getChatGroupId();
        this.error = chatWindowState.getError();
        this.summary = chatWindowState.getSummary();
        this.transcription = chatWindowState.getTranscription();
    }

    /**
     * Default constructor for ChatWindowState.
     */
    public ChatWindowState() {
    }

    /**
     * Gets the list of chat messages.
     *
     * @return a list of ChatMessageDTO objects
     */
    public List<ChatMessageDTO> getChatMessages() {
        return chatMessages;
    }

    /**
     * Sets the list of chat messages.
     *
     * @param chatMessages the list of ChatMessageDTO objects to set
     */
    public void setChatMessages(List<ChatMessageDTO> chatMessages) {
        this.chatMessages = chatMessages;
    }

    /**
     * Gets the error message.
     *
     * @return the error message as a string
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error message.
     *
     * @param error the error message to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the current chat group ID.
     *
     * @return the chat group ID
     */
    public int getChatGroupId() {
        return chatGroupId;
    }

    /**
     * Sets the current chat group ID.
     *
     * @param chatGroupId the chat group ID to set
     */
    public void setChatGroupId(int chatGroupId) {
        this.chatGroupId = chatGroupId;
    }

    /**
     * Gets the summary of the chat history.
     *
     * @return the summary of the chat history
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the summary of the chat history.
     *
     * @param summary the summary of the chat history to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets the transcription of the audio message.
     *
     * @return the transcription of the audio message
     */
    public String getTranscription() {
        return transcription;
    }

    /**
     * Sets the transcription of the audio message.
     *
     * @param transcription the transcription of the audio message to set
     */
    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
