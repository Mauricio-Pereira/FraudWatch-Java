package com.fiap.FraudWatch.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AiService {

    private final ChatLanguageModel model;

    public AiService() {
        this.model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("tinyllama")
                .build();
    }
    public String gerarDicaSaudeBucal(Locale locale) {
        String prompt = locale.getLanguage().equals("pt")
                ? "Me dê uma dica rápida de saúde bucal"
                : "Give me a quick dental health tip";

        return model.generate(prompt);
    }

}
