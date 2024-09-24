package com.fiap.FraudWatch.dto.usuarioDto;

import com.fiap.FraudWatch.dto.enderecoDto.EnderecoResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponse(
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
        EnderecoResponse endereco
) {
}
