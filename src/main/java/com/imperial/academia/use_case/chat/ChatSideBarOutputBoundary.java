package com.imperial.academia.use_case.chat;

import java.util.List;
import com.imperial.academia.entity.chat_group.ChatGroupDTO;

public interface ChatSideBarOutputBoundary {

    void presentChatGroups(List<ChatGroupDTO> chatGroups);
    void presentError(String error);

}