package org.acme.domain.gateway;

import org.acme.domain.model.Order;

import java.util.List;

public interface OrderGateway {

    void create(Order order);

    List<Order> getAll();
}
