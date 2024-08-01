package com.imperial.academia.entity.chat_message;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonChatMessageFactoryTest {
    private CommonChatMessageFactory commonChatMessageFactory;

    @BeforeEach
    public void init(){
        commonChatMessageFactory = new CommonChatMessageFactory();
    }

    @Test
    void createChatMessage() {
        ChatMessage chatMessage = commonChatMessageFactory.createChatMessage(1,1,1,
                "","");

         assertEquals(1,chatMessage.getId());
         assertEquals(1,chatMessage.getSenderId());
         assertEquals(1, chatMessage.getGroupId());
         assertEquals("", chatMessage.getContent());
         assertEquals("",chatMessage.getContentType());
    }
}