package org.acme.infrastructure.config.db.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.config.db.schema.ProductSchema;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<ProductSchema> {

}
