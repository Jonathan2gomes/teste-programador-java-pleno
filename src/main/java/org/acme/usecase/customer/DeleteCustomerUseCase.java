package org.acme.usecase.customer;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;

@ApplicationScoped
public class DeleteCustomerUseCase {

    CustomerGateway customerGateway;

    public DeleteCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(Long id) {
        customerGateway.delete(id);
    }
}
