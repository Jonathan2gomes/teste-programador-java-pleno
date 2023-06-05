package org.acme.infrastructure.config.db.schema;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "order_schema")
public class OrderSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private LocalDate dataEmissao;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerSchema customerSchema;

    @ManyToMany
    private List<ProductSchema> listaProdutos;

    @Column
    private Double valorTotal;

    public OrderSchema() {
    }

    public OrderSchema(Long id, LocalDate dataEmissao, String descricao, List<ProductSchema> listaProdutos, Double valorTotal, CustomerSchema customerSchema) {
        this.id = id;
        this.dataEmissao = dataEmissao;
        this.descricao = descricao;
        this.listaProdutos = listaProdutos;
        this.valorTotal = valorTotal;
        this.customerSchema = customerSchema;
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

    public List<ProductSchema> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<ProductSchema> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public CustomerSchema getCustomerSchema() {
        return customerSchema;
    }

    public void setCustomerSchema(CustomerSchema customerSchema) {
        this.customerSchema = customerSchema;
    }
}
