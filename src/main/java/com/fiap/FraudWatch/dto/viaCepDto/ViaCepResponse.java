package com.fiap.FraudWatch.dto.viaCepDto;

import java.util.concurrent.Future;

public record ViaCepResponse(
        String cep,
        String logradouro,
        String bairro,
        String localidade,
        String estado,
        String regiao
){}
