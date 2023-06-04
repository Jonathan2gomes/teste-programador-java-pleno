package org.acme.usecase.customer;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerInput(

        @NotNull(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "Telefone é obrigatório")
        @Pattern(regexp = "^\\([0-9]{2}\\)\\s[0-9]{5}\\-[0-9]{4}$", message = "Telefone inválido")
        String telefone,

        @NotNull(message = "CPF é obrigatório")
        @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$", message = "CPF inválido")
        @CPF(message = "CPF inválido")
        String cpf,

        @NotNull(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email) {
}
