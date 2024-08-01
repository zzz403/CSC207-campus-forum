package com.imperial.academia.use_case.ASR;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.websocket.RecognizeCallback;
import com.imperial.academia.config.ApiKeyConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class IBMInteractorTest {

    private IBMInteractor ibmInteractor;
    private IamAuthenticator mockAuthenticator;
    private SpeechToText mockSpeechToText;
    private SpeechRecognitionResults mockResults;

    @BeforeEach
    public void setUp() {
        // Mock the ApiKeyConfig to return a dummy API key
        MockedStatic<ApiKeyConfig> mockedConfig = Mockito.mockStatic(ApiKeyConfig.class);
        mockedConfig.when(ApiKeyConfig::getIBMSpeechToTextApiKey).thenReturn("dummyApiKey");

        // Create the IBMInteractor instance
        ibmInteractor = new IBMInteractor();

        // Mock the IBM Watson services
        mockAuthenticator = mock(IamAuthenticator.class);
        mockSpeechToText = mock(SpeechToText.class);
        mockResults = mock(SpeechRecognitionResults.class);

        // Mock the IamAuthenticator and SpeechToText behavior
    }

    @Test
    public void testSpeechToText_Success() throws Exception {
        // Mock the behavior of the recognize method
        when(mockSpeechToText.recognize(any(RecognizeOptions.class)).execute().getResult()).thenReturn(mockResults);

        // Mock the result extraction
        String expectedTranscription = "This is a test transcription.";
        when(mockResults.getResults()).thenReturn(mockResults.getResults());

        // Simulate the interaction
        String transcription = ibmInteractor.speechToText("path/to/audio.wav");

        // Verify the expected interactions
        assertEquals(expectedTranscription, transcription);
    }

    @Test
    public void testSpeechToText_ApiKeyNotFound() {
        // Mock the ApiKeyConfig to return null
        MockedStatic<ApiKeyConfig> mockedConfig = Mockito.mockStatic(ApiKeyConfig.class);
        mockedConfig.when(ApiKeyConfig::getIBMSpeechToTextApiKey).thenReturn(null);

        // Create a new IBMInteractor instance to use the mocked config
        IBMInteractor ibmInteractorWithNoApiKey = new IBMInteractor();

        String transcription = ibmInteractorWithNoApiKey.speechToText("path/to/audio.wav");

        assertEquals("API key not found", transcription);
    }

    @Test
    public void testSpeechToText_ExceptionHandling() throws Exception {
        // Mock the behavior of the recognize method to throw an exception
        when(mockSpeechToText.recognize(any(RecognizeOptions.class))).thenThrow(new RuntimeException("Test Exception"));

        // Simulate the interaction
        String transcription = ibmInteractor.speechToText("path/to/audio.wav");

        // Verify the expected result
        assertEquals(null, transcription);
    }
}
