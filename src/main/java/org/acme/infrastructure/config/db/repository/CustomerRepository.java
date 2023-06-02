package org.acme.infrastructure.config.db.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.config.db.schema.CustomerSchema;


@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerSchema> {



}
