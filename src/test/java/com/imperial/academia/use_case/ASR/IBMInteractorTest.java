package com.imperial.academia.use_case.ASR;

import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionAlternative;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.imperial.academia.config.ApiKeyConfig;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IBMInteractorTest {

    private IBMInteractor ibmInteractor;
    private MockWebServer mockWebServer;
    private MockedStatic<ApiKeyConfig> mockedApiKeyConfig;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockedApiKeyConfig = mockStatic(ApiKeyConfig.class);
        mockedApiKeyConfig.when(ApiKeyConfig::getIBMSpeechToTextApiKey).thenReturn("test-api-key");
        ibmInteractor = new IBMInteractor("test-api-key", mockWebServer.url("/v1/recognize").toString());
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
        mockedApiKeyConfig.close();
    }

    @Test
    void testSpeechToTextWithValidApiKey() throws Exception {
        SpeechRecognitionAlternative alternative = mock(SpeechRecognitionAlternative.class);
        when(alternative.getTranscript()).thenReturn("Test transcription");

        SpeechRecognitionResult result = mock(SpeechRecognitionResult.class);
        when(result.getAlternatives()).thenReturn(Collections.singletonList(alternative));

        SpeechRecognitionResults results = mock(SpeechRecognitionResults.class);
        when(results.getResults()).thenReturn(Collections.singletonList(result));

        String transcription = ibmInteractor.extractResults(results);

        assertNotNull(transcription);
        assertEquals("Test transcription", transcription);
    }

    @Test
    void testSpeechToTextWithInvalidApiKey() {
        mockedApiKeyConfig.when(ApiKeyConfig::getIBMSpeechToTextApiKey).thenReturn(null);

        ibmInteractor = new IBMInteractor();

        String result = ibmInteractor.speechToText("test.wav");

        assertEquals("API key not found", result);
    }

    @Test
    void testSpeechToTextWithException() throws Exception {
        String audioFile = "resources/audio/for_test/admin_20240626_180700.wav";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody("{\"error\": \"Internal Server Error\"}"));

        String result = ibmInteractor.speechToText(audioFile);

        assertNull(result);
    }

    @Test
    void testExtractResultsWithEmptyResults() {
        SpeechRecognitionResults results = mock(SpeechRecognitionResults.class);
        when(results.getResults()).thenReturn(Collections.emptyList());

        String transcription = ibmInteractor.extractResults(results);

        assertNotNull(transcription);
        assertEquals("", transcription);
    }

    @Test
    void testExtractResultsWithValidResults() {
        SpeechRecognitionAlternative alternative = mock(SpeechRecognitionAlternative.class);
        when(alternative.getTranscript()).thenReturn("Test transcription");

        SpeechRecognitionResult result = mock(SpeechRecognitionResult.class);
        when(result.getAlternatives()).thenReturn(Collections.singletonList(alternative));

        SpeechRecognitionResults results = mock(SpeechRecognitionResults.class);
        when(results.getResults()).thenReturn(Collections.singletonList(result));

        String transcription = ibmInteractor.extractResults(results);

        assertNotNull(transcription);
        assertEquals("Test transcription", transcription);
    }

    @Test
    void testExtractResultsWithMultipleAlternatives() {
        SpeechRecognitionAlternative alternative1 = mock(SpeechRecognitionAlternative.class);
        when(alternative1.getTranscript()).thenReturn("First transcription");

        SpeechRecognitionAlternative alternative2 = mock(SpeechRecognitionAlternative.class);
        when(alternative2.getTranscript()).thenReturn("Second transcription");

        SpeechRecognitionResult result = mock(SpeechRecognitionResult.class);
        when(result.getAlternatives()).thenReturn(Arrays.asList(alternative1, alternative2));

        SpeechRecognitionResults results = mock(SpeechRecognitionResults.class);
        when(results.getResults()).thenReturn(Collections.singletonList(result));

        String transcription = ibmInteractor.extractResults(results);

        assertNotNull(transcription);
        assertEquals("First transcription Second transcription", transcription);
    }
}
