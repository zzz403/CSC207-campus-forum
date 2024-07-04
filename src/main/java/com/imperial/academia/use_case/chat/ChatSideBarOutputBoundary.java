package com.imperial.academia.use_case.chat;

/**
 * The ChatSideBarOutputBoundary interface defines methods for presenting chat groups and handling errors
 * in the chat sidebar use case.
 */
public interface ChatSideBarOutputBoundary {

    /**
     * Presents the chat groups in the chat sidebar.
     *
     * @param chatSideBarOutputData The data containing the list of chat groups.
     */
    void presentChatGroups(ChatSideBarOutputData chatSideBarOutputData);

    /**
     * Presents an error message in the chat sidebar.
     *
     * @param error The error message to be displayed.
     */
    void presentError(String error);
}
