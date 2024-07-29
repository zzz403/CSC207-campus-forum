package com.imperial.academia.use_case.chat;

/**
 * The ChatWindowOutputBoundary interface defines methods for presenting chat messages and handling errors
 * in the chat window use case.
 */
public interface ChatWindowOutputBoundary {

    /**
     * Presents the chat messages in the chat window.
     *
     * @param chatWindowOutputData The data containing the list of chat messages.
     */
    void presentChatMessages(ChatWindowOutputData chatWindowOutputData);

    /**
     * Presents an error message in the chat window.
     *
     * @param error The error message to be displayed.
     */
    void presentError(String error);

    /**
     * Presents the summary of the chat history in the chat window.
     *
     * @param chatWindowOutputData The data containing the summary of the chat history.
     */
    void presentSummary(String chatWindowOutputData);

    /**
     * Presents the speech-to-text output in the chat window.
     *
     * @param chatWindowOutputData The data containing the speech-to-text output.
     */
    void presentSpeechToText(String chatWindowOutputData);

    /**
     * Presents the translated text in the chat window.
     *
     * @param chatWindowOutputData The data containing the translated text.
     */
    void presentTranslatedText(String chatWindowOutputData);
}
