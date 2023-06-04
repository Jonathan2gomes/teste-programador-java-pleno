package org.acme.infrastructure.config.db.schema;

import jakarta.persistence.*;

@Entity(name = "product_schema")
public class ProductSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String descricao;

    @Column
    private String unidade;

    @Column
    private Double valor;

    public ProductSchema() {
    }

    public ProductSchema(Long id, String descricao, String unidade, Double valor) {
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
