package com.imperial.academia.use_case.Translator;

import com.imperial.academia.config.ApiKeyConfig;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeepLInteractor implements TranslatorInputBoundary {
    private final String API_KEY;
    private static final String API_URL = "https://api-free.deepl.com/v2/translate";

    public DeepLInteractor() {
        this.API_KEY = ApiKeyConfig.getDeepLApiKey();
    }

    @Override
    public String translate(String text, String targetLanguage) {
        if (API_KEY.isEmpty()) {
            return "No API key found.";
        }
        try {
            return translateText(text, targetLanguage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No API key found.";
    }

    public String translateText(String text, String targetLanguage) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(API_URL);

        // 设置请求头
        httpPost.setHeader("Authorization", "DeepL-Auth-Key " + API_KEY);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // 设置请求体
        StringEntity params = new StringEntity("text=" + text + "&target_lang=" + targetLanguage);
        httpPost.setEntity(params);

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        // 解析响应
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            JSONObject jsonResponse = new JSONObject(responseString);
            JSONArray translations = jsonResponse.getJSONArray("translations");
            return translations.getJSONObject(0).getString("text");
        }

        return null;
    }
}
