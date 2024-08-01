package com.imperial.academia.entity.chat_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageTest {
    private ChatMessage chatMessage;

    @BeforeEach
    public void init(){
        chatMessage = new ChatMessage(1,1,1,
                "","",new Timestamp(0));

    }

    @Test
    void setSenderId() {
        chatMessage.setSenderId(2);
        assertEquals(2,chatMessage.getSenderId());
    }

    @Test
    void setGroupId() {
        chatMessage.setGroupId(3);
        assertEquals(3, chatMessage.getGroupId());
    }

    @Test
    void setContent() {
        chatMessage.setContent("asd");
        assertEquals("asd", chatMessage.getContent());
    }

    @Test
    void setTimestamp() {
        chatMessage.setTimestamp(new Timestamp(999));
        assertEquals(new Timestamp(999), chatMessage.getTimestamp());
    }

    @Test
    void setContentType() {
        chatMessage.setContentType("test");
        assertEquals("test",chatMessage.getContentType());
    }
}