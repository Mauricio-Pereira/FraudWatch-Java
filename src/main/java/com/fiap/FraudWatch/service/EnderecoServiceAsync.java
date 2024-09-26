package com.fiap.FraudWatch.service;

import com.fiap.FraudWatch.dto.viaCepDto.ViaCepResponse;
import com.fiap.FraudWatch.exception.CepNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class EnderecoServiceAsync {
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    @Autowired
    public EnderecoServiceAsync(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.executorService = Executors.newVirtualThreadPerTaskExecutor();
    }


    public Future<ViaCepResponse> obterEnderecoPorCepAsync(String cep) {
        // Criar um Future para retornar o resultado da execução
        return executorService.submit(() -> obterEnderecoPorCep(cep));
    }

    public ViaCepResponse obterEnderecoPorCep(String cep) {
        String cepFormatado = cep.replaceAll("-", "");
        String url = "https://viacep.com.br/ws/" + cepFormatado + "/json/";
        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        if (response == null || response.cep() == null) {
            throw new CepNaoEncontradoException("CEP não encontrado para o CEP: " + cep);
        } else {
            return response;
        }
    }

}