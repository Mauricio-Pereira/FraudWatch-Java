package com.fiap.FraudWatch.dto.usuarioDto;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;


public record UsuarioResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String email,
        String senha,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        LocalDateTime dataCadastro,
        String tipoUsuario,
        EnderecoResponseDTO endereco
) {
}
