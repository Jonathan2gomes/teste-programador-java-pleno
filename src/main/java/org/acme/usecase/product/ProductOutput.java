package org.acme.usecase.product;

public record ProductOutput(Long codigo, String descricao, String unidade, Double valor) {

    public String getValor() {
        return String.format("R$%.2f", valor);
    }

}
