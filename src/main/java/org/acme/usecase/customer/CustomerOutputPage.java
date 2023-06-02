package org.acme.usecase.customer;

import java.util.List;

public record CustomerOutputPage(String paginaAtual, String totalPaginas, String totalItens, List<CustomerOutput> itens) {
}
