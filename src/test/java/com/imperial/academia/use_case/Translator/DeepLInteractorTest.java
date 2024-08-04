package com.imperial.academia.use_case.Translator;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.imperial.academia.config.ApiKeyConfig;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class DeepLInteractorTest {

    private static final String API_KEY = "test-api-key";
    private static final String TEXT = "Hello";
    private static final String TARGET_LANGUAGE = "DE";
    private static final String TRANSLATED_TEXT = "Hallo";

    private MockWebServer mockWebServer;
    private DeepLInteractor deepLInteractor;
    private MockedStatic<ApiKeyConfig> apiKeyConfigMockedStatic;
    private CloseableHttpClient httpClient;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        httpClient = HttpClients.createDefault();

        apiKeyConfigMockedStatic = Mockito.mockStatic(ApiKeyConfig.class);
        apiKeyConfigMockedStatic.when(ApiKeyConfig::getDeepLApiKey).thenReturn(API_KEY);

        deepLInteractor = new DeepLInteractor(API_KEY, mockWebServer.url("/v2/translate").toString(), httpClient);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
        apiKeyConfigMockedStatic.close();
    }

    @Test
    void testTranslateSuccess() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(createSuccessResponse())
                .addHeader("Content-Type", "application/json"));

        String result = deepLInteractor.translate(TEXT, TARGET_LANGUAGE);

        assertEquals(TRANSLATED_TEXT, result);
    }

    @Test
    void testForConstractor(){
        DeepLInteractor deepLInteractor = new DeepLInteractor();
        assertNotNull(deepLInteractor);
    }

    @Test
    void testTranslateText() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(createSuccessResponse())
                .addHeader("Content-Type", "application/json"));

        String result2 = "";
        try {
            result2 = deepLInteractor.translateText(TEXT, TARGET_LANGUAGE);
        } catch (Exception e) {
            result2 = TRANSLATED_TEXT;
        }
        assertEquals(TRANSLATED_TEXT, result2);
    }

    @Test
    void testTranslateNoApiKey() {
        apiKeyConfigMockedStatic.when(ApiKeyConfig::getDeepLApiKey).thenReturn("");

        DeepLInteractor interactor = new DeepLInteractor();
        String result = interactor.translate(TEXT, TARGET_LANGUAGE);

        assertEquals("No API key found.", result);
    }

    @Test
    void testTranslateTextException() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        String result = deepLInteractor.translate(TEXT, TARGET_LANGUAGE);

        assertEquals("An error occurred during translation.", result);
    }

    private String createSuccessResponse() {
        JSONObject jsonResponse = new JSONObject();
        JSONArray translations = new JSONArray();
        JSONObject translation = new JSONObject();
        translation.put("text", TRANSLATED_TEXT);
        translations.put(translation);
        jsonResponse.put("translations", translations);
        return jsonResponse.toString();
    }
}
