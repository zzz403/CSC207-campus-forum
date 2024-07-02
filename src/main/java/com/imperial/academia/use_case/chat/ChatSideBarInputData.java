package com.imperial.academia.use_case.chat;

/**
 * Input data class for chat sidebar use cases.
 * Encapsulates the search query used in chat sidebar operations.
 */
public class ChatSideBarInputData {
    private final String searchQuery;

    /**
     * Constructor for ChatSideBarInputData.
     *
     * @param searchQuery the search query for the chat sidebar operation
     */
    public ChatSideBarInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Gets the search query.
     *
     * @return the search query as a string
     */
    public String getSearchQuery() {
        return searchQuery;
    }
}
