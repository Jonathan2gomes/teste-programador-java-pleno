package org.acme.usecase.customer;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.infrastructure.config.db.schema.CustomerSchema;
import org.acme.infrastructure.mapper.CustomerMapper;
import org.acme.usecase.OutputPage;

import java.util.stream.Collectors;

@ApplicationScoped
public class GetCustomerUseCase {

    private final CustomerGateway customerGateway;

    public GetCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public OutputPage execute(int page, int size) {

         return toPageable(customerGateway.findAll(page, size));
    }

    public CustomerOutput execute(Long id) {
        return CustomerMapper.fromCustomerToCustomerOutput(customerGateway.findById(id));
    }


    private OutputPage toPageable(PanacheQuery<CustomerSchema> schema) {
        return new OutputPage(
            String.valueOf(schema.page().index),
            String.valueOf(schema.pageCount()),
            String.valueOf(schema.count()),
                schema.stream().map(customer -> new CustomerOutput(
                        customer.getId(),
                        customer.getNome(),
                        customer.getTelefone(),
                        customer.getCpf(),
                        customer.getEmail())).collect(Collectors.toList()));
    }
}
