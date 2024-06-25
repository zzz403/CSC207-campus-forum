package com.imperial.academia.app.usecase_factory;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.imperial.academia.app.ServiceFactory;
import com.imperial.academia.interface_adapter.chat.ChatSideBarController;
import com.imperial.academia.interface_adapter.chat.ChatSideBarViewModel;
import com.imperial.academia.interface_adapter.chat.ChatWindowController;
import com.imperial.academia.interface_adapter.chat.ChatWindowPresenter;
import com.imperial.academia.interface_adapter.chat.ChatWindowViewModel;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;
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

public class ChatUseCaseFactory {
    private ChatUseCaseFactory() {}

    public static ChatView create(ViewManagerModel viewManagerModel, ChatSideBarViewModel chatSideBarViewModel, ChatWindowViewModel chatWindowViewModel) throws ClassNotFoundException {
        try{
            ChatSideBarController chatSideBarController = createChatSideBarController(chatSideBarViewModel);
            ChatWindowController chatWindowController = createChatWindowController(viewManagerModel, chatWindowViewModel);

            ChatSideBarView chatSideBarView = new ChatSideBarView(chatSideBarController, chatSideBarViewModel);
            ChatWindowView chatWindowView = new ChatWindowView(chatWindowController, chatWindowViewModel);

            return new ChatView(chatSideBarView, chatWindowView);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static ChatSideBarController createChatSideBarController(ChatSideBarViewModel chatSideBarViewModel) throws SQLException, ClassNotFoundException {
        ChatGroupService chatGroupService = ServiceFactory.getChatGroupService();
        ChatSideBarOutputBoundary chatPresenter = new ChatSideBarPresenter(chatSideBarViewModel);
        ChatSideBarInputBoundary chatSideBarInteractor = new ChatSideBarInteractor(chatGroupService, chatPresenter);
        return new ChatSideBarController(chatSideBarInteractor);
    }

    private static ChatWindowController createChatWindowController(ViewManagerModel viewManagerModel, ChatWindowViewModel chatWindowViewModel) throws SQLException, ClassNotFoundException {
        ChatMessageService chatMessageService = ServiceFactory.getChatMessageService();
        ChatWindowOutputBoundary chatWindowPresenter = new ChatWindowPresenter(chatWindowViewModel);
        ChatWindowInputBoundary chatWindowInteractor = new ChatWindowInteractor(chatMessageService, chatWindowPresenter);
        return new ChatWindowController(chatWindowInteractor);
    }

}
