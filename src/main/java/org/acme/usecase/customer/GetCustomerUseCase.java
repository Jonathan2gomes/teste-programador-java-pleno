package org.acme.usecase.customer;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.infrastructure.config.db.schema.CustomerSchema;

import java.util.List;

@ApplicationScoped
public class GetCustomerUseCase {

    private final CustomerGateway customerGateway;

    public GetCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public CustomerOutputPage execute(int page, int size) {



         return toPageable(customerGateway.findAll(page, size));

    }

    public List<CustomerSchema> execute() {
        return customerGateway.findAll();

    }



    private CustomerOutputPage toPageable(PanacheQuery<CustomerSchema> schema) {
        return new CustomerOutputPage(
            String.valueOf(schema.page().index),
            String.valueOf(schema.pageCount()),
            String.valueOf(schema.count()),
            schema.stream().map(customer -> new CustomerOutput(customer.getNome(), customer.getTelefone(), customer.getCpf(), customer.getEmail())).toList()
        );
    }
}
