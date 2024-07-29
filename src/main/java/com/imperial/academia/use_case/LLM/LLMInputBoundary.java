package com.imperial.academia.use_case.LLM;

/**
 * The LLMInputBoundary interface defines the input boundary for the LLM use
 * case.
 */
public interface LLMInputBoundary {

    /**
     * Takes the content that need to be enhace and send to LLM toArray
     * enhace content
     * 
     * @param content the content that need to be enhance
     * @return the enhaced content
     */
    String enhanceContent(String content);

    /**
     * Takes the chat history and summarize it
     * 
     * @param chatHistory the chat history that need to be summarize
     * @return the summarized chat history
     */
    String summarizeChatHistory(String chatHistory);
}
