package com.imperial.academia.use_case.ASR;

import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.imperial.academia.config.ApiKeyConfig;

import java.io.FileInputStream;
import java.io.InputStream;

public class IBMInteractor implements ASRInputBoundary {
    String apiKey;

    public IBMInteractor() {
        apiKey = ApiKeyConfig.getIBMSpeechToTextApiKey();
    }

    @Override
    public String speechToText(String audioFile) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "API key not found";
        }
        try {
            IamAuthenticator authenticator = new IamAuthenticator.Builder()
                    .apikey(apiKey)
                    .build();

            SpeechToText speechToText = new SpeechToText(authenticator);
            speechToText.setServiceUrl("https://api.us-east.speech-to-text.watson.cloud.ibm.com/instances/7dde5480-c90d-4450-8b48-81a997c34650");

            InputStream audioStream = new FileInputStream(audioFile);

            RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
                    .audio(audioStream)
                    .contentType("audio/wav")
                    .model("en-US_BroadbandModel")
                    .build();

            SpeechRecognitionResults speechRecognitionResults = speechToText.recognize(recognizeOptions).execute().getResult();

            return extractResults(speechRecognitionResults);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractResults(SpeechRecognitionResults speechRecognitionResults) {
        StringBuilder fullTranscription = new StringBuilder();

        speechRecognitionResults.getResults().forEach(result -> {
            result.getAlternatives().forEach(alternative -> {
                if (!fullTranscription.isEmpty()) {
                    fullTranscription.append(" "); // 添加空格分隔
                }
                fullTranscription.append(alternative.getTranscript());
            });
        });

        return fullTranscription.toString();
    }
}
