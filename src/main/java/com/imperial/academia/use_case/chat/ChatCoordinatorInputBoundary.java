package com.imperial.academia.use_case.chat;

/**
 * The ChatCoordinatorInputBoundary interface defines the contract for classes that handle the coordination of private chats.
 */
public interface ChatCoordinatorInputBoundary {

    /**
     * Initiates a private chat with the specified user.
     *
     * @param userId The ID of the user to initiate the private chat with.
     */
    void toPrivateChat(int userId);
}
