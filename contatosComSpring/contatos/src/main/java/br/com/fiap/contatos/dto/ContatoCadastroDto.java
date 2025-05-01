package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ContatoCadastroDto(
        Long id,
        //anotação do Bean Validation para validação
        @NotBlank(message = "Nome do contato é obrigatório") //não pode estar vazio e não pode estár nulo
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "O e-mail está com o formato inválido!")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6,max = 10, message = "A senha deve conter entre 6 e 10 caracteres.")
        String senha,

        @NotNull(message = "Data de nascimento é obrigatória") //PARA DATA TEM QUE SER ANOTAÇÃO @NOTNULL
        LocalDate dataNascimento
) {

//COLOCAR NO CONTROLLER NO MÉTODO GRAVAR ANOTAÇÃO @VALID PARA QUE A VALIDAÇÃO FEITA NO DTO POSSA REFLETIR

}
