package com.imperial.academia.use_case.ASR;

import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.imperial.academia.config.ApiKeyConfig;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * The IBMInteractor class provides functionality to convert speech to text using IBM Watson's Speech to Text API.
 * It implements the ASRInputBoundary interface.
 */
public class IBMInteractor implements ASRInputBoundary {
    String apiKey;

    /**
     * Constructor to initialize IBMInteractor.
     * Retrieves the IBM Speech to Text API key from the ApiKeyConfig.
     */
    public IBMInteractor() {
        apiKey = ApiKeyConfig.getIBMSpeechToTextApiKey();
    }

    /**
     * Converts the speech from the provided audio file to text using IBM Watson's Speech to Text API.
     *
     * @param audioFile Path to the audio file in .wav format.
     * @return The transcribed text as a String. If an error occurs or the API key is not found, it returns an appropriate error message or null.
     */
    @Override
    public String speechToText(String audioFile) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "API key not found";
        }
        try {
            // Initialize IBM Watson Speech to Text service with the API key
            IamAuthenticator authenticator = new IamAuthenticator.Builder()
                    .apikey(apiKey)
                    .build();

            SpeechToText speechToText = new SpeechToText(authenticator);
            speechToText.setServiceUrl("https://api.us-east.speech-to-text.watson.cloud.ibm.com/instances/7dde5480-c90d-4450-8b48-81a997c34650");

            // Load the audio file as InputStream
            InputStream audioStream = new FileInputStream(audioFile);

            // Set recognition options for the audio file
            RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
                    .audio(audioStream)
                    .contentType("audio/wav")
                    .model("en-US_BroadbandModel")
                    .build();

            // Perform speech recognition and get results
            SpeechRecognitionResults speechRecognitionResults = speechToText.recognize(recognizeOptions).execute().getResult();

            // Extract and return the transcription from the results
            return extractResults(speechRecognitionResults);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Extracts and concatenates the transcript from the speech recognition results.
     *
     * @param speechRecognitionResults The results returned by the IBM Watson API.
     * @return A String containing the full transcription of the audio.
     */
    private String extractResults(SpeechRecognitionResults speechRecognitionResults) {
        StringBuilder fullTranscription = new StringBuilder();

        speechRecognitionResults.getResults().forEach(result -> {
            result.getAlternatives().forEach(alternative -> {
                if (fullTranscription.length() > 0) {
                    fullTranscription.append(" "); // Add space separator
                }
                fullTranscription.append(alternative.getTranscript());
            });
        });

        return fullTranscription.toString();
    }
}
