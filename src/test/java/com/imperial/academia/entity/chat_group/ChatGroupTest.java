package com.imperial.academia.entity.chat_group;

import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ChatGroupTest {
    private ChatGroup chatGroup;

    @BeforeEach
    public void init(){
        chatGroup = new ChatGroup(
                1,
                "name",
                true,
                new Timestamp(1),
                new Timestamp(2)
        );
    }

    @Test
    void setGroupName() {
        chatGroup.setGroupName("test");
        assertEquals("test", chatGroup.getGroupName());
    }

    @Test
    void setCreationDate() {
        chatGroup.setCreationDate(new Timestamp(555));
        assertEquals(new Timestamp(555), chatGroup.getCreationDate());
    }

    @Test
    void setLastModified() {
        chatGroup.setLastModified(new Timestamp(666));
        assertEquals(new Timestamp(666), chatGroup.getLastModified());
    }

    @Test
    void setGroup() {
        chatGroup.setGroup(false);
        assertFalse(chatGroup.isGroup());
    }
}