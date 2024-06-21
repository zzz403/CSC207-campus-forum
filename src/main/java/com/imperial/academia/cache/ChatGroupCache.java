package com.imperial.academia.cache;

import com.imperial.academia.entity.chat_group.ChatGroup;
import java.util.List;

public interface ChatGroupCache {
    void setChatGroup(String key, ChatGroup chatGroup);
    ChatGroup getChatGroup(String key);
    void deleteChatGroup(String key);
    boolean existsChatGroup(String key);

    void setChatGroups(String key, List<ChatGroup> chatGroups);
    List<ChatGroup> getChatGroups(String key);
    void deleteChatGroups(String key);
    boolean existsChatGroups(String key);
}
