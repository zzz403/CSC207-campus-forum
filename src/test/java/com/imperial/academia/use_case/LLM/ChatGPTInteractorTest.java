package com.imperial.academia.use_case.LLM;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.imperial.academia.config.ApiKeyConfig;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class ChatGPTInteractorTest {

    private MockWebServer mockWebServer;
    private ChatGPTInteractor chatGPTInteractor;
    private MockedStatic<ApiKeyConfig> mockedApiKeyConfig;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockedApiKeyConfig = mockStatic(ApiKeyConfig.class);
        chatGPTInteractor = new ChatGPTInteractor(mockWebServer.url("/v1/chat/completions").toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
        mockedApiKeyConfig.close();
    }

    @Test
    void testEnhanceContentSuccess() throws IOException {
        String originalContent = "This is a test content.";
        String enhancedContent = "This is an enhanced test content.";

        String mockResponse = "{\"choices\":[{\"message\":{\"content\":\"" + enhancedContent + "\"}}]}";

        mockWebServer.enqueue(new MockResponse()
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        mockedApiKeyConfig.when(ApiKeyConfig::getGPTApi).thenReturn("test-api-key");

        String result = chatGPTInteractor.enhanceContent(originalContent);

        assertEquals(enhancedContent, result);
    }

    @Test
    void testEnhanceContentApiKeyMissing() {
        mockedApiKeyConfig.when(ApiKeyConfig::getGPTApi).thenReturn(""); // Setting an empty API key
        String originalContent = "This is a test content.";

        String result = chatGPTInteractor.enhanceContent(originalContent);

        assertEquals(originalContent, result); // Expecting the original content to be returned
    }

    @Test
    void testEnhanceContentApiFailure() throws IOException {
        String originalContent = "This is a test content.";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("{\"error\": \"Internal Server Error\"}"));

        mockedApiKeyConfig.when(ApiKeyConfig::getGPTApi).thenReturn("test-api-key");

        String result = chatGPTInteractor.enhanceContent(originalContent);

        assertEquals("", result); // Expecting an empty string due to API failure
    }

}
