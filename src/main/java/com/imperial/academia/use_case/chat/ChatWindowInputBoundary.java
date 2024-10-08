package com.imperial.academia.use_case.chat;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The ChatWindowInputBoundary interface defines methods for managing chat window interactions,
 * such as executing actions, recording audio, loading audio, and sending messages.
 */
public interface ChatWindowInputBoundary {

    /**
     * Executes the primary action for the specified chat group.
     *
     * @param chatGroupId The ID of the chat group.
     */
    void execute(int chatGroupId);

    /**
     * Starts recording audio for the specified chat group.
     *
     * @param chatGroupId The ID of the chat group.
     */
    void startRecording(int chatGroupId);

    /**
     * Stops the current audio recording.
     *
     * @param chatGroupId The ID of the chat group.
     */
    void stopRecording(int chatGroupId);

    /**
     * Loads and plays an audio file from the specified path.
     *
     * @param audioPath The path to the audio file to be loaded.
     */
    void loadAudio(String audioPath);

    /**
     * Sends a chat message with the provided input data.
     *
     * @param chatWindowInputData The data of the chat message to be sent.
     * @throws UnsupportedAudioFileException If the specified audio file is not supported.
     * @throws IOException If an I/O error occurs while reading the audio file.
     * @throws SQLException If a database access error occurs.
     */
    void sendMessage(ChatWindowInputData chatWindowInputData) throws UnsupportedAudioFileException, IOException, SQLException;

    /**
     * Send a location message to the specified chat group.
     *
     * @param groupId The ID of the chat group.
     */
    void sendLocation(int groupId);

    /**
     * Send a file to the specified chat group.
     *
     * @param groupId The ID of the chat group.
     * @param file The file to be sent.
     */
    void sendFile(int groupId, File file);

    /**
     * Summarizes the chat history for the specified group.
     *
     * @param groupId The ID of the chat group.
     */
    void summarizeChatHistory(int groupId) throws SQLException;

    /**
     * Converts speech to text for the specified audio file and chat group.
     *
     * @param audioPath The path to the audio file.
     */
    void speechToText(String audioPath) throws Exception;

    /**
     * Translates the specified text to the target language.
     *
     * @param text The text to translate.
     * @param targetLanguage The target language to translate to.
     */
    void translate(String text, String targetLanguage);
}
