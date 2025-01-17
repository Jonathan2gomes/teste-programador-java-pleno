package org.acme.domain.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.domain.model.Product;
import org.acme.infrastructure.config.db.schema.ProductSchema;

import java.util.List;

public interface ProductGateway {

    PanacheQuery<ProductSchema> findAll(int page, int size);

    void create(Product product);

    void update(Long id, Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findByIds(List<Long> idList);

//    List<Long> findIdsByIds(List<Long> idList);
}
