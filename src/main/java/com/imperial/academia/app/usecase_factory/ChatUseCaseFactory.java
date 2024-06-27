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
import com.imperial.academia.service.ChatGroupService;
import com.imperial.academia.service.ChatMessageService;
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

public class ChatUseCaseFactory {
    private ChatUseCaseFactory() {}

    public static ChatView create(ViewManagerModel viewManagerModel, ChatSideBarViewModel chatSideBarViewModel, ChatWindowViewModel chatWindowViewModel) throws ClassNotFoundException {

        ChatGroupService chatGroupService = ServiceFactory.getChatGroupService();
        ChatSideBarOutputBoundary chatPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        ChatSideBarInputBoundary chatSideBarInteractor = new ChatSideBarInteractor(chatGroupService, chatPresenter);

        ChatMessageService chatMessageService = ServiceFactory.getChatMessageService();
        AudioService audioService = ServiceFactory.getAudioService();
        ChatWindowOutputBoundary chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);
        ChatMessageFactory chatMessageFactory = new CommonChatMessageFactory();
        ChatWindowInputBoundary chatWindowInteractor = new ChatWindowInteractor(chatMessageService, audioService, chatWindowPresenter, chatMessageFactory);
        
        // ChatSideBarController chatSideBarController = createChatSideBarController(chatSideBarViewModel);
        ChatSideBarController chatSideBarController = new ChatSideBarController(chatSideBarInteractor, chatWindowInteractor);
        ChatWindowController chatWindowController = new ChatWindowController(chatWindowInteractor);
        
        ChatSideBarView chatSideBarView = new ChatSideBarView(chatSideBarController, chatSideBarViewModel);
        ChatWindowView chatWindowView = new ChatWindowView(chatWindowController, chatWindowViewModel);

        return new ChatView(chatSideBarView, chatWindowView);
    }

}
