package com.fiap.FraudWatch.dto.enderecoDto;

import com.fiap.FraudWatch.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoRequest (
        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "O CEP deve estar no formato 99999-999")
        String cep,
        @NotBlank(message = "O logradouro é obrigatório")
        String logradouro,
        @NotBlank(message = "O bairro é obrigatório")
        String bairro,
        @NotBlank(message = "A cidade é obrigatória")
        String cidade,
        @NotBlank(message = "O estado é obrigatório")
        String estado,
        @NotBlank(message = "A região é obrigatória")
        String regiao,
        String complemento,
        String numero
){
}
