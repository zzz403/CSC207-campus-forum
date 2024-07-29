package com.imperial.academia.use_case.Translator;

public interface TranslatorInputBoundary {
    /**
     * Translates the specified text to the target language.
     *
     * @param text the text to translate
     * @param targetLanguage the target language to translate to
     * @return the translated text
     */
    String translate(String text, String targetLanguage);
}
