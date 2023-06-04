package org.acme.domain.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.domain.model.Customer;
import org.acme.infrastructure.config.db.schema.CustomerSchema;

import java.util.List;
import java.util.Optional;

public interface CustomerGateway {
    PanacheQuery<CustomerSchema> findAll(int page, int size);

    List<CustomerSchema> findAll();

    void create(Customer customer);

    Optional<CustomerSchema> findByCpf(String cpf);

    void update(Long id, Customer customer);

    void delete(Long id);

    Customer findById(Long id);
}
