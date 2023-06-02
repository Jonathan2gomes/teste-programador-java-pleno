package org.acme.infrastructure.gateway;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import org.acme.infrastructure.config.db.schema.CustomerSchema;
import org.acme.usecase.customer.CustomerOutputPage;

import java.util.stream.Collectors;

public class Mapper {

    public static CustomerOutputPage fromSchemaToCustomerOutputPage(PanacheQuery<CustomerSchema> schema) {
        return new CustomerOutputPage(
            schema.getContent().stream().map(
                customer -> new ColaboradorOutput(
                    customer.getNome(),
                    customer.getTelefone(),
                    customer.getCpf(),
                    customer.getEmail()
                )
            ).collect(Collectors.toList()),
            schema.getPageable().getPageNumber(),
            schema.getPageable().getPageSize(),
            schema.getPageable().getTotalElements(),
            schema.getPageable().getTotalPages()
        );
    }
}
