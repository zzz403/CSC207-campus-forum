package com.imperial.academia.entity.chat_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageDTOTest {
    private ChatMessageDTO chatMessageDTO;

    @BeforeEach
    public void init(){
        chatMessageDTO = new ChatMessageDTO(1,1,
                "","",1,
                "","",true,new Timestamp(0));
    }

    @Test
    void setId() {
        chatMessageDTO.setId(2);
        assertEquals(2, chatMessageDTO.getId());
    }

    @Test
    void setSenderId() {
        chatMessageDTO.setSenderId(3);
        assertEquals(3,chatMessageDTO.getSenderId());
    }

    @Test
    void setSenderName() {
        chatMessageDTO.setSenderName("sender");
        assertEquals("sender", chatMessageDTO.getSenderName());
    }

    @Test
    void setSenderAvatarUrl() {
        chatMessageDTO.setSenderAvatarUrl("url");
        assertEquals("url",chatMessageDTO.getSenderAvatarUrl());
    }

    @Test
    void setGroupId() {
        chatMessageDTO.setGroupId(4);
        assertEquals(4,chatMessageDTO.getGroupId());
    }

    @Test
    void setContentType() {
        chatMessageDTO.setContentType("type");
        assertEquals("type", chatMessageDTO.getContentType());
    }

    @Test
    void setContent() {
        chatMessageDTO.setContent("asds");
        assertEquals("asds", chatMessageDTO.getContent());
    }

    @Test
    void setMe() {
        chatMessageDTO.setMe(false);
        assertEquals(false,chatMessageDTO.isMe());
    }

    @Test
    void setTimestamp() {
        chatMessageDTO.setTimestamp(new Timestamp(888));
        assertEquals(new Timestamp(888), chatMessageDTO.getTimestamp());
    }

    @Test
    void setWaveformData() {
        chatMessageDTO.setWaveformData(new WaveformData(new ArrayList<Integer>(),
                new ArrayList<Integer>(), 3));
        assertNotNull(chatMessageDTO.getWaveformData());
    }

    @Test
    void setMapData() {
        chatMessageDTO.setMapData(new MapData(1,1,""));
        assertNotNull(chatMessageDTO.getMapData());
    }

    @Test
    void setFileData() {
        chatMessageDTO.setFileData(new FileData("","",""));
        assertNotNull(chatMessageDTO.getFileData());
    }
}