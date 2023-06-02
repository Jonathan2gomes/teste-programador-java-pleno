package org.acme.domain.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.infrastructure.config.db.schema.CustomerSchema;

import java.util.List;

public interface CustomerGateway {
    PanacheQuery<CustomerSchema> findAll(int page, int size);

    List<CustomerSchema> findAll();

    void persist(CustomerSchema customerSchema);
}
