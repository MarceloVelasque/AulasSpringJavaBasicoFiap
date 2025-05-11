package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank(message  =" O E-mail do usuário é obrigatório!")
        @Email(message = "O E-mail do usuário está no formato incorreto")
        String email,

        @NotBlank(message  =" A senha do usuário é obrigatório!")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String senha
) {
}
