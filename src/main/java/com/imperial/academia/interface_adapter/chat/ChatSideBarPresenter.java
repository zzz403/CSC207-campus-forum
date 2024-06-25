package com.imperial.academia.interface_adapter.chat;

import com.imperial.academia.use_case.chat.ChatSideBarOutputData;
import com.imperial.academia.use_case.chat.ChatSideBarOutputBoundary;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;

import java.util.List;

public class ChatSideBarPresenter implements ChatSideBarOutputBoundary{
    private final ChatSideBarViewModel chatSideBarViewModel;

    public ChatSideBarPresenter(ChatSideBarViewModel chatSideBarViewModel){
        this.chatSideBarViewModel = chatSideBarViewModel;
    }

    public void presentChatGroups(ChatSideBarOutputData chatSideBarOutputData) {
        ChatSideBarState state = chatSideBarViewModel.getState();
        List<ChatGroupDTO> chatGroups = chatSideBarOutputData.getChatGroups();
        state.setChatGroups(chatGroups);
        chatSideBarViewModel.setState(state);
        
        chatSideBarViewModel.firePropertyChanged();
    }

    public void presentError(String error) {
        ChatSideBarState state = chatSideBarViewModel.getState();
        state.setError(error);
        chatSideBarViewModel.setState(state);
        
        chatSideBarViewModel.firePropertyChanged();
    }
}
