package org.acme.domain.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.domain.model.Product;
import org.acme.infrastructure.config.db.schema.ProductSchema;

public interface ProductGateway {

    PanacheQuery<ProductSchema> findAll(int page, int size);

    void create(Product product);

    void update(Long id, Product product);

    void delete(Long id);

    Product findById(Long id);
}
