package com.imperial.academia.entity.chat_message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileDataTest {
    private FileData fileData;

    @BeforeEach
    public void init(){
        fileData = new FileData("","","");
    }

    @Test
    void setFileName() {
        fileData.setFileName("name");
        assertEquals("name",fileData.getFileName());
    }

    @Test
    void setFileSize() {
        fileData.setFileSize("12");
        assertEquals("12",fileData.getFileSize());
    }

    @Test
    void setFileType() {
        fileData.setFileType("as");
        assertEquals("as",fileData.getFileType());
    }
}