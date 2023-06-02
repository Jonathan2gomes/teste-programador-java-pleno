package org.acme.infrastructure.gateway;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.domain.gateway.OrderGateway;

@ApplicationScoped
public class OrderDatabaseGateway implements OrderGateway {
}
