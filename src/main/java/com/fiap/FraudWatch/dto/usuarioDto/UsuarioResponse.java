package com.fiap.FraudWatch.dto.usuarioDto;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private LocalDateTime dataCadastro;
    private String tipoUsuario;
    private EnderecoResponseDTO endereco;
    private Link link;
}