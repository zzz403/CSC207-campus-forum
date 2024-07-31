package com.imperial.academia.use_case.edit;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditInputDataTest {
    private EditInputData inputData;

    @BeforeEach
    public void init(){
        this.inputData = new EditInputData(
                "name",
                "password",
                "repeatPassword",
                "url",
                "email"
        );
    }


    @Test
    void getUsername() {
        assertEquals("name", inputData.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("password", inputData.getPassword());
    }

    @Test
    void getRepeatPassword() {
        assertEquals("repeatPassword", inputData.getRepeatPassword());
    }

    @Test
    void getAvatarURL() {
        assertEquals("url", inputData.getAvatarURL());
    }

    @Test
    void getEmail() {
        assertEquals("email", inputData.getEmail());
    }
}