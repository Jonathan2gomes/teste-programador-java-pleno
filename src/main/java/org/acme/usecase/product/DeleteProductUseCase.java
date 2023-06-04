package org.acme.usecase.product;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;

@ApplicationScoped
public class DeleteProductUseCase {

    CustomerGateway customerGateway;

    public DeleteProductUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(Long id) {
        customerGateway.delete(id);
    }
}
