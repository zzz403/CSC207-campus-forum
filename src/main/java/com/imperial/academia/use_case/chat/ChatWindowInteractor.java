package com.imperial.academia.use_case.chat;

import com.imperial.academia.app.UsecaseFactory;
import com.imperial.academia.entity.chat_message.*;
import com.imperial.academia.service.AudioService;
import com.imperial.academia.service.ChatMessageService;
import com.imperial.academia.service.FileService;
import com.imperial.academia.service.MapService;
import com.imperial.academia.session.SessionManager;
import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.use_case.LLM.LLMInputBoundary;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Interactor class for handling chat window operations.
 * Implements the ChatWindowInputBoundary interface to process input data and
 * perform actions.
 */
public class ChatWindowInteractor implements ChatWindowInputBoundary {
    private final ChatMessageService chatMessageService;
    private final AudioService audioService;
    private final MapService mapService;
    private final FileService fileService;
    private final ChatWindowOutputBoundary chatWindowPresenter;
    private final ChatMessageFactory chatMessageFactory;
    private final LLMInputBoundary llmInputBoundary;


    /**
     * Constructor for ChatWindowInteractor.
     *
     * @param chatWindowPresenter the presenter for chat window output
     * @param chatMessageFactory  the factory for creating chat messages
     */
    public ChatWindowInteractor(ChatWindowOutputBoundary chatWindowPresenter, ChatMessageFactory chatMessageFactory) {
        this.chatMessageService = ServiceFactory.getChatMessageService();
        this.mapService = ServiceFactory.getMapService();
        this.fileService = ServiceFactory.getFileService();
        this.audioService = ServiceFactory.getAudioService();
        this.chatWindowPresenter = chatWindowPresenter;
        this.chatMessageFactory = chatMessageFactory;
        this.llmInputBoundary = UsecaseFactory.getLLMInteractor();
    }

    /**
     * for unit test only
     */
    public ChatWindowInteractor(ChatWindowOutputBoundary chatWindowPresenter, ChatMessageFactory chatMessageFactory, ChatMessageService chatMessageService, MapService mapService, FileService fileService, AudioService audioService, LLMInputBoundary llmInputBoundary) {
        this.chatMessageService = chatMessageService;
        this.mapService = mapService;
        this.fileService = fileService;
        this.audioService = audioService;
        this.chatWindowPresenter = chatWindowPresenter;
        this.chatMessageFactory = chatMessageFactory;
        this.llmInputBoundary = llmInputBoundary;
    }

    /**
     * Loads chat messages for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    @Override
    public void execute(int chatGroupId) {
        try {
            List<ChatMessageDTO> chatMessages = chatMessageService.getAllByGroupId(chatGroupId);
            ChatWindowOutputData chatWindowOutputData = new ChatWindowOutputData(chatMessages, chatGroupId);
            chatWindowPresenter.presentChatMessages(chatWindowOutputData);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while loading chat messages.");
        }
    }

    /**
     * Starts recording audio for a specific chat group.
     *
     * @param chatGroupId the ID of the chat group
     */
    @Override
    public void startRecording(int chatGroupId) {
        try {
            audioService.startRecording(chatGroupId);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while starting recording.");
        }
    }

    /**
     * Stops recording audio and sends the recorded message.
     *
     * @param chatGroupId the ID of the chat group
     */
    @Override
    public void stopRecording(int chatGroupId) {
        try {
            audioService.stopRecording();
            ChatWindowInputData chatWindowInputData = new ChatWindowInputData(chatGroupId,
                    audioService.getOutputFilePath(), "audio");
            sendMessage(chatWindowInputData);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while stopping recording.");
        }
    }

    /**
     * Loads an audio file for playback.
     *
     * @param audioPath the path to the audio file
     */
    @Override
    public void loadAudio(String audioPath) {
        try {
            audioService.loadAudio(audioPath);
        } catch (Exception e) {
            chatWindowPresenter.presentError("An error occurred while loading audio.");
        }
    }

    @Override
    public void sendLocation(int groupId){
        double[] location = mapService.getUserLocation();
        double latitude = location[0];
        double longitude = location[1];

        mapService.generateMapImage(groupId, latitude, longitude);

        String filePath = mapService.getFilePath();

        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(groupId, filePath, "map");
        try {
            sendMessage(chatWindowInputData);
        } catch (UnsupportedAudioFileException | IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendFile(int groupId, File file){
        String type = isImageFile(file) ? "image" : "file";
        fileService.saveFile(groupId, file, type);
        String filePath = fileService.getOutputFilePath();
        ChatWindowInputData chatWindowInputData = new ChatWindowInputData(groupId, filePath, type);
        try {
            sendMessage(chatWindowInputData);
        } catch (UnsupportedAudioFileException | IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif")|| extension.equals("bmp");
    }

    /**
     * Sends a chat message.
     *
     * @param chatWindowInputData the input data for the chat message
     * @throws UnsupportedAudioFileException if the audio file format is not
     *                                       supported
     * @throws IOException                   if an I/O error occurs
     * @throws SQLException                  if a database access error occurs
     */
    @Override
    public void sendMessage(ChatWindowInputData chatWindowInputData)
            throws UnsupportedAudioFileException, IOException, SQLException {
        int senderId = SessionManager.getCurrentUser().getId();
        int groupId = chatWindowInputData.getChatGroupId();
        String contentType = chatWindowInputData.getContentType();
        String content = chatWindowInputData.getContent();
        ChatMessage chatMessage = chatMessageFactory.createChatMessage(senderId, 1, groupId, contentType, content);

        if (contentType.equals("audio")) {
            WaveformData waveformData = audioService.processAudio(chatMessage.getContent());
            chatMessageService.insert(chatMessage, waveformData);
        } else if(contentType.equals("map")){
            double[] location = mapService.getUserLocation();
            double latitude = location[0];
            double longitude = location[1];

            String locationInfo = mapService.getLocationInfo(latitude, longitude);
            if (locationInfo == null) {
                locationInfo = "Unknown location";
            }

            MapData mapData = new MapData(latitude, longitude,locationInfo);
            chatMessageService.insert(chatMessage, mapData);
            System.out.println("Map data inserted" + mapData.getLocationInfo());
        } else if (contentType.equals("file")) {
            FileData fileData = fileService.getFileData(chatMessage.getContent());
            chatMessageService.insert(chatMessage, fileData);
        } else {
            chatMessageService.insert(chatMessage);
        }

        // Refresh the chat messages after sending the message
        execute(groupId);
    }

    /**
     * Summarizes the chat history for the specified group.
     *
     * @param chatGroupId the ID of the chat group
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void summarizeChatHistory(int chatGroupId) throws SQLException {
        List<ChatMessageDTO> chatMessages = chatMessageService.getAllByGroupId(chatGroupId);
        StringBuilder chatHistory = new StringBuilder();
        for (ChatMessageDTO chatMessage : chatMessages) {
            chatHistory.append(chatMessage.getSenderName()).append(": ").append(chatMessage.getContent()).append("\n");
        }
        String summarizeChatHistory = llmInputBoundary.summarizeChatHistory(chatHistory.toString());
        chatWindowPresenter.presentSummary(summarizeChatHistory);
    }
}
