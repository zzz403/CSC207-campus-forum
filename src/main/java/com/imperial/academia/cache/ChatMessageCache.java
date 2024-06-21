package com.imperial.academia.cache;

import com.imperial.academia.entity.chat_message.ChatMessage;
import java.util.List;

public interface ChatMessageCache {
    void setChatMessage(String key, ChatMessage chatMessage);
    ChatMessage getChatMessage(String key);
    void deleteChatMessage(String key);
    boolean existsChatMessage(String key);

    void setChatMessages(String key, List<ChatMessage> chatMessages);
    List<ChatMessage> getChatMessages(String key);
    void deleteChatMessages(String key);
    boolean existsChatMessages(String key);
}
