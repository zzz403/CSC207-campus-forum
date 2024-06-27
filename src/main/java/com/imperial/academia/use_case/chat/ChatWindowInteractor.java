package com.imperial.academia.use_case.chat;

import com.imperial.academia.service.AudioService;
import com.imperial.academia.service.ChatMessageService;
import com.imperial.academia.entity.chat_message.ChatMessageDTO;

import java.util.List;

public class ChatWindowInteractor implements ChatWindowInputBoundary {
    private final ChatMessageService chatMessageService;
    private final AudioService audioService;
    private final ChatWindowOutputBoundary chatWindowPresenter;

    public ChatWindowInteractor(ChatMessageService chatMessageService, AudioService audioService, ChatWindowOutputBoundary chatWindowPresenter) {
        this.chatMessageService = chatMessageService;
        this.audioService = audioService;
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

    @Override
    public void startRecording(ChatWindowInputData chatWindowInputData) {
        int chatGroupId = chatWindowInputData.getChatGroupId();
        try {
            audioService.startRecording(chatGroupId);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while starting recording.");
        }
    }

    @Override
    public void stopRecording() {
        try {
            audioService.stopRecording();
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while stopping recording.");
        }
    }

    @Override
    public void loadAudio(ChatWindowInputData chatWindowInputData) {
        String audioPath = chatWindowInputData.getAudioPath();
        try {
            audioService.loadAudio(audioPath);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while loading audio.");
        }
    }
}
