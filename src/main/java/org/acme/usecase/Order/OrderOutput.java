package org.acme.usecase.Order;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.acme.usecase.product.ProductOutput;

import java.time.LocalDate;
import java.util.List;

public record OrderOutput(
        Long codigo,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataEmissao,
        String descricao,
        Double valorTotal,
        List<ProductOutput> listaProdutos) {

    public String getValorTotal() {
            return String.format("R$%.2f", valorTotal);
    }

}
