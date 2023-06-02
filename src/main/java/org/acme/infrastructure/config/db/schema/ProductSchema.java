package org.acme.infrastructure.config.db.schema;

import jakarta.persistence.*;

@Entity
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
}
