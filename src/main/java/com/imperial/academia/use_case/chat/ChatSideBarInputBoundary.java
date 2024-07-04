package com.imperial.academia.use_case.chat;

/**
 * Input boundary interface for chat sidebar use cases.
 * Defines the contract for executing chat sidebar related operations.
 */
public interface ChatSideBarInputBoundary {

    /**
     * Executes the chat sidebar operation with the given input data.
     *
     * @param chatSideBarInputData the input data for the chat sidebar operation
     */
    void execute(ChatSideBarInputData chatSideBarInputData);

    void execute();
}
