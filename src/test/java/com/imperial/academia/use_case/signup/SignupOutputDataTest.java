package com.imperial.academia.use_case.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupOutputDataTest {
    private SignupOutputData outputData;

    @BeforeEach
    public void init(){
        outputData = new SignupOutputData("name", "time");
    }

    @Test
    void getUsername() {
        assertEquals("name", outputData.getUsername());
    }

    @Test
    void getCreationTime() {
        assertEquals("time", outputData.getCreationTime());
    }

    @Test
    void setCreationTime() {
        outputData.setCreationTime("qqqq");
        assertEquals("qqqq", outputData.getCreationTime());
    }
}