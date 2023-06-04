package org.acme.infrastructure.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.domain.exception.CustomerNotFoundException;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.domain.model.Customer;
import org.acme.infrastructure.config.db.repository.CustomerRepository;
import org.acme.infrastructure.config.db.schema.CustomerSchema;
import org.acme.infrastructure.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
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
    public void create(Customer customer) {
        customerRepository.persist(CustomerMapper.fromCustomerToCustomerSchema(customer));
    }

    @Override
    public Optional<CustomerSchema> findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }

    @Override
    public PanacheQuery<CustomerSchema> findAll(int page, int size) {
        return customerRepository.findAll().page(page, size);
    }

    @Override
    public void update(Long id, Customer customer) {
        CustomerSchema customerSchema = customerRepository.findByIdOptional(id).orElseThrow(CustomerNotFoundException::new);
        customerRepository.persist(CustomerMapper.updateFromCustomerToSchema(customer, customerSchema));
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findById(Long id) {
        CustomerSchema customerSchema = customerRepository.findByIdOptional(id).orElseThrow(CustomerNotFoundException::new);
        return new Customer(
                customerSchema.getId(),
                customerSchema.getNome(),
                customerSchema.getTelefone(),
                customerSchema.getCpf(),
                customerSchema.getEmail());
    }
}
