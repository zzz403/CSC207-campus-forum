package com.imperial.academia.use_case.chat;

public class ChatSideBarInputData {
    private final String searchQuery;

    public ChatSideBarInputData(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
