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

/**
 * Interactor class for handling translation using the DeepL API.
 * Implements the TranslatorInputBoundary interface.
 */
public class DeepLInteractor implements TranslatorInputBoundary {
    private final String API_KEY;
    private final String API_URL;
    private final CloseableHttpClient httpClient;

    /**
     * Constructor for DeepLInteractor.
     * Initializes the API key from the configuration.
     */
    public DeepLInteractor() {
        this(ApiKeyConfig.getDeepLApiKey(), "https://api-free.deepl.com/v2/translate", HttpClients.createDefault());
    }

    /**
     * Constructor for DeepLInteractor with custom parameters for testing.
     *
     * @param apiKey The API key for DeepL API.
     * @param apiUrl The URL for DeepL API.
     * @param httpClient The HTTP client to use for making requests.
     */
    public DeepLInteractor(String apiKey, String apiUrl, CloseableHttpClient httpClient) {
        this.API_KEY = apiKey;
        this.API_URL = apiUrl;
        this.httpClient = httpClient;
    }

    /**
     * Translates the given text to the specified target language using the DeepL API.
     *
     * @param text The text to translate.
     * @param targetLanguage The target language for the translation.
     * @return The translated text, or an error message if the API key is not found or an exception occurs.
     */
    @Override
    public String translate(String text, String targetLanguage) {
        if (API_KEY.isEmpty()) {
            return "No API key found.";
        }
        try {
            return translateText(text, targetLanguage);
        } catch (Exception e) {
            System.out.println("An error occurred during translation");
        }
        return "An error occurred during translation.";
    }

    /**
     * Translates the given text to the specified target language using the DeepL API.
     *
     * @param text The text to translate.
     * @param targetLanguage The target language for the translation.
     * @return The translated text.
     * @throws Exception if an error occurs during the translation process.
     */
    public String translateText(String text, String targetLanguage) throws Exception {
        // Create an HTTP client and post request
        HttpPost httpPost = new HttpPost(API_URL);

        // Set request headers
        httpPost.setHeader("Authorization", "DeepL-Auth-Key " + API_KEY);
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        // Set request body
        StringEntity params = new StringEntity("text=" + text + "&target_lang=" + targetLanguage);
        httpPost.setEntity(params);

        // Execute the request
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        // Parse the response
        if (entity != null) {
            String responseString = EntityUtils.toString(entity);
            JSONObject jsonResponse = new JSONObject(responseString);
            JSONArray translations = jsonResponse.getJSONArray("translations");
            return translations.getJSONObject(0).getString("text");
        }

        return null;
    }
}
