package com.fiap.FraudWatch.service;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatLanguageModel model;

    public AiService() {
        this.model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3")
                .build();
    }
    public String gerarDicaSaudeBucal() {
        return model.generate("Gere uma dica curta sobre saúde bucal. Seja direto e objetivo.");
    }
}
