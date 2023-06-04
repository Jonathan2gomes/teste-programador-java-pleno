package org.acme.usecase;

import java.util.List;

public record OutputPage(String paginaAtual, String totalPaginas, String totalItens, List<Object> itens) {
}
