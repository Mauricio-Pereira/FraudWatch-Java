package com.fiap.FraudWatch.dto.enderecoDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponse {
    private Long id;
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String regiao;
    private String complemento;
    private String numero;
    private Link link;
}
