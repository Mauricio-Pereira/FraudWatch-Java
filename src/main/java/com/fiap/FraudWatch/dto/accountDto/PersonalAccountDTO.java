package com.fiap.FraudWatch.dto.accountDto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PersonalAccountDTO {

    // Getters e Setters
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório")
    private String sobrenome;

    @NotBlank(message = "O email é obrigatório")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "O email deve ser válido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "A senha deve conter no mínimo 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial")
    private String senha;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve estar no formato 99999999999")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{2}\\d{4,5}-\\d{4}", message = "O telefone deve estar no formato 9999999-9999")
    private String telefone;

    @NotNull(message = "O tipo de usuário é obrigatório")
    private Long tipoUsuarioid;

    public PersonalAccountDTO() {
    }

}
