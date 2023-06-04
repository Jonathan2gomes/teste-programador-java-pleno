package org.acme.infrastructure.config.db.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infrastructure.config.db.schema.CustomerSchema;

import java.util.Optional;


@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerSchema> {

    public Optional<CustomerSchema> findByCpf(String cpf) {
        return find("cpf", cpf).stream().findFirst();
    }

    public Optional<CustomerSchema> findByIdOptional(Long id) {
        return find("id", id).stream().findFirst();
    }

}
