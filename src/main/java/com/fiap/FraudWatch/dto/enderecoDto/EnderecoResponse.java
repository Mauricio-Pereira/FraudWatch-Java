package com.fiap.FraudWatch.dto.enderecoDto;

public record EnderecoResponse(
        Long id,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String regiao,
        String complemento,
        String numero
) {
}