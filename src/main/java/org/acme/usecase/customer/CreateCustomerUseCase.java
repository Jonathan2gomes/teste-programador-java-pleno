package org.acme.usecase.customer;


import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;

@ApplicationScoped
public class CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public CreateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(CustomerInput input) {
        customerGateway.create(input);
    }
}
