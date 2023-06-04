package org.acme.usecase.Order;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderInput(

        @NotNull(message = "Descrição é obrigatório")
        String descricao,
        @NotNull(message = "É necessário ao menos um produto para criar um pedido")
        List<Long> listaProdutos){

}
