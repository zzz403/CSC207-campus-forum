package com.imperial.academia.config;

public class ApiKeyConfig {
    private static final String MAP_BOX_API_KEY = "";
    private static final String GPT_3_5_turbo_0125_TOKEN = "";
    private static final String IBM_SpeechToText_API_KEY = "";
    private static final String DeepL_API_KEY = "";

    public static String getMapBoxApiKey() {
        return MAP_BOX_API_KEY;
    }

    public static String getGPTApi() {
        return GPT_3_5_turbo_0125_TOKEN;
    }

    public static String getIBMSpeechToTextApiKey() { return IBM_SpeechToText_API_KEY; }

    public static String getDeepLApiKey() {
        return DeepL_API_KEY;
    }
}
