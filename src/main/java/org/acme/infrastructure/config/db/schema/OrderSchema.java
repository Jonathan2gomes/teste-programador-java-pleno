package org.acme.infrastructure.config.db.schema;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class OrderSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private LocalDate dataEmissao;

    @Column
    private String descricao;

    @Column
    private List<Long> listaProdutos;

    @Column
    private Double valorTotal;

    public OrderSchema() {
    }

    public OrderSchema(Long id, LocalDate dataEmissao, String descricao, List<Long> listaProdutos, Double valorTotal) {
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
}
