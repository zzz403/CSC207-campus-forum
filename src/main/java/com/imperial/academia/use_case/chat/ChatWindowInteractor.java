package com.imperial.academia.use_case.chat;

import com.imperial.academia.service.ChatMessageService;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import java.util.List;

public class ChatWindowInteractor implements ChatWindowInputBoundary {
    private final ChatMessageService chatMessageService;
    private final ChatWindowOutputBoundary chatWindowPresenter;

    public ChatWindowInteractor(ChatMessageService chatMessageService, ChatWindowOutputBoundary chatWindowPresenter) {
        this.chatMessageService = chatMessageService;
        this.chatWindowPresenter = chatWindowPresenter;
    }

    @Override
    public void execute(ChatWindowInputData chatWindowInputData) {
        int chatGroupId = chatWindowInputData.getChatGroupId();
        try {
            List<ChatMessageDTO> chatMessages = chatMessageService.getAllByGroupId(chatGroupId);
            ChatWindowOutputData chatWindowOutputData = new ChatWindowOutputData(chatMessages, chatGroupId);
            chatWindowPresenter.presentChatMessages(chatWindowOutputData);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while loading chat messages.");
        }
    }
}
