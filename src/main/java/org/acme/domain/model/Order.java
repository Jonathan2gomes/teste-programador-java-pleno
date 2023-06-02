package org.acme.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private Long id;

    private LocalDate dataEmissao;

    private String descricao;

    private List<Product> listaProdutos;

    private Double valorTotal;

    public Order() {
    }

    public Order(Long id, LocalDate dataEmissao, String descricao, List<Product> listaProdutos, Double valorTotal) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.descricao = descricao;
        this.listaProdutos = listaProdutos;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Product> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Product> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}