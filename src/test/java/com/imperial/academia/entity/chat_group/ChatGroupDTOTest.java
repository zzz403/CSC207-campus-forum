package com.imperial.academia.entity.chat_group;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ChatGroupDTOTest {
    private ChatGroupDTO chatGroupDTO;

    @BeforeEach
    public void init(){
        chatGroupDTO = new ChatGroupDTO(1, "",true,
                new Timestamp(0), "", new Timestamp(2), "");
    }

    @Test
    void setId() {
        chatGroupDTO.setId(123);
        assertEquals(123,chatGroupDTO.getId());
    }

    @Test
    void setGroupName() {
        chatGroupDTO.setGroupName("group");
        assertEquals("group", chatGroupDTO.getGroupName());
    }

    @Test
    void setLastModified() {
        chatGroupDTO.setLastModified(new Timestamp(999));
        assertEquals(new Timestamp(999), chatGroupDTO.getLastModified());
    }

    @Test
    void setGroup() {
        chatGroupDTO.setGroup(false);
        assertFalse(chatGroupDTO.isGroup());
    }

    @Test
    void setLastMessage() {
        chatGroupDTO.setLastMessage("blala");
        assertEquals("blala", chatGroupDTO.getLastMessage());
    }

    @Test
    void setLastMessageTime() {
        chatGroupDTO.setLastMessageTime(new Timestamp(666));
        assertEquals(new Timestamp(666), chatGroupDTO.getLastMessageTime());
    }

    @Test
    void setAvatarUrl() {
        chatGroupDTO.setAvatarUrl("url");
        assertEquals("url", chatGroupDTO.getAvatarUrl());
    }
}