package com.imperial.academia.use_case.chat;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChatWindowInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        int chatGroupId = 123;
        String content = "Hello, world!";
        String contentType = "text";

        ChatWindowInputData inputData = new ChatWindowInputData(chatGroupId, content, contentType);

        assertEquals(chatGroupId, inputData.getChatGroupId());
        assertEquals(content, inputData.getContent());
        assertEquals(contentType, inputData.getContentType());
    }
}
