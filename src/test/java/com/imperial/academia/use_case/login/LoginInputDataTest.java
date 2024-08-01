package com.imperial.academia.use_case.login;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LoginInputDataTest {
    private LoginInputData inputData;

    @BeforeEach
    public void iniy(){
        inputData = new LoginInputData("","",true);
    }

    @Test
    void testEquals() {
        assertEquals(inputData, inputData);
        boolean flag = inputData == null;
        assertFalse(flag);
        boolean flag1 = inputData.equals("asd");
        assertFalse(flag1);
        LoginInputData inputData1 = new LoginInputData("", "", true);
        assertTrue(inputData1.equals(inputData));
    }

    @Test
    void testHashCode() {
        assertEquals(Objects.hash(inputData.getUsername(),inputData.getPassword(), inputData.isRememberMe()),
                inputData.hashCode());
    }
}