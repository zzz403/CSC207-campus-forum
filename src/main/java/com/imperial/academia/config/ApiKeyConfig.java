package com.imperial.academia.config;

public class ApiKeyConfig {
    private static final String MAP_BOX_API_KEY = "nothing";
    private static final String GPT_3_5_turbo_0125_TOKEN_FILE_PATH = "";

    public static String getMapBoxApiKey() {
        return MAP_BOX_API_KEY;
    }

    public static String getGPTApi() {
        return GPT_3_5_turbo_0125_TOKEN_FILE_PATH;
    }
}