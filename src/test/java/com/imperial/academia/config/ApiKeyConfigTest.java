package com.imperial.academia.config;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class ApiKeyConfigTest {

    @Test
    void getMapBoxApiKey_shouldReturnMapBoxApiKey() {
        String expected = "";
        String actual = ApiKeyConfig.getMapBoxApiKey();
    }

    @Test
    void getGPTApi_shouldReturnGPTApi() {
        String expected = "";
        String actual = ApiKeyConfig.getGPTApi();
    }

    @Test
    void getIBMSpeechToTextApiKey_shouldReturnIBMSpeechToTextApiKey() {
        String expected = "";
        String actual = ApiKeyConfig.getIBMSpeechToTextApiKey();
    }

    @Test
    void getDeepLApiKey_shouldReturnDeepLApiKey() {
        String expected = "";
        String actual = ApiKeyConfig.getDeepLApiKey();
    }
}
