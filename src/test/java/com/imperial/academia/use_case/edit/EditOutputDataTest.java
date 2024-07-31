package com.imperial.academia.use_case.edit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditOutputDataTest {
    private EditOutputData outputData;

    @BeforeEach
    public void init(){
        this.outputData = new EditOutputData(
                1,
                "name",
                "password",
                "email",
                "url"
        );
    }

    @Test
    void getUserId() {
        assertEquals(1, outputData.getUserId());
    }

    @Test
    void getUserName() {
        assertEquals("name", outputData.getUserName());
    }

    @Test
    void getPassword() {
        assertEquals("password", outputData.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("email", outputData.getEmail());
    }

    @Test
    void getAvatarURL() {
        assertEquals("url", outputData.getAvatarURL());
    }
}