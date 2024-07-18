package com.imperial.academia.use_case.LLM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import com.imperial.academia.config.ApiKeyConfig;

public class ChatGPTInteractor implements LLMInputBoundary {

    @Override
    public String enhanceContent(String content) {
        String apiKey = ApiKeyConfig.getGPTApi();
        if (apiKey.equals("")) {
            System.out.println("No gpt token key found");
            return content;
        }
        String apiUrl = "https://api.openai.com/v1/chat/completions";
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
        } catch (IOException | org.json.JSONException e) {
            e.printStackTrace();
        }

        return enhancedContent;
    }

}
