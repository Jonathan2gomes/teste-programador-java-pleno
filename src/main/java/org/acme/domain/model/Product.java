package org.acme.domain.model;

public class Product {

    private Long id;

    private String descricao;

    private String unidade;

    private Double valor;

    public Product() {
    }

    public Product(Long id, String descricao, String unidade, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
