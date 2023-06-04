package org.acme.usecase.Order;

import org.acme.usecase.product.ProductOutput;

import java.time.LocalDate;
import java.util.List;

public record OrderOutput(Long codigo, LocalDate dataEmissao, String descricao, Double valorTotal, List<ProductOutput> listaProdutos) {

}
