package com.imperial.academia.use_case.LLM;

import java.util.List;

public interface LLMInputBoundary {


    /**
     * Takes the content that need to be enhace and send to LLM toArray
     * enhace content
     * 
     * @param content the content that need to be enhance
     * @return the enhaced content
     */
    String enhanceContent(String content);
    String summarizeChatHistory(String chatHistory);
}
