package org.acme.usecase.product;


import jakarta.validation.constraints.NotNull;

public record ProductInput(

        @NotNull(message = "Descrição é obrigatório")
        String descricao,

        @NotNull(message = "Unidade é obrigatório")
        String unidade,

        @NotNull(message = "Valor é obrigatório")
        Double valor) {
}
