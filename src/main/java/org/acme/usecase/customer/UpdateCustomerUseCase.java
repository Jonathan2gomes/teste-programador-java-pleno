package org.acme.usecase.customer;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.CustomerGateway;
import org.acme.domain.model.Customer;

@ApplicationScoped
public class UpdateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public UpdateCustomerUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public void execute(Long id, CustomerInput customerIncoming) {

        customerGateway.update(id, new Customer(
                null,
            customerIncoming.nome(),
            customerIncoming.cpf(),
            customerIncoming.telefone(),
            customerIncoming.email()));
    }
}
