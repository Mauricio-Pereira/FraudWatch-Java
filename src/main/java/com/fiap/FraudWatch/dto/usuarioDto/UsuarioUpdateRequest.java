package com.fiap.FraudWatch.dto.usuarioDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record UsuarioUpdateRequest (
        String nome,
        String sobrenome,
        String email,
        String senha,
        String cpf,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate dataNascimento,
        String telefone,
        Long tipoUsuarioid,
        EnderecoRequest endereco
){
}
