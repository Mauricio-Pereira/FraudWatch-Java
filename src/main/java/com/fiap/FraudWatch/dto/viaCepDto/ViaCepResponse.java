package com.fiap.FraudWatch.dto.viaCepDto;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String estado,
        String regiao
) {
}
