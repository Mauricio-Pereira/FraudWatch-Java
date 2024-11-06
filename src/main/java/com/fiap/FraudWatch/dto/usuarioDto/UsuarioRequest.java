        package com.fiap.FraudWatch.dto.usuarioDto;

        import com.fasterxml.jackson.annotation.JsonFormat;
        import com.fiap.FraudWatch.dto.enderecoDto.EnderecoRequest;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import jakarta.validation.constraints.Pattern;

        import java.time.LocalDate;

        public record UsuarioRequest (
                @NotBlank(message = "O nome é obrigatório")
                String nome,
                @NotBlank(message = "O sobrenome é obrigatório")
                String sobrenome,
                @NotBlank(message = "O email é obrigatório")
                @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "O email deve ser válido")
                String email,
                @NotBlank(message = "A senha é obrigatória")
                @Pattern(
                        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
                        message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial"
                )
                String senha,
                @NotBlank(message = "O CPF é obrigatório")
                @Pattern(regexp = "\\d{11}", message = "O CPF deve estar no formato 99999999999")
                String cpf,
                @NotNull(message = "A data de nascimento é obrigatória")
                @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                LocalDate dataNascimento,
                @NotBlank(message = "O telefone é obrigatório")
                @Pattern(regexp = "\\d{2}\\d{4,5}-\\d{4}", message = "O telefone deve estar no formato 9999999-9999")
                String telefone,
                @NotNull(message = "O tipo de usuário é obrigatório, sendo 1 para Dentista, 2 para Paciente e 3 para Analista")
                Long tipoUsuarioid,
                EnderecoRequest endereco
        ){
        }
