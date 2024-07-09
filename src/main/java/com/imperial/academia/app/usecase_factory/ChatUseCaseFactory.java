package com.imperial.academia.app.usecase_factory;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.entity.chat_message.CommonChatMessageFactory;
import com.imperial.academia.interface_adapter.chat.ChatSideBarController;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowController;
import com.imperial.academia.interface_adapter.chat.ChatWindowPresenter;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
import com.imperial.academia.service.AudioService;
import com.imperial.academia.use_case.chat.ChatSideBarOutputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInputBoundary;
import com.imperial.academia.use_case.chat.ChatWindowInteractor;
import com.imperial.academia.use_case.chat.ChatWindowOutputBoundary;
import com.imperial.academia.view.ChatView;
import com.imperial.academia.view.components.ChatSideBarView;
import com.imperial.academia.view.components.ChatWindowView;
import com.imperial.academia.interface_adapter.chat.ChatSideBarPresenter;
import com.imperial.academia.use_case.chat.ChatSideBarInputBoundary;
import com.imperial.academia.use_case.chat.ChatSideBarInteractor;
import com.imperial.academia.entity.chat_message.ChatMessageFactory;

import javax.swing.*;

/**
 * Factory class to create instances of the chat use cases and related
 * components.
 */
public class ChatUseCaseFactory {
    private ChatUseCaseFactory() {
    }

    /**
     * Creates and sets up the chat view with all its components and dependencies.
     *
     * @param viewManagerModel     the view manager model
     * @param chatSideBarViewModel the chat side bar view model
     * @param chatWindowViewModel  the chat window view model
     * @return an instance of ChatView
     * @throws ClassNotFoundException if a class needed for creating services is not
     *                                found
     */
    public static ChatView create(ViewManagerModel viewManagerModel, ChatSideBarViewModel chatSideBarViewModel,
                                  ChatWindowViewModel chatWindowViewModel, JFrame application) throws ClassNotFoundException {

        // Get required services
        AudioService audioService = ServiceFactory.getAudioService();

        // Create presenters
        ChatSideBarOutputBoundary chatSideBarPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        ChatWindowOutputBoundary chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);

        // Create interactors
        ChatSideBarInputBoundary chatSideBarInteractor = new ChatSideBarInteractor(
                chatSideBarPresenter);
        ChatMessageFactory chatMessageFactory = new CommonChatMessageFactory();
        ChatWindowInputBoundary chatWindowInteractor = new ChatWindowInteractor(
                chatWindowPresenter, chatMessageFactory);

        // Create controllers
        ChatSideBarController chatSideBarController = new ChatSideBarController(chatSideBarInteractor,
                chatWindowInteractor);
        ChatWindowController chatWindowController = new ChatWindowController(chatWindowInteractor);

        // Create views
        ChatSideBarView chatSideBarView = new ChatSideBarView(chatSideBarController, chatSideBarViewModel);
        ChatWindowView chatWindowView = new ChatWindowView(chatWindowController, chatWindowViewModel, application);

        // Return the combined chat view
        return new ChatView(chatSideBarView, chatWindowView);
    }
}
