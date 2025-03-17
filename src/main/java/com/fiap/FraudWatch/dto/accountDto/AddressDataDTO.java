package com.fiap.FraudWatch.dto.accountDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDataDTO {

    // Getters e Setters
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;

    @NotBlank(message = "A região é obrigatória")
    private String regiao;

    private String complemento;

    @NotBlank(message = "O número é obrigatório")
    private String numero;

    public AddressDataDTO() {
    }

}
