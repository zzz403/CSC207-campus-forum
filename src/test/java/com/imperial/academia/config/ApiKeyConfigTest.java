package com.imperial.academia.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApiKeyConfigTest {

    @Test
    void getMapBoxApiKey_shouldReturnMapBoxApiKey() {
        String expected = "";
        String actual = ApiKeyConfig.getMapBoxApiKey();
        assertEquals(expected, actual);
    }

    @Test
    void getGPTApi_shouldReturnGPTApi() {
        String expected = "";
        String actual = ApiKeyConfig.getGPTApi();
        assertEquals(expected, actual);
    }

    @Test
    void getIBMSpeechToTextApiKey_shouldReturnIBMSpeechToTextApiKey() {
        String expected = "";
        String actual = ApiKeyConfig.getIBMSpeechToTextApiKey();
        assertEquals(expected, actual);
    }

    @Test
    void getDeepLApiKey_shouldReturnDeepLApiKey() {
        String expected = "";
        String actual = ApiKeyConfig.getDeepLApiKey();
        assertEquals(expected, actual);
    }
}
