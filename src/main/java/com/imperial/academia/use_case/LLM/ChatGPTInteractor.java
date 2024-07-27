package com.imperial.academia.use_case.LLM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.imperial.academia.config.ApiKeyConfig;

public class ChatGPTInteractor implements LLMInputBoundary {
    private final String apiUrl;

    /**
     * Init the Interactor
     * and set the url for the LLM model to the correct one
     * 
     */
    public ChatGPTInteractor() {
        this("https://api.openai.com/v1/chat/completions");
    }

    /**
     * For unit test
     * 
     * @param apiUrl - url for LLM model
     */
    public ChatGPTInteractor(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Takes the content that need to be enhace and send to gpt-3.5-turbo-0125
     * toArray
     * enhace content
     * 
     * @param content the content that need to be enhance
     * @return the enhaced content
     */
    @Override
    public String enhanceContent(String content) {
        String apiKey = ApiKeyConfig.getGPTApi();
        if (apiKey.equals("")) {
            System.out.println("No gpt token key found");
            return content;
        }
        String enhancedContent = "";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            // 构建 JSON 请求体
            JSONObject systemMessage = new JSONObject()
                    .put("role", "system")
                    .put("content", "You are a helpful assistant.");

            JSONObject userMessage = new JSONObject()
                    .put("role", "user")
                    .put("content", "Enhance the following content but don't say another things: " + content);

            JSONArray messages = new JSONArray()
                    .put(systemMessage)
                    .put(userMessage);

            JSONObject requestBody = new JSONObject()
                    .put("model", "gpt-3.5-turbo-0125")
                    .put("messages", messages)
                    .put("max_tokens", 1500)
                    .put("temperature", 0.7);

            String inputJson = requestBody.toString();

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = inputJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int statusCode = connection.getResponseCode();
            if (statusCode >= 200 && statusCode < 300) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    JSONObject responseJson = new JSONObject(response.toString());
                    enhancedContent = responseJson.getJSONArray("choices").getJSONObject(0).getJSONObject("message")
                            .getString("content");
                }
            } else {
                // 处理错误响应
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        errorResponse.append(responseLine.trim());
                    }
                    System.out.println("Error response from API: " + errorResponse.toString());
                }
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }

        return enhancedContent;
    }

    @Override
    public String summarizeChatHistory(String chatHistory) {
        String apiKey = ApiKeyConfig.getGPTApi();
        if (apiKey.equals("")) {
            System.out.println("No GPT token key found");
            return "No GPT token key found";
        }
        String summary = "";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setDoOutput(true);

            // 构建 JSON 请求体
            JSONObject systemMessage = new JSONObject()
                    .put("role", "system")
                    .put("content", "You are a helpful assistant.");

            JSONObject userMessage = new JSONObject()
                    .put("role", "user")
                    .put("content", "Summarize the following chat history and try to list all main points: " + chatHistory);

            JSONArray messages = new JSONArray()
                    .put(systemMessage)
                    .put(userMessage);

            JSONObject requestBody = new JSONObject()
                    .put("model", "gpt-3.5-turbo-0125")
                    .put("messages", messages)
                    .put("max_tokens", 1500)
                    .put("temperature", 0.7);

            String inputJson = requestBody.toString();

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = inputJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int statusCode = connection.getResponseCode();
            if (statusCode >= 200 && statusCode < 300) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    JSONObject responseJson = new JSONObject(response.toString());
                    summary = responseJson.getJSONArray("choices").getJSONObject(0).getJSONObject("message")
                            .getString("content");
                }
            } else {
                // 处理错误响应
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        errorResponse.append(responseLine.trim());
                    }
                    System.out.println("Error response from API: " + errorResponse.toString());
                }
            }
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }

        return summary;
    }

}
