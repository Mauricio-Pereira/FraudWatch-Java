package com.fiap.FraudWatch.dto.usuarioDto;

import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginRequest(
        @NotBlank(message = "O email é obrigatório")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        String senha
) {
}
