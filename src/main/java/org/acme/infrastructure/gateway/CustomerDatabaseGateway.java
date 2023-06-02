package org.acme.infrastructure.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.infrastructure.config.db.repository.CustomerRepository;
import org.acme.infrastructure.config.db.schema.CustomerSchema;

import java.util.List;

@ApplicationScoped
public class CustomerDatabaseGateway implements CustomerGateway {


    private final CustomerRepository customerRepository;

    public CustomerDatabaseGateway(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerSchema> findAll() {
        return customerRepository.findAll().list();
    }

    @Override
    public void persist(CustomerSchema customerSchema) {
        customerRepository.persist(customerSchema);
    }

    @Override
    public PanacheQuery<CustomerSchema> findAll(int page, int size) {
        return customerRepository.findAll().page(page, size);
    }


}
