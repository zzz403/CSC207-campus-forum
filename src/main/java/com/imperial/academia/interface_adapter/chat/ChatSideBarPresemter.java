package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarOutputBoundary;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;
import com.imperial.academia.interface_adapter.common.ViewManagerModel;

import java.util.List;

public class ChatSideBarPresemter implements ChatSideBarOutputBoundary{
    private final ChatSideBarViewModel chatSideBarViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChatSideBarPresemter(ChatSideBarViewModel chatSideBarViewModel, ViewManagerModel viewManagerModel){
        this.chatSideBarViewModel = chatSideBarViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void presentChatGroups(List<ChatGroupDTO> chatGroups) {
        ChatSideBarState state = chatSideBarViewModel.getState();
        state.setChatGroups(chatGroups);
        chatSideBarViewModel.setState(state);
        
        chatSideBarViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    public void presentError(String error) {
        ChatSideBarState state = chatSideBarViewModel.getState();
        state.setError(error);
        chatSideBarViewModel.setState(state);
        
        chatSideBarViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
