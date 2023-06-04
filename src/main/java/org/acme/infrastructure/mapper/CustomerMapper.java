package org.acme.infrastructure.mapper;

import org.acme.domain.model.Customer;
import org.acme.infrastructure.config.db.schema.CustomerSchema;
import org.acme.usecase.customer.CustomerInput;
import org.acme.usecase.customer.CustomerOutput;

public class CustomerMapper {
    public static CustomerSchema fromCustomerToCustomerSchema(Customer customer) {

        return new CustomerSchema(
                customer.getId(),
            customer.getNome(),
            customer.getTelefone(),
            customer.getCpf(),
            customer.getEmail()
        );
    }

    public static Customer fromInputToCustomer(CustomerInput input) {

        return new Customer(
                null,
                input.nome(),
                input.telefone(),
                input.cpf(),
                input.email()
        );
    }

    public static CustomerSchema updateFromCustomerToSchema(Customer customer, CustomerSchema customerSchema) {

        customerSchema.setNome(customer.getNome());
        customerSchema.setTelefone(customer.getTelefone());
        customerSchema.setCpf(customer.getCpf());
        customerSchema.setEmail(customer.getEmail());

        return customerSchema;
    }

    public static CustomerOutput fromCustomerToCustomerOutput(Customer customer) {
        return new CustomerOutput(
            customer.getId(),
            customer.getNome(),
            customer.getTelefone(),
            customer.getCpf(),
            customer.getEmail()
        );
    }
}
