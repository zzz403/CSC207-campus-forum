package com.imperial.academia.app;


import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WatsonSpeechToTextExample {
    public static void main(String[] args) throws Exception {
        IamAuthenticator authenticator = new IamAuthenticator.Builder()
                .apikey("690demFccc3iZEzTo__UPTxeTg_1OA75erJpNBl7c4j9")
                .build();

        SpeechToText speechToText = new SpeechToText(authenticator);
        speechToText.setServiceUrl("https://api.us-east.speech-to-text.watson.cloud.ibm.com/instances/7dde5480-c90d-4450-8b48-81a997c34650");

        InputStream audioStream = new FileInputStream("resources/audio/1/john_doe_20240701_180149.wav");

        RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
                .audio(audioStream)
                .contentType("audio/wav")
                .model("en-US_BroadbandModel")
                .build();

        SpeechRecognitionResults speechRecognitionResults = speechToText.recognize(recognizeOptions).execute().getResult();

        String fullTranscription = extractResults(speechRecognitionResults);
        System.out.println(fullTranscription);
    }

    public static String extractResults(SpeechRecognitionResults speechRecognitionResults) {
        StringBuilder fullTranscription = new StringBuilder();

        speechRecognitionResults.getResults().forEach(result -> {
            result.getAlternatives().forEach(alternative -> {
                fullTranscription.append(alternative.getTranscript());
            });
        });

        return fullTranscription.toString();
    }
}



