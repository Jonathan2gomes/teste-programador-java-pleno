package org.acme.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private Long id;

    private LocalDate dataEmissao;

    private String descricao;

    private List<Long> listaProdutos;

    private Double valorTotal;

    private Long customerId;

    public Order() {
    }

    public Order(Long id, LocalDate dataEmissao, String descricao, List<Long> listaProdutos, Double valorTotal, Long customerId) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.descricao = descricao;
        this.listaProdutos = listaProdutos;
        this.valorTotal = valorTotal;
        this.customerId = customerId;
    }

    public Order(String descricao, List<Long> listaProdutos, Long customerId) {
        this.descricao = descricao;
        this.listaProdutos = listaProdutos;
        this.customerId = customerId;
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

    public List<Long> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Long> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
